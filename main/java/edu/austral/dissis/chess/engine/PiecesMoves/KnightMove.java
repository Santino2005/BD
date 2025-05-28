package edu.austral.dissis.chess.engine.PiecesMoves;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.PiecesMaked.King;
import edu.austral.dissis.chess.engine.Position;
import java.util.ArrayList;
import java.util.List;

public class KnightMove implements Valuate {

  @Override
  public boolean valuate(Position initialPos, Position finalPos, Board board, Color color) {
    int rowWay = Math.abs(finalPos.row() - initialPos.row());
    int colWay = Math.abs(finalPos.column() - initialPos.column());
    if (!((rowWay == 2 && colWay == 1) || (rowWay == 1 && colWay == 2))) {
      return false;
    }
    return board.getCells().get(finalPos) == null
        || !(board.getCells().get(finalPos) instanceof King)
        || color != board.getCells().get(finalPos).color();
  }

  @Override
  public List<Position> possibleMoves(Position initialPos, Board board, Color color) {
    List<Position> allMoves = new ArrayList<>();
    int row = initialPos.row();
    int col = initialPos.column();
    List<Position> positions =
        List.of(
            new Position(row + 2, col + 1),
            new Position(row + 2, col - 1),
            new Position(row - 2, col + 1),
            new Position(row - 2, col - 1),
            new Position(row + 1, col + 2),
            new Position(row + 1, col - 2),
            new Position(row - 1, col + 2),
            new Position(row - 1, col - 2));
    for (Position pos : positions) {
      if (board
          .getRules()
          .validMove(initialPos, pos, board.getCells().get(initialPos), board, color, false)
          .valid()) {
        allMoves.add(pos);
      }
    }
    return allMoves;
  }
}
