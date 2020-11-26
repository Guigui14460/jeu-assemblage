package model.pieceFactories;

import piecesPuzzle.Piece;

/**
 * Classe définissant une usine de pièce en fonction de la stratégie d'usine
 * utilisée.
 */
public class PieceFactory {
    /**
     * Récupère une pièce venant d'une usine abstraite.
     * 
     * @param factory stratégie de création de pièce à utiliser (usine)
     * @return pièce créée
     * @throws Exception levée lorsque l'on arrive pas à créer la pièce
     */
    public static Piece getPiece(PieceAbstractFactory factory) throws Exception {
        return factory.createPiece();
    }
}
