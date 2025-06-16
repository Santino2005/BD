package edu.austral.dissis.chess.engine.Chess.Moves.DiagonalMoves;

import edu.austral.dissis.chess.engine.Chess.MoveConditions.MovesWithCapture.MovesValidator;
import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Moves.Valuate;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;
import edu.austral.dissis.chess.engine.Commons.Positions.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiagonalLeftTopMove implements Valuate {

  private final List<MovesValidator> movesValidator;

  public DiagonalLeftTopMove(MovesValidator... movesValidator) {
    this.movesValidator = Arrays.asList(movesValidator);
  }

  @Override
  public boolean valuate(MoveTo moveTo, Board board) {
    if (-(moveTo.getTo().row() - moveTo.getFrom().row()) == moveTo.getTo().column() - moveTo.getFrom().column()) {
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

    int currentRow = initialPos.getFrom().row() + 1;
    int currentCol = initialPos.getFrom().column() - 1;
    MoveTo moveTo = new MoveTo(initialPos.getFrom(), new Position(currentRow,currentCol));

    while (board.validPosition(moveTo)) {


      if (!board.getCells().containsKey(moveTo.getTo())) {
        if (allValidatorsPass(moveTo, board)){
          list.add(moveTo.getTo());
        }
      } else {
        break;
      }

      currentRow += 1;
      currentCol -= 1;
      moveTo = new MoveTo(moveTo.getFrom(),new Position(currentRow, currentCol));
    }
    list.add(moveTo.getTo());
    return list;
  }

  private boolean allValidatorsPass(MoveTo moveTo, Board board) {
    for (MovesValidator validator : movesValidator) {
      if (!validator.valid(moveTo, board)) return false;
    }
    return true;
  }
}
