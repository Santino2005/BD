package edu.austral.dissis.chess.engine.Moves;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.Position;
import edu.austral.dissis.chess.engine.Result;
import edu.austral.dissis.chess.engine.pieces.King;
import edu.austral.dissis.chess.engine.pieces.LastMove;
import edu.austral.dissis.chess.engine.pieces.Piece;
import edu.austral.dissis.chess.engine.pieces.Rook;

import java.util.HashMap;
import java.util.Map;

public class Castling implements SpecialMoves{

    public boolean isSpecialMove(Position initialPos, Position finalPos, Map<Position, Piece> cells){
        return cells.get(initialPos) instanceof King && cells.get(finalPos) instanceof Rook;
    }
    @Override
    public Result applyMove(Position initialPos, Position finalPos, Piece piece, Map<Position,Piece> cells) {
        Map<Position, Piece> newCells = new HashMap<>(cells);
        if(finalPos.column() > initialPos.column()) {
            if(cells.get(initialPos).color() == Color.WHITE){
                newCells.put(new Position(1,7) , new King(Color.WHITE));
                newCells.remove(initialPos);
                newCells.remove(finalPos);
                newCells.put(new Position(1,6) , new Rook(Color.WHITE, true));
            }
            newCells.put(new Position(8,7) , new King(Color.BLACK));
            newCells.remove(initialPos);
            newCells.remove(finalPos);
            newCells.put(new Position(8,6) , new Rook(Color.BLACK, true));
        }
        if(cells.get(initialPos).color() == Color.WHITE){
            newCells.put(new Position(1,3) , new King(Color.WHITE));
            newCells.remove(initialPos);
            newCells.remove(finalPos);
            newCells.put(new Position(1,4) , new Rook(Color.WHITE, true));
        }
        newCells.put(new Position(8,3) , new King(Color.BLACK));
        newCells.remove(initialPos);
        newCells.remove(finalPos);
        newCells.put(new Position(8,4) , new Rook(Color.BLACK, true));
        return new Result(new LastMove(initialPos,finalPos,piece), newCells);
    }
}
