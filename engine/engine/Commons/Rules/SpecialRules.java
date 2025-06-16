package edu.austral.dissis.chess.engine.Commons.Rules;

import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;

public interface SpecialRules {

    boolean checkSpecialRules(MoveTo moveTo, Board board);
}
