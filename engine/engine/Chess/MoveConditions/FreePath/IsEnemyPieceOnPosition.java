package edu.austral.dissis.chess.engine.Chess.MoveConditions.FreePath;

import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Moves.ValidConditions;
import edu.austral.dissis.chess.engine.Commons.Pieces.Piece;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;

import java.util.Optional;

public class IsEnemyPieceOnPosition implements ValidConditions {
    @Override
    public boolean valid(MoveTo moveTo, Board board) {
        Optional<Piece> fromPiece = board.getPieceByPosition(moveTo.getFrom());
        Optional<Piece> toPiece = board.getPieceByPosition(moveTo.getTo());
        if(toPiece.isEmpty()){
            return false;
        }
        return fromPiece.isPresent()
                && fromPiece.get().getColor() != toPiece.get().getColor();
    }
}
