package edu.austral.dissis.chess.engine;

import edu.austral.dissis.chess.engine.Moves.CallMoves;
import edu.austral.dissis.chess.engine.PiecesMaked.MixedPieces;
import java.util.Objects;

public class MovesType {

  private final Board cells;
  private final MixedPieces piece;
  private final String moveName;
  private final Position initialPos;
  private final Position finalPos;
  private final CallMoves callMoves;

  public MovesType(
      MixedPieces piece, String moveName, Position initialPos, Position finalPos, Board board) {
    this.moveName = moveName;
    this.piece = piece;
    this.cells = board;
    this.finalPos = finalPos;
    this.initialPos = initialPos;
    this.callMoves = new CallMoves();
  }

  public Board move(Position initialPos, Position finalPos, MixedPieces piece, Board cells) {
    return new Board(
        null,
        callMoves
            .getMoves(this.moveName)
            .applyMove(initialPos, finalPos, piece, cells.getCells())
            .getNewCells(),
        cells.getRules(),
        null,
        cells.getOriginalCells());
  }

  public boolean valid() {
    return !Objects.equals(this.moveName, "invalid");
  }
}
