package edu.austral.dissis.chess.engine.Chess.MoveConditions.FreePath;

import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Moves.ValidConditions;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;
import edu.austral.dissis.chess.engine.Commons.Positions.Position;

public class FreeColumnPath implements ValidConditions {

    private final ValidConditions isPieceOnTheRoad;

    public FreeColumnPath(ValidConditions isPieceOnTheRoad) {
        this.isPieceOnTheRoad = isPieceOnTheRoad;
    }

    @Override
    public boolean valid(MoveTo moveTo, Board board) {
        int rowWay = moveTo.getFrom().row();
        int step = moveTo.getTo().row() > rowWay ? 1 : -1;
        return checkColumn(rowWay,step,moveTo,board);
    }
    private boolean checkColumn(int rowWay, int step, MoveTo moveTo, Board board){
        if (rowWay == moveTo.getTo().row() || moveTo.getTo().column() != moveTo.getFrom().column()) {
            return false;
        }
        for (int row = rowWay + step; row != moveTo.getTo().row(); row += step) {
            if (isPieceOnTheRoad.valid(new MoveTo(moveTo.getFrom(), new Position(row,moveTo.getFrom().column())),board)){
                return false;
            }
        }
        return true;
    }
}
