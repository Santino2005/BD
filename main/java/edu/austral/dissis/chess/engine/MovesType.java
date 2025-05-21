package edu.austral.dissis.chess.engine;

import edu.austral.dissis.chess.engine.Moves.CallMoves;
import edu.austral.dissis.chess.engine.pieces.Piece;

import java.util.Map;
import java.util.Objects;

public class MovesType {

    private final Map<Position,Piece> cells;
    private final Piece piece;
    private final String moveName;
    private final Position initialPos;
    private final Position finalPos;
    private CallMoves callMoves;

    public MovesType(Piece piece, String moveName, Position initialPos, Position finalPos,Map<Position,Piece> board){
        this.moveName = moveName;
        this.piece = piece;
        this.cells = board;
        this.finalPos = finalPos;
        this.initialPos = initialPos;
    }

    public Map<Position,Piece> move(Position initialPos, Position finalPos, Piece piece, Map<Position, Piece> cells){
        return callMoves.getMoves(this.moveName).applyMove(initialPos,finalPos,piece,cells).getNewCells();
    }
    public boolean valid(){
        return !Objects.equals(this.moveName, "invalid");
    }
    public Piece getPiece(){
        return piece;
    }

}
