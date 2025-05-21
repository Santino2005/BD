package edu.austral.dissis.chess.engine.pieces;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.MovesType;
import edu.austral.dissis.chess.engine.Position;
import edu.austral.dissis.chess.engine.rules.Rules;
import javafx.geometry.Pos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Piece {
  public MovesType validMove(Position initialPos, Position finalPos, Map<Position, Piece> cells);
  public Color color();
  public List<Position> possibleMoves(Position initialPos, Map<Position, Piece> cells, Board board, Rules rules);
}
