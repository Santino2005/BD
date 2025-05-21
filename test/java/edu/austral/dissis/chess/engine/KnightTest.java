package edu.austral.dissis.chess.engine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KnightTest {

  @Test
  public void cantMoveTest() {

    Board board = new GameFactory().createClassicBoard();

    User user1 = new User(Color.WHITE, "pepe");
    Position initial = new Position(1, 2);
    Position finalPos = new Position(2, 3);

    Board newBoard = board.move(initial, finalPos, board.getCells().get(initial), user1);

    Assertions.assertEquals(board.getCells(), newBoard.getCells());
  }

  @Test
  public void canMoveTest() {
    Board board = new GameFactory().createClassicBoard();

    User user1 = new User(Color.WHITE, "pepe");
    User user2 = new User(Color.BLACK, "popo");
    Position initial = new Position(1, 2);
    Position finalPos = new Position(3, 3);

    Position initial2 = new Position(8, 2);
    Position finalPos2 = new Position(6, 1);

    Board whiteMoves = board.move(initial, finalPos, board.getCells().get(initial), user1);
    Board blackMoves =
        whiteMoves.move(initial2, finalPos2, whiteMoves.getCells().get(initial2), user2);

    Assertions.assertNotNull(whiteMoves.getCells().get(finalPos));
    Assertions.assertNotNull(blackMoves.getCells().get(finalPos2));
  }

  @Test
  public void getColor() {

    Board board = new GameFactory().createClassicBoard();

    User user1 = new User(Color.WHITE, "pepe");
    User user2 = new User(Color.BLACK, "popo");
    Position initial = new Position(1, 2);
    Position finalPos = new Position(3, 1);

    Position initial2 = new Position(8, 2);
    Position finalPos2 = new Position(6, 3);

    Board whiteMoves = board.move(initial, finalPos, board.getCells().get(initial), user1);
    Board blackMoves =
        whiteMoves.move(initial2, finalPos2, whiteMoves.getCells().get(initial2), user2);

    Assertions.assertEquals(Color.WHITE, whiteMoves.getCells().get(finalPos).color());
    Assertions.assertEquals(Color.BLACK, blackMoves.getCells().get(finalPos2).color());
  }
}
