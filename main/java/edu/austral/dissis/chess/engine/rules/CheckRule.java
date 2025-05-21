package edu.austral.dissis.chess.engine.rules;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.Position;
import edu.austral.dissis.chess.engine.pieces.King;
import edu.austral.dissis.chess.engine.pieces.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class CheckRule {
    private final Board board;

    public CheckRule(Board board){
        this.board = board;
    }
    public boolean isCheck(Color color, Position initialPos, Board board){
        Map<Position, List<Position>> newBoard = board.getAllPossibleMoves(color);
        return newBoard.containsKey(initialPos);
    }
    public boolean checkMate(Color color){
        Position position = null;
        for (Map.Entry<Position, Piece> entry : board.getCells().entrySet()) {
            if (entry.getValue() instanceof King && entry.getValue().color() == color) {
                position = entry.getKey();
                break;
            }
        }
        if(!isCheck(color, position, board)){
            return false;
        }
        Map<Position, List<Position>> allMoves = board.getAllPossibleMoves(color);
        Color opposite = (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
        for (Map.Entry<Position, List<Position>> initial : allMoves.entrySet()) {
            for (Position to : initial.getValue()) {
                Map<Position, Piece> cells = new HashMap<>(board.getCells());
                cells.remove(initial.getKey());
                cells.put(to, board.getCells().get(initial.getKey()));
                Board simulatedBoard = new Board(opposite, cells, board.getRules(), null);
                Position newKingPos = (board.getCells().get(initial.getKey()) instanceof King) ? to : position;

                if (!isCheck(color, newKingPos, simulatedBoard)) {
                    return false;
                }
            }
        }
        return true;
    }
}
