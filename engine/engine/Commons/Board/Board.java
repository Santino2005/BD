package edu.austral.dissis.chess.engine.Commons.Board;

import edu.austral.dissis.chess.engine.Commons.Colors.Color;
import edu.austral.dissis.chess.engine.Commons.GameAndAdapters.Game;
import edu.austral.dissis.chess.engine.Commons.Moves.SpecialMoves;
import edu.austral.dissis.chess.engine.Commons.Pieces.Piece;
import edu.austral.dissis.chess.engine.Commons.Positions.MoveTo;
import edu.austral.dissis.chess.engine.Commons.Positions.Position;
import edu.austral.dissis.chess.engine.Commons.Rules.Rules;
import edu.austral.dissis.chess.engine.Commons.Turns.TurnsManager;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Board {

    private final int rowMax;
    private final int colMax;
    private final Map<Position, Piece> cells;
    private final Rules rules;

    public Board(int rowMax, int colMax, Map<Position, Piece> cells, Rules rules) {
        this.rowMax = rowMax;
        this.colMax = colMax;
        this.cells = Map.copyOf(cells);
        this.rules = rules;
    }
    public int getRowMax(){
        return rowMax;
    }
    public int getColMax(){
        return colMax;
    }
    public Map<Position, Piece> getCells(){
        return cells;
    }
    public Rules getRules(){
        return rules;
    }

    public Game move(MoveTo moveTo, Piece piece, TurnsManager turnsManager){
        if(!rules.valid(moveTo, piece, turnsManager, this)){
            return new Game(this, rules.keepsPlaying());
        }
        Map<Position, Piece> newCells = new HashMap<>(cells);
        Board simulatedBoard = new Board(rowMax,colMax,move(moveTo, piece, newCells) , rules);
        return new Game(simulatedBoard, rules.moved());
    }

    public Optional<Piece> getPieceByPosition(Position position){
        return Optional.ofNullable(cells.get(position));
    }
    public Optional<Position> getPositionByPiece(Piece piece){
        Position pos = null;
        for(Map.Entry<Position,Piece>  search : cells.entrySet()){
            if(search.getValue().getId().equals(piece.getId())){
                pos = search.getKey();
            }
        }
        return Optional.ofNullable(pos);
    }

    private Map<Position, Piece> move(MoveTo moveTo, Piece piece, Map<Position, Piece> originalCells){
        Map<Position, Piece> newCells = new HashMap<>(originalCells);
        for (SpecialMoves specialMoves : piece.getSpecialMoves()) {
            if (specialMoves.isSpecialMove(piece, moveTo, this)) {
                return specialMoves.execute(moveTo, piece, this);
            }
        }
        newCells.remove(moveTo.getFrom());
        newCells.remove(moveTo.getTo());
        newCells.put(moveTo.getTo(), piece.move());
        return newCells;
    }

    public boolean validPosition(MoveTo moveTo){
        int finalPosRow = moveTo.getFrom().row();
        int finalPosCol = moveTo.getFrom().column();
        return finalPosRow <= this.rowMax && finalPosCol <= this.colMax && finalPosRow > 0 && finalPosCol > 0;
    }
}
