package model.arrangements;

import model.PlateauPuzzle;
import piecesPuzzle.Piece;

/**
 * Classe définissant une usine à plateau de jeu en fonction de la stratégie
 * d'usine utilisée.
 */
public class PlateauPuzzleFactory {
    /**
     * Génère un plateau de jeu venant d'une usine abstraite.
     * 
     * @param factory stratégie de création de plateau à utiliser (usine)
     * @return plateau de jeu
     */
    public static PlateauPuzzle getPlateauPuzzle(PieceArrangement factory) {
        Arrangement arrangement = factory.generateArrangement();
        PlateauPuzzle board = new PlateauPuzzle(arrangement.getWidth(), arrangement.getHeight());
        for (Piece piece : arrangement.getPieces()) {
            board.addPiece(piece);
        }
        return board;
    }
}
