package view;

import displayer.Displayer;
import game.PlateauPuzzle2;
import piecesPuzzle.observer.ModelListener;

public class TerminalView implements Displayer, ModelListener {
    private PlateauPuzzle2 board;

    public TerminalView(PlateauPuzzle2 board){
        this.board = board;
        this.board.addModelListener(this);
    }

    @Override
    public void somethingHasChanged(Object arg0) {
        this.show();
    }

    @Override
    public void show() {
        this.board.showBoard();
        System.out.println();
    }
}
