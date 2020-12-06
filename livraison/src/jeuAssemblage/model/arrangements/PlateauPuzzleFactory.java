package jeuAssemblage.model.arrangements;

import jeuAssemblage.model.PlateauPuzzle;
import piecesPuzzle.Piece;

/**
 * Classe définissant une usine à plateau de jeu en fonction de la stratégie
 * d'usine utilisée.
 */
public class PlateauPuzzleFactory {
    /**
     * Génère un plateau de jeu venant d'une usine abstraite à arrangement de
     * pièces.
     * 
     * @param strategy stratégie de création de plateau à utiliser (usine)
     * @return plateau de jeu
     */
    public static PlateauPuzzle generatePlateauPuzzle(PieceArrangement strategy) {
        return PlateauPuzzleFactory.getPlateauPuzzle(PlateauPuzzleFactory.getArrangement(strategy));
    }

    /**
     * Récupère un arrangement à partir d'une usine abstraite à arrangement de
     * pièces.
     * 
     * @param strategy stratégie de création de plateau à utiliser (usine)
     * @return arrangement fabriqué
     */
    public static Arrangement getArrangement(PieceArrangement strategy) {
        return strategy.generateArrangement();
    }

    /**
     * Récupère le plateau associé à l'arrangement donné.
     * 
     * @param arrangement arrangement
     * @return plateau puzzle associé à l'arrangement
     */
    public static PlateauPuzzle getPlateauPuzzle(Arrangement arrangement) {
        PlateauPuzzle board = new PlateauPuzzle(arrangement.getWidth(), arrangement.getHeight(),
                arrangement.getMaxAvailableActions());
        for (Piece piece : arrangement.getPieces()) {
            board.addPiece(piece);
        }
        return board;
    }
}
