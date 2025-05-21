package edu.austral.dissis.chess.engine;

import edu.austral.dissis.chess.engine.boards.ClassicBoard;
import edu.austral.dissis.chess.engine.rules.OriginalRules;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {
  @Test
  public void getColorToPlay() {
    Board board = new GameFactory().createNoPawnsBoard();

    User user1 = new User(Color.WHITE, "pepe");
    User user2 = new User(Color.BLACK, "popo");
    Position initial = new Position(1, 3);
    Position finalPos = new Position(2, 2);

    Position initial2 = new Position(8, 3);
    Position finalPos2 = new Position(6, 1);

    Board whiteMoves = board.move(initial, finalPos, board.getCells().get(initial), user1);
    Board blackMoves =
        whiteMoves.move(initial2, finalPos2, whiteMoves.getCells().get(initial2), user2);

    Assertions.assertEquals(Color.WHITE, blackMoves.getColorToPlay());
    Assertions.assertEquals(Color.BLACK, whiteMoves.getColorToPlay());
  }
}
