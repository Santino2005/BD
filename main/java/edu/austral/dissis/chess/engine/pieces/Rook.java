package edu.austral.dissis.chess.engine.pieces;

import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.MovesType;
import edu.austral.dissis.chess.engine.Position;
import java.util.Map;
import java.util.Objects;

public record Rook(Color color, boolean moved) implements Piece {

  @Override
  public MovesType validMove(Position initialPos, Position finalPos, Map<Position, Piece> cells) {
    if (finalPos.column() == initialPos.column()
            || finalPos.row() == initialPos.row()) {

      int columnWay = initialPos.column();
      int rowWay = initialPos.row();

      if (rowWay == finalPos.row()) {
        if(checkRow(finalPos, cells, columnWay, rowWay)){
          return new MovesType(this, "Valid", initialPos,finalPos,cells);
        }
      } else if (columnWay == finalPos.column()) {
        if(checkCol(finalPos, cells, columnWay, rowWay)){
          return new MovesType(this, "valid", initialPos, finalPos,cells);
        }
      }
      return new MovesType(this,"invalid", initialPos,finalPos,cells);
    }
    return new MovesType(this,"invalid", initialPos,finalPos,cells);
  }

  private boolean checkRow(
          Position finalPos, Map<Position, Piece> cells, int columnWay, int rowWay) {
    int step = finalPos.column() > columnWay ? 1 : -1;
    for (int col = columnWay + step; col != finalPos.column(); col += step) {
      if (cells.containsKey(new Position(rowWay, col))) {
        return false;
      }
    }
    return cells.get(finalPos) == null /*|| isKing(cells, finalPos) */|| capture(cells, finalPos);
  }

  private boolean checkCol(
          Position finalPos, Map<Position, Piece> cells, int columnWay, int rowWay) {
    int step = finalPos.row() > rowWay ? 1 : -1;
    for (int row = rowWay + step; row != finalPos.row(); row += step) {
      if (cells.containsKey(new Position(row, columnWay))) {
        return false;
      }
    }
    return cells.get(finalPos) == null /*|| isKing(cells, finalPos) */|| capture(cells, finalPos);
  }

  /*private boolean isKing(Map<Position, Piece> cells, Position finalPos) {
    return !(cells.get(finalPos) instanceof King);
  }*/

  private boolean capture(Map<Position, Piece> cells, Position finalPos) {
    return cells.get(finalPos).color() != this.color;
  }

  public Rook move(){
    return new Rook(color, true);
  }
  public boolean hasMoved(){
    return moved;
  }

  // Para los test
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Rook rook = (Rook) obj;
    return this.color.equals(rook.color);
  }

}
