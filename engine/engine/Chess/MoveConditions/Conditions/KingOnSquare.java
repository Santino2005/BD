package edu.austral.dissis.chess.engine.Chess.MoveConditions.Conditions;

import edu.austral.dissis.chess.engine.Chess.MoveConditions.FreePath.IsEnemyPieceOnPosition;
import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Pieces.Piece;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;
import edu.austral.dissis.chess.engine.Commons.Positions.Position;

import java.util.Map;

public class KingOnSquare {

    public Position valid(Board board, Position kingPos) {
        for(Map.Entry<Position, Piece> kingSearch : board.getCells().entrySet()){
            if(kingSearch.getValue().getPieceId().equals('K') && new IsEnemyPieceOnPosition().valid(new MoveTo(kingPos, kingSearch.getKey()), board) ){
                return kingSearch.getKey();
            }
        }
        return null;
    }
}
