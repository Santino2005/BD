package edu.austral.dissis.chess.engine.PiecesMoves;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.Position;
import java.util.List;

public class CheckFreeWayDiagonal implements Valuate {

  @Override
  public boolean valuate(Position initialPos, Position finalPos, Board board, Color color) {
    int currentRow = Integer.compare(finalPos.row(), initialPos.row());
    int currentCol = Integer.compare(finalPos.column(), initialPos.column());
    int colWay = initialPos.column() + currentCol;
    int rowWay = initialPos.row() + currentRow;

    while (rowWay != finalPos.row() || colWay != finalPos.column()) {
      Position actualPosition = new Position(rowWay, colWay);
      if (board.getCells().containsKey(actualPosition)) {
        return false;
      }
      colWay += currentCol;
      rowWay += currentRow;
    }
    return board.getCells().get(finalPos) == null /*|| !(board.getCells().get(finalPos)) */
        || color != board.getCells().get(finalPos).color();
  }

  @Override
  public List<Position> possibleMoves(Position initialPos, Board board, Color color) {
    return null;
  }
}
