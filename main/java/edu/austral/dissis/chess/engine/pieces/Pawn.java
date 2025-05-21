package edu.austral.dissis.chess.engine.pieces;

import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.MovesType;
import edu.austral.dissis.chess.engine.Position;
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
      return /*|| isKing(cells,finalPos) ||*/ capture(cells,finalPos);
    }
    return false;
  }
  /*private boolean isKing(Map<Position, Piece> cells, Position finalPos){
    return !(cells.get(finalPos) instanceof King);
  }*/
  private boolean capture(Map<Position, Piece> cells, Position finalPos){
    return cells.get(finalPos) != null && cells.get(finalPos).color() != this.color;
  }

  @Override
  public Color color() {
    return this.color;
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
