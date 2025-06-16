package edu.austral.dissis.chess.engine.Chess.Moves.PawnMoves;

import edu.austral.dissis.chess.engine.Chess.MoveConditions.MovesWithCapture.MovesValidator;
import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Colors.Color;
import edu.austral.dissis.chess.engine.Commons.Moves.ValidConditions;
import edu.austral.dissis.chess.engine.Commons.Moves.Valuate;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;
import edu.austral.dissis.chess.engine.Commons.Positions.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoubleMoveToFront implements Valuate {

    private final List<MovesValidator> conditions;

    public DoubleMoveToFront(MovesValidator...conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    @Override
    public boolean valuate(MoveTo moveTo, Board board) {
        int dir = (board.getPieceByPosition(moveTo.getFrom()).get().getColor().equals(Color.WHITE)) ? 1 : -1;
        if(Math.abs(moveTo.getTo().row() - moveTo.getFrom().row()) != 2
                || moveTo.getFrom().column() != moveTo.getTo().column()){
            return false;
        }
        Position intermediate = new Position(moveTo.getFrom().row() + dir, moveTo.getFrom().column());
        for (MovesValidator condition : conditions) {
            boolean validIntermediate = condition.valid(new MoveTo(moveTo.getFrom(), intermediate), board);
            boolean validDestination = condition.valid(moveTo, board);
            if (!validIntermediate && !validDestination){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Position> possiblePosition(MoveTo moveTo, Board board) {
        List<Position> allMoves = new ArrayList<>();

        int dir = (Color.WHITE == board.getPieceByPosition(moveTo.getFrom()).get().getColor()) ? 1 : -1;

        Position oneStepForward = new Position(moveTo.getFrom().row() + dir, moveTo.getFrom().column());
        Position twoStepsForward = new Position(moveTo.getFrom().row() + 2 * dir, moveTo.getFrom().column());

        MoveTo moveOne = new MoveTo(moveTo.getFrom(), oneStepForward);
        MoveTo moveTwo = new MoveTo(moveTo.getFrom(), twoStepsForward);
        if (allValidatorsPass(moveOne, board) && allValidatorsPass(moveTwo,board)) {
            allMoves.add(oneStepForward);
        }
        allMoves.add(oneStepForward);
        allMoves.add(twoStepsForward);
        return allMoves;
    }

    private boolean allValidatorsPass(MoveTo moveTo, Board board) {
        for (MovesValidator validator : conditions) {
            if (!validator.valid(moveTo, board)) return false;
        }
        return true;
    }
}
