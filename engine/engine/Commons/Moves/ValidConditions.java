package edu.austral.dissis.chess.engine.Commons.Moves;

import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;
import edu.austral.dissis.chess.engine.Commons.Positions.Position;

public interface ValidConditions {

    public boolean valid(MoveTo moveTo, Board board);
}
