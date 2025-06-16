package edu.austral.dissis.chess.engine.Chess.MoveConditions.FreePath;

import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Moves.ValidConditions;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;

public class OneSquareMove implements ValidConditions {
    @Override
    public boolean valid(MoveTo moveTo, Board board) {
        return Math.abs(moveTo.getFrom().row() - moveTo.getTo().row()) <= 1
                && Math.abs(moveTo.getFrom().column() - moveTo.getTo().column()) <= 1;
    }
}
