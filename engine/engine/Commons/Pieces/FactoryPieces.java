package edu.austral.dissis.chess.engine.Commons.Pieces;

import edu.austral.dissis.chess.engine.Chess.MoveConditions.Conditions.IsFirstMove;
import edu.austral.dissis.chess.engine.Chess.MoveConditions.Conditions.IsNotKing;
import edu.austral.dissis.chess.engine.Chess.MoveConditions.Conditions.KingCantBeInCheckAfterMove;
import edu.austral.dissis.chess.engine.Chess.MoveConditions.Conditions.KingRestriction;
import edu.austral.dissis.chess.engine.Chess.MoveConditions.FreePath.*;
import edu.austral.dissis.chess.engine.Chess.MoveConditions.MovesWithCapture.MovesValidator;
import edu.austral.dissis.chess.engine.Chess.MoveConditions.PieceValidMoves.PieceMovesValidator;
import edu.austral.dissis.chess.engine.Chess.Moves.ColumnMoves.DownColumn;
import edu.austral.dissis.chess.engine.Chess.Moves.ColumnMoves.TopColumn;
import edu.austral.dissis.chess.engine.Chess.Moves.DiagonalMoves.DiagonalLeftBottomMove;
import edu.austral.dissis.chess.engine.Chess.Moves.DiagonalMoves.DiagonalLeftTopMove;
import edu.austral.dissis.chess.engine.Chess.Moves.DiagonalMoves.DiagonalRightBottomMove;
import edu.austral.dissis.chess.engine.Chess.Moves.DiagonalMoves.DiagonalRightTopMove;
import edu.austral.dissis.chess.engine.Chess.Moves.KingMoves.Castling;
import edu.austral.dissis.chess.engine.Chess.Moves.KingMoves.KingMove;
import edu.austral.dissis.chess.engine.Chess.Moves.KnightMove.KnightMove;
import edu.austral.dissis.chess.engine.Chess.Moves.PawnMoves.DiagonalEatMove;
import edu.austral.dissis.chess.engine.Chess.Moves.PawnMoves.DoubleMoveToFront;
import edu.austral.dissis.chess.engine.Chess.Moves.PawnMoves.PromotionCondition;
import edu.austral.dissis.chess.engine.Chess.Moves.RowMoves.DownRow;
import edu.austral.dissis.chess.engine.Chess.Moves.RowMoves.TopRow;
import edu.austral.dissis.chess.engine.Chess.Rules.CheckRules;
import edu.austral.dissis.chess.engine.Commons.Colors.Color;

import java.util.Collections;
import java.util.List;

public class FactoryPieces {

        private final KingCantBeInCheckAfterMove kingSafetyRule =
                new KingCantBeInCheckAfterMove(new CheckRules());

        public Piece createBishop(String name, Color color, String id, Character character, boolean moved) {
                MovesValidator validator = new MovesValidator(
                        new FreeDiagonalPath(new IsPieceOnTheRoad()),
                        new IsNotKing(),
                        new IsEnemyPieceOnPosition());
                MovesValidator valid = new MovesValidator(
                        new FreeDiagonalPath(new IsPieceOnTheRoad()),
                        new IsNotKing(),
                        new IsPieceNotOnRoad()
                );
                return new Piece(id, color, name, character, moved, null,
                        new PieceMovesValidator(Collections.emptyList(),
                                new DiagonalLeftBottomMove(validator,valid),
                                new DiagonalRightBottomMove(validator,valid),
                                new DiagonalLeftTopMove(validator,valid),
                                new DiagonalRightTopMove(validator,valid)));
        }

        public Piece createKnight(String name, Color color, String id, Character character, boolean moved) {
                MovesValidator validator = new MovesValidator(
                        new IsNotKing(),
                        new IsEnemyPieceOnPosition()
                );
                MovesValidator valid = new MovesValidator(
                        new IsNotKing(),
                        new IsPieceNotOnRoad()
                );

                return new Piece(id, color, name, character, moved, Collections.emptyList(),
                        new PieceMovesValidator(Collections.emptyList(),
                                new KnightMove(valid,validator)));
        }

        public Piece createQueen(String name, Color color, String id, Character character, boolean moved) {
                MovesValidator diagonalValidator = new MovesValidator(
                        new FreeDiagonalPath(new IsPieceOnTheRoad()),
                        new IsNotKing(),
                        new IsEnemyPieceOnPosition()
                );
                MovesValidator valid = new MovesValidator(
                        new FreeDiagonalPath(new IsPieceOnTheRoad()),
                        new IsNotKing(),
                        new IsPieceNotOnRoad()
                );

                MovesValidator colValidator = new MovesValidator(
                        new FreeColumnPath(new IsPieceOnTheRoad()),
                        new IsNotKing(),
                        new IsEnemyPieceOnPosition()
                );
                MovesValidator colValid = new MovesValidator(
                        new FreeColumnPath(new IsPieceOnTheRoad()),
                        new IsNotKing(),
                        new IsPieceNotOnRoad()
                );

                MovesValidator rowValidator = new MovesValidator(
                        new FreeRowPath(new IsPieceOnTheRoad()),
                        new IsNotKing(),
                        new IsEnemyPieceOnPosition()
                );
                MovesValidator rowValid = new MovesValidator(
                        new FreeRowPath(new IsPieceOnTheRoad()),
                        new IsNotKing(),
                        new IsPieceNotOnRoad()
                );

                return new Piece(id, color, name, character, moved, null,
                        new PieceMovesValidator(Collections.emptyList(),
                                new DiagonalRightTopMove(valid,diagonalValidator),
                                new DiagonalLeftTopMove(valid,diagonalValidator),
                                new DiagonalLeftBottomMove(valid,diagonalValidator),
                                new DiagonalRightBottomMove(valid,diagonalValidator),
                                new TopRow(colValidator,colValid),
                                new DownRow(colValidator,colValid),
                                new TopColumn(rowValidator,rowValid),
                                new DownColumn(rowValidator,rowValid)));
        }
        public Piece createArchbishop(String name, Color color, String id, Character character, boolean moved) {
                MovesValidator diagValidator = new MovesValidator(
                        new FreeDiagonalPath(new IsPieceOnTheRoad()),
                        new IsNotKing(),
                        new IsEnemyPieceOnPosition(),
                        new IsPieceNotOnRoad()
                );

                MovesValidator knightValidator = new MovesValidator(
                        new IsNotKing(),
                        new IsEnemyPieceOnPosition(),
                        new IsPieceNotOnRoad()
                );

                return new Piece(id, color, name, character, moved, null,
                        new PieceMovesValidator(Collections.emptyList(),
                                new KnightMove(knightValidator),
                                new DiagonalLeftBottomMove(diagValidator),
                                new DiagonalRightBottomMove(diagValidator),
                                new DiagonalLeftTopMove(diagValidator),
                                new DiagonalRightTopMove(diagValidator)));
        }

        public Piece createChancellor(String name, Color color, String id, Character character, boolean moved) {
                MovesValidator diagValidator = new MovesValidator(
                        new FreeDiagonalPath(new IsPieceOnTheRoad()),
                        new IsNotKing(),
                        new IsEnemyPieceOnPosition(),
                        new IsPieceNotOnRoad()
                );

                MovesValidator knightValidator = new MovesValidator(
                        new IsNotKing(),
                        new IsEnemyPieceOnPosition(),
                        new IsPieceNotOnRoad()
                );

                return new Piece(id, color, name, character, moved, null,
                        new PieceMovesValidator(Collections.emptyList(),
                                new KnightMove(knightValidator),
                                new DiagonalLeftBottomMove(diagValidator),
                                new DiagonalRightBottomMove(diagValidator),
                                new DiagonalLeftTopMove(diagValidator),
                                new DiagonalRightTopMove(diagValidator)));
        }

        public Piece createKing(String name, Color color, String id, Character character, boolean moved) {
                return new Piece(id, color, name, character, moved, List.of(new Castling()), new PieceMovesValidator(List.of(new Castling()),
                        new KingMove(new MovesValidator(new OneSquareMove(),new IsPieceNotOnRoad(), new KingRestriction()),new MovesValidator(
                                new IsEnemyPieceOnPosition(), new KingRestriction(), new OneSquareMove()))));
        }

        public Piece createRook(String name, Color color, String id, Character character, boolean moved) {
                MovesValidator col = new MovesValidator(new FreeColumnPath(new IsPieceOnTheRoad()), new IsEnemyPieceOnPosition(), new IsNotKing());
                MovesValidator colValid = new MovesValidator(new FreeColumnPath(new IsPieceOnTheRoad()), new IsPieceNotOnRoad(), new IsNotKing());
                MovesValidator row = new MovesValidator(new FreeRowPath(new IsPieceOnTheRoad()), new IsEnemyPieceOnPosition(), new IsNotKing());
                MovesValidator rowValid = new MovesValidator(new FreeRowPath(new IsPieceOnTheRoad()), new IsPieceNotOnRoad(), new IsNotKing());
                return new Piece(id, color, name, character, moved, null,
                        new PieceMovesValidator(Collections.emptyList(),
                                new DownColumn(rowValid,row),
                                new TopColumn(rowValid,row),
                                new DownRow(col,colValid),
                                new TopRow(col,colValid)));
        }

        public Piece createPawn(String name, Color color, String id, Character character, boolean moved) {
                return new Piece(id, color, name, character, moved, List.of(new PromotionCondition()),
                        new PieceMovesValidator(List.of(new PromotionCondition()),
                                new MoveToFront(new MovesValidator(new IsPieceNotOnRoad())),
                                new DoubleMoveToFront(new MovesValidator(new IsPieceNotOnRoad(), new IsFirstMove())),
                                new DiagonalEatMove(new MovesValidator(new IsNotKing(), new IsEnemyPieceOnPosition()))));
        }
}
        /*public Piece createChecker(String name, Color color, String id, Character character, boolean moved){
                new Piece(id, color, name, character,moved, new DiagonalEat(), new MoveDiagonal()))
        }*/
