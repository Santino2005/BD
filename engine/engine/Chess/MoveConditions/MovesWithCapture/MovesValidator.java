package edu.austral.dissis.chess.engine.Chess.MoveConditions.MovesWithCapture;

import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Moves.ValidConditions;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;

import java.util.Arrays;
import java.util.List;

public class MovesValidator implements ValidConditions {
    private final List<ValidConditions> conditions;

    public MovesValidator(ValidConditions... conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    @Override
    public boolean valid(MoveTo moveTo, Board board){
        for(ValidConditions conditions : conditions){
            if(!conditions.valid(moveTo,board)){
                return false;
            }
        }
        return true;
    }
}
