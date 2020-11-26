package model.chains;

import model.PlateauPuzzle;
import piecesPuzzle.Piece;

/**
 * Représente un maillon vérifiant si une pièce peut se trouver dans le plateau
 * en fonction des coordonnées et dimensions.
 */
public class InBoardChain extends Chain {
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur par défaut.
     * 
     * @param next maillon suivant de la chaîne
     */
    public InBoardChain(Chain next) {
        super(next);
    }

    @Override
    protected boolean localeVerification(PlateauPuzzle board, Piece piece) {
        if (piece.getX() < 0 || piece.getY() < 0) {
            return false;
        }
        if (piece.getX() + piece.getWidth() - 1 >= board.getWidth()
                || piece.getY() + piece.getHeight() - 1 >= board.getHeight()) {
            return false;
        }
        return true;
    }
}
