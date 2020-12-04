package jeuAssemblage.model.aiAlgorithms;


import jeuAssemblage.model.PlateauPuzzle;
import piecesPuzzle.Piece;

public class Capsule {
    
    private PlateauPuzzle board;
    private int value;

    public Capsule(PlateauPuzzle board, int value) {
        this.board = board;
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public PlateauPuzzle getBoard(){
        return this.board;
    }
    

}
