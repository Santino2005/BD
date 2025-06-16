package edu.austral.dissis.chess.engine.Chess.MoveConditions.FreePath;

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

public class MoveToFront implements Valuate {

    private final List<MovesValidator> moves;
    public MoveToFront(MovesValidator... moves){
        this.moves = Arrays.asList(moves);
    }

    @Override
    public boolean valuate(MoveTo moveTo, Board board) {
        int dir = (board.getPieceByPosition(moveTo.getFrom()).get().getColor().equals(Color.WHITE)) ? 1 : -1;
        if (moveTo.getTo().row() == moveTo.getFrom().row() + dir &&
                moveTo.getTo().column() == moveTo.getFrom().column()) {
            for (ValidConditions moves : moves) {
                if(!moves.valid(moveTo, board)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public List<Position> possiblePosition(MoveTo moveTo, Board board) {
        List<Position> allMoves = new ArrayList<>();
        int dir = (Color.WHITE == board.getPieceByPosition(moveTo.getFrom()).get().getColor()) ? 1 : -1;
        if(allValidatorsPass(new MoveTo(moveTo.getFrom(), new Position(moveTo.getTo().row() + dir, moveTo.getFrom().column())),board)){
            allMoves.add(new Position(moveTo.getTo().row() + dir, moveTo.getFrom().column()));
        }
        return allMoves;
    }

    private boolean allValidatorsPass(MoveTo moveTo, Board board) {
        for (MovesValidator validator : moves) {
            if (!validator.valid(moveTo, board)){
                return false;
            }
        }
        return true;
    }
}
