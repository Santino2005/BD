package edu.austral.dissis.chess.engine.Commons.Board;

import edu.austral.dissis.chess.engine.Commons.Pieces.FactoryPieces;
import edu.austral.dissis.chess.engine.Commons.Pieces.Piece;
import edu.austral.dissis.chess.engine.Commons.Positions.Position;

import java.util.Map;

public interface BoardCreation {

    public Map<Position, Piece> create(FactoryPieces factoryPieces);
}
