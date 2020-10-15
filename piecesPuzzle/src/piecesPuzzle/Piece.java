package piecesPuzzle;

/**
 * Interface décrivant les caractéristiques d'une pièce.
 */
public interface Piece {
    /**
     * Énumérateur permettant de définir les angles dans lesquels les pièces peuvent tourner.
     */
    public enum Rotate {
        PLUS_90_DEGREES, MINUS_90_DEGREES
    };

    /**
     * Affiche le tableau contenant les blocs de la pièce.
     * Les 1 correspond aux blocs et les 0, au vide.
     */
    public void showBoard();

    /**
     * Déplace une pièce.
     * @param dx écart sur l'axe des abscisses
     * @param dy écart sur l'axe des ordonnées
     */
    public void translate(int dx, int dy);

    /**
     * Tourne la pièce.
     * @param nbDegrees nombre de degrès à faire tourner la pièce
     * @return la même pièce retournée
     */
    public void rotate(Piece.Rotate nbDegrees);

    /**
     * Vérifie si un bloc de la pièce occupe une certaine place dans le tableau interne de la pièce.
     * @param x coordonnées en x
     * @param y coordonnées en y
     * @return boolean vérifiant l'occupation d'un bloc à la place à tester
     * @throws IllegalArgumentException levée lorsque les coordonnées passées ne permettent pas d'accéder à un élément du tableau
     */
    public boolean occupies(int x, int y) throws IllegalArgumentException;

    /**
     * Récupère la coordonnée en x.
     * @return coordonnée en x
     */
    public int getX();
    public int getX();

    /**
     * Récupère la coordonnée en y.
     * @return coordonnée en y
     */
    public int getY();

    /**
     * Récupère le tableau contenant les blocs.
     * @return tableau contenant les blocs
     */
    public boolean[][] getBoard();
}
