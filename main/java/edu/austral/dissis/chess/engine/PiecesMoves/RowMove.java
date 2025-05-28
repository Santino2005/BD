package edu.austral.dissis.chess.engine.PiecesMoves;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.PiecesMaked.King;
import edu.austral.dissis.chess.engine.Position;
import java.util.ArrayList;
import java.util.List;

public class RowMove implements Valuate {

  @Override
  public boolean valuate(Position initialPos, Position finalPos, Board board, Color color) {
    if (initialPos.column() == finalPos.column() && initialPos.row() != finalPos.row()) {
      return checkRow(finalPos, board, initialPos.column(), initialPos.row(), color);
    }
    return false;
  }

  private boolean checkRow(Position finalPos, Board cells, int columnWay, int rowWay, Color color) {
    int step = finalPos.column() > columnWay ? 1 : -1;
    if (columnWay == finalPos.column()) {
      return false;
    }
    for (int col = columnWay + step; col != finalPos.column(); col += step) {
      if (cells.getCells().containsKey(new Position(rowWay, col))) {
        return false;
      }
    }
    return cells.getCells().get(finalPos) == null
        || (!(cells.getCells().get(finalPos) instanceof King)
            && color != cells.getCells().get(finalPos).color());
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
    int currentRow = initialPos.row() + dir;
    int currentCol = initialPos.column();
    try {
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
          list.add(actualPosition);
          currentRow += dir;
        } else {
          break;
        }
      }
    } catch (Exception e) {
      System.err.println("Error en checkWays:");
      System.err.println("initialPos = " + initialPos);
      System.err.println("currentRow = " + currentRow);
      System.err.println("currentCol = " + currentCol);
      e.printStackTrace();
    }
    return list;
  }
}
