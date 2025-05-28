package edu.austral.dissis.chess.engine;

import edu.austral.dissis.chess.engine.PiecesMaked.MixedPieces;
import edu.austral.dissis.chess.engine.boards.BoardOrganization;
import edu.austral.dissis.chess.engine.rules.CheckRule;
import edu.austral.dissis.chess.engine.rules.Rules;

public class Game {
  private final Board currentBoard;
  private final boolean finished;
  private final Color winner;

  public Game(BoardOrganization organization, Rules rules) {
    this.currentBoard = new Board(organization, rules, null);
    this.finished = false;
    this.winner = null;
  }

  private Game(Board currentBoard, boolean finished, Color winner) {
    this.currentBoard = currentBoard;
    this.finished = finished;
    this.winner = winner;
  }

  public Board getBoard() {
    return currentBoard;
  }

  public Color getWinner() {
    return winner;
  }

  public Game move(Position initialPos, Position finalPos, MixedPieces piece, User user) {
    if (finished) {
      return this;
    }
    Board newBoard = currentBoard.move(initialPos, finalPos, piece, user);
    if (newBoard.equals(currentBoard)) {
      return this;
    }
    Color newWinner = null;
    if (new CheckRule(newBoard).checkMate(newBoard.getColorToPlay())) {
      newWinner = user.getColor();
    }
    return new Game(
        newBoard, new CheckRule(newBoard).checkMate(newBoard.getColorToPlay()), newWinner);
  }
}
