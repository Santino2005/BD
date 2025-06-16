package edu.austral.dissis.chess.engine.Chess.Boards;

import edu.austral.dissis.chess.engine.Commons.Board.BoardCreation;
import edu.austral.dissis.chess.engine.Commons.Colors.Color;
import edu.austral.dissis.chess.engine.Commons.Pieces.FactoryPieces;
import edu.austral.dissis.chess.engine.Commons.Pieces.Piece;
import edu.austral.dissis.chess.engine.Commons.Positions.Position;

import java.util.HashMap;
import java.util.Map;

public class ClassicBoard implements BoardCreation {
    @Override
    public Map<Position, Piece> create(FactoryPieces factoryPieces) {
        Map<Position, Piece> initialCells = new HashMap<>();
        addBishop(initialCells, factoryPieces);
        addRook(initialCells, factoryPieces);
        addKnight(initialCells, factoryPieces);
        addQueen(initialCells, factoryPieces);
        addKing(initialCells, factoryPieces);
        addPawn(initialCells, factoryPieces);
        return initialCells;
    }
    private Map<Position, Piece> addPawn(Map<Position, Piece> mapToAdd, FactoryPieces factoryPieces) {
        for (int i = 1; i < 9; i++) {
            mapToAdd.put(new Position(7, i), factoryPieces.createPawn("pawn", Color.BLACK, "7"+i, 'P',false));
        }
        for (int i = 1; i < 9; i++) {
            mapToAdd.put(new Position(2, i), factoryPieces.createPawn("pawn", Color.WHITE, "2"+i, 'P',false));
        }
        return mapToAdd;
    }
    private Map<Position, Piece> addBishop(
            Map<Position, Piece> mapToAdd, FactoryPieces factoryPieces) {
        mapToAdd.put(new Position(1, 3), factoryPieces.createBishop("bishop", Color.WHITE, "13", 'B',false));
        mapToAdd.put(new Position(1, 6), factoryPieces.createBishop("bishop", Color.WHITE, "16", 'B', false));
        mapToAdd.put(new Position(8, 3), factoryPieces.createBishop("bishop", Color.BLACK, "83", 'B', false));
        mapToAdd.put(new Position(8, 6), factoryPieces.createBishop("bishop", Color.BLACK, "86", 'B', false));
        return mapToAdd;
    }

    private Map<Position, Piece> addRook(Map<Position, Piece> mapToAdd, FactoryPieces factoryPieces) {
        mapToAdd.put(new Position(1, 1), factoryPieces.createRook("rook", Color.WHITE, "11", 'R',false));
        mapToAdd.put(new Position(1, 8), factoryPieces.createRook("rook", Color.WHITE, "18", 'R',false));
        mapToAdd.put(new Position(8, 1), factoryPieces.createRook("rook", Color.BLACK, "81", 'R',false));
        mapToAdd.put(new Position(8, 8), factoryPieces.createRook("rook", Color.BLACK, "88", 'R',false));
        return mapToAdd;
    }

    private Map<Position, Piece> addQueen(Map<Position, Piece> mapToAdd, FactoryPieces factoryPieces) {
        mapToAdd.put(new Position(1, 4), factoryPieces.createQueen("queen", Color.WHITE, "14", 'Q', false));

        mapToAdd.put(new Position(8, 4), factoryPieces.createQueen("queen", Color.BLACK, "84", 'Q', false));
        return mapToAdd;
    }

    private Map<Position, Piece> addKing(Map<Position, Piece> mapToAdd, FactoryPieces factoryPieces) {
        mapToAdd.put(new Position(1, 5), factoryPieces.createKing("king", Color.WHITE, "15", 'K', false));
        mapToAdd.put(new Position(8, 5), factoryPieces.createKing("king", Color.BLACK, "85", 'K',false));
        return mapToAdd;
    }

    private Map<Position, Piece> addKnight(
            Map<Position, Piece> mapToAdd, FactoryPieces factoryPieces) {
        mapToAdd.put(new Position(1, 2), factoryPieces.createKnight("knight", Color.WHITE, "12", 'N',false));
        mapToAdd.put(new Position(1, 7), factoryPieces.createKnight("knight", Color.WHITE, "17", 'N',false));
        mapToAdd.put(new Position(8, 2), factoryPieces.createKnight("knight", Color.BLACK, "82", 'N',false));
        mapToAdd.put(new Position(8, 7), factoryPieces.createKnight("knight", Color.BLACK, "87", 'N',false));
        return mapToAdd;
    }
}
