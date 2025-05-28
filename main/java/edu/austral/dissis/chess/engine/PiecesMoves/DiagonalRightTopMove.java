package edu.austral.dissis.chess.engine.PiecesMoves;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.Position;
import java.util.ArrayList;
import java.util.List;

public class DiagonalRightTopMove implements Valuate {
  @Override
  public boolean valuate(Position initialPos, Position finalPos, Board board, Color color) {
    if (finalPos.row() - initialPos.row() == finalPos.column() - initialPos.column()) {
      return new CheckFreeWayDiagonal().valuate(initialPos, finalPos, board, color);
    }
    return false;
  }

  @Override
  public List<Position> possibleMoves(Position initialPos, Board board, Color color) {
    List<Position> list = new ArrayList<>();
    int currentRow = initialPos.row() + 1;
    int currentCol = initialPos.column() + 1;
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
        if (board.getCells().get(actualPosition) == null) {
          list.add(actualPosition);
        } else if (board.getCells().get(actualPosition).color() != color) {
          list.add(actualPosition);
          break;
        }
      }
      list.add(actualPosition);
      currentRow += 1;
      currentCol += 1;
    }
    return list;
  }
}
