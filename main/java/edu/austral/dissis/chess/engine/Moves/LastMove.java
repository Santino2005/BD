package edu.austral.dissis.chess.engine.Moves;

import edu.austral.dissis.chess.engine.Position;
import edu.austral.dissis.chess.engine.pieces.Piece;

public class LastMove {

  private final Position from;
  private final Position to;
  private final Piece piece;

  public LastMove(Position from, Position to, Piece piece) {
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

  public Piece getPiece() {
    return piece;
  }
}
