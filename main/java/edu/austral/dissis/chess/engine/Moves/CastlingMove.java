package edu.austral.dissis.chess.engine.Moves;

import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.PiecesMaked.King;
import edu.austral.dissis.chess.engine.PiecesMaked.MixedPieces;
import edu.austral.dissis.chess.engine.PiecesMaked.Rook;
import edu.austral.dissis.chess.engine.Position;
import edu.austral.dissis.chess.engine.Result;
import java.util.HashMap;
import java.util.Map;

public class CastlingMove implements validMoves {

  @Override
  public Result applyMove(
      Position initialPos, Position finalPos, MixedPieces piece, Map<Position, MixedPieces> cells) {
    Map<Position, MixedPieces> newCells = new HashMap<>(cells);

    int kingRow = initialPos.row();
    Color color = piece.color();
    King king = new King(piece.name(), color);
    Rook rook = new Rook(piece.name(), color);

    if (finalPos.column() > initialPos.column()) {
      newCells.remove(initialPos);
      newCells.remove(finalPos);
      newCells.put(new Position(kingRow, finalPos.column() - 1), king.moved());
      newCells.put(new Position(kingRow, finalPos.column() - 2), rook.move());
    } else {
      newCells.remove(initialPos);
      newCells.remove(finalPos);
      newCells.put(new Position(kingRow, initialPos.column() - 2), king.moved());
      newCells.put(new Position(kingRow, initialPos.column() + 1), rook.move());
    }
    return new Result(new LastMove(initialPos, finalPos, piece), newCells);
  }
}
