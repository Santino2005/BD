package edu.austral.dissis.chess.engine.Moves;

import edu.austral.dissis.chess.engine.PiecesMaked.MixedPieces;
import edu.austral.dissis.chess.engine.Position;
import edu.austral.dissis.chess.engine.Result;
import java.util.HashMap;
import java.util.Map;

public class Valid implements validMoves {

  @Override
  public Result applyMove(
      Position initialPos, Position finalPos, MixedPieces piece, Map<Position, MixedPieces> cells) {
    Map<Position, MixedPieces> newBoard = new HashMap<>(cells);
    newBoard.remove(initialPos);
    newBoard.put(finalPos, piece);
    return new Result(new LastMove(initialPos, finalPos, piece), newBoard);
  }
}
