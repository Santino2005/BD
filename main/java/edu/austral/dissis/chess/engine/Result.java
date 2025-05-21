package edu.austral.dissis.chess.engine;

import edu.austral.dissis.chess.engine.Moves.LastMove;
import edu.austral.dissis.chess.engine.pieces.Piece;

import java.util.Map;

public class Result {

    private final Map<Position, Piece> cells;
    private final LastMove lastMove;

    public Result(LastMove lastMove, Map<Position,Piece> cells){
        this.lastMove = lastMove;
        this.cells = cells;
    }

    public Map<Position, Piece> getNewCells(){
        return cells;
    }
    public LastMove getLastMove(){
        return lastMove;
    }

}
