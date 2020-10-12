package piecesPuzzle;

/**
 * Classe abstraite définissant une pièce générale pouvant être utilisée dans n'importe quel jeu de puzzle.
 */
public abstract class PieceImplementation implements Piece {
    /**
     * Coordonnées et dimensions de la pièce.
     */
    protected int x, y, width, height;

    /**
     * Tableau contenant les blocs de la pièce.
     */
    protected boolean board[][];
 
    /**
     * Constructeur. Initialise un tableau 2D vide à la taille {@code height} sur {@code width}.
     * @param x coordonnée en x
     * @param y coordonnée en y
     * @param width largeur de la pièce
     * @param heigth hauteur de la pièce
     */
    public PieceImplementation(int x, int y, int width, int height){
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.board = new boolean[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                this.board[i][j] = false;
            }
        }
    }
 
    /**
     * Constructeur. Prend en argument un tableau et initialise les attributs {@code width} et {@code height} en fonction de la taille du tableau 2D.
     * @param x coordonnée en x
     * @param y coordonnée en y
     * @param board tableau contenant les blocs
     */
    public PieceImplementation(int x, int y, boolean[][] board){
        this.setX(x);
        this.setY(y);
        this.setBoard(board);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    /**
     * Récupère la coordonnée en x.
     * @return coordonnée en x
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * Met à jour la coordonnée en x.
     * @param x nouvelle coorodnnée en x
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Récupère la coordonnée en y.
     * @return coordonnée en y
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * Met à jour la coordonnée en y.
     * @param y nouvelle coorodnnée en y
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * Récupère la largeur de la pièce.
     * @return largeur de la pièce
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Met à jour la largeur de la pièce.
     * @param width nouvelle largeur de la pièce
     */
    protected void setWidth(int width){
        this.width = width;
    }

    /**
     * Récupère la hauteur de la pièce.
     * @return hauteur de la pièce
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Met à jour la hauteur de la pièce.
     * @param width nouvelle hauteur de la pièce
     */
    protected void setHeight(int height){
        this.height = height;
    }

    /**
     * Récupère le tableau contenant les blocs.
     * @return tableau contenant les blocs
     */
    @Override
    public boolean[][] getBoard(){
        return this.board;
    }

    /**
     * Remplace le tableau contenant les blocs de la pièce.
     * @param board nouvelle organisation des blocs
     * @throws IllegalArgumentException levée lorsque l'on passe un tableau non initialisé
     */
    protected void setBoard(boolean[][] board) throws IllegalArgumentException {
        if(board == null){
            throw new IllegalArgumentException("can't put a null 2D-array as a board");
        }
        this.board = board;
        this.setWidth(board[0].length);
        this.setHeight(board.length);
    }

    @Override
    public void showBoard(){
        for(int i = 0; i < this.height; i++){
            System.out.print("[");
            for(int j = 0; j < this.width; j++){
                if(j == this.width - 1){
                    System.out.print((this.board[i][j] ? "1" : "0"));
                } else {
                    System.out.print((this.board[i][j] ? "1" : "0") + " | ");
                }
            }
            System.out.println("]");
        }
    }

    @Override
    public void translate(int dx, int dy){
        this.setX(x + dx);
        this.setY(y + dy);
    }

    @Override
    public void rotate(Piece.Rotate nbDegrees){
        int width = this.height, height = this.width;
        boolean[][] newBoard = new boolean[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(nbDegrees.equals(Piece.Rotate.PLUS_90_DEGREES)){
                    newBoard[i][width - j - 1] = this.board[j][i]; // première ligne devient dernière colonne
                } else {
                    newBoard[i][j] = this.board[j][height - i - 1]; // première colonne devient dernière ligne
                }
            }
        }
        this.setWidth(width);
        this.setHeight(height);
        this.setBoard(newBoard);
    }

    /**
     * Vérifie que les coordonnées données se trouvent bien à l'intérieur du tableau.
     * @param x coordonnée en x
     * @param y coordonnée en y
     * @throws IllegalArgumentException levée lorsque les coordonnées passées ne permettent pas d'accéder à un élément du tableau
     */
    protected void verifyCoordinates(int x, int y) throws IllegalArgumentException {
        if(x < 0 || x >= height){
            throw new IllegalArgumentException("the x arg is invalid");
        }
        if(y < 0 || y >= width){
            throw new IllegalArgumentException("the y arg is invalid");
        }
    }

    @Override
    public boolean occupies(int x, int y) throws IllegalArgumentException {
        this.verifyCoordinates(x, y);
        return this.board[x][y];
    }

    /**
     * Met une valeur à la place donnée dans le tableau.
     * @param x coordonnée en x
     * @param y coordonnée en y
     * @param value valeur à placer
     * @throws IllegalArgumentException levée lorsque les coordonnées passées ne permettent pas d'accéder à un élément du tableau
     */
    public void setBoardValueAtPosition(int x, int y, boolean value) throws IllegalArgumentException {
        this.verifyCoordinates(x, y);
        this.board[x][y] = value;
    }
}
