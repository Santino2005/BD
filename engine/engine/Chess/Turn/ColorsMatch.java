package edu.austral.dissis.chess.engine.Chess.Turn;

import edu.austral.dissis.chess.engine.Commons.Colors.Color;
import edu.austral.dissis.chess.engine.Commons.Turns.TurnsManager;

public class ColorsMatch {
    public TurnsManager chessColors(){
        return new TurnsManager(Color.WHITE,Color.BLACK);
    }

}
