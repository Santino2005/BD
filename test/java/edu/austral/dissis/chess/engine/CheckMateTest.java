package edu.austral.dissis.chess.engine;

import edu.austral.dissis.chess.engine.boards.CheckMateBoard;
import edu.austral.dissis.chess.engine.rules.OriginalRules;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckMateTest {

  @Test
  public void checkMateTest() {

    Board board = new GameFactory().createClassicBoard();

    User user1 = new User(Color.WHITE, "pepe");
    User user2 = new User(Color.BLACK, "popo");

    Position initial = new Position(2, 7);
    Position finalPos = new Position(4, 7);

    Position initial2 = new Position(7, 5);
    Position finalPos2 = new Position(5, 5);

    Position initial3 = new Position(2, 6);
    Position finalPos3 = new Position(3, 6);

    Position initial4 = new Position(8, 4);
    Position finalPos4 = new Position(4, 8);

    Position initial5 = new Position(1, 5);
    Position finalPos5 = new Position(2, 6);

    Board whiteMoves = board.move(initial, finalPos, board.getCells().get(initial), user1);
    Board blackMoves =
        whiteMoves.move(initial2, finalPos2, whiteMoves.getCells().get(initial2), user2);
    Board whiteMoves2 =
        blackMoves.move(initial3, finalPos3, blackMoves.getCells().get(initial3), user1);
    Board blackMoves2 =
        whiteMoves2.move(initial4, finalPos4, whiteMoves2.getCells().get(initial4), user2);
    Board whiteTryMoves =
        blackMoves2.move(initial5, finalPos5, blackMoves2.getCells().get(initial5), user1);

    Assertions.assertEquals(Color.WHITE, blackMoves2.getColorToPlay());
    Assertions.assertEquals(Color.WHITE, whiteTryMoves.getColorToPlay());
    Assertions.assertEquals(blackMoves2, whiteTryMoves);
  }

  @Test
  public void check() {

    Board board = new Board(new CheckMateBoard(), new OriginalRules(), null);

    User user1 = new User(Color.WHITE, "pepe");
    User user2 = new User(Color.BLACK, "popo");

    Position initial = new Position(7, 8);
    Position finalPos = new Position(8, 7);

    Position initial2 = new Position(5, 6);
    Position finalPos2 = new Position(6, 7);

    Position initial3 = new Position(8, 8);
    Position finalPos3 = new Position(8, 6);

    Position initial4 = new Position(8, 7);
    Position finalPos4 = new Position(8, 6);
  }
}
