package edu.austral.dissis.chess.engine.PiecesMaked;

import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.PiecesMoves.*;

public class Archbishop extends MixedPieces {

  public Archbishop(String name, Color color) {
    super(
        name,
        color,
        new DiagonalLeftBottomMove(),
        new DiagonalRightBottomMove(),
        new DiagonalLeftTopMove(),
        new DiagonalRightTopMove(),
        new KnightMove());
  }
}
