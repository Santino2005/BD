package edu.austral.dissis.chess.engine.boards;

import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.PiecesMaked.FactoryPieces;
import edu.austral.dissis.chess.engine.PiecesMaked.King;
import edu.austral.dissis.chess.engine.PiecesMaked.MixedPieces;
import edu.austral.dissis.chess.engine.Position;
import java.util.HashMap;
import java.util.Map;

public class CheckMateBoard implements BoardOrganization {

  FactoryPieces pieces = new FactoryPieces();

  @Override
  public Map<Position, MixedPieces> create() {
    Map<Position, MixedPieces> newBoard = new HashMap<>();
    addKing(newBoard);
    addQueen(newBoard);
    return newBoard;
  }

  @Override
  public Map<Position, MixedPieces> addBishop(Map<Position, MixedPieces> mapToAdd) {
    return null;
  }

  @Override
  public Map<Position, MixedPieces> addRook(Map<Position, MixedPieces> mapToAdd) {
    return null;
  }

  @Override
  public Map<Position, MixedPieces> addQueen(Map<Position, MixedPieces> mapToAdd) {
    mapToAdd.put(new Position(6, 6), pieces.createQueen("Queen1", Color.BLACK));
    mapToAdd.put(new Position(5, 6), pieces.createQueen("Queen2", Color.BLACK));
    return mapToAdd;
  }

  @Override
  public Map<Position, MixedPieces> addKing(Map<Position, MixedPieces> mapToAdd) {
    mapToAdd.put(new Position(7, 8), new King("King1", Color.WHITE));
    mapToAdd.put(new Position(1, 5), new King("King2", Color.BLACK));
    return mapToAdd;
  }

  @Override
  public Map<Position, MixedPieces> addPawn(Map<Position, MixedPieces> mapToAdd) {
    return null;
  }

  @Override
  public Map<Position, MixedPieces> addKnight(Map<Position, MixedPieces> mapToAdd) {
    return null;
  }
}
