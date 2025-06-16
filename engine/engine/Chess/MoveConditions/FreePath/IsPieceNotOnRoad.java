package edu.austral.dissis.chess.engine.Chess.MoveConditions.FreePath;

import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Moves.ValidConditions;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;

public class IsPieceNotOnRoad implements ValidConditions {
    @Override
    public boolean valid(MoveTo moveTo, Board board) {
        return board.getPieceByPosition(moveTo.getTo()).isEmpty();
    }
}
