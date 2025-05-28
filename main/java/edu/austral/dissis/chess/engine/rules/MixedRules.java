package edu.austral.dissis.chess.engine.rules;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.Moves.LastMove;
import edu.austral.dissis.chess.engine.MovesType;
import edu.austral.dissis.chess.engine.PiecesMaked.MixedPieces;
import edu.austral.dissis.chess.engine.Position;
import java.util.Arrays;
import java.util.List;

public class MixedRules implements Rules {

  private final List<Rules> list;

  public MixedRules(Rules... rules) {
    this.list = Arrays.asList(rules);
  }

  @Override
  public MovesType validMove(
      Position initialPos,
      Position finalPos,
      MixedPieces piece,
      Board board,
      Color userColor,
      boolean turn) {
    for (Rules rules : list) {
      if (rules.validMove(initialPos, finalPos, piece, board, userColor, turn).valid()) {
        return rules.validMove(initialPos, finalPos, piece, board, userColor, turn);
      }
    }
    return new MovesType(piece, "Valid", initialPos, finalPos, board);
  }

  @Override
  public Color starterPlayer() {
    return list.get(0).starterPlayer();
  }

  @Override
  public boolean validPosition(Position position) {
    for (Rules rule : list) {
      if (!rule.validPosition(position)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public Color afterMove(Board board, LastMove lastMove) {
    return list.get(list.size() - 1).afterMove(board, lastMove);
  }
}
