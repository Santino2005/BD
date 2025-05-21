package edu.austral.dissis.chess.engine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserGame {

  @Test
  public void checkUserToMove() {
    User user1 = new User(Color.WHITE, "pepe");
    User user2 = new User(Color.BLACK, "pepe2");

    Board board = new GameFactory().createNoPawnsBoard();

    Board whiteMoves =
        user1.move(
            board.getCells().get(new Position(1, 1)),
            new Position(1, 1),
            new Position(8, 1),
            board);
    Board blackMoves =
        user2.move(
            board.getCells().get(new Position(8, 8)),
            new Position(8, 8),
            new Position(1, 8),
            board);

    Assertions.assertEquals(Color.WHITE, blackMoves.getColorToPlay());
    Assertions.assertEquals(Color.BLACK, whiteMoves.getColorToPlay());
  }
}
