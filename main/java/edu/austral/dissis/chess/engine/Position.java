package edu.austral.dissis.chess.engine;

public record Position(int row, int column) {

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Position)) {
      return false;
    }
    Position pos = (Position) o;
    return row == pos.row && column == pos.column;
  }

}
