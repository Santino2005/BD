package edu.austral.dissis.chess.engine.Moves;

import edu.austral.dissis.chess.engine.PiecesMaked.MixedPieces;
import edu.austral.dissis.chess.engine.Position;
import edu.austral.dissis.chess.engine.Result;
import java.util.Map;

public interface validMoves {
  Result applyMove(
      Position initialPos, Position finalPos, MixedPieces piece, Map<Position, MixedPieces> board);
}
