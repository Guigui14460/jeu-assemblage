package jeuAssemblage.view;

import jeuAssemblage.model.PlateauPuzzle;
import piecesPuzzle.observer.ModelListener;

/**
 * Vue sur le terminal respectant le modèle MVC.
 */
public class TerminalView implements View, ModelListener {
    /**
     * Plateau de jeu.
     */
    private PlateauPuzzle board;

    /**
     * Constructeur par défaut.
     * 
     * @param board plateau de jeu
     */
    public TerminalView(PlateauPuzzle board) {
        this.board = board;
        this.board.addModelListener(this); // on ajoute l'objet en tant qu'écouteur du plateau
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
