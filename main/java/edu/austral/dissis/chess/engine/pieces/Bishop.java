package edu.austral.dissis.chess.engine.pieces;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.MovesType;
import edu.austral.dissis.chess.engine.Position;
import edu.austral.dissis.chess.engine.rules.Rules;

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
    return cells.get(finalPos) == null || isKing(cells, finalPos) || capture(cells, finalPos);
  }

  private boolean isKing(Map<Position, Piece> cells, Position finalPos) {
    return !(cells.get(finalPos) instanceof King);
  }

  private boolean capture(Map<Position, Piece> cells, Position finalPos) {
    return cells.get(finalPos).color() != this.color;
  }

  @Override
  public List<Position> possibleMoves(Position initialPos, Map<Position, Piece> cells, Board board, Rules rules) {
    List<Position> allMoves = new ArrayList<>();
    allMoves.addAll(getMoves(initialPos, cells, 1,1, board, rules, allMoves));
    allMoves.addAll(getMoves(initialPos, cells, -1,1, board, rules, allMoves));
    allMoves.addAll(getMoves(initialPos, cells, 1,-1, board, rules, allMoves));
    allMoves.addAll(getMoves(initialPos, cells, -1,-1, board, rules, allMoves));
    return allMoves;
  }

  private List<Position> getMoves(Position initialPos, Map<Position,Piece> cells, int row, int col, Board board, Rules rules, List<Position> list){
    int currentRow = initialPos.row() + row;
    int currentCol = initialPos.column() + col;
    while (rules.validMove(initialPos, new Position(currentRow, currentCol),this, board, board.getColorToPlay()).valid()) {
      Position actualPosition = new Position(currentRow, currentCol);
      if (!cells.containsKey(actualPosition)) {
        currentCol += col;
        currentRow += row;
        list.add(actualPosition);
      }
    }
    return list;
  }

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
