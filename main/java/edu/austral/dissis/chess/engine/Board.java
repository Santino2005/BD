package edu.austral.dissis.chess.engine;

import edu.austral.dissis.chess.engine.boards.BoardOrganization;
import edu.austral.dissis.chess.engine.Moves.LastMove;
import edu.austral.dissis.chess.engine.pieces.King;
import edu.austral.dissis.chess.engine.pieces.Piece;
import edu.austral.dissis.chess.engine.rules.CheckRule;
import edu.austral.dissis.chess.engine.rules.Rules;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
  private final Color colorToPlay;
  private final Map<Position, Piece> cells;
  private final Rules game;
  private final LastMove lastMove;

  public Board(Color color, Map<Position, Piece> board, Rules rules, LastMove lastMove) {
    this.colorToPlay = color;
    this.cells = Collections.unmodifiableMap(board);
    this.game = rules;
    this.lastMove = lastMove;
  }

  public Board(BoardOrganization board, Rules rules, LastMove lastMove) {
    this.colorToPlay = rules.starterPlayer();
    this.cells = Collections.unmodifiableMap(board.create());
    this.game = rules;
    this.lastMove = lastMove;
  }

  public Map<Position, Piece> getCells() {
    return this.cells;
  }

  public Piece getPiece(Position position){
      return cells.get(position);
  }

  public Color getColorToPlay() {
    return colorToPlay;
  }

  public Rules getRules() {
    return game;
  }

  public Board move(Position initialPos, Position finalPos, Piece piece, User userToMove) {
    if (game.validMove(initialPos, finalPos, piece, this, userToMove.getColor()).valid()) {
      LastMove newLast = new LastMove(initialPos,finalPos,piece);
      Map<Position, Piece> newBoard = new HashMap<>(cells);
      newBoard = piece.validMove(initialPos,finalPos,newBoard).move(initialPos,finalPos,piece,newBoard);
      return new Board(
          this.colorToPlay == Color.WHITE ? Color.BLACK : Color.WHITE, newBoard, this.game, newLast);

    }
    return new Board(this.colorToPlay, this.cells, this.game, this.lastMove);
  }

  public Map<Position, List<Position>> getAllPossibleMoves(Color color){
    Map<Position, List<Position>> allMoves = new HashMap<>();
    for (Map.Entry<Position, Piece> move : cells.entrySet()) {
      Position initialPos = move.getKey();
      Piece piece = move.getValue();
      if (piece.color() == color) {
        List<Position> moves = piece.possibleMoves(initialPos, cells, this, game);
        allMoves.put(initialPos, moves);
      }
    }
    return allMoves;
  }
}
//Interfaz tablero, Game class, menor responsabilidad posible, pieza reutilizable