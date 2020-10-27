package piecesPuzzle;

import piecesPuzzle.observer.AbstractListenableModel;

/**
 * Classe abstraite définissant une pièce générale pouvant être utilisée dans n'importe quel jeu de puzzle.
 */
public abstract class PieceImplementation extends AbstractListenableModel implements Piece {
    /**
     * Coordonnées et dimensions de la pièce.
     */
    protected int x, y, width, height;

    /**
     * Tableau contenant les blocs de la pièce.
     */
    protected boolean board[][];

    /**
     * Représente le type de la pièce.
     */
    protected String pieceType;
 
    /**
     * Constructeur. Initialise un tableau 2D vide à la taille {@code height} sur {@code width}.
     * @param x coordonnée en x
     * @param y coordonnée en y
     * @param width largeur de la pièce
     * @param height hauteur de la pièce
     */
    public PieceImplementation(int x, int y, int width, int height, String pieceType){
        this.pieceType = pieceType;
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

    @Override
    public int getX() {
        return this.x;
    }

    /**
     * Met à jour la coordonnée en x.
     * @param x nouvelle coorodnnée en x
     */
    protected void setX(int x){
        this.x = x;
        this.fireChange();
    }

    @Override
    public int getY() {
        return this.y;
    }

    /**
     * Met à jour la coordonnée en y.
     * @param y nouvelle coorodnnée en y
     */
    protected void setY(int y){
        this.y = y;
        this.fireChange();
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * Met à jour la largeur de la pièce.
     * @param width nouvelle largeur de la pièce
     */
    protected void setWidth(int width){
        this.width = width;
        this.fireChange();
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     * Met à jour la hauteur de la pièce.
     * @param height nouvelle hauteur de la pièce
     */
    protected void setHeight(int height){
        this.height = height;
        this.fireChange();
    }

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
        this.fireChange();
    }

    /**
     * Vérifie que les coordonnées données se trouvent bien à l'intérieur du tableau.
     * @param x coordonnée en x
     * @param y coordonnée en y
     * @throws IllegalArgumentException levée lorsque les coordonnées passées ne permettent pas d'accéder à un élément du tableau
     */
    protected void verifyCoordinates(int x, int y) throws IllegalArgumentException {
        if(x < 0 || x >= this.height){
            throw new IllegalArgumentException("the x arg is invalid");
        }
        if(y < 0 || y >= this.width){
            throw new IllegalArgumentException("the y arg is invalid");
        }
    }

    @Override
    public void setBoardValueAtPosition(int x, int y, boolean value) throws IllegalArgumentException {
        this.verifyCoordinates(x, y);
        this.board[x][y] = value;
        this.fireChange();
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
    public void showCaracteristics(){
        System.out.println("Piece type : " + this.pieceType);
        System.out.println("Coordinates : " + this.toString());
        System.out.println("Dimensions : (" + this.width + ", " + this.height + ")");
    }

    @Override
    public void translate(int dx, int dy){
        this.setX(this.x + dx);
        this.setY(this.y + dy);
        this.fireChange();
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
        this.fireChange();
    }

    @Override
    public boolean occupies(int x, int y) throws IllegalArgumentException {
        this.verifyCoordinates(x, y);
        return this.board[x][y];
    }

    @Override
    public boolean occupiesInBoard(int x, int y) {
        if(x < this.x || x > this.x + this.width){
            return false;
        }
        if(y < this.y || y > this.y + this.height){
            return false;
        }
        x -= this.x;
        y -= this.y;
        try{
            return this.board[y][x];
        } catch(Exception e) {
            return false;
        }
    }
}
