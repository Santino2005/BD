package edu.austral.dissis.chess.engine.boards;

import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.PiecesMaked.*;
import edu.austral.dissis.chess.engine.PiecesMaked.King;
import edu.austral.dissis.chess.engine.Position;
import java.util.HashMap;
import java.util.Map;

public class ClassicBoard implements BoardOrganization {

  FactoryPieces pieces = new FactoryPieces();

  @Override
  public Map<Position, MixedPieces> create() {
    Map<Position, MixedPieces> initialCells = new HashMap<>();
    addBishop(initialCells);
    addRook(initialCells);
    addKnight(initialCells);
    addQueen(initialCells);
    addKing(initialCells);
    addPawn(initialCells);
    return initialCells;
  }

  @Override
  public Map<Position, MixedPieces> addPawn(Map<Position, MixedPieces> mapToAdd) {
    for (int i = 1; i < 9; i++) {
      mapToAdd.put(new Position(7, i), pieces.createPawn("PawnBlack" + i, Color.BLACK));
    }
    for (int i = 1; i < 9; i++) {
      mapToAdd.put(new Position(2, i), pieces.createPawn("PawnWhite" + i, Color.WHITE));
    }
    return mapToAdd;
  }

  @Override
  public Map<Position, MixedPieces> addBishop(Map<Position, MixedPieces> mapToAdd) {
    mapToAdd.put(new Position(1, 3), pieces.createBishop("bishop", Color.WHITE));
    mapToAdd.put(new Position(1, 6), pieces.createBishop("bishop2", Color.WHITE));
    mapToAdd.put(new Position(8, 3), pieces.createBishop("bishop3", Color.BLACK));
    mapToAdd.put(new Position(8, 6), pieces.createBishop("bishop4", Color.BLACK));
    return mapToAdd;
  }

  @Override
  public Map<Position, MixedPieces> addRook(Map<Position, MixedPieces> mapToAdd) {
    mapToAdd.put(new Position(1, 1), new Rook("rook", Color.WHITE));
    mapToAdd.put(new Position(1, 8), new Rook("rook2", Color.WHITE));
    mapToAdd.put(new Position(8, 1), new Rook("rook3", Color.BLACK));
    mapToAdd.put(new Position(8, 8), new Rook("rook4", Color.BLACK));
    return mapToAdd;
  }

  @Override
  public Map<Position, MixedPieces> addQueen(Map<Position, MixedPieces> mapToAdd) {
    mapToAdd.put(new Position(1, 4), pieces.createQueen("Queen1", Color.WHITE));

    mapToAdd.put(new Position(8, 4), pieces.createQueen("Queen2", Color.BLACK));
    return mapToAdd;
  }

  @Override
  public Map<Position, MixedPieces> addKing(Map<Position, MixedPieces> mapToAdd) {
    mapToAdd.put(new Position(1, 5), new King("King1", Color.WHITE));
    mapToAdd.put(new Position(8, 5), new King("King2", Color.BLACK));
    return mapToAdd;
  }

  @Override
  public Map<Position, MixedPieces> addKnight(Map<Position, MixedPieces> mapToAdd) {
    mapToAdd.put(new Position(1, 2), pieces.creteKnight("Knight1", Color.WHITE));
    mapToAdd.put(new Position(1, 7), pieces.creteKnight("Knight2", Color.WHITE));
    mapToAdd.put(new Position(8, 2), pieces.creteKnight("Knight3", Color.BLACK));
    mapToAdd.put(new Position(8, 7), pieces.creteKnight("Knight4", Color.BLACK));
    return mapToAdd;
  }
}
