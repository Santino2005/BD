package edu.austral.dissis.chess.engine.Chess.Moves.KingMoves;

import edu.austral.dissis.chess.engine.Chess.MoveConditions.Conditions.IsNotKing;
import edu.austral.dissis.chess.engine.Chess.MoveConditions.FreePath.FreeColumnPath;
import edu.austral.dissis.chess.engine.Chess.MoveConditions.Conditions.IsFirstMove;
import edu.austral.dissis.chess.engine.Chess.MoveConditions.FreePath.FreeRowPath;
import edu.austral.dissis.chess.engine.Chess.MoveConditions.FreePath.IsPieceNotOnRoad;
import edu.austral.dissis.chess.engine.Chess.MoveConditions.FreePath.IsPieceOnTheRoad;
import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Moves.SpecialMoves;
import edu.austral.dissis.chess.engine.Commons.Pieces.Piece;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;
import edu.austral.dissis.chess.engine.Commons.Positions.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Castling implements SpecialMoves {
    @Override
    public boolean isSpecialMove(Piece piece, MoveTo moveTo, Board board) {
        if (!new IsNotKing().valid(moveTo,board)){
            return false;
        }
        int rowDiff = Math.abs(moveTo.getTo().row() - moveTo.getFrom().row());
        int colDiff = Math.abs(moveTo.getTo().column() - moveTo.getFrom().column());
        if (rowDiff != 0 || colDiff != 2){
            return false;
        }
        if (!(new IsFirstMove().valid(moveTo, board))){
            return false;
        }
        int rookColumn = moveTo.getTo().column() > moveTo.getFrom().column() ? 8 : 1;
        Position rookPos = new Position(moveTo.getFrom().row(), rookColumn);
        MoveTo rookMove = new MoveTo(rookPos, rookPos);
        if (!new IsFirstMove().valid(rookMove, board)){
            return false;
        }
        if (!new FreeRowPath(new IsPieceOnTheRoad()).valid(new MoveTo(moveTo.getFrom(), rookPos), board)){
            return false;
        }
        return true;
    }

    @Override
    public Map<Position, Piece> execute(MoveTo moveTo, Piece piece, Board board) {
        Map<Position, Piece> newCells = new HashMap<>(board.getCells());

        int colDiff = moveTo.getTo().column() - moveTo.getFrom().column();
        Optional<Piece> king = board.getPieceByPosition(moveTo.getFrom());
        newCells.remove(moveTo.getFrom());
        int rookCol = colDiff > 0 ? 8 : 1;
        Position rookPos = new Position(moveTo.getFrom().row(), rookCol);
        Optional<Piece> rook = board.getPieceByPosition(rookPos);
        Position newRookPos = colDiff > 0 ?
                new Position(moveTo.getFrom().row(), moveTo.getTo().column() - 1)
                : new Position(moveTo.getFrom().row(), moveTo.getTo().column() + 1);
        newCells.remove(rookPos);
        newCells.put(moveTo.getTo(), king.get().move());
        newCells.put(newRookPos, rook.get().move());
        return newCells;
    }
}
