package piecesPuzzle;

/**
 * Classe définissant une pièce rectangulaire.
 */
public class RectanglePiece extends PieceImplementation {
    /**
     * Constructeur définissant tous les attributs.
     * @param x coordonnée en x
     * @param y coordonnée en y
     * @param width largeur de la pièce
     * @param heigth hauteur de la pièce
     */
    public RectanglePiece(int x, int y, int width, int heigth){
        super(x, y, width, heigth);
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                this.board[i][j] = true;
            }
        }
    }

    /**
     * Constructeur par défaut.
     * Initialise {@code width} et {@code height} à la valeur 2.
     * @param x coordonnée en x
     * @param y coordonnée en y
     */
    public RectanglePiece(int x, int y){
        this(x, y, 2, 2);
    }
}
