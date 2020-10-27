package model;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import piecesPuzzle.InvertedLPiece;
import piecesPuzzle.LPiece;
import piecesPuzzle.Piece;
import piecesPuzzle.RectanglePiece;
import piecesPuzzle.TPiece;

/**
 * Usine de pièce utilisant une stratégie aléatoire de création de pièces.
 */
public class RandomPieceFactory implements PieceAbstractFactory {
    /**
     * Coordonnées et dimensions de la pièce à créer.
     */
    private int x, y, width, height;

    /**
     * Liste des classes de pièces créables.
     */
    private static List<Class<?>> pieceClasses = new ArrayList<>();

    /**
     * Générateur pseudo-aléatoire.
     */
    private static Random random = new Random();

    static {
        pieceClasses.add(RectanglePiece.class);
        pieceClasses.add(TPiece.class);
        pieceClasses.add(LPiece.class);
        pieceClasses.add(InvertedLPiece.class);
    }

    /**
     * Constructeur.
     * @param maxX max X
     * @param maxY max Y
     * @param maxWidth largeur maximum
     * @param maxHeight hauteur maximum
     */
    public RandomPieceFactory(int maxX, int maxY, int maxWidth, int maxHeight) {
        this.x = (maxX == 0 ? 0 : RandomPieceFactory.random.nextInt(maxX));
        this.y = (maxY == 0 ? 0 : RandomPieceFactory.random.nextInt(maxY));
        this.width = RandomPieceFactory.random.nextInt(maxWidth);
        this.height = RandomPieceFactory.random.nextInt(maxHeight);
    }

    /**
     * Constructeur. Initialise les pièces par défaut.
     * @param maxX max X
     * @param maxY max Y
     */
    public RandomPieceFactory(int maxX, int maxY) {
        this.x = (maxX == 0 ? 0 : RandomPieceFactory.random.nextInt(maxX));
        this.y = (maxY == 0 ? 0 : RandomPieceFactory.random.nextInt(maxY));
        this.width = -1;
        this.height = -1;
    }

    @Override
    public Piece createPiece() throws Exception {
        // choix de la classe à instancier
        Class<?> pieceClassChosen = RandomPieceFactory.pieceClasses.get(RandomPieceFactory.random.nextInt(RandomPieceFactory.pieceClasses.size()));
        if(this.width != -1){ // si on veut une pièce paramétrée
            if(TPiece.class.equals(pieceClassChosen)){
                // changements pour éviter les exceptions pour la pièce en T
                if(this.width < 3){
                    this.width = 3;
                } else if(this.width % 2 == 0){
                    this.width += 1;
                }
                if(this.height < 3){
                    this.height = 3;
                } else if(this.height % 2 == 0){
                    this.height += 1;
                }
            }
            if(LPiece.class.equals(pieceClassChosen) || InvertedLPiece.class.equals(pieceClassChosen)){
                // on modifie pour que ça ressemble à un L (ou L inversé)
                if(this.width < 2){
                    this.width = 2;
                }
                if(this.height < 2){
                    this.height = 2;
                }
            }
        }

        // instanciation de la pièce
        Constructor<?>[] constructors = pieceClassChosen.getConstructors();
        for(Constructor<?> constructor: constructors){
            if(this.width == -1){ // pièce par défaut
                if(constructor.getParameterCount() == 2){
                    return (Piece) constructor.newInstance(this.x, this.y);
                }
            } else { // pièce paramétrée
                if(constructor.getParameterCount() == 4){
                    return (Piece) constructor.newInstance(this.x, this.y, this.width, this.height);
                }
            }
        }
        throw new InstantiationException("cannot instanciate a piece");
    }
}
