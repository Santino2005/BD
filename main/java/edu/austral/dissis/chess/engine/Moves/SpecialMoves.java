package edu.austral.dissis.chess.engine.Moves;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.Position;
import edu.austral.dissis.chess.engine.Result;
import edu.austral.dissis.chess.engine.pieces.Piece;

import java.util.Map;

public interface SpecialMoves {
    Result applyMove(Position initialPos, Position finalPos, Piece piece, Map<Position,Piece> board);

}
