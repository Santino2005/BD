package edu.austral.dissis.chess.engine.rules;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Position;
import edu.austral.dissis.chess.engine.pieces.Piece;

import java.util.List;
import java.util.Map;
public class CheckRule {
    private final boolean check;
    private final Board board;

    public CheckRule(boolean check, Board board){
        this.check = check;
        this.board = board;
    }
    public boolean isCheck(Piece piece, Position initialPos){
        Map<Position, List<Position>> newBoard = board.getAllPossibleMoves(piece.color());
        return newBoard.containsKey(initialPos);
    }
}
