package edu.austral.dissis.chess.engine.Commons.Moves;

import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;
import edu.austral.dissis.chess.engine.Commons.Positions.Position;
import edu.austral.dissis.chess.engine.Commons.Board.Board;
import java.util.List;

public interface Valuate {

    public boolean valuate(MoveTo moveTo, Board board);

    List<Position> possiblePosition(MoveTo moveTo, Board board);
}
