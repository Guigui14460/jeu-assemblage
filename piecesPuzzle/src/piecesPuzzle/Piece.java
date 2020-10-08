package piecesPuzzle;

/**
 * Classe définissant une pièces générale pouvant être utilisée dans n'importe quel jeu de puzzle.
 */
public class Piece {
    /**
     * Énumérateur permettant de définir les angles dans lesquels les pièces peuvent tourner.
     */
    public enum Rotate {
        PLUS_90_DEGREES, MINUS_90_DEGREES
    };

    /**
     * Coordonnées de la pièce.
     */
    private int x, y;

    /**
     * Tableau contenant les blocs de la pièce.
     */
    protected int[][] board;
 
    /**
     * Constructeur par défaut.
     * @param x coordonnée en x
     * @param y coordonnée en y
     */
    public Piece(int x, int y) {
        this.setX(x);
        this.setY(y);
        this.board = new int[3][3];
        for(int i = 0; i < this.board.length; i++){
            for(int j = 0; j < this.board[i].length; j++){
                this.board[i][j] = 1;
            }
        }
    }
 
    /**
     * Constructeur par défaut.
     * @param x coordonnée en x
     * @param y coordonnée en y
     * @param board tableau contenant les blocs
     */
    public Piece(int x, int y, int[][] board) {
        this.setX(x);
        this.setY(y);
        this.board = board;
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    /**
     * Affiche le tableau contenant les blocs de la pièce.
     * Les 1 correspond aux blocs et les 0, au vide.
     */
    public void showBoard(){
        for(int i = 0; i < this.board.length; i++){
            System.out.print("[");
            for(int j = 0; j < this.board[i].length; j++){
                if(j == this.board[i].length - 1){
                    System.out.print(this.board[i][j]);
                } else {
                    System.out.print(this.board[i][j] + " | ");
                }
            }
            System.out.println("]");
        }
    }

    /**
     * Récupère la coordonnée en x.
     * @return coordonnée en x
     */
    public int getX() {
        return x;
    }

    /**
     * Met à jour la coordonnée en x.
     * @param x nouvelle coorodnnée en x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Récupère la coordonnée en y.
     * @return coordonnée en y
     */
    public int getY() {
        return y;
    }

    /**
     * Met à jour la coordonnée en y.
     * @param y nouvelle coorodnnée en y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Tourne une pièce.
     * @param nbDegrees nombre de degrès à faire tourner la pièce
     * @return la même pièce retournée (nouvelle instance)
     */
    public Piece rotatePiece(Piece.Rotate nbDegrees){
        int[][] newBoard = new int[this.board[0].length][this.board.length];
        for(int i = 0; i < this.board.length; i++){
            for(int j = 0; j < this.board[i].length; j++){
                if(nbDegrees.equals(Piece.Rotate.PLUS_90_DEGREES)){
                    newBoard[j][this.board[i].length - i - 1] = this.board[i][j]; // première ligne devient dernière colonne
                } else {
                    newBoard[j][i] = this.board[i][this.board.length - j - 1]; // première colonne devient dernière ligne
                }
            }
        }
        return new Piece(this.x, this.y, newBoard);
    }

    public static void main(String[] args){
        int[][] board = new int[3][3];
        board[0][0] = 1;
        board[0][1] = 1;
        board[0][2] = 1;
        board[1][1] = 1;
        board[2][1] = 1;
        Piece p = new Piece(1, 2, board);
        System.out.println(p);
        p.showBoard();
        System.out.println("----------------------------------------");
        Piece p2 = p.rotatePiece(Rotate.PLUS_90_DEGREES);
        p2.showBoard();
        System.out.println("----------------------------------------");
        p2 = p2.rotatePiece(Rotate.MINUS_90_DEGREES);
        p2.showBoard();
    }

}
