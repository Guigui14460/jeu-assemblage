package model.arrangements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import piecesPuzzle.Piece;

/**
 * Classe pour représenter un arrangement.
 */
public class Arrangement {
    /**
     * Dimensions du plateau.
     */
    private int width, height;

    /**
     * Liste de pièces
     */
    private List<Piece> pieces;

    /**
     * Constructeur par défaut.
     * 
     * @param width  largeur du plateau
     * @param height hauteur du plateau
     */
    public Arrangement(int width, int height) {
        this.width = width;
        this.height = height;
        this.pieces = new ArrayList<>();
    }

    /**
     * Récupère la largeur du plateau.
     * 
     * @return largeur du plateau
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Récupère la hauteur du plateau.
     * 
     * @return hauteur du plateau
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Récupère la liste des pièces de l'arrangement.
     * 
     * @return liste des pièces de l'arrangement
     */
    public List<Piece> getPieces() {
        return this.pieces;
    }

    /**
     * Ajoute des pièces à la liste de pièces de l'arrangement.
     * 
     * @param pieces pièces à ajouter
     */
    public void addPieces(Piece... pieces) {
        this.pieces.addAll(Arrays.asList(pieces));
    }
}