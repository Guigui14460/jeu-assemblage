package jeuAssemblage.model.arrangements;

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
     * Nombre maximal d'actions autorisées.
     */
    private int maxAvailableActions;

    /**
     * Constructeur.
     * 
     * @param width               largeur du plateau
     * @param height              hauteur du plateau
     * @param maxAvailableActions nombre maximal d'actions autorisées
     */
    public Arrangement(int width, int height, int maxAvailableActions) {
        this.width = width;
        this.height = height;
        this.maxAvailableActions = maxAvailableActions;
        this.pieces = new ArrayList<>();
    }

    /**
     * Constructeur par défaut.
     * 
     * @param width  largeur du plateau
     * @param height hauteur du plateau
     */
    public Arrangement(int width, int height) {
        this(width, height, 0);
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
     * Récupère le nombre maximal d'actions autorisées.
     * 
     * @return nombre maximal d'actions autorisées
     */
    public int getMaxAvailableActions() {
        return this.maxAvailableActions;
    }

    /**
     * Change le nombre maximal d'actions autorisées.
     * 
     * @param maxAvailableActions nombre maximal d'actions autorisées
     */
    public void setMaxAvailableActions(int maxAvailableActions) {
        this.maxAvailableActions = maxAvailableActions;
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