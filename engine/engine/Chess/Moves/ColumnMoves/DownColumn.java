package edu.austral.dissis.chess.engine.Chess.Moves.ColumnMoves;

import edu.austral.dissis.chess.engine.Chess.MoveConditions.MovesWithCapture.MovesValidator;
import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Moves.Valuate;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;
import edu.austral.dissis.chess.engine.Commons.Positions.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DownColumn implements Valuate {

    private final List<MovesValidator> movesValidator;

    public DownColumn(MovesValidator... movesValidator) {
        this.movesValidator = Arrays.asList(movesValidator);
    }
    @Override
    public boolean valuate(MoveTo moveTo, Board board) {
        if (moveTo.getFrom().row() == moveTo.getTo().row() && moveTo.getFrom().column() > moveTo.getTo().column()){
            for(MovesValidator moves : movesValidator){
                if(moves.valid(moveTo,board)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Position> possiblePosition(MoveTo moveTo, Board board) {
        return checkWays(moveTo, board);
    }
    private List<Position> checkWays(MoveTo initialPos, Board board) {
        List<Position> list = new ArrayList<>();
        int currentRow = initialPos.getFrom().row();
        int currentCol = initialPos.getFrom().column() - 1;
        Position actualPosition = new Position(currentRow, currentCol);
        while (board.validPosition(new MoveTo(initialPos.getFrom(), actualPosition))) {
            MoveTo tempMove = new MoveTo(initialPos.getFrom(), actualPosition);
            if (!board.getCells().containsKey(actualPosition)) {
                if (allValidatorsPass(tempMove, board)) {
                    list.add(actualPosition);
                }
            } else {
                break;
            }

            currentCol -= 1;
            actualPosition = new Position(currentRow, currentCol);
        }
        list.add(actualPosition);
        return list;
    }
    private boolean allValidatorsPass(MoveTo moveTo, Board board) {
        for (MovesValidator validator : movesValidator) {
            if (!validator.valid(moveTo, board)) return false;
        }
        return true;
    }
}
