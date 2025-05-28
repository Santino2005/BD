package edu.austral.dissis.chess.engine.PiecesMaked;

import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.PiecesMoves.ColumnMove;
import edu.austral.dissis.chess.engine.PiecesMoves.KnightMove;
import edu.austral.dissis.chess.engine.PiecesMoves.RowMove;

public class Chancellor extends MixedPieces {

  public Chancellor(String name, Color color) {
    super(name, color, new KnightMove(), new RowMove(), new ColumnMove());
  }
}
