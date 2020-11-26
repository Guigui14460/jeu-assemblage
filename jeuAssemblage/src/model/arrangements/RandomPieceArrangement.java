package model.arrangements;

import java.util.Random;

import model.pieceFactories.PieceAbstractFactory;
import model.pieceFactories.PieceFactory;
import model.pieceFactories.RandomPieceFactory;
import model.pieceFactories.RandomRotatedPieceFactory;

/**
 * Cette classe permet de créer un arrangmenent totalement aléatoire.
 */
public class RandomPieceArrangement implements PieceArrangement {
    /**
     * Objet pour faire de la génération pseudo-aléatoire.
     */
    private static Random random = new Random();

    /**
     * Dimensions du plateau.
     */
    private int width, height;

    /**
     * Usine à pièce de puzzle.
     */
    private PieceAbstractFactory abstractFactory;

    /**
     * Nombre de pièces à placer.
     */
    private int pieceToPlace = 20;

    /**
     * Constructeur par défaut.
     * 
     * @param abstractFactory usine à pièces à utiliser
     * @param width           largeur du plateau
     * @param height          hauteur du plateau
     */
    public RandomPieceArrangement(PieceAbstractFactory abstractFactory, int width, int height) {
        this.abstractFactory = abstractFactory;
        this.width = width;
        this.height = height;
    }

    /**
     * Remplace le nombre de pièces à placer.
     * 
     * @param pieceToPlace nombre de pièces à placer
     */
    public void setPieceToPlace(int pieceToPlace) {
        this.pieceToPlace = pieceToPlace;
    }

    /**
     * Méthode d'usine permettant de générer un {@code RandomObjectArrangement} avec
     * des pièces aléatoires et qui ont un nombre de rotation aléatoire.
     * 
     * @param minWidth    largeur minimale du plateau
     * @param maxWidth    largeur maximale du plateau
     * @param minHeight   hauteur minimale du plateau
     * @param maxHeight   hauteur maximale du plateau
     * @param maxRotation nombre maximale de rotation autorisées
     * @return un arrangement aléatoire de pièces
     */
    public static RandomPieceArrangement fullRandomWithRotation(int minWidth, int maxWidth, int minHeight,
            int maxHeight, int maxRotation) {
        maxWidth = RandomPieceArrangement.random.nextInt(maxWidth - minWidth) + minWidth;
        maxHeight = RandomPieceArrangement.random.nextInt(maxHeight - minHeight) + minHeight;
        PieceAbstractFactory factory = new RandomRotatedPieceFactory(maxWidth - maxWidth / 2, maxHeight - maxHeight / 2,
                maxWidth / 2, maxHeight / 2, maxRotation);
        return new RandomPieceArrangement(factory, maxWidth, maxHeight);
    }

    /**
     * Méthode d'usine permettant de générer un {@code RandomObjectArrangement} avec
     * des pièces aléatoires.
     * 
     * @param minWidth  largeur minimale du plateau
     * @param maxWidth  largeur maximale du plateau
     * @param minHeight hauteur minimale du plateau
     * @param maxHeight hauteur maximale du plateau
     * @return un arrangement aléatoire de pièces
     */
    public static RandomPieceArrangement fullRandomWithoutRotation(int minWidth, int maxWidth, int minHeight,
            int maxHeight) {
        maxWidth = RandomPieceArrangement.random.nextInt(maxWidth - minWidth) + minWidth;
        maxHeight = RandomPieceArrangement.random.nextInt(maxHeight - minHeight) + minHeight;
        PieceAbstractFactory factory = new RandomPieceFactory(maxWidth - maxWidth / 2, maxHeight - maxHeight / 2,
                maxWidth / 2, maxHeight / 2);
        return new RandomPieceArrangement(factory, maxWidth, maxHeight);
    }

    @Override
    public Arrangement generateArrangement() {
        Arrangement arrangement = new Arrangement(this.width, this.height);
        for (int i = 0; i < this.pieceToPlace; i++) {
            try {
                arrangement.addPieces(PieceFactory.getPiece(this.abstractFactory));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrangement;
    }
}
