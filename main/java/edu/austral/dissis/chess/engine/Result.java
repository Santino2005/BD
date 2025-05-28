package edu.austral.dissis.chess.engine;

import edu.austral.dissis.chess.engine.Moves.LastMove;
import edu.austral.dissis.chess.engine.PiecesMaked.MixedPieces;
import java.util.Map;

public class Result {

  private final Map<Position, MixedPieces> cells;
  private final LastMove lastMove;

  public Result(LastMove lastMove, Map<Position, MixedPieces> cells) {
    this.lastMove = lastMove;
    this.cells = cells;
  }

  public Map<Position, MixedPieces> getNewCells() {
    return cells;
  }

  public LastMove getLastMove() {
    return lastMove;
  }
}
