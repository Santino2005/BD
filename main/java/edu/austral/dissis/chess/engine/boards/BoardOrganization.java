package edu.austral.dissis.chess.engine.boards;

import edu.austral.dissis.chess.engine.Position;
import edu.austral.dissis.chess.engine.pieces.Piece;
import java.util.HashMap;

public interface BoardOrganization {
  public HashMap<Position, Piece> create();
}
