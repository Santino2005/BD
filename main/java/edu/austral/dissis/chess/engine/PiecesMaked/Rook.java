package edu.austral.dissis.chess.engine.PiecesMaked;

import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.PiecesMoves.*;

public class Rook extends MixedPieces {

  private final boolean moved;

  public Rook(String name, Color color) {
    super(name, color, new ColumnMove(), new RowMove());
    this.moved = false;
  }

  private Rook(String name, Color color, boolean moved) {
    super(name, color, new ColumnMove(), new RowMove());
    this.moved = moved;
  }

  public Rook move() {
    return new Rook(this.name(), this.color(), true);
  }

  public boolean hasMoved() {
    return moved;
  }
}
