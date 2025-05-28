package edu.austral.dissis.chess.engine.boards;

import edu.austral.dissis.chess.engine.PiecesMaked.MixedPieces;
import edu.austral.dissis.chess.engine.Position;
import java.util.Map;

public interface BoardOrganization {
  public Map<Position, MixedPieces> create();

  public Map<Position, MixedPieces> addBishop(Map<Position, MixedPieces> mapToAdd);

  public Map<Position, MixedPieces> addRook(Map<Position, MixedPieces> mapToAdd);

  public Map<Position, MixedPieces> addQueen(Map<Position, MixedPieces> mapToAdd);

  public Map<Position, MixedPieces> addKing(Map<Position, MixedPieces> mapToAdd);

  public Map<Position, MixedPieces> addPawn(Map<Position, MixedPieces> mapToAdd);

  public Map<Position, MixedPieces> addKnight(Map<Position, MixedPieces> mapToAdd);
}
