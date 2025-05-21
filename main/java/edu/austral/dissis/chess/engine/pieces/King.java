package edu.austral.dissis.chess.engine.pieces;

import edu.austral.dissis.chess.engine.*;
import edu.austral.dissis.chess.engine.Moves.Castling;
import edu.austral.dissis.chess.engine.rules.CheckRule;
import edu.austral.dissis.chess.engine.rules.Rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class King implements Piece{

    private final Color color;
    private final boolean moved;
    public Rules rules;

    public King(Color color){
        this.color = color;
        this.moved = false;
    }
    private King(Color color, boolean moved){
        this.color = color;
        this.moved = moved;
    }
    @Override
    public MovesType validMove(Position initialPos, Position finalPos, Map<Position, Piece> cells) {
        if(Math.abs(initialPos.row()-finalPos.row()) == 1 && Math.abs(initialPos.column() - finalPos.column()) == 1){
            if(cells.get(finalPos) == null || cells.get(finalPos).color() != this.color){
                return new MovesType(this, "Valid", initialPos, finalPos, cells);
            }
        }else if(!this.moved && cells.get(finalPos) instanceof Rook && !(((Rook) cells.get(finalPos)).hasMoved())){
            int columnWay = Integer.signum(finalPos.column() - initialPos.column());
            for(int i = initialPos.column() + columnWay; i != finalPos.column(); i += columnWay){
                if(cells.get(new Position(initialPos.row(), i)) != null){
                    return new MovesType(this, "invalid", initialPos, finalPos, cells);
                }
                Map<Position, Piece> tempBoard = new HashMap<>(cells);
                tempBoard.remove(initialPos);
                tempBoard.put(new Position(initialPos.row(), initialPos.column()), this);
                Board newBoard = new Board(color, tempBoard, rules, null);
                if(isInCheck(new Position(initialPos.row(), initialPos.column()),this.color, newBoard) || cells.get(new Position(initialPos.row(), i)).color() != this.color){
                    return new MovesType(this, "invalid", initialPos, finalPos, cells);
                }
            }
            return new MovesType(this, "Castling", initialPos, finalPos, cells);
        }
        return new MovesType(this, "invalid", initialPos, finalPos, cells);
    }

    @Override
    public Color color() {
        return color;
    }
    public King moved(){
        return new King(color, true);
    }
    @Override
    public List<Position> possibleMoves(Position initialPos, Map<Position, Piece> cells, Board board, Rules rules){
        List<Position> allMoves = new ArrayList<>();
        int row = initialPos.row();
        int col = initialPos.column();
        List<Position> positions  = List.of(new Position(row,col+1), new Position(row, col -1),
                new Position(row+1, col), new Position(row+1,col+1),new Position(row+1,col-1),
                new Position(row-1,col),new Position(row-1,col-1),new Position(row-1,col +1));
        for(Position pos: positions){
            if(rules.validMove(initialPos, pos,this,board,board.getColorToPlay()).valid()){
                allMoves.add(pos);
            }
        }
        int kingRow = this.color == Color.WHITE ? 1 : 8;
        if(!this.moved && validMove(initialPos, new Position(kingRow,8),cells).valid()){
            allMoves.add(new Position(kingRow,8));
        }else if(!this.moved && validMove(initialPos, new Position(kingRow, 1),cells).valid()){
            allMoves.add(new Position(kingRow,1));
        }
        return allMoves;
    }


    public boolean isInCheck(Position kingPos, Color color, Board board){
        Color opponent = (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
        return new CheckRule(board).isCheck(opponent, kingPos, board);
    }
}
