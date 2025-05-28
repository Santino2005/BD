package edu.austral.dissis.chess.engine.Moves;

import edu.austral.dissis.chess.engine.PiecesMaked.MixedPieces;
import edu.austral.dissis.chess.engine.Position;

public class LastMove {

  private final Position from;
  private final Position to;
  private final MixedPieces piece;

  public LastMove(Position from, Position to, MixedPieces piece) {
    this.from = from;
    this.to = to;
    this.piece = piece;
  }

  public Position getFrom() {
    return from;
  }

  public Position getTo() {
    return to;
  }

  public MixedPieces getPiece() {
    return piece;
  }
}
