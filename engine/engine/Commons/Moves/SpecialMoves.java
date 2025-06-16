package edu.austral.dissis.chess.engine.Commons.Moves;

import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Pieces.Piece;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;
import edu.austral.dissis.chess.engine.Commons.Positions.Position;

import java.util.Map;

public interface SpecialMoves {

    public boolean isSpecialMove(Piece piece, MoveTo moveTo, Board board);
    public Map<Position, Piece> execute(MoveTo moveTo, Piece piece, Board board);

}
