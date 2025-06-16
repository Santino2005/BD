package edu.austral.dissis.chess.engine.Commons.GameAndAdapters;

import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Pieces.Piece;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;
import edu.austral.dissis.chess.engine.Commons.Colors.Color;
import edu.austral.dissis.chess.engine.Commons.Turns.TurnsManager;
import edu.austral.dissis.chess.engine.Commons.WinCondition.WinConditions;

import java.util.Arrays;
import java.util.List;

public class Game {

    private final Board currentBoard;
    private final boolean finished;
    private final Color winner;
    private final List<WinConditions> winConditions;
    private final TurnsManager turnToPlay;
    public Game(Board board, boolean finished, Color winner, WinConditions... winConditions){
        this.currentBoard = board;
        this.finished = finished;
        this.winner = winner;
        this.turnToPlay = null;
        this.winConditions = Arrays.asList(winConditions);
    }
    public Game(Board board, TurnsManager turn){
        this.currentBoard = board;
        this.winner = null;
        this.finished = false;
        this.turnToPlay = turn;
        this.winConditions = null;
    }

    public Board getCurrentBoard(){
        return currentBoard;
    }
    public TurnsManager getTurnToPlay(){
        return turnToPlay;
    }
    public Color getWinner(){
        return winner;
    }
    public boolean isFinished(){
        return finished;
    }

    public Game move(MoveTo moveTo, Piece piece, TurnsManager color){
        if (finished) {
            return this;
        }

        Game newGame = currentBoard.move(moveTo, piece, turnToPlay);

        if (newGame.getCurrentBoard().equals(currentBoard)) {
            return this;
        }

        Color newWinner = null;
        if (winConditions != null) {
            for (WinConditions condition : winConditions) {
                if (condition.winner(color.getFirstPlayer())) {
                    newWinner = color.getFirstPlayer();
                    break;
                }
            }
        }
        if (newWinner != null) {
            return new Game(newGame.getCurrentBoard(), true, newWinner, winConditions.toArray(new WinConditions[0]));
        }
        return new Game(newGame.getCurrentBoard(), turnToPlay.PlayerMoved());
    }
}

