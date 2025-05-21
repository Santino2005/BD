package edu.austral.dissis.chess.engine.rules;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.MovesType;
import edu.austral.dissis.chess.engine.Position;
import edu.austral.dissis.chess.engine.pieces.Piece;

public class OriginalRules implements Rules {

  @Override
  public MovesType validMove(
      Position initialPos, Position finalPos, Piece piece, Board board, Color userColor) {
    if(piece.validMove(initialPos, finalPos, board.getCells()).valid()
        && userColor == piece.color()
        && board.getColorToPlay() == userColor
        && validPosition(initialPos)
        && validPosition(finalPos)
    ){
      return new MovesType(piece,"valid", initialPos,finalPos,board.getCells());
    }
    return new MovesType(piece,"invalid", initialPos, finalPos, board.getCells());
  }

  private boolean validPosition(Position position) {
    return position.row() < 9 && position.row() > 0;
  }

  @Override
  public Color starterPlayer() {
    return Color.WHITE;
  }
}
