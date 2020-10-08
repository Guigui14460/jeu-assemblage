package piecesPuzzle;

/**
 * Classe définissant une pièces générale pouvant être utilisée dans n'importe quel jeu de puzzle.
 */
public class Piece {
    /**
     * Coordonnées de la pièce.
     */
    private int x, y;

    /**
     * Constructeur par défaut.
     * @param x coordonnée en x
     * @param y coordonnée en y
     */
    public Piece(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
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

    public static void main(String[] args){
        Piece p = new Piece(1,2);
        System.out.println(p);
    }

}
