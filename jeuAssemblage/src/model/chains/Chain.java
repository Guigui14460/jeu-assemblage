package model.chains;

import java.io.Serializable;

import model.PlateauPuzzle;
import piecesPuzzle.Piece;

/**
 * Classe représentant un maillon d'une chaîne de responsabilité pour action
 * d'une pièce sur un plateau de jeu.
 */
public abstract class Chain implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * Prochain maillon de la chaîne.
     */
    private Chain next;

    /**
     * Constructeur par défaut.
     * 
     * @param next prochain maillon de la chaîne
     */
    public Chain(Chain next) {
        this.setNext(next);
    }

    /**
     * Change le prochain maillon.
     * 
     * @param next nouveau prochain maillon
     */
    public void setNext(Chain next) {
        this.next = next;
    }

    /**
     * Permet de lancer la chaîne. Chaque maillon de la chaîne va être testé tant
     * qu'aucun maillon avant n'a pas vérifié la requête.
     * 
     * @param board plateau
     * @param piece pièce à ajouter
     * @return si la pièce peut être ajoutée ou non
     */
    public final boolean performAction(PlateauPuzzle board, Piece piece) {
        if (!this.localeVerification(board, piece))
            return false;
        if (this.next == null)
            return true;
        return this.next.performAction(board, piece);
    }

    /**
     * Permet de lancer la vérification locale correspondant au maillon.
     * 
     * @param board plateau
     * @param piece pièce à ajouter
     * @return si la pièce peut être ajoutée ou non
     */
    protected abstract boolean localeVerification(PlateauPuzzle board, Piece piece);
}
