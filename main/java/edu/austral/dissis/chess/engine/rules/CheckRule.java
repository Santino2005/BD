package edu.austral.dissis.chess.engine.rules;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.PiecesMaked.King;
import edu.austral.dissis.chess.engine.PiecesMaked.MixedPieces;
import edu.austral.dissis.chess.engine.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckRule {
  private final Board board;

  public CheckRule(Board board) {
    this.board = board;
  }

  public boolean isCheck(Color color, Position kingPos, Board board) {
    Color opponentColor = (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    Map<Position, List<Position>> newBoard = board.getAllPossibleMoves(opponentColor);
    for (List<Position> moves : newBoard.values()) {
      if (moves.contains(kingPos)) {
        return true;
      }
    }
    return false;
  }

  public boolean checkMate(Color color) {
    Position position = null;
    for (Map.Entry<Position, MixedPieces> entry : board.getCells().entrySet()) {
      if (entry.getValue() instanceof King && entry.getValue().color() == color) {
        position = entry.getKey();
        break;
      }
    }
    if (!isCheck(color, position, board)) {
      System.out.println("a");
      return false;
    }
    Map<Position, List<Position>> allMoves = board.getAllPossibleMoves(color);
    Color opposite = (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    for (Map.Entry<Position, List<Position>> initial : allMoves.entrySet()) {
      for (Position to : initial.getValue()) {
        Map<Position, MixedPieces> cells = new HashMap<>(board.getCells());
        cells.remove(initial.getKey());
        cells.put(to, board.getCells().get(initial.getKey()));
        Board simulatedBoard =
            new Board(opposite, cells, board.getRules(), null, board.getOriginalCells());
        Position newKingPos =
            (board.getCells().get(initial.getKey()) instanceof King) ? to : position;

        if (!isCheck(color, newKingPos, simulatedBoard)) {
          return false;
        }
      }
    }
    return true;
  }
}
