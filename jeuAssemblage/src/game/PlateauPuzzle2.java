package game;

import java.util.HashSet;
import java.util.Set;

import piecesPuzzle.Piece;
import piecesPuzzle.observer.AbstractListenableModel;
import piecesPuzzle.observer.ModelListener;

// TODO: à mettre dans la partie modèle

/**
 * Classe représentant le plateau d'un jeu de pièce.
 */
public class PlateauPuzzle2 extends AbstractListenableModel implements ModelListener {
    /**
     * Liste contenant les pièces.
     */
    private Set<Piece> pieces = new HashSet<>();

    /**
     * Dimensions du plateau.
     */
    private int width, height;

    /**
     * Constructeur par défaut.
     * @param width largeur du plateau
     * @param height hauteur du plateau
     */
    public PlateauPuzzle2(int width, int height){
        this.width = width;
        this.height = height;
    }

    /**
     * Affiche le plateau sur la sortie standard.
     */
    public void showBoard(){
        for(int i = 0; i < this.height; i++){
            System.out.print("[");
            for(int j = 0; j < this.width; j++){
                boolean here = false;
                for(Piece p: this.pieces){
                    here = here || p.occupiesInBoard(j, i);
                }
                if(j == this.width - 1){
                    System.out.print((here ? "1" : "0"));
                } else {
                    System.out.print((here ? "1" : "0") + " | ");
                }
            }
            System.out.println("]");
        }
    }

    /**
     * Permet d'ajouter une pièce à la liste en vérifiant si elle peut être placée.
     * @param piece pièce à ajouter
     * @return booléen représentant si la pièce à été ajouté à la liste ou non
     * @see #pieces
     * @see #collision(Piece)
     */
    public boolean addPiece(Piece piece){
        if(piece.getX() < 0 || piece.getY() < 0){
            return false;
        }
        if(piece.getX() + piece.getWidth() >= this.width || piece.getY() + piece.getHeight() >= this.height){
            return false;
        }
        if(this.collision(piece)){
            return false;
        }
        boolean added = this.pieces.add(piece);
        this.fireChange();
        return added;
    }

    /**
     * Vérifie si une pièce est en collision avec les autres pièces déjà présentes sur le plateau.
     * @param piece pièce à vérifier
     * @return booléen représentant s'il y a au moins une collision
     */
    public boolean collision(Piece piece){
        for(Piece p: this.pieces){
            if(!p.equals(piece)){
                int minX = Math.min(p.getX(), piece.getX());
                int maxX = Math.max(p.getX() + p.getWidth(), piece.getX() + piece.getWidth());
                int minY = Math.min(p.getY(), piece.getY());
                int maxY = Math.max(p.getY() + p.getHeight(), piece.getY() + piece.getHeight());

                for(int i = minX; i < maxX; i++){
                    for(int j = minY; j < maxY; j++){
                        if(piece.occupiesInBoard(i, j) && p.occupiesInBoard(i, j)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean rotatePiece(Piece pieceToTurn, Piece.Rotate rotationAngle){
        boolean toAdd = !this.pieces.contains(pieceToTurn);
        boolean plus90Degrees = rotationAngle.equals(Piece.Rotate.PLUS_90_DEGREES);
    
        pieceToTurn.rotate(rotationAngle);
        if(toAdd){
            if(!this.addPiece(pieceToTurn)){
                pieceToTurn.rotate((plus90Degrees ? Piece.Rotate.MINUS_90_DEGREES : Piece.Rotate.PLUS_90_DEGREES));
                return false;
            }
        } else {
            this.pieces.remove(pieceToTurn);
            if(!this.addPiece(pieceToTurn)){
                this.pieces.add(pieceToTurn);
                pieceToTurn.rotate((plus90Degrees ? Piece.Rotate.MINUS_90_DEGREES : Piece.Rotate.PLUS_90_DEGREES));
                return false;
            }
        }
        this.fireChange();
        return true;
    }

    public boolean translatePiece(Piece pieceToTranslate, int dx, int dy){
        boolean toAdd = !this.pieces.contains(pieceToTranslate);
    
        pieceToTranslate.translate(dx, dy);
        if(toAdd){
            if(!this.addPiece(pieceToTranslate)){
                pieceToTranslate.translate(-dx, -dy);
                return false;
            }
        } else {
            this.pieces.remove(pieceToTranslate);
            if(!this.addPiece(pieceToTranslate)){
                this.pieces.add(pieceToTranslate);
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
}
