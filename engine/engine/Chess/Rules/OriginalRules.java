package edu.austral.dissis.chess.engine.Chess.Rules;

import edu.austral.dissis.chess.engine.Chess.MoveConditions.PieceValidMoves.PieceMovesValidator;
import edu.austral.dissis.chess.engine.Chess.Turn.ColorsMatch;
import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Colors.Color;
import edu.austral.dissis.chess.engine.Commons.Moves.SpecialMoves;
import edu.austral.dissis.chess.engine.Commons.Moves.ValidConditions;
import edu.austral.dissis.chess.engine.Commons.Moves.Valuate;
import edu.austral.dissis.chess.engine.Commons.Pieces.Piece;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;
import edu.austral.dissis.chess.engine.Commons.Positions.Position;
import edu.austral.dissis.chess.engine.Commons.Rules.Rules;
import edu.austral.dissis.chess.engine.Commons.Rules.SpecialRules;
import edu.austral.dissis.chess.engine.Commons.Turns.TurnsManager;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OriginalRules implements Rules {

    private final TurnsManager turnsManager;
    private final List<SpecialRules> specialRules;
    public OriginalRules(TurnsManager turnsManager, SpecialRules... specialRules){
        this.turnsManager = turnsManager;
        this.specialRules = Arrays.asList(specialRules);
    }

    @Override
    public boolean valid(MoveTo moveTo, Piece piece, TurnsManager color, Board board) {
        for(Valuate possiblesMoves : piece.getPiecesMoves()){
            if(possiblesMoves.valuate(moveTo,board)){
                for(SpecialRules specialRules1 : specialRules){
                    if(!specialRules1.checkSpecialRules(moveTo,board)){
                        return false;
                    }
                }
                return board.validPosition(moveTo)
                        && color.getFirstPlayer() == piece.getColor();
            }
        }
        return false;
    }
    @Override
    public TurnsManager keepsPlaying() {
        return turnsManager.PlayerKeepsPlaying();
    }

    @Override
    public Color starterPlayer() {
        return new ColorsMatch().chessColors().getFirstPlayer();
    }

    @Override
    public TurnsManager moved() {
        return turnsManager.PlayerMoved();
    }

    @Override
    public Color getActualPlayer() {
        return turnsManager.getFirstPlayer();
    }
    @Override
    public Color getOpponentColor() {
        return turnsManager.getOpponent();
    }

    @Override
    public SpecialMoves isSpecialMove() {
        return null;
    }


}
