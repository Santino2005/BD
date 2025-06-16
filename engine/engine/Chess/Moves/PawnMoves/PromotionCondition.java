package edu.austral.dissis.chess.engine.Chess.Moves.PawnMoves;

import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Colors.Color;
import edu.austral.dissis.chess.engine.Commons.Moves.SpecialMoves;
import edu.austral.dissis.chess.engine.Commons.Pieces.FactoryPieces;
import edu.austral.dissis.chess.engine.Commons.Pieces.Piece;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;
import edu.austral.dissis.chess.engine.Commons.Positions.Position;

import java.util.HashMap;
import java.util.Map;

public class PromotionCondition implements SpecialMoves {
    @Override
    public boolean isSpecialMove(Piece piece, MoveTo moveTo, Board board) {
        return (moveTo.getTo().row() == board.getRowMax()
                && board.getRowMax() == moveTo.getFrom().row() + 1
                && board.getPieceByPosition(moveTo.getFrom()).get().getColor().equals(Color.WHITE)
                || moveTo.getTo().row() == 1
                && 1 == moveTo.getFrom().row() - 1
                && board.getPieceByPosition(moveTo.getFrom()).get().getColor().equals(Color.BLACK));
    }

    @Override
    public Map<Position, Piece> execute(MoveTo moveTo, Piece piece, Board board) {
        Map<Position, Piece> newCells = new HashMap<>(board.getCells());
        newCells.remove(moveTo.getFrom());
        newCells.put(moveTo.getTo(), new FactoryPieces().createQueen("queen", piece.getColor(), piece.getId(), 'Q', true));
        return newCells;
    }
}
