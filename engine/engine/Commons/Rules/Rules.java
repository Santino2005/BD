package edu.austral.dissis.chess.engine.Commons.Rules;

import edu.austral.dissis.chess.engine.Commons.Board.Board;
import edu.austral.dissis.chess.engine.Commons.Colors.Color;
import edu.austral.dissis.chess.engine.Commons.Moves.SpecialMoves;
import edu.austral.dissis.chess.engine.Commons.Pieces.Piece;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;
import edu.austral.dissis.chess.engine.Commons.Turns.TurnsManager;

public interface Rules {

    public boolean valid(MoveTo moveTo, Piece piece, TurnsManager turnsManager, Board board);
    public TurnsManager keepsPlaying();
    public Color starterPlayer();
    public TurnsManager moved();
    public Color getActualPlayer();
    public Color getOpponentColor();
    public SpecialMoves isSpecialMove();

}
