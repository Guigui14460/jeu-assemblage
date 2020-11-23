package model;

import piecesPuzzle.Piece;

/**
 * Représente un maillon vérifiant si une pièce peut se trouver en collision
 * avec une autre pièce d'un plateau donné.
 */
public class CollisionChain extends Chain {
    /**
     * Constructeur par défaut.
     * 
     * @param next maillon suivant de la chaîne
     */
    public CollisionChain(Chain next) {
        super(next);
    }

    @Override
    protected boolean localeVerification(PlateauPuzzle board, Piece piece) {
        return !board.collision(piece);
    }
}
