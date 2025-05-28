package edu.austral.dissis.chess.engine.boards;

import edu.austral.dissis.chess.engine.PiecesMaked.MixedPieces;
import edu.austral.dissis.chess.engine.Position;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MixedBoards implements BoardOrganization {
  private final List<BoardOrganization> board;

  public MixedBoards(BoardOrganization... board) {
    this.board = Arrays.asList(board);
  }

  @Override
  public Map<Position, MixedPieces> create() {
    Map<Position, MixedPieces> cells = new HashMap<>();
    for (BoardOrganization create : board) {
      cells.putAll(create.create());
    }
    return cells;
  }

  @Override
  public Map<Position, MixedPieces> addBishop(Map<Position, MixedPieces> mapToAdd) {
    Map<Position, MixedPieces> result = new HashMap<>(mapToAdd);
    for (BoardOrganization add : board) {
      result.putAll(add.addBishop(result));
    }
    return result;
  }

  @Override
  public Map<Position, MixedPieces> addRook(Map<Position, MixedPieces> mapToAdd) {
    Map<Position, MixedPieces> result = new HashMap<>(mapToAdd);
    for (BoardOrganization add : board) {
      result.putAll(add.addRook(result));
    }
    return result;
  }

  @Override
  public Map<Position, MixedPieces> addQueen(Map<Position, MixedPieces> mapToAdd) {
    Map<Position, MixedPieces> result = new HashMap<>(mapToAdd);
    for (BoardOrganization add : board) {
      result.putAll(add.addQueen(result));
    }
    return result;
  }

  @Override
  public Map<Position, MixedPieces> addKing(Map<Position, MixedPieces> mapToAdd) {
    Map<Position, MixedPieces> result = new HashMap<>(mapToAdd);
    for (BoardOrganization add : board) {
      result.putAll(add.addKing(result));
    }
    return result;
  }

  @Override
  public Map<Position, MixedPieces> addPawn(Map<Position, MixedPieces> mapToAdd) {
    Map<Position, MixedPieces> result = new HashMap<>(mapToAdd);
    for (BoardOrganization add : board) {
      result.putAll(add.addPawn(result));
    }
    return result;
  }

  @Override
  public Map<Position, MixedPieces> addKnight(Map<Position, MixedPieces> mapToAdd) {
    Map<Position, MixedPieces> result = new HashMap<>(mapToAdd);
    for (BoardOrganization add : board) {
      result.putAll(add.addKnight(result));
    }
    return result;
  }
}
