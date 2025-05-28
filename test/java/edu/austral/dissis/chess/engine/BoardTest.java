package edu.austral.dissis.chess.engine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {
  @Test
  public void getColorToPlay() {
    Board board = new GameFactory().createClassicBoard();

    User user1 = new User(Color.WHITE, "pepe");
    User user2 = new User(Color.BLACK, "popo");

    Position initial = new Position(2, 2);
    Position finalPos = new Position(4, 2);

    Position initial2 = new Position(7, 3);
    Position finalPos2 = new Position(5, 3);

    Board whiteMoves = board.move(initial, finalPos, board.getCells().get(initial), user1);
    Board blackMoves =
        whiteMoves.move(initial2, finalPos2, whiteMoves.getCells().get(initial2), user2);

    Assertions.assertEquals(Color.BLACK, whiteMoves.getColorToPlay());
    Assertions.assertEquals(Color.WHITE, blackMoves.getColorToPlay());
  }
}
