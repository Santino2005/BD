package edu.austral.dissis.chess.engine;

import edu.austral.dissis.chess.engine.rules.OriginalRules;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClassicRulesTest {

  @Test
  public void cantMoveOtherColorPieces() {

    Board board = new GameFactory().createNoPawnsBoard();

    User user1 = new User(Color.WHITE, "pepe");
    User user2 = new User(Color.BLACK, "popo");

    Position initial = new Position(1, 8);
    Position finalPos = new Position(8, 8);

    Position initial2 = new Position(8, 8);
    Position finalPos2 = new Position(5, 8);

    Board whiteMoves = board.move(initial, finalPos, board.getCells().get(initial), user1);
    Board blackMoves =
        whiteMoves.move(initial2, finalPos2, whiteMoves.getCells().get(initial2), user2);

    Assertions.assertEquals(Color.WHITE, whiteMoves.getCells().get(finalPos).color());
    Assertions.assertEquals(Color.WHITE, blackMoves.getCells().get(initial2).color());
    Assertions.assertNull(blackMoves.getCells().get(finalPos2));
    Assertions.assertEquals(whiteMoves.getCells(), blackMoves.getCells());
  }

  @Test
  public void notIsTheColorToPlay() {

    Board board = new GameFactory().createNoPawnsBoard();

    User user1 = new User(Color.WHITE, "pepe");
    User user2 = new User(Color.BLACK, "popo");

    Position initial = new Position(1, 8);
    Position finalPos = new Position(8, 8);

    Position initial2 = new Position(8, 8);
    Position finalPos2 = new Position(8, 3);

    Board whiteMoves = board.move(initial, finalPos, board.getCells().get(initial), user1);
    Board whiteMovesAgain =
        whiteMoves.move(initial2, finalPos2, whiteMoves.getCells().get(initial2), user2);

    Assertions.assertEquals(whiteMovesAgain.getCells(), whiteMoves.getCells());
    Assertions.assertEquals(whiteMovesAgain.getColorToPlay(), whiteMoves.getColorToPlay());
  }

  @Test
  public void getRules() {
    Board board = new GameFactory().createNoPawnsBoard();

    Assertions.assertEquals(Color.WHITE, new OriginalRules().starterPlayer());
    Assertions.assertEquals(new OriginalRules().starterPlayer(), board.getColorToPlay());
  }
}
