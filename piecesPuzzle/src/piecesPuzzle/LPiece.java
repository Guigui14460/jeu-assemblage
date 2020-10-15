package piecesPuzzle;

/**
 * Classe définissant une pièce en forme de L.
 */
public class LPiece extends PieceImplementation {
    /**
     * Constructeur définissant tous les attributs.
     * @param x coordonnée en x
     * @param y coordonnée en y
     * @param width largeur de la pièce
     * @param height hauteur de la pièce
     */
    public LPiece(int x, int y, int width, int height){
        super(x, y, width, height);
        for(int i = 0; i < height; i++){
            this.setBoardValueAtPosition(i, 0, true);
        }
        for(int i = 1; i < width; i++){
            this.setBoardValueAtPosition(height-1, i, true);
        }
    }

    /**
     * Constructeur par défaut.
     * Initialise {@code width} à la valeur 3 et {@code height} à la valeur 5.
     * @param x coordonnée en x
     * @param y coordonnée en y
     */
    public LPiece(int x, int y){
        this(x, y, 3, 5);
    }
}
