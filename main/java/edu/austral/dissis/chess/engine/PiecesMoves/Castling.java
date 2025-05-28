package edu.austral.dissis.chess.engine.PiecesMoves;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.PiecesMaked.King;
import edu.austral.dissis.chess.engine.PiecesMaked.Rook;
import edu.austral.dissis.chess.engine.Position;
import java.util.List;

public class Castling implements Valuate {

  @Override
  public boolean valuate(Position initialPos, Position finalPos, Board board, Color color) {
    return board.getCells().get(initialPos) instanceof King
        && !(((King) board.getCells().get(initialPos)).getMoved())
        && board.getCells().get(finalPos) instanceof Rook
        && !(((Rook) board.getCells().get(finalPos)).hasMoved()
            && board.getCells().get(initialPos).color() == board.getCells().get(finalPos).color());
  }

  @Override
  public List<Position> possibleMoves(Position initialPos, Board board, Color color) {
    return null;
  }
}
