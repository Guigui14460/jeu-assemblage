package piecesPuzzle;

/**
 * Classe définissant une pièce en forme de T.
 */
public class TPiece extends PieceImplementation {
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur définissant tous les attributs.
     * 
     * @param x      coordonnée en x
     * @param y      coordonnée en y
     * @param width  largeur de la pièce
     * @param height hauteur de la pièce
     */
    public TPiece(int x, int y, int width, int height) {
        super(x, y, width, height, "T");
        for (int i = 0; i < width; i++) {
            this.setBoardValueAtPosition(i, 0, true);
        }
        for (int i = 1; i < height; i++) {
            this.setBoardValueAtPosition(width / 2, i, true);
        }
    }

    /**
     * Constructeur par défaut. Initialise {@code width} à la valeur 3 et
     * {@code height} à la valeur 5.
     * 
     * @param x coordonnée en x
     * @param y coordonnée en y
     */
    public TPiece(int x, int y) {
        this(x, y, 3, 5);
    }

    @Override
    public void setWidth(int width) {
        if (width % 2 == 0) {
            throw new IllegalArgumentException("width must be an odd value");
        }
        super.setWidth(width);
    }

    @Override
    public void setHeight(int height) {
        if (height % 2 == 0) {
            throw new IllegalArgumentException("height must be an odd value");
        }
        super.setHeight(height);
    }

    @Override
    public Piece copy() {
        TPiece pieceCopy = new TPiece(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        pieceCopy.setBoard(this.copyBoard());
        return pieceCopy;
    }
}
