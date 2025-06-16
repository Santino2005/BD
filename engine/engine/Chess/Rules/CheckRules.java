package edu.austral.dissis.chess.engine.Chess.Rules;

import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Colors.Color;
import edu.austral.dissis.chess.engine.Commons.Moves.Valuate;
import edu.austral.dissis.chess.engine.Commons.Pieces.Piece;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;
import edu.austral.dissis.chess.engine.Commons.Positions.Position;
import edu.austral.dissis.chess.engine.Commons.Rules.SpecialRules;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CheckRules implements SpecialRules {

    @Override
    public boolean checkSpecialRules(MoveTo moveTo, Board board) {
        Optional<Piece> maybePiece = board.getPieceByPosition(moveTo.getFrom());
        if (maybePiece.isEmpty()) return true;

        Piece piece = maybePiece.get();
        Color kingColor = piece.getColor();

        return !isCheck(board, kingColor);
    }

    public boolean isCheck(Board board, Color kingColor) {
        Position kingPos = findKing(board, kingColor);
        List<Position> allPos = new ArrayList<>();
        if (kingPos == null) return false;

        for (Map.Entry<Position, Piece> entry : board.getCells().entrySet()) {
            Piece attacker = entry.getValue();
            if (!attacker.getColor().equals(kingColor == Color.WHITE ? Color.BLACK : Color.WHITE)){
                continue;
            }

            MoveTo move = new MoveTo(entry.getKey(), kingPos);
            for (Valuate moveType : attacker.getPiecesMoves()) {
                allPos.addAll(moveType.possiblePosition(move,board));
            }
        }
        return allPos.contains(kingPos);
    }

    private Position findKing(Board board, Color color) {
        for (Map.Entry<Position, Piece> entry : board.getCells().entrySet()) {
            Piece piece = entry.getValue();
            if (piece.getPieceId() == 'K' && piece.getColor().equals(color)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
