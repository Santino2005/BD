package edu.austral.dissis.chess.engine;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @Test
    public void cantMove(){

        Board board = new GameFactory().createClassicBoard();

        User user1 = new User(Color.WHITE, "pepe");
        User user2 = new User(Color.BLACK, "pepe");
        Position initial = new Position(2, 1);
        Position finalPos = new Position(1, 2);

        Position initial2 = new Position(7, 2);
        Position finalPos2 = new Position(7, 3);

        Position initial3 = new Position(2, 4);
        Position finalPos3 = new Position(3, 3);

        Board newBoard = board.move(initial, finalPos, board.getCells().get(initial), user1);
        Board newBoard2 = newBoard.move(initial2, finalPos2, newBoard.getCells().get(initial2), user2);
        Board newBoard3 = newBoard2.move(initial3, finalPos3, newBoard2.getCells().get(initial3), user1);

        Assertions.assertEquals(board.getCells(), newBoard.getCells());
        Assertions.assertEquals(board.getCells(), newBoard2.getCells());
        Assertions.assertEquals(board.getCells(), newBoard3.getCells());

    }

    @Test
    public void canMoveToFront(){

        Board board = new GameFactory().createClassicBoard();

        User user1 = new User(Color.WHITE, "pepe");
        User user2 = new User(Color.BLACK, "pepe");

        Position initial = new Position(2, 4);
        Position finalPos = new Position(4, 4);

        Position initial2 = new Position(7, 5);
        Position finalPos2 = new Position(5, 5);

        Position initial3 = new Position(4, 4);
        Position finalPos3 = new Position(5, 5);

        Position initial4 = new Position(7, 6);
        Position finalPos4 = new Position(5, 6);

        Board newBoard = board.move(initial, finalPos, board.getCells().get(initial), user1);
        Board newBoard2 = newBoard.move(initial2, finalPos2, newBoard.getCells().get(initial2), user2);
        Board newBoard3 = newBoard2.move(initial3, finalPos3, newBoard2.getCells().get(initial3), user1);
        Board newBoard4 = newBoard3.move(initial4, finalPos4, newBoard3.getCells().get(initial4), user2);

        Assertions.assertNotNull(newBoard.getCells().get(finalPos));
        Assertions.assertNotNull(newBoard2.getCells().get(finalPos2));
        Assertions.assertNotNull(newBoard3.getCells().get(finalPos3));
        Assertions.assertNotNull(newBoard4.getCells().get(finalPos4));
        Assertions.assertEquals(newBoard4.getCells().get(finalPos4).color(), Color.BLACK);
        Assertions.assertEquals(newBoard4.getCells().get(finalPos3).color(), Color.WHITE);
    }
}
