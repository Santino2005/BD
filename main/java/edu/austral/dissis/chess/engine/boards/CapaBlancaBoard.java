package edu.austral.dissis.chess.engine.boards;

import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.PiecesMaked.*;
import edu.austral.dissis.chess.engine.PiecesMaked.King;
import edu.austral.dissis.chess.engine.Position;
import java.util.HashMap;
import java.util.Map;

public class CapaBlancaBoard implements BoardOrganization {

  FactoryPieces pieces = new FactoryPieces();

  @Override
  public Map<Position, MixedPieces> create() {
    Map<Position, MixedPieces> initialCells = new HashMap<>();
    addKing(initialCells);
    addKnight(initialCells);
    addQueen(initialCells);
    addPawn(initialCells);
    addRook(initialCells);
    addBishop(initialCells);
    addArchbishop(initialCells);
    addChancellor(initialCells);
    return initialCells;
  }

  private Map<Position, MixedPieces> addChancellor(Map<Position, MixedPieces> mapToAdd) {
    mapToAdd.put(new Position(1, 8), pieces.createChancellor("Chancellor1", Color.WHITE));
    mapToAdd.put(new Position(8, 8), pieces.createChancellor("Chancellor2", Color.BLACK));
    return mapToAdd;
  }

  private Map<Position, MixedPieces> addArchbishop(Map<Position, MixedPieces> mapToAdd) {
    mapToAdd.put(new Position(1, 8), pieces.createChancellor("Archbishop1", Color.WHITE));
    mapToAdd.put(new Position(8, 8), pieces.createChancellor("Archbishop2", Color.BLACK));
    return mapToAdd;
  }

  @Override
  public Map<Position, MixedPieces> addBishop(Map<Position, MixedPieces> mapToAdd) {
    mapToAdd.put(new Position(1, 4), pieces.createBishop("Bishop1", Color.WHITE));
    mapToAdd.put(new Position(1, 7), pieces.createBishop("Bishop2", Color.WHITE));
    mapToAdd.put(new Position(8, 4), pieces.createBishop("Bishop3", Color.BLACK));
    mapToAdd.put(new Position(8, 7), pieces.createBishop("Bishop4", Color.BLACK));
    return mapToAdd;
  }

  @Override
  public Map<Position, MixedPieces> addRook(Map<Position, MixedPieces> mapToAdd) {
    mapToAdd.put(new Position(1, 10), new Rook("Rook1", Color.WHITE));
    mapToAdd.put(new Position(1, 1), new Rook("Rook2", Color.WHITE));
    mapToAdd.put(new Position(8, 1), new Rook("Rook3", Color.BLACK));
    mapToAdd.put(new Position(8, 10), new Rook("Rook4", Color.BLACK));
    return mapToAdd;
  }

  @Override
  public Map<Position, MixedPieces> addQueen(Map<Position, MixedPieces> mapToAdd) {
    mapToAdd.put(new Position(1, 5), pieces.createQueen("Queen1", Color.WHITE));
    mapToAdd.put(new Position(8, 5), pieces.createQueen("Queen2", Color.BLACK));
    return mapToAdd;
  }

  @Override
  public Map<Position, MixedPieces> addKing(Map<Position, MixedPieces> mapToAdd) {
    mapToAdd.put(new Position(1, 6), new King("King1",Color.WHITE));
    mapToAdd.put(new Position(8, 6), new King("King2",Color.BLACK));
    return mapToAdd;
  }

  @Override
  public Map<Position, MixedPieces> addPawn(Map<Position, MixedPieces> mapToAdd) {
    for (int i = 1; i < 11; i++) {
      mapToAdd.put(new Position(7, i), pieces.createPawn("PawnWhite" + i,Color.BLACK));
    }
    for (int i = 1; i < 11; i++) {
      mapToAdd.put(new Position(2, i), pieces.createPawn("PawnBlack" + i,Color.WHITE));
    }
    return mapToAdd;
  }

  @Override
  public Map<Position, MixedPieces> addKnight(Map<Position, MixedPieces> mapToAdd) {
    mapToAdd.put(new Position(1, 2), pieces.creteKnight("Knight1",Color.WHITE));
    mapToAdd.put(new Position(1, 9), pieces.creteKnight("Knight2",Color.WHITE));
    mapToAdd.put(new Position(8, 2), pieces.creteKnight("Knight3",Color.BLACK));
    mapToAdd.put(new Position(8, 9), pieces.creteKnight("Knight4",Color.BLACK));
    return mapToAdd;
  }
}
