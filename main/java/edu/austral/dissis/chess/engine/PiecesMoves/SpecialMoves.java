package edu.austral.dissis.chess.engine.PiecesMoves;

public class SpecialMoves {

  public boolean specialMove(Valuate piece) {
    if (piece instanceof Castling) {
      return true;
    }
    return false;
  }

  public String moveName(Valuate piece) {
    String className = piece.getClass().getSimpleName();
    return "edu.austral.dissis.chess.engine.Moves." + className;
  }
}
