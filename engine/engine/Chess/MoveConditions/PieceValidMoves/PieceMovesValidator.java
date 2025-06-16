package edu.austral.dissis.chess.engine.Chess.MoveConditions.PieceValidMoves;

import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Colors.Color;
import edu.austral.dissis.chess.engine.Commons.Moves.SpecialMoves;
import edu.austral.dissis.chess.engine.Commons.Moves.ValidConditions;
import edu.austral.dissis.chess.engine.Commons.Moves.Valuate;
import edu.austral.dissis.chess.engine.Commons.Pieces.Piece;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;
import edu.austral.dissis.chess.engine.Commons.Positions.Position;

import java.util.*;

public class PieceMovesValidator implements Valuate{
    private final List<Valuate> moves;
    private final List<SpecialMoves> specialMovesList;

    public PieceMovesValidator(List<SpecialMoves> specialMovesList,Valuate... moves) {
        this.moves = Arrays.asList(moves);
        this.specialMovesList = specialMovesList;
    }
    @Override
    public boolean valuate(MoveTo moveTo, Board board) {
        for (SpecialMoves specialMoves : specialMovesList){
            if(specialMoves.isSpecialMove(board.getPieceByPosition(moveTo.getFrom()).get(),moveTo,board)){
                return true;
            }
        }
        for(Valuate moves : moves){
            if(moves.valuate(moveTo,board)){
                return true;
            }
        }
        return false;
    }
    @Override
    public List<Position> possiblePosition(MoveTo moveTo, Board board) {
        List<Position> allMoves = new ArrayList<>();
        for(Valuate valuate : moves){
            valuate.possiblePosition(moveTo, board);
        }
        return allMoves;
    }
}
