package edu.austral.dissis.chess.engine.Moves;

import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.Position;
import edu.austral.dissis.chess.engine.Result;
import edu.austral.dissis.chess.engine.pieces.King;
import edu.austral.dissis.chess.engine.pieces.Piece;
import edu.austral.dissis.chess.engine.pieces.Rook;

import java.util.HashMap;
import java.util.Map;

public class Castling implements SpecialMoves{

    @Override
    public Result applyMove(Position initialPos, Position finalPos, Piece piece, Map<Position, Piece> cells) {
        Map<Position, Piece> newCells = new HashMap<>(cells);

        int kingRow = initialPos.row();
        Color color = piece.color();

        if (finalPos.column() > initialPos.column()) {
            newCells.remove(initialPos);
            newCells.remove(finalPos);
            King king = new King(color);
            newCells.put(new Position(kingRow, initialPos.column() + 2), king.moved());
            newCells.put(new Position(kingRow, initialPos.column() + 1), new Rook(color, true));
        }
        else {
            newCells.remove(initialPos);
            newCells.remove(finalPos);
            King king = new King(color);
            newCells.put(new Position(kingRow, initialPos.column() - 2), king.moved());
            newCells.put(new Position(kingRow, initialPos.column() - 1), new Rook(color, true));
        }

        return new Result(new LastMove(initialPos, finalPos, piece), newCells);
    }
}
