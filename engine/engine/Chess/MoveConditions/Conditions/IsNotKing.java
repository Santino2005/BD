package edu.austral.dissis.chess.engine.Chess.MoveConditions.Conditions;

import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Moves.ValidConditions;
import edu.austral.dissis.chess.engine.Commons.Pieces.Piece;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;

import java.util.Optional;

public class IsNotKing implements ValidConditions {
    @Override
    public boolean valid(MoveTo moveTo, Board board) {
        Optional<Piece> piece = board.getPieceByPosition(moveTo.getTo());
        if(piece.isEmpty()){
            return true;
        }
        return piece.get().getPieceId() != 'K';
    }
}
