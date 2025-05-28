package edu.austral.dissis.chess.engine.rules;

import edu.austral.dissis.chess.engine.*;
import edu.austral.dissis.chess.engine.Moves.LastMove;
import edu.austral.dissis.chess.engine.PiecesMaked.MixedPieces;

public interface Rules {
  public MovesType validMove(
      Position initialPos,
      Position finalPos,
      MixedPieces piece,
      Board board,
      Color userColor,
      boolean turn);

  public Color starterPlayer();

  public boolean validPosition(Position position);

  public Color afterMove(Board board, LastMove lastMove);
}
