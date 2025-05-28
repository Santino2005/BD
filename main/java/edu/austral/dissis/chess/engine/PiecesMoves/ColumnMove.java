package edu.austral.dissis.chess.engine.PiecesMoves;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.PiecesMaked.King;
import edu.austral.dissis.chess.engine.Position;
import java.util.ArrayList;
import java.util.List;

public class ColumnMove implements Valuate {

  @Override
  public boolean valuate(Position initialPos, Position finalPos, Board board, Color color) {
    if (initialPos.column() != finalPos.column() && initialPos.row() == finalPos.row()) {
      return checkCol(finalPos, board, initialPos.column(), initialPos.row(), color);
    }
    return false;
  }

  private boolean checkCol(Position finalPos, Board board, int columnWay, int rowWay, Color color) {
    int step = finalPos.row() > rowWay ? 1 : -1;
    if (rowWay == finalPos.row()) {
      return false;
    }
    for (int row = rowWay + step; row != finalPos.row(); row += step) {
      if (board.getCells().containsKey(new Position(row, columnWay))) {
        return false;
      }
    }
    return board.getCells().get(finalPos) == null
        || (!(board.getCells().get(finalPos) instanceof King)
            && color != board.getCells().get(finalPos).color());
  }

  @Override
  public List<Position> possibleMoves(Position initialPos, Board board, Color color) {
    List<Position> allMoves = new ArrayList<>();
    allMoves.addAll(checkWays(initialPos, board, color, allMoves, 1));
    allMoves.addAll(checkWays(initialPos, board, color, allMoves, -1));
    return allMoves;
  }

  private List<Position> checkWays(
      Position initialPos, Board board, Color color, List<Position> list, int dir) {
    int currentRow = initialPos.row();
    int currentCol = initialPos.column() + dir;
    while (board
        .getRules()
        .validMove(
            initialPos,
            new Position(currentRow, currentCol),
            board.getCells().get(initialPos),
            board,
            color,
            false)
        .valid()) {
      Position actualPosition = new Position(currentRow, currentCol);
      if (!board.getCells().containsKey(actualPosition)) {
        currentCol += dir;
        list.add(actualPosition);
      } else {
        break;
      }
    }
    return list;
  }
}
