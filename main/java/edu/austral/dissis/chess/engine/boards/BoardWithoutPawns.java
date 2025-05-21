package edu.austral.dissis.chess.engine.boards;

import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.Position;
import edu.austral.dissis.chess.engine.pieces.*;

import java.util.HashMap;

public class BoardWithoutPawns implements BoardOrganization {

    @Override
    public HashMap<Position, Piece> create() {
        HashMap<Position, Piece> initialCells = new HashMap<>();
        addBishop(initialCells);
        addRook(initialCells);
        addKnight(initialCells);
        initialCells.put(new Position(1, 4), new Queen(Color.WHITE));
        initialCells.put(new Position(1, 5), new King(Color.WHITE));
        initialCells.put(new Position(8, 4), new Queen(Color.BLACK));
        initialCells.put(new Position(8, 5), new King(Color.BLACK));
        return initialCells;
    }

    private HashMap<Position, Piece> addBishop(HashMap<Position, Piece> mapToAdd) {
        mapToAdd.put(new Position(1, 3), new Bishop(Color.WHITE));
        mapToAdd.put(new Position(1, 6), new Bishop(Color.WHITE));
        mapToAdd.put(new Position(8, 3), new Bishop(Color.BLACK));
        mapToAdd.put(new Position(8, 6), new Bishop(Color.BLACK));
        return mapToAdd;
    }

    private HashMap<Position, Piece> addRook(HashMap<Position, Piece> mapToAdd) {
        mapToAdd.put(new Position(1, 1), new Rook(Color.WHITE, false));
        mapToAdd.put(new Position(1, 8), new Rook(Color.WHITE, false));
        mapToAdd.put(new Position(8, 1), new Rook(Color.BLACK, false));
        mapToAdd.put(new Position(8, 8), new Rook(Color.BLACK, false));
        return mapToAdd;
    }

    private HashMap<Position, Piece> addKnight(HashMap<Position, Piece> mapToAdd) {
        mapToAdd.put(new Position(1, 2), new Knight(Color.WHITE));
        mapToAdd.put(new Position(1, 7), new Knight(Color.WHITE));
        mapToAdd.put(new Position(8, 2), new Knight(Color.BLACK));
        mapToAdd.put(new Position(8, 7), new Knight(Color.BLACK));
        return mapToAdd;
    }
}
