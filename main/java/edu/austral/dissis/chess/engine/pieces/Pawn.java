package edu.austral.dissis.chess.engine.pieces;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.MovesType;
import edu.austral.dissis.chess.engine.Position;
import edu.austral.dissis.chess.engine.rules.Rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Pawn implements Piece {

  private final Color color;
  private final int initialRow;

  public Pawn(Color color, int initialRow) {
    this.color = color;
    this.initialRow = initialRow;
  }

  @Override
  public MovesType validMove(Position initialPos, Position finalPos, Map<Position, Piece> cells) {
    if(canMove(initialPos, finalPos, cells)){
      return new MovesType(this, "Valid", initialPos, finalPos, cells);
    }
    return new MovesType(this, "invalid", initialPos, finalPos, cells);
  }
  private boolean canMove(
          Position initialPos, Position finalPos, Map<Position, Piece> cells) {
    int rowWay = finalPos.row() - initialPos.row();
    int colWay = Math.abs(finalPos.column() - initialPos.column());

    int whiteOrBlackPawn = (Color.WHITE == this.color) ? 1 : -1;

    if (colWay == 0) {
      if (initialPos.row() == initialRow
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
  private boolean isKing(Map<Position, Piece> cells, Position finalPos){
    return !(cells.get(finalPos) instanceof King);
  }
  private boolean capture(Map<Position, Piece> cells, Position finalPos){
    return cells.get(finalPos) != null && cells.get(finalPos).color() != this.color;
  }
  @Override
  public Color color() {
    return this.color;
  }

  @Override
  public List<Position> possibleMoves(Position initialPos, Map<Position, Piece> cells, Board board, Rules rules) {
    List<Position> allMoves = new ArrayList<>();
    int dir = (Color.WHITE == this.color) ? 1 : -1;
    if(rules.validMove(initialPos,new Position(initialPos.row() + dir, initialPos.column()), this, board,board.getColorToPlay()).valid()){
      allMoves.add(new Position(initialPos.row() + dir, initialPos.column()));
    }
    if(rules.validMove(initialPos,new Position(initialPos.row() + 2 * dir, initialPos.column()), this, board,board.getColorToPlay()).valid() && initialPos.row() == initialRow)
      allMoves.add(new Position(initialPos.row() + 2 * dir, initialPos.column()));
    Position digR = new Position(initialPos.row() + dir, initialPos.column() + 1);
    Position digL = new Position(initialPos.row() + dir, initialPos.column() + -1);
    if(rules.validPosition(digR) && (cells.get(digR) == null || cells.get(digR).color() != this.color )){
      allMoves.add(digR);
    }if(rules.validPosition(digL) && (cells.get(digL) == null || cells.get(digL).color() != this.color )){
      allMoves.add(digL);
    }
    return allMoves;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Pawn pawn = (Pawn) obj;
    return this.color.equals(pawn.color);
  }
  @Override
  public int hashCode() {
    return Objects.hash(color);
  }
}
