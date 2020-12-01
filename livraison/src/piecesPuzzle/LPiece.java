package piecesPuzzle;

/**
 * Classe définissant une pièce en forme de L.
 */
public class LPiece extends PieceImplementation {
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur définissant tous les attributs.
     * 
     * @param x      coordonnée en x
     * @param y      coordonnée en y
     * @param width  largeur de la pièce
     * @param height hauteur de la pièce
     */
    public LPiece(int x, int y, int width, int height) {
        super(x, y, width, height, "L");
        for (int i = 0; i < height; i++) {
            this.setBoardValueAtPosition(0, i, true);
        }
        for (int i = 1; i < width; i++) {
            this.setBoardValueAtPosition(i, height - 1, true);
        }
    }

    /**
     * Constructeur par défaut. Initialise {@code width} à la valeur 3 et
     * {@code height} à la valeur 5.
     * 
     * @param x coordonnée en x
     * @param y coordonnée en y
     */
    public LPiece(int x, int y) {
        this(x, y, 3, 5);
    }

    @Override
    public Piece copy() {
        LPiece pieceCopy = new LPiece(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        pieceCopy.setBoard(this.copyBoard());
        return pieceCopy;
    }
}
