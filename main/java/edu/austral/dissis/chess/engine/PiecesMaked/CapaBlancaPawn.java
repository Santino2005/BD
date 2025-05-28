/*package edu.austral.dissis.chess.engine.PiecesMaked;

import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.PiecesMoves.Piece;
import edu.austral.dissis.chess.engine.Position;

import java.util.Map;

public class CapaBlancaPawn extends Pawn {
    public CapaBlancaPawn(Color color, int initialRow) {
        super(color, initialRow);
    }

    public boolean canMove(Position initialPos, Position finalPos, Map<Position, Piece> cells){
        int rowWay = finalPos.row() - initialPos.row();
        int colWay = Math.abs(finalPos.column() - initialPos.column());

        int whiteOrBlackPawn = (Color.WHITE == this.color()) ? 1 : -1;

        if (colWay == 0) {
            if (initialPos.row() == this.getInitialRow()
                    && rowWay == 2 * whiteOrBlackPawn
                    && cells.get(finalPos) == null
                    && cells.get(new Position(initialPos.row() + whiteOrBlackPawn, initialPos.column()))
                    == null) {
                return true;
            } else return rowWay == whiteOrBlackPawn && cells.get(finalPos) == null;
        }
        if (colWay == 1 && rowWay == whiteOrBlackPawn) {
            return isKing(cells,finalPos) || capture(cells,finalPos);
        }
        return false;
    }
}
*/
