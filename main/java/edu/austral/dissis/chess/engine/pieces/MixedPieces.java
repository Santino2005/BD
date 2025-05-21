package edu.austral.dissis.chess.engine.pieces;

import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.MovesType;
import edu.austral.dissis.chess.engine.Position;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MixedPieces implements Piece{

    private final Color color;
    private final List<Piece> list;

    public MixedPieces(Color color, Piece... pieces){
        this.color = color;
        this.list = Arrays.asList(pieces);
    }

    @Override
    public MovesType validMove(Position initialPos, Position finalPos, Map<Position,Piece> cells){
        for(Piece pieces : list){
            if(pieces.validMove(initialPos,finalPos,cells).valid()){
                return new MovesType(pieces,"Valid",initialPos,finalPos,cells);
            }
        }
        return new MovesType(this,"invalid",initialPos,finalPos,cells);
    }

    @Override
    public Color color(){
        return color;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MixedPieces pieces= (MixedPieces) obj;
        return this.color.equals(pieces.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
