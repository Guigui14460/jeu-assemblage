package model.pieceFactories;

import piecesPuzzle.Piece;

/**
 * Usine de pièce utilisant une stratégie aléatoire de création de pièces et les
 * tournant un nombre de fois aléatoire.
 */
public class RandomRotatedPieceFactory extends RandomPieceFactory {
    /**
     * Nombre maximal de rotation.
     */
    private int nbRotation;

    /**
     * Constructeur.
     * 
     * @param maxX        max X
     * @param maxY        max Y
     * @param maxWidth    largeur maximum
     * @param maxHeight   hauteur maximum
     * @param maxRotation nombre maximal de rotation
     */
    public RandomRotatedPieceFactory(int maxX, int maxY, int maxWidth, int maxHeight, int maxRotation) {
        super(maxX, maxY, maxWidth, maxHeight);
        this.nbRotation = RandomPieceFactory.random.nextInt(maxRotation);
    }

    /**
     * Constructeur.
     * 
     * @param maxX      max X
     * @param maxY      max Y
     * @param maxWidth  largeur maximum
     * @param maxHeight hauteur maximum
     */
    public RandomRotatedPieceFactory(int maxX, int maxY, int maxWidth, int maxHeight) {
        this(maxX, maxY, maxWidth, maxHeight, 4);
    }

    /**
     * Constructeur. Initialise les pièces par défaut.
     * 
     * @param maxX        max X
     * @param maxY        max Y
     * @param maxRotation nombre maximal de rotation
     */
    public RandomRotatedPieceFactory(int maxX, int maxY, int maxRotation) {
        super(maxX, maxY);
        this.nbRotation = RandomPieceFactory.random.nextInt(maxRotation);
    }

    /**
     * Constructeur. Initialise les pièces par défaut.
     * 
     * @param maxX max X
     * @param maxY max Y
     */
    public RandomRotatedPieceFactory(int maxX, int maxY) {
        this(maxX, maxY, 4);
    }

    @Override
    public Piece createPiece() throws Exception {
        Piece piece = super.createPiece();
        for (int i = 0; i < this.nbRotation; i++) {
            piece.rotate(Piece.Rotate.PLUS_90_DEGREES);
        }
        return piece;
    }
}
