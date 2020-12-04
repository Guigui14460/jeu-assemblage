package jeuAssemblage.model.aiAlgorithms;

import java.util.List;
import java.util.ArrayList;

import jeuAssemblage.model.PlateauPuzzle;
import piecesPuzzle.Piece;
import piecesPuzzle.Piece.Rotate;

public class NegaMin {
    private PlateauPuzzle board;
    private int profondeur;

    public NegaMin(PlateauPuzzle board, int profondeur) throws CloneNotSupportedException {
        this.profondeur = profondeur;
        this.board = board;
    }

    public Capsule start() {
        try {
            if (this.profondeur == 0 || this.board.isFinished()) {
                return new Capsule(this.board, this.board.calculateScore());
            } else {
                int score = this.board.calculateScore();
                for (Capsule p : this.createNode(this.board)) {
                    Capsule val = new NegaMin(p.getBoard(), this.profondeur - 1).start();
                    System.out.println("(" + score + ";" + val.getValue() + ")");

                    if (score > val.getValue()) {
                        System.out.println("a");
                        score = val.getValue();
                        this.board = val.getBoard();
                    }
                }
            }
        } catch (CloneNotSupportedException e) {
            System.out.println("bug:");
            System.out.println(e);
        }
        return new Capsule(this.board, this.board.calculateScore());
    }

    public List<Capsule> createNode(PlateauPuzzle base) {
        List<Capsule> capsules = new ArrayList<>();
        try {
            for (int i = 0; i < this.board.getNumberOfPiece(); i++) {
                Piece p = this.board.getPiece(i);

                int x = p.getX();
                int y = p.getY();
                System.out.println("P=" + p);
                for (int j = -this.board.getWidth() + 1; j < this.board.getWidth(); j++) {
                    for (int k = -this.board.getHeight() + 1; k < this.board.getHeight(); k++) {
                        for (Rotate rotationAngle : Rotate.values()) {
                            for (int l = 0; l <= 2; l++) {
                                if (!(j == 0 && k == 0
                                        && (l == 0 || (l == 2 && Rotate.MINUS_90_DEGREES.equals(rotationAngle))))) {
                                    Capsule tmpBoard = new Capsule(board.clone(), 0);
                                    if (tmpBoard.getBoard().translateAndRotatePiece(tmpBoard.getBoard().getPiece(i), j,
                                            k, rotationAngle, l)) {
                                        capsules.add(tmpBoard);
                                        System.out.println(j + "," + k + " : " + l + "x " + rotationAngle);
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
