package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.chains.Chain;
import model.chains.CollisionChain;
import model.chains.InBoardChain;
import piecesPuzzle.Piece;
import piecesPuzzle.observer.AbstractListenableModel;
import piecesPuzzle.observer.ModelListener;

/**
 * Classe représentant le plateau d'un jeu de pièce.
 */
public class PlateauPuzzle extends AbstractListenableModel implements ModelListener, Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Liste contenant les pièces.
     */
    private List<Piece> pieces = new ArrayList<>();

    /**
     * Dimensions du plateau.
     */
    private int width, height;

    /**
     * Chaîne de responsabilité pour vérifier l'action du joueur.
     */
    private Chain actionResponsabilityChain;

    /**
     * Constructeur.
     * 
     * @param width                     largeur du plateau
     * @param height                    hauteur du plateau
     * @param actionResponsabilityChain chaîne de responsabilité pour vérifier
     *                                  l'action du joueur
     */
    public PlateauPuzzle(int width, int height, Chain actionResponsabilityChain) {
        this.width = width;
        this.height = height;
        this.actionResponsabilityChain = actionResponsabilityChain;
    }

    /**
     * Constructeur par défaut. Initialise la chaîne de responsabilité par défaut.
     * 
     * @param width  largeur du plateau
     * @param height hauteur du plateau
     */
    public PlateauPuzzle(int width, int height) {
        this(width, height, new InBoardChain(new CollisionChain(null)));
    }

    /**
     * Récupère la largeur du plateau.
     * 
     * @return largeur du plateau
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Récupère la hauteur du plateau.
     * 
     * @return hauteur du plateau
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Récupère la chaîne de responsabilité vérifiant les actions du joueur.
     * 
     * @return chaîne de responsabilité
     */
    public Chain getActionResponsabilityChain() {
        return this.actionResponsabilityChain;
    }

    /**
     * Affiche le plateau sur la sortie standard.
     */
    public void showBoard() {
        for (int i = 0; i < this.height; i++) {
            System.out.print("[");
            for (int j = 0; j < this.width; j++) {
                boolean here = false;
                for (Piece p : this.pieces) {
                    here = here || p.occupiesInBoard(j, i);
                }
                if (j == this.width - 1) {
                    System.out.print((here ? "1" : "0"));
                } else {
                    System.out.print((here ? "1" : "0") + " | ");
                }
            }
            System.out.println("]");
        }
    }

    /**
     * Récupère le nombre de pièces contenues sur le plateau.
     * 
     * @return nombre de pièces
     */
    public int getNumberOfPiece() {
        return this.pieces.size();
    }

    /**
     * Récupère la pièce à un certain indice.
     * 
     * @param index indice de la pièce à récupérer
     * @return pièce posée à l'indice choisi
     */
    public Piece getPiece(int index) {
        return this.pieces.get(index);
    }

    /**
     * Met à jour le plateau.
     */
    public void update() {
        this.fireChange();
    }

    /**
     * Permet d'ajouter une pièce à la liste en vérifiant si elle peut être placée.
     * 
     * @param piece pièce à ajouter
     * @return booléen représentant si la pièce à été ajouté à la liste ou non
     * @see #pieces
     * @see #collision(Piece)
     */
    public boolean addPiece(Piece piece) {
        if (!this.actionResponsabilityChain.performAction(this, piece))
            return false;
        if (!this.pieces.add(piece))
            return false;
        this.fireChange();
        return true;
    }

    /**
     * Vérifie si une pièce est en collision avec les autres pièces déjà présentes
     * sur le plateau.
     * 
     * @param piece pièce à vérifier
     * @return booléen représentant s'il y a au moins une collision
     */
    public boolean collision(Piece piece) {
        for (Piece p : this.pieces) {
            if (!p.equals(piece)) {
                // on prend les coordonnées minimaux et maximaux
                int minX = Math.min(p.getX(), piece.getX());
                int maxX = Math.max(p.getX() + p.getWidth(), piece.getX() + piece.getWidth());
                int minY = Math.min(p.getY(), piece.getY());
                int maxY = Math.max(p.getY() + p.getHeight(), piece.getY() + piece.getHeight());

                // on vérifie pour chaque place du plus grand rectangle englobant les 2 pièces
                for (int i = minX; i < maxX; i++) {
                    for (int j = minY; j < maxY; j++) {
                        if (piece.occupiesInBoard(i, j) && p.occupiesInBoard(i, j)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Tourne la pièce choisie dans un angle choisi en vérifiant si cela est
     * possible.
     * 
     * @param pieceToTurn   pièce à tourner
     * @param rotationAngle l'angle pour la rotation
     * @return booléen représentant le fait que la rotation a été effectuée ou non
     */
    public boolean rotatePiece(Piece pieceToTurn, Piece.Rotate rotationAngle) {
        boolean toAdd = !this.pieces.contains(pieceToTurn);
        boolean plus90Degrees = rotationAngle.equals(Piece.Rotate.PLUS_90_DEGREES);

        pieceToTurn.rotate(rotationAngle);
        if (toAdd) {
            if (!this.addPiece(pieceToTurn)) {
                pieceToTurn.rotate((plus90Degrees ? Piece.Rotate.MINUS_90_DEGREES : Piece.Rotate.PLUS_90_DEGREES));
                return false;
            }
        } else {
            if (!this.actionResponsabilityChain.performAction(this, pieceToTurn)) {
                pieceToTurn.rotate((plus90Degrees ? Piece.Rotate.MINUS_90_DEGREES : Piece.Rotate.PLUS_90_DEGREES));
                return false;
            }
        }
        this.fireChange();
        return true;
    }

    /**
     * Déplace la pièce choisie selon les valeurs données.
     * 
     * @param pieceToTranslate pièce à déplacer
     * @param dx               déplacement de la pièce sur l'axe des X
     * @param dy               déplacement de la pièce sur l'axe des Y
     * @return booléen représentant le fait que la rotation a été effectuée ou non
     */
    public boolean translatePiece(Piece pieceToTranslate, int dx, int dy) {
        boolean toAdd = !this.pieces.contains(pieceToTranslate);

        pieceToTranslate.translate(dx, dy);
        if (toAdd) {
            if (!this.addPiece(pieceToTranslate)) {
                pieceToTranslate.translate(-dx, -dy);
                return false;
            }
        } else {
            if (!this.actionResponsabilityChain.performAction(this, pieceToTranslate)) {
                pieceToTranslate.translate(-dx, -dy);
                return false;
            }
        }
        this.fireChange();
        return true;
    }

    @Override
    public void somethingHasChanged(Object arg0) {
        this.fireChange();
    }

    /**
     * Calcule le score de l'état actuel du plateau.
     * 
     * @return score du plateau
     */
    public int calculateScore() {
        int minX = this.width;
        int minY = this.height;
        int maxX = 0;
        int maxY = 0;
        for (Piece piece : this.pieces) {
            if (piece.getX() < minX) {
                minX = piece.getX();
            }
            if (piece.getX() + piece.getWidth() > maxX) {
                maxX = piece.getX() + piece.getWidth();
            }
            if (piece.getY() < minY) {
                minY = piece.getY();
            }
            if (piece.getY() + piece.getHeight() > maxY) {
                maxY = piece.getY() + piece.getHeight();
            }
        }
        return (maxX - minX) * (maxY - minY);
    }
}
