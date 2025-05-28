package edu.austral.dissis.chess.engine.PiecesMoves;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.Position;
import java.util.ArrayList;
import java.util.List;

public class CheckDoubleFront implements Valuate {
  @Override
  public boolean valuate(Position initialPos, Position finalPos, Board board, Color color) {
    MoveToFront move = new MoveToFront();
    int dirtRow = (color == Color.WHITE) ? 1 : -1;
    return move.valuate(
            initialPos, new Position(finalPos.row() - dirtRow, finalPos.column()), board, color)
        && move.valuate(
            new Position(initialPos.row() + dirtRow, initialPos.column()), finalPos, board, color)
        && Math.abs(finalPos.row() - initialPos.row()) == 2
        && initialPos.column() == finalPos.column()
        && board.getCells().get(finalPos) == null;
  }

  @Override
  public List<Position> possibleMoves(Position initialPos, Board board, Color color) {
    List<Position> allMoves = new ArrayList<>();
    int dir = (Color.WHITE == color) ? 1 : -1;
    if (board
            .getRules()
            .validMove(
                initialPos,
                new Position(initialPos.row() + 2 * dir, initialPos.column()),
                board.getCells().get(initialPos),
                board,
                board.getColorToPlay(),
                false)
            .valid()
        && board.getCells().get(initialPos)
            == new Board(board.getOriginalCells(), board.getRules(), null)
                .getCells()
                .get(initialPos)) {
      allMoves.add(new Position(initialPos.row() + 2 * dir, initialPos.column()));
    }
    return allMoves;
  }
}
