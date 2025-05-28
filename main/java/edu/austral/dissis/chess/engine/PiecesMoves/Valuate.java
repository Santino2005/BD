package edu.austral.dissis.chess.engine.PiecesMoves;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.Position;
import java.util.List;

public interface Valuate {
  public boolean valuate(Position initialPos, Position finalPos, Board board, Color color);

  List<Position> possibleMoves(Position initialPos, Board board, Color color);
}
