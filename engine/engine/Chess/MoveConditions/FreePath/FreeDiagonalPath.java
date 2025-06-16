package edu.austral.dissis.chess.engine.Chess.MoveConditions.FreePath;

import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Moves.ValidConditions;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;
import edu.austral.dissis.chess.engine.Commons.Positions.Position;

public class FreeDiagonalPath implements ValidConditions {

    private final ValidConditions isPieceOnTheRoad;

    public FreeDiagonalPath(ValidConditions isPieceOnTheRoad) {
        this.isPieceOnTheRoad = isPieceOnTheRoad;
    }

    @Override
    public boolean valid(MoveTo moveTo, Board board) {
        int currentRow = Integer.compare(moveTo.getTo().row(), moveTo.getFrom().row());
        int currentCol = Integer.compare(moveTo.getTo().column(), moveTo.getFrom().column());
        int colWay = moveTo.getFrom().column() + currentCol;
        int rowWay = moveTo.getFrom().row() + currentRow;
        return checkRoad(currentCol, currentRow, rowWay, colWay, moveTo, board);
    }

    private boolean checkRoad(int currentCol, int currentRow,int rowWay, int colWay, MoveTo moveTo, Board board){
        while (rowWay != moveTo.getTo().row() || colWay != moveTo.getTo().column()) {
            Position actualPosition = new Position(rowWay, colWay);
            if(isPieceOnTheRoad.valid(new MoveTo(moveTo.getFrom(), actualPosition), board)){return false;}
            colWay += currentCol;
            rowWay += currentRow;
        }
        return true;
    }

}
