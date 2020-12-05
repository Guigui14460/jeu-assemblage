package jeuAssemblage.model.aiAlgorithms;

import java.util.List;
import java.util.ArrayList;

import jeuAssemblage.Settings;
import jeuAssemblage.model.PlateauPuzzle;
import piecesPuzzle.Piece;
import piecesPuzzle.Piece.Rotate;

/**
 * Algorithme d'intelligence artificielle dérivé de l'algorithme MinMax, utilisé
 * notamment à la théorie des jeux.
 */
public class NegaMin implements AI {
    /**
     * Plateau de jeu.
     */
    private PlateauPuzzle board;

    /**
     * Profondeur de raisonnement de l'algorithme.
     */
    private int depth;

    /**
     * Constructeur.
     * 
     * @param board plateau de jeu
     * @param depth profondeur de raisonnement
     */
    public NegaMin(PlateauPuzzle board, int depth) {
        this.board = board;
        this.depth = depth;
    }

    /**
     * Constructeur. Initialise la profondeur à la valeur par défaut.
     * 
     * @param board plateau de jeu
     */
    public NegaMin(PlateauPuzzle board) {
        this(board, Settings.AI_DEPTH);
    }

    /**
     * Constructeur.
     * 
     * @param depth profondeur de raisonnement
     */
    public NegaMin(int depth) {
        this.depth = depth;
    }

    /**
     * Constructeur. Initialise la profondeur à la valeur par défaut.
     */
    public NegaMin() {
        this(Settings.AI_DEPTH);
    }

    @Override
    public void setBoard(PlateauPuzzle board) {
        this.board = board;
    }

    @Override
    public Capsule start() throws CloneNotSupportedException {
        System.out.println(depth);
        try {
            if (this.depth == 0 || this.board.isFinished()) {
                return new Capsule(this.board, this.board.calculateScore());
            }
            int score = this.board.calculateScore();
            for (Capsule capsule : this.createNode()) {
                if (score >= capsule.getValue()) {
                    Capsule val = new NegaMin(capsule.getBoard(), this.depth - 1).start();
                    score = val.getValue();
                    this.board = val.getBoard();
                }
            }
            return new Capsule(this.board, score);
        } catch (CloneNotSupportedException e) {
            System.out.println("Bug clonage : " + e);
        }
        return null;
    }

    /**
     * Génère toutes les actions possibles pour un noeud. On peut voir cela comme la
     * creation de tous les noeuds fils d'un certain noeud donné.
     * 
     * @param base plateau actuellement dans la pile de récusivité
     * @return liste des noeuds fils
     */
    private List<Capsule> createNode() {
        List<Capsule> capsules = new ArrayList<>();
        try {
            PlateauPuzzle tmpBoard;
            for (int i = 0; i < this.board.getNumberOfPiece(); i++) {
                Piece p = this.board.getPiece(i);
                for (int j = -p.getX(); j < this.board.getWidth() - p.getWidth() - p.getX(); j++) {
                    for (int k = -p.getY(); k < this.board.getHeight() - p.getHeight() - p.getY(); k++) {
                        for (Rotate rotationAngle : Rotate.values()) {
                            for (int l = 0; l <= 2; l++) {
                                if (!(j == 0 && k == 0
                                        && (l == 0 || (l == 2 && Rotate.MINUS_90_DEGREES.equals(rotationAngle))))) {
                                    tmpBoard = board.clone();
                                    if (tmpBoard.translateAndRotatePiece(tmpBoard.getPiece(i), j, k, rotationAngle,
                                            l)) {
                                        capsules.add(new Capsule(tmpBoard, tmpBoard.calculateScore()));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return capsules;
    }
}
