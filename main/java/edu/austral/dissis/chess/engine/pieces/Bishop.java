package edu.austral.dissis.chess.engine.pieces;

import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.MovesType;
import edu.austral.dissis.chess.engine.Position;

import java.util.*;

public record Bishop(Color color) implements Piece {

  @Override
  public MovesType validMove(Position initialPos, Position finalPos, Map<Position, Piece> cells) {
    if (Math.abs(finalPos.row() - initialPos.row())
            == Math.abs(finalPos.column() - initialPos.column())) {
      int rowWay = Integer.signum(finalPos.row() - initialPos.row());
      int columnWay = Integer.signum(finalPos.column() - initialPos.column());
      if(checkFreeWay(rowWay, columnWay, initialPos, finalPos, cells)){
        return new MovesType(this,"Valid", initialPos,finalPos,cells);
      }
    }
    return new MovesType(this, "invalid", initialPos, finalPos, cells);
  }

  private boolean checkFreeWay(
          int rowWay,
          int columnWay,
          Position initialPos,
          Position finalPos,
          Map<Position, Piece> cells) {
    int currentRow = initialPos.row() + rowWay;
    int currentCol = initialPos.column() + columnWay;

    while (currentRow != finalPos.row() || currentCol != finalPos.column()) {
      Position actualPosition = new Position(currentRow, currentCol);
      if (cells.containsKey(actualPosition)) {
        return false;
      }
      currentCol += columnWay;
      currentRow += rowWay;
    }
    return cells.get(finalPos) == null /*|| isKing(cells, finalPos) */|| capture(cells, finalPos);
  }

  /*private boolean isKing(Map<Position, Piece> cells, Position finalPos) {
    return !(cells.get(finalPos) instanceof King);
  }*/

  private boolean capture(Map<Position, Piece> cells, Position finalPos) {
    return cells.get(finalPos).color() != this.color;
  }

  /*@Override
  public List<Position> possibleMoves(Position initialPos, Map<Position, Piece> cells, Board board, Rules rules) {
    List<Position> possiblesMoves = new ArrayList<>();
    int currentRow = initialPos.row() + rowWay;
    int currentCol = initialPos.column() + columnWay;

    while (rules.validMove(initialPos, new Position(currentRow, currentCol),this, board, board.getColorToPlay())) {
      Position actualPosition = new Position(currentRow, currentCol);
      if (!cells.containsKey(actualPosition)) {
        currentCol += columnWay;
        currentRow += rowWay;
        possiblesMoves.add(actualPosition);
      }
    }
    return possiblesMoves;
  }*/

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Bishop bishop = (Bishop) obj;
    return this.color.equals(bishop.color);
  }

}
