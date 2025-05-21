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

public class Knight implements Piece {

  private final Color color;

  public Knight(Color color) {
    this.color = color;
  }

  @Override
  public MovesType validMove(Position initialPos, Position finalPos, Map<Position, Piece> cells) {
    int rowWay = Math.abs(finalPos.row() - initialPos.row());
    int colWay = Math.abs(finalPos.column() - initialPos.column());
    if (!((rowWay == 2 && colWay == 1) || (rowWay == 1 && colWay == 2))) {
      return new MovesType(this, "invalid",initialPos,finalPos,cells);
    }else if(cells.get(finalPos) == null || isKing(cells,finalPos) || capture(cells,finalPos)){
      return new MovesType(this, "Valid", initialPos, finalPos, cells);
    }return new MovesType(this, "invalid", initialPos, finalPos, cells);
  }

  private boolean isKing(Map<Position, Piece> cells, Position finalPos){
    return !(cells.get(finalPos) instanceof King);
  }
  private boolean capture(Map<Position, Piece> cells, Position finalPos){
    return cells.get(finalPos).color() != this.color;
  }
  @Override
  public Color color() {
    return color;
  }

  @Override
  public List<Position> possibleMoves(Position initialPos, Map<Position, Piece> cells, Board board, Rules rules) {
    List<Position> allMoves = new ArrayList<>();
    int row = initialPos.row();
    int col = initialPos.column();
    List<Position> positions  = List.of(new Position(row+2,col+1), new Position(row+2, col -1),
            new Position(row-2, col + 1), new Position(row-2,col-1),new Position(row+1,col+2),
            new Position(row+1,col-2),new Position(row-1,col+2),new Position(row-1,col -2));
    for(Position pos: positions){
      if(rules.validMove(initialPos, pos,this,board,board.getColorToPlay()).valid()){
          allMoves.add(pos);
      }
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
    Knight knight = (Knight) obj;
    return this.color.equals(knight.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(color);
  }
}
