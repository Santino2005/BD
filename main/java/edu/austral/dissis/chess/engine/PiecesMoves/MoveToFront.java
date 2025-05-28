package edu.austral.dissis.chess.engine.PiecesMoves;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.Position;
import java.util.ArrayList;
import java.util.List;

public class MoveToFront implements Valuate {

  @Override
  public boolean valuate(Position initialPos, Position finalPos, Board board, Color color) {
    if (Math.abs(finalPos.row() - initialPos.row()) == 1) {
      Piece piece = board.getCells().get(finalPos);
      return piece == null || piece.color() != color;
    }
    return false;
  }

  @Override
  public List<Position> possibleMoves(Position initialPos, Board board, Color color) {
    List<Position> allMoves = new ArrayList<>();
    int dir = (Color.WHITE == color) ? 1 : -1;
    if (board
        .getRules()
        .validMove(
            initialPos,
            new Position(initialPos.row() + dir, initialPos.column()),
            board.getCells().get(initialPos),
            board,
            color,
            false)
        .valid()) {
      allMoves.add(new Position(initialPos.row() + dir, initialPos.column()));
    }
    return allMoves;
  }
}
