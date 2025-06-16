package edu.austral.dissis.chess.engine.Chess.MoveConditions.Conditions;

import edu.austral.dissis.chess.engine.Chess.Rules.CheckRules;
import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Colors.Color;
import edu.austral.dissis.chess.engine.Commons.Pieces.Piece;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;
import edu.austral.dissis.chess.engine.Commons.Positions.Position;
import edu.austral.dissis.chess.engine.Commons.Rules.SpecialRules;

import java.util.*;

public class KingCantBeInCheckAfterMove implements SpecialRules {

    private final List<SpecialRules> specialRules;
    public KingCantBeInCheckAfterMove(SpecialRules... specialRules){
        this.specialRules = Arrays.asList(specialRules);
    }

    @Override
    public boolean checkSpecialRules(MoveTo moveTo, Board board) {
        Optional<Piece> pieceToSimulate = board.getPieceByPosition(moveTo.getFrom());
        if (pieceToSimulate.isEmpty()){
            return false;
        }

        Piece piece = pieceToSimulate.get();
        Color color = piece.getColor();

        Map<Position, Piece> simulatedCells = new HashMap<>(board.getCells());
        simulatedCells.remove(moveTo.getFrom());
        simulatedCells.remove(moveTo.getTo());
        simulatedCells.put(moveTo.getTo(), piece.move());

        Position kingPos = null;
        for (Map.Entry<Position, Piece> entry : simulatedCells.entrySet()) {
            Piece p = entry.getValue();
            if (p.getPieceId() == 'K' && p.getColor().equals(color)) {
                kingPos = entry.getKey();
                break;
            }
        }

        if (kingPos == null){
            return false;
        }

        Board simulatedBoard = new Board(board.getRowMax(), board.getColMax(), simulatedCells, board.getRules());

        for(SpecialRules special : specialRules) {
            if(!special.checkSpecialRules(new MoveTo(kingPos, kingPos), simulatedBoard)){
                return false;
            }
        }

        return true;
    }
}
