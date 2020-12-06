package jeuAssemblage.model.arrangements;

import java.util.Random;

import jeuAssemblage.model.PlateauPuzzle;
import jeuAssemblage.model.pieceFactories.PieceAbstractFactory;
import jeuAssemblage.model.pieceFactories.PieceFactory;
import jeuAssemblage.model.pieceFactories.RandomPieceFactory;
import jeuAssemblage.model.pieceFactories.RandomRotatedPieceFactory;
import piecesPuzzle.Piece;

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
     * Nombre de pièces à générer.
     */
    private int pieceToGenerate;

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
        this.pieceToGenerate = width * height / 10;
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
        PieceAbstractFactory factory = new RandomRotatedPieceFactory(maxWidth, maxHeight,
                (maxWidth / 3 > 1 ? maxWidth / 3 : 1), (maxHeight / 3 > 1 ? maxHeight / 3 : 1), maxRotation);
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
        PieceAbstractFactory factory = new RandomPieceFactory(maxWidth, maxHeight,
                (maxWidth / 3 > 1 ? maxWidth / 3 : 1), (maxHeight / 3 > 1 ? maxHeight / 3 : 1));
        return new RandomPieceArrangement(factory, maxWidth, maxHeight);
    }

    @Override
    public Arrangement generateArrangement() {
        Arrangement arrangement = new Arrangement(this.width, this.height);
        PlateauPuzzle board = new PlateauPuzzle(this.width, this.height, 100);
        int i = 0, pieceAdded = 0;
        while (pieceAdded < this.pieceToGenerate && i < 100) { // variable i pour éviter une boucle infinie
            try {
                Piece piece = PieceFactory.getPiece(this.abstractFactory);
                if (board.addPiece(piece)) {
                    arrangement.addPieces(piece);
                    pieceAdded++;
                }
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        arrangement.setMaxAvailableActions(arrangement.getPieces().size() * 2);
        return arrangement;
    }
}
