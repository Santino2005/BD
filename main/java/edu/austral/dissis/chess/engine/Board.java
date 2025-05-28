package edu.austral.dissis.chess.engine;

import edu.austral.dissis.chess.engine.Moves.LastMove;
import edu.austral.dissis.chess.engine.PiecesMaked.MixedPieces;
import edu.austral.dissis.chess.engine.boards.BoardOrganization;
import edu.austral.dissis.chess.engine.rules.CheckRule;
import edu.austral.dissis.chess.engine.rules.Rules;
import java.util.*;

public class Board {
  private final Color colorToPlay;
  private final Map<Position, MixedPieces> cells;
  private final Rules game;
  private final LastMove lastMove;
  private final BoardOrganization originalCells;

  public Board(BoardOrganization board, Rules rules, LastMove lastMove) {
    this.colorToPlay = rules.starterPlayer();
    this.cells = Collections.unmodifiableMap(board.create());
    this.originalCells = board;
    this.game = rules;
    this.lastMove = lastMove;
  }

  public Board(
      Color color,
      Map<Position, MixedPieces> board,
      Rules rules,
      LastMove lastMove,
      BoardOrganization originalCells) {
    this.colorToPlay = color;
    this.cells = Collections.unmodifiableMap(board);
    this.game = rules;
    this.lastMove = lastMove;
    this.originalCells = originalCells;
  }

  public Map<Position, MixedPieces> getCells() {
    return this.cells;
  }

  public MixedPieces getPiece(Position position) {
    return cells.get(position);
  }

  public Color getColorToPlay() {
    return colorToPlay;
  }

  public Rules getRules() {
    return game;
  }

  public BoardOrganization getOriginalCells() {
    return originalCells;
  }

  public Board move(Position initialPos, Position finalPos, MixedPieces piece, User userToMove) {
    if (game.validMove(initialPos, finalPos, piece, this, userToMove.getColor(), true).valid()
        && !(new CheckRule(this).checkMate(colorToPlay))) {
      LastMove newLast = new LastMove(initialPos, finalPos, piece);
      Board newBoard = this;
      newBoard =
          piece.validMove(initialPos, finalPos, this).move(initialPos, finalPos, piece, newBoard);
      return new Board(
          this.game.afterMove(this, newLast),
          newBoard.getCells(),
          this.game,
          newLast,
          originalCells);
    }
    return new Board(this.colorToPlay, this.cells, this.game, this.lastMove, originalCells);
  }

  public Map<Position, List<Position>> getAllPossibleMoves(Color color) {
    Map<Position, List<Position>> allMoves = new HashMap<>();
    for (Map.Entry<Position, MixedPieces> move : cells.entrySet()) {
      Position initialPos = move.getKey();
      MixedPieces piece = move.getValue();
      if (piece.color() == color) {
        List<Position> moves = piece.possibleMoves(initialPos, this, game);
        allMoves.put(initialPos, moves);
      }
    }
    return allMoves;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Board board = (Board) o;
    return Objects.equals(cells, board.cells) && Objects.equals(colorToPlay, board.colorToPlay);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cells, colorToPlay);
  }
}
// Interfaz tablero, Game class, menor responsabilidad posible
