package edu.austral.dissis.chess.engine.PiecesMoves;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.MovesType;
import edu.austral.dissis.chess.engine.Position;
import edu.austral.dissis.chess.engine.rules.Rules;
import java.util.List;

public interface Piece {
  MovesType validMove(Position initialPos, Position finalPos, Board board);

  Color color();

  String name();

  public List<Position> possibleMoves(Position initialPos, Board board, Rules rules);
}

// public record PieceImpl(name,color,rules)

// piece.getRules.validate()
