package edu.austral.dissis.chess.engine.PiecesMoves;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.PiecesMaked.King;
import edu.austral.dissis.chess.engine.Position;
import java.util.ArrayList;
import java.util.List;

public class Diagonal implements Valuate {
  @Override
  public boolean valuate(Position initialPos, Position finalPos, Board board, Color color) {
    return Math.abs((finalPos.column() - initialPos.column())) == 1
        && board.getCells().get(finalPos) != null
        && board.getCells().get(finalPos).color() != color
        && !(board.getCells().get(finalPos) instanceof King);
  }

  @Override
  public List<Position> possibleMoves(Position initialPos, Board board, Color color) {
    List<Position> allMoves = new ArrayList<>();
    int dir = (Color.WHITE == color) ? 1 : -1;
    Position digR = new Position(initialPos.row() + dir, initialPos.column() + 1);
    Position digL = new Position(initialPos.row() + dir, initialPos.column() - 1);

    if (board.getRules().validPosition(digR)
        && (board.getCells().get(digR) != null && board.getCells().get(digR).color() != color)) {
      allMoves.add(digR);
    }
    if (board.getRules().validPosition(digL)
        && (board.getCells().get(digL) != null && board.getCells().get(digL).color() != color)) {
      allMoves.add(digL);
    }
    return allMoves;
  }
}
