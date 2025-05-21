package edu.austral.dissis.chess.engine.pieces;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.MovesType;
import edu.austral.dissis.chess.engine.Position;
import edu.austral.dissis.chess.engine.rules.Rules;

import java.util.List;
import java.util.Map;

public class King implements Piece{

    private final Color color;
    private final boolean moved;
    public Rules rules;

    public King(Color color){
        this.color = color;
        this.moved = false;
    }
    private King(Color color, boolean moved){
        this.color = color;
        this.moved = moved;
    }
    @Override
    public MovesType validMove(Position initialPos, Position finalPos, Map<Position, Piece> cells) {
        if(Math.abs(initialPos.row()-finalPos.row()) == 1 && Math.abs(initialPos.column() - finalPos.column()) == 1){
            if(cells.get(finalPos) == null || cells.get(finalPos).color() != this.color){
                return new MovesType(this, "Valid", initialPos, finalPos, cells);
            }
        }else if(!this.moved && cells.get(finalPos) instanceof Rook && ((Rook) cells.get(finalPos)).hasMoved()){
            int columnWay = Integer.signum(finalPos.column() - initialPos.column());
            for(int i = initialPos.column(); i != finalPos.column(); i = i + columnWay){
                if(this.color == Color.WHITE  && cells.containsKey(new Position(1,i)) || this.color == Color.BLACK && cells.containsKey(new Position(8,i))){
                    return new MovesType(this, "invalid", initialPos, finalPos, cells);
                }
            }
            return new MovesType(this, "Castling", initialPos, finalPos, cells);
        }
        return new MovesType(this, "invalid", initialPos, finalPos, cells);
    }

    @Override
    public Color color() {
        return color;
    }
    public King moved(){
        return new King(color, true);
    }
    @Override
    public List<Position> possibleMoves(Position initialPos, Map<Position, Piece> cells, Board board, Rules rules){
        
        return null;
    }
}
