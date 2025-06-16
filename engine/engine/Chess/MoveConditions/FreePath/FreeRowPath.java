package edu.austral.dissis.chess.engine.Chess.MoveConditions.FreePath;

import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Moves.ValidConditions;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;
import edu.austral.dissis.chess.engine.Commons.Positions.Position;

public class FreeRowPath implements ValidConditions {

    private final ValidConditions isPieceOnTheRoad;

    public FreeRowPath(ValidConditions isPieceOnTheRoad) {
        this.isPieceOnTheRoad = isPieceOnTheRoad;
    }

    @Override
    public boolean valid(MoveTo moveTo, Board board) {
        int columnWay = moveTo.getFrom().column();
        int step = moveTo.getTo().column() > columnWay ? 1 : -1;
        return checkRow(columnWay,step,moveTo,board);
    }
    private boolean checkRow(int columnWay, int step, MoveTo moveTo, Board board){
        if (columnWay == moveTo.getTo().column() || moveTo.getFrom().row() != moveTo.getTo().row()) {
            return false;
        }
        for (int col = columnWay + step; col != moveTo.getTo().column(); col += step) {
            if (isPieceOnTheRoad.valid(new MoveTo(moveTo.getFrom(), new Position(moveTo.getFrom().row(),col)),board)){
                return false;
            }
        }
        return true;
    }
}
