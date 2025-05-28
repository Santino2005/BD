package edu.austral.dissis.chess.engine;

import edu.austral.dissis.chess.engine.boards.BoardWithoutPawns;
import edu.austral.dissis.chess.engine.boards.ClassicBoard;
import edu.austral.dissis.chess.engine.rules.OriginalRules;

public class GameFactory {

  public Board createClassicBoard() {
    return new Board(new ClassicBoard(), new OriginalRules(), null);
  }

  public Board createNoPawnsBoard() {
    return new Board(new BoardWithoutPawns(), new OriginalRules(), null);
  }
}
