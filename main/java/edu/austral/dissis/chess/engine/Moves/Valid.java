package edu.austral.dissis.chess.engine.Moves;

import edu.austral.dissis.chess.engine.Position;
import edu.austral.dissis.chess.engine.Result;
import edu.austral.dissis.chess.engine.pieces.Piece;

import java.util.HashMap;
import java.util.Map;

public class Valid implements SpecialMoves{

    @Override
    public Result applyMove(Position initialPos, Position finalPos, Piece piece , Map<Position,Piece> cells){
        Map<Position,Piece> newBoard = new HashMap<>(cells);
        newBoard.remove(initialPos);
        newBoard.put(finalPos, piece);
        return new Result(new LastMove(initialPos, finalPos, piece), newBoard);
    }
}
