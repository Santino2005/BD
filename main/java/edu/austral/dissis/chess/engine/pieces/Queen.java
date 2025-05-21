package edu.austral.dissis.chess.engine.pieces;

import edu.austral.dissis.chess.engine.Color;

public class Queen extends MixedPieces {
  public Queen(Color color) {
    super(color, new Bishop(color), new Rook(color, false));
  }

}
