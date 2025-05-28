package edu.austral.dissis.chess.engine.rules;

import edu.austral.dissis.chess.engine.Position;

public class CapaBlancaRules extends MixedRules {

  public CapaBlancaRules() {
    super(new OriginalRules());
  }

  @Override
  public boolean validPosition(Position position) {
    return position.row() < 9
        && position.row() > 0
        && position.column() > 0
        && position.column() < 11;
  }
}
