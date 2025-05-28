package edu.austral.dissis.chess.engine.PiecesMaked;

import edu.austral.dissis.chess.engine.Board;
import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.MovesType;
import edu.austral.dissis.chess.engine.PiecesMoves.Piece;
import edu.austral.dissis.chess.engine.PiecesMoves.SpecialMoves;
import edu.austral.dissis.chess.engine.PiecesMoves.Valuate;
import edu.austral.dissis.chess.engine.Position;
import edu.austral.dissis.chess.engine.rules.Rules;
import java.util.*;

public class MixedPieces implements Piece {

  private final Color color;
  private final List<Valuate> list;
  private final String name;

  public MixedPieces(String name, Color color, Valuate... pieces) {
    this.name = name;
    this.color = color;
    this.list = Arrays.asList(pieces);
  }

  @Override
  public MovesType validMove(Position initialPos, Position finalPos, Board board) {
    for (Valuate pieces : list) {
      boolean pieceValuate = pieces.valuate(initialPos, finalPos, board, color);
      if (new SpecialMoves().specialMove(pieces) && pieceValuate) {
        return new MovesType(
            this, new SpecialMoves().moveName(pieces), initialPos, finalPos, board);
      }
      if (pieceValuate) {
        return new MovesType(this, "Valid", initialPos, finalPos, board);
      }
    }
    return new MovesType(this, "invalid", initialPos, finalPos, board);
  }

  public Color color() {
    return color;
  }

  @Override
  public String name() {
    return this.name;
  }

  @Override
  public List<Position> possibleMoves(Position initialPos, Board board, Rules rules) {
    List<Position> pos = new ArrayList<>();
    for (Valuate pieces : list) {
      List<Position> list = pieces.possibleMoves(initialPos, board, color);
      if (list != null && !list.isEmpty()) {
        pos.addAll(list);
      }
    }
    return pos;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    MixedPieces pieces = (MixedPieces) obj;
    return this.color.equals(pieces.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(color);
  }
}
