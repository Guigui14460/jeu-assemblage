package piecesPuzzle;

/**
 * Classe définissant une pièce rectangulaire.
 */
public class RectanglePiece extends Piece {
    /**
     * Constructeur définissant tous les attributs.
     * @param x coordonnée en x
     * @param y coordonnée en y
     * @param width largeur de la pièce
     * @param heigth hauteur de la pièce
     */
    public RectanglePiece(int x, int y, int width, int heigth){
        super(x, y);
        int[][] newBoard = new int[width][heigth];
        for(int i = 0; i < width; i++){0
            for(int j = 0; j < heigth; j++){
                newBoard[i][j] = 1;
            }
        }
        this.board = newBoard;
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
