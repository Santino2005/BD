package edu.austral.dissis.chess.engine.PiecesMaked;

import edu.austral.dissis.chess.engine.Color;
import edu.austral.dissis.chess.engine.PiecesMoves.*;

public class FactoryPieces {
  public MixedPieces createBishop(String name, Color color) {
    return new MixedPieces(
        name,
        color,
        new DiagonalLeftBottomMove(),
        new DiagonalRightBottomMove(),
        new DiagonalLeftTopMove(),
        new DiagonalRightTopMove());
  }

  public MixedPieces creteKnight(String name, Color color) {
    return new MixedPieces(name, color, new KnightMove());
  }

  public MixedPieces createPawn(String name, Color color) {
    return new MixedPieces(name, color, new MoveToFront(), new CheckDoubleFront(), new Diagonal());
  }

  public MixedPieces createQueen(String name, Color color) {
    return new MixedPieces(
        name,
        color,
        new DiagonalRightTopMove(),
        new DiagonalLeftTopMove(),
        new DiagonalLeftBottomMove(),
        new DiagonalRightBottomMove(),
        new RowMove(),
        new ColumnMove());
  }

  public MixedPieces createArchbishop(String name, Color color) {
    return new MixedPieces(
        name,
        color,
        new KnightMove(),
        new DiagonalLeftBottomMove(),
        new DiagonalRightBottomMove(),
        new DiagonalLeftTopMove(),
        new DiagonalRightTopMove());
  }

  public MixedPieces createChancellor(String name, Color color) {
    return new MixedPieces(
        name,
        color,
        new KnightMove(),
        new DiagonalLeftBottomMove(),
        new DiagonalRightBottomMove(),
        new DiagonalLeftTopMove(),
        new DiagonalRightTopMove());
  }
}
