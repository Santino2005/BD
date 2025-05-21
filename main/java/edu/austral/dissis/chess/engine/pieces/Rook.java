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

public record Rook(Color color, boolean moved) implements Piece {

  @Override
  public MovesType validMove(Position initialPos, Position finalPos, Map<Position, Piece> cells) {
    if (finalPos.column() == initialPos.column()
            || finalPos.row() == initialPos.row()) {

      int columnWay = initialPos.column();
      int rowWay = initialPos.row();

      if (rowWay == finalPos.row() && columnWay != finalPos.column()) {
        if(checkRow(finalPos, cells, columnWay, rowWay)){
          return new MovesType(this, "Valid", initialPos,finalPos,cells);
        }
      } else if (columnWay == finalPos.column() && rowWay != finalPos.row()) {
        if(checkCol(finalPos, cells, columnWay, rowWay)){
          return new MovesType(this, "valid", initialPos, finalPos,cells);
        }
      }
      return new MovesType(this,"invalid", initialPos,finalPos,cells);
    }
    return new MovesType(this,"invalid", initialPos,finalPos,cells);
  }

  @Override
  public List<Position> possibleMoves(Position initialPos, Map<Position, Piece> cells, Board board, Rules rules) {
    List<Position> allMoves = new ArrayList<>();
    allMoves.addAll(getMoves(initialPos, cells, 1,0, board, rules, allMoves));
    allMoves.addAll(getMoves(initialPos, cells, -1,0, board, rules, allMoves));
    allMoves.addAll(getMoves(initialPos, cells, 0,1, board, rules, allMoves));
    allMoves.addAll(getMoves(initialPos, cells, 0,-1, board, rules, allMoves));
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

  private boolean checkRow(Position finalPos, Map<Position, Piece> cells, int columnWay, int rowWay) {
    int step = finalPos.column() > columnWay ? 1 : -1;
    for (int col = columnWay + step; col != finalPos.column(); col += step) {
      if (cells.containsKey(new Position(rowWay, col))) {
        return false;
      }
    }
    return cells.get(finalPos) == null || isKing(cells, finalPos) || capture(cells, finalPos);
  }

  private boolean checkCol(
          Position finalPos, Map<Position, Piece> cells, int columnWay, int rowWay) {
    int step = finalPos.row() > rowWay ? 1 : -1;
    for (int row = rowWay + step; row != finalPos.row(); row += step) {
      if (cells.containsKey(new Position(row, columnWay))) {
        return false;
      }
    }
    return cells.get(finalPos) == null || isKing(cells, finalPos) || capture(cells, finalPos);
  }

  private boolean isKing(Map<Position, Piece> cells, Position finalPos) {
    return !(cells.get(finalPos) instanceof King);
  }

  private boolean capture(Map<Position, Piece> cells, Position finalPos) {
    return cells.get(finalPos).color() != this.color;
  }

  public Rook move(){
    return new Rook(color, true);
  }
  public boolean hasMoved(){
    return moved;
  }
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
