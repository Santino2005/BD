package edu.austral.dissis.chess.engine.rules;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.Moves.LastMove;
import edu.austral.dissis.chess.engine.MovesType;
import edu.austral.dissis.chess.engine.PiecesMaked.MixedPieces;
import edu.austral.dissis.chess.engine.Position;

public class OriginalRules implements Rules {

  @Override
  public MovesType validMove(
      Position initialPos,
      Position finalPos,
      MixedPieces piece,
      Board board,
      Color userColor,
      boolean allPossibleMoves) {
    if (validPosition(initialPos)
        && validPosition(finalPos)
        && piece.validMove(initialPos, finalPos, board).valid()
        && userColor == piece.color()
        && (!allPossibleMoves || board.getColorToPlay() == userColor)) {
      return new MovesType(piece, "valid", initialPos, finalPos, board);
    }
    return new MovesType(piece, "invalid", initialPos, finalPos, board);
  }

  public boolean validPosition(Position position) {
    return position.row() < 9
        && position.row() > 0
        && position.column() < 9
        && position.column() > 0;
  }

  @Override
  public Color afterMove(Board board, LastMove lastMove) {
    if (lastMove == null) {
      return Color.BLACK;
    }
    return (lastMove.getPiece().color() == Color.WHITE) ? Color.BLACK : Color.WHITE;
  }

  @Override
  public Color starterPlayer() {
    return Color.WHITE;
  }
}
