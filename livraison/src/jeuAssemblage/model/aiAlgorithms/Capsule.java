package jeuAssemblage.model.aiAlgorithms;

import jeuAssemblage.model.PlateauPuzzle;
import piecesPuzzle.Piece;

/**
 * Permet de stocker différentes choses importantes pour les algorithmes
 * d'intelligence artificielle.
 */
public class Capsule {
    /**
     * Plateau de jeu.
     */
    private PlateauPuzzle board;

    /**
     * Valeur associée au plateau.
     */
    private int value;

    /**
     * Constructeur par défaut.
     * 
     * @param board plateau de jeu
     * @param value valeur associée au plateau
     */
    public Capsule(PlateauPuzzle board, int value) {
        this.board = board;
        this.value = value;
    }

    /**
     * Récupère le plateau de jeu
     * 
     * @return ^plateau de jeu
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Récupère la valeur associée au plateau.
     * 
     * @return valeur associée au plateau
     */
    public PlateauPuzzle getBoard() {
        return this.board;
    }
}
