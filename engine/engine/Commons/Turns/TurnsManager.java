package edu.austral.dissis.chess.engine.Commons.Turns;

import edu.austral.dissis.chess.engine.Commons.Colors.Color;

public class TurnsManager {
    private final Color firstPlayer;
    private final Color opponent;
    public TurnsManager(Color color1, Color color2){
        this.firstPlayer = color1;
        this.opponent = color2;
    }
    public TurnsManager PlayerMoved(){
        return new TurnsManager(opponent, firstPlayer);
    }
    public TurnsManager PlayerKeepsPlaying(){
        return new TurnsManager(firstPlayer, opponent);
    }
    public Color getFirstPlayer(){
        return firstPlayer;
    }
    public Color getOpponent(){
        return opponent;
    }
}
