package edu.austral.dissis.chess.engine.rules;

import edu.austral.dissis.chess.engine.*;
import edu.austral.dissis.chess.engine.pieces.Piece;

public interface Rules {
  public MovesType validMove(Position initialPos, Position finalPos, Piece piece, Board board, Color userColor);
  public Color starterPlayer();
}
