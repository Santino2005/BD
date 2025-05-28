package edu.austral.dissis.chess.engine.PiecesMaked;

import edu.austral.dissis.chess.engine.*;
import edu.austral.dissis.chess.engine.PiecesMoves.*;

public class King extends MixedPieces {
  private final boolean moved;

  public King(String name, Color color) {
    super(name, color, new OneSquareMove(), new Castling());
    this.moved = false;
  }

  private King(String name, Color color, boolean moved) {
    super(name, color, new KingOneSquare(), new Castling());
    this.moved = moved;
  }

  public King moved() {
    return new King(name(), color(), true);
  }

  public boolean getMoved() {
    return moved;
  }
}
