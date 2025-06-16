package edu.austral.dissis.chess.engine.Commons.Pieces;
import edu.austral.dissis.chess.engine.Commons.Colors.Color;
import edu.austral.dissis.chess.engine.Commons.Moves.SpecialMoves;
import edu.austral.dissis.chess.engine.Commons.Moves.Valuate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Piece {

    private final String id;
    private final Color color;
    private final String name;
    private final Character pieceId;
    private final List<Valuate> valuate;
    private final boolean hasMove;
    private final List<SpecialMoves> specialMoves;


    public Piece(String id, Color color, String name, Character pieceId,
                 boolean moved, List<SpecialMoves> specialMoves, Valuate... valuates) {
        this.id = id;
        this.color = color;
        this.name = name;
        this.pieceId = pieceId;
        this.hasMove = moved;
        this.valuate = Collections.unmodifiableList(Arrays.asList(valuates));
        this.specialMoves = specialMoves;
    }

    private Piece(String id, Color color, String name, Character pieceId,
                  boolean moved,List<SpecialMoves> specialMoves, List<Valuate> valuates) {
        this.id = id;
        this.color = color;
        this.name = name;
        this.pieceId = pieceId;
        this.valuate = valuates;
        this.hasMove = moved;
        this.specialMoves = specialMoves;
    }

    public String getId(){
        return this.id;
    }
    public String getName(){
        return name;
    }
    public Color getColor(){
        return this.color;
    }
    public boolean hasMove(){
        return this.hasMove;
    }
    public Character getPieceId(){
        return this.pieceId;
    }
    public List<Valuate> getPiecesMoves(){
        return this.valuate;
    }
    public List<SpecialMoves> getSpecialMoves(){
        if(specialMoves == null){
            return Collections.emptyList();
        }
        return specialMoves;}
    public Piece move(){
        return new Piece(id,color,name,pieceId,true, specialMoves, valuate);
    }
}
