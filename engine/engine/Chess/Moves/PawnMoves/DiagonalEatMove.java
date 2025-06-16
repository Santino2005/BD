package edu.austral.dissis.chess.engine.Chess.Moves.PawnMoves;

import edu.austral.dissis.chess.engine.Chess.MoveConditions.MovesWithCapture.MovesValidator;
import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Colors.Color;
import edu.austral.dissis.chess.engine.Commons.Moves.ValidConditions;
import edu.austral.dissis.chess.engine.Commons.Moves.Valuate;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;
import edu.austral.dissis.chess.engine.Commons.Positions.Position;

import java.util.Arrays;
import java.util.List;

public class DiagonalEatMove implements Valuate {

    private final List<MovesValidator> conditions;

    public DiagonalEatMove(MovesValidator... conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    @Override
    public boolean valuate(MoveTo moveTo, Board board) {
        int dir = (board.getPieceByPosition(moveTo.getFrom()).get().getColor() == Color.WHITE) ? 1 : -1;
        if(Math.abs(moveTo.getTo().column() - moveTo.getFrom().column())== 1 &&
                moveTo.getTo().row() == moveTo.getFrom().row() + dir) {
            for(MovesValidator conditions : conditions){
                if(conditions.valid(moveTo,board)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Position> possiblePosition(MoveTo moveTo, Board board) {
        return null;
    }
}
