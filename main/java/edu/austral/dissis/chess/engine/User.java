package edu.austral.dissis.chess.engine;

import edu.austral.dissis.chess.engine.pieces.Piece;

public class User {

  private final Color color;

  private final String name;

  public User(Color color, String name) {
    this.color = color;
    this.name = name;
  }

  public Board move(Piece piece, Position initialPos, Position finalPos, Board board) {
    return board.move(initialPos, finalPos, piece, this);
  }

  public Color getColor() {
    return color;
  }
}
