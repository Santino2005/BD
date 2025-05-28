package edu.austral.dissis.chess.engine.PiecesMoves;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.Position;
import java.util.ArrayList;
import java.util.List;

public class OneSquareMove implements Valuate {

  @Override
  public boolean valuate(Position initialPos, Position finalPos, Board board, Color color) {
    if (Math.abs(initialPos.row() - finalPos.row()) == 1
        && Math.abs(initialPos.column() - finalPos.column()) == 1) {
      return board.getCells().get(finalPos) == null
          || board.getCells().get(finalPos).color() != color;
    }
    return false;
  }

  @Override
  public List<Position> possibleMoves(Position initialPos, Board board, Color color) {
    List<Position> allMoves = new ArrayList<>();
    int row = initialPos.row();
    int col = initialPos.column();
    List<Position> positions =
        List.of(
            new Position(row, col + 1),
            new Position(row, col - 1),
            new Position(row + 1, col),
            new Position(row + 1, col + 1),
            new Position(row + 1, col - 1),
            new Position(row - 1, col),
            new Position(row - 1, col - 1),
            new Position(row - 1, col + 1));
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
