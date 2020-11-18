package piecesPuzzle;

import piecesPuzzle.observer.ListenableModel;

/**
 * Interface décrivant les caractéristiques d'une pièce.
 */
public interface Piece extends ListenableModel {
    /**
     * Énumérateur permettant de définir les angles dans lesquels les pièces peuvent
     * tourner.
     */
    public enum Rotate {
        PLUS_90_DEGREES, MINUS_90_DEGREES
    };

    /**
     * Récupère la coordonnée en x.
     * 
     * @return coordonnée en x
     */
    public int getX();

    /**
     * Récupère la coordonnée en y.
     * 
     * @return coordonnée en y
     */
    public int getY();

    /**
     * Récupère le tableau contenant les blocs.
     * 
     * @return tableau contenant les blocs
     */
    public boolean[][] getBoard();

    /**
     * Récupère le type de la pièce.
     * 
     * @return type de la pièce
     */
    public String getPieceType();

    /**
     * Récupère la largeur de la pièce.
     * 
     * @return largeur de la pièce
     */
    public int getWidth();

    /**
     * Récupère la hauteur de la pièce.
     * 
     * @return hauteur de la pièce
     */
    public int getHeight();

    /**
     * Affiche le tableau contenant les blocs de la pièce. Les 1 correspond aux
     * blocs et les 0, au vide.
     */
    public void showBoard();

    /**
     * Affiche les caractéristiques de la pièce.
     */
    public void showCaracteristics();

    /**
     * Met une valeur à la place donnée dans le tableau.
     * 
     * @param x     coordonnée en x
     * @param y     coordonnée en y
     * @param value valeur à placer
     * @throws IllegalArgumentException levée lorsque les coordonnées passées ne
     *                                  permettent pas d'accéder à un élément du
     *                                  tableau
     */
    public void setBoardValueAtPosition(int x, int y, boolean value) throws IllegalArgumentException;

    /**
     * Déplace une pièce.
     * 
     * @param dx écart sur l'axe des abscisses
     * @param dy écart sur l'axe des ordonnées
     */
    public void translate(int dx, int dy);

    /**
     * Tourne la pièce.
     * 
     * @param nbDegrees nombre de degrès à faire tourner la pièce
     */
    public void rotate(Piece.Rotate nbDegrees);

    /**
     * Vérifie si un bloc de la pièce occupe une certaine place dans le tableau
     * interne de la pièce.
     * 
     * @param x coordonnées en x
     * @param y coordonnées en y
     * @return boolean vérifiant l'occupation d'un bloc à la place à tester
     * @throws IllegalArgumentException levée lorsque les coordonnées passées ne
     *                                  permettent pas d'accéder à un élément du
     *                                  tableau
     */
    public boolean occupies(int x, int y) throws IllegalArgumentException;

    /**
     * Vérifie si un bloc de la pièce occupe une certaine place dans le tableau
     * interne de la pièce en fonction de la place de la pièce dans un plateau de
     * jeu.
     * 
     * @param x coordonnées en x correspond à une place d'un plateau de jeu
     * @param y coordonnées en y correspond à une place d'un plateau de jeu
     * @return boolean vérifiant l'occupation d'un bloc à la place à tester en
     *         fonction de la place de la pièce dans un plateau
     */
    public boolean occupiesInBoard(int x, int y) throws IllegalArgumentException;
}
