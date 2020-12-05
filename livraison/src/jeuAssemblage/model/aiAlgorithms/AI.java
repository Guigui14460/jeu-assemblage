package jeuAssemblage.model.aiAlgorithms;

import jeuAssemblage.model.PlateauPuzzle;

/**
 * Interface décrivant une intelligence artificielle.
 */
public interface AI {
    /**
     * Lance l'algorithme.
     * 
     * @throws CloneNotSupportedException levée lorsqu'une classe ne supporte pas le
     *                                    clonage
     * @return la solution finale trouvée
     */
    public Capsule start() throws CloneNotSupportedException;

    /**
     * Remplace le plateau de jeu par un nouveau.
     * 
     * @param board nouveau plateau de jeu
     */
    public void setBoard(PlateauPuzzle board);
}
