package model;

import piecesPuzzle.Piece;

/**
 * Interface décrivant une usine à pièce de puzzle.
 */
public interface PieceAbstractFactory {
    /**
     * Crée une pièce à partir d'une usine.
     * @return pièce créée
     * @throws Exception levée lorsque l'on arrive pas à créer la pièce
     */
    public Piece createPiece() throws Exception;
}
