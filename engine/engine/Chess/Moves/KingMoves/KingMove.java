package edu.austral.dissis.chess.engine.Chess.Moves.KingMoves;

import edu.austral.dissis.chess.engine.Chess.MoveConditions.MovesWithCapture.MovesValidator;
import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Moves.Valuate;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;
import edu.austral.dissis.chess.engine.Commons.Positions.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KingMove implements Valuate {

    private final List<MovesValidator> movesValidator;

    public KingMove(MovesValidator... movesValidator) {
        this.movesValidator = Arrays.asList(movesValidator);
    }
    @Override
    public boolean valuate(MoveTo moveTo, Board board) {
        for(MovesValidator moves : movesValidator){
            if(moves.valid(moveTo,board)){
                    return true;
            }
        }
        return false;
    }

    @Override
    public List<Position> possiblePosition(MoveTo moveTo, Board board) {
        List<Position> allMoves = new ArrayList<>();
        int row = moveTo.getFrom().row();
        int col = moveTo.getFrom().column();
        List<Position> positions =
                List.of(
                        new Position(row, col + 1),
                        new Position(row, col - 1),
                        new Position(row + 1, col),
                        new Position(row + 1, col + 1),
                        new Position(row + 1, col - 1),
                        new Position(row - 1, col),
                        new Position(row - 1, col - 1),
                        new Position(row - 1, col + 1));
        for (Position pos : positions) {
            if(board.getCells().containsKey(pos)) {
                if (allValidatorsPass(new MoveTo(moveTo.getFrom(),pos), board)) {
                    allMoves.add(pos);
                }allMoves.add(pos);
            }
        }
        return allMoves;
    }

    private boolean allValidatorsPass(MoveTo moveTo, Board board) {
        for (MovesValidator validator : movesValidator) {
            if (!validator.valid(moveTo, board)) return false;
        }
        return true;
    }
}
