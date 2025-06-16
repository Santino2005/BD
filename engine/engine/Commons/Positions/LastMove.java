package edu.austral.dissis.chess.engine.Commons.Positions;

import edu.austral.dissis.chess.engine.Commons.Pieces.Piece;

public class LastMove {

    private final Position from;
    private final Position to;
    private final Piece piece;

    public LastMove(Position from, Position to, Piece piece) {
        this.from = from;
        this.to = to;
        this.piece = piece;
    }

    public Position getTo(){
        return to;
    }
    public Position getFrom(){
        return from;
    }
    public Piece getPiece(){
        return piece;
    }
}
