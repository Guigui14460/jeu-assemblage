package jeuAssemblage.model.aiAlgorithms;

import java.util.List;
import java.util.ArrayList;
import jeuAssemblage.model.PlateauPuzzle;
import piecesPuzzle.Piece;

public class NegaMax {

    private int nb_coup;
    private PlateauPuzzle board, otherBoard;
    private Integer score, x, y;
    private int profondeur;
    private Integer nb_Pieces;

    public NegaMax(PlateauPuzzle board, int profondeur) throws CloneNotSupportedException {
        this.score = 0;
        this.profondeur = profondeur;
        this.board = board;
        this.x = this.board.getWidth();
        this.y = this.board.getHeight();
        this.nb_Pieces = this.board.getNumberOfPiece();

    }

    public Capsule start() {
        try {

            if (this.profondeur == 0 || this.board.isFinished()) {

                return new Capsule(this.board, this.board.calculateScore());
            } else {

                this.score = Integer.MIN_VALUE;

                for (Capsule p : this.createNode(this.board)) {
                    // p.getBoard().showBoard();
                    this.score = this.board.calculateScore();
                    Capsule val = new NegaMax(p.getBoard(), this.profondeur - 1).start();
                    System.out.println("(" + this.score + ";" + val.getValue() + ")");

                    if (this.score < val.getValue()) {
                        System.out.println("a");
                        this.score = val.getValue();
                        this.board = p.getBoard();
                    }

                }
            }

        } catch (CloneNotSupportedException e) {
            System.out.println("bug:");
            System.out.println(e);
        }

        return new Capsule(this.board, this.score);
    }

    public List<Capsule> createNode(PlateauPuzzle base) {
        List<Capsule> r = new ArrayList<>();
        try {

            PlateauPuzzle otherBoard = this.board.clone();
            List<Piece> listOfOtherBoard = new ArrayList<>();
            listOfOtherBoard.addAll(otherBoard.getPieces());

            for (int i = 0; i < this.board.getPieces().size(); i++) {

                Piece p = listOfOtherBoard.get(i);

                // for (int j = 0; j < this.x; j++) {
                //     for (int k = 0; k < this.y; k++) {
                //         // System.out.println("test1 ");
                       
                //         if (otherBoard.translatePiece(p, j, k)) {

                //             r.add(new Capsule(otherBoard, otherBoard.calculateScore()));
                //             // System.out.println("test 2 ");
                //             otherBoard = this.board.clone();
                //             listOfOtherBoard.clear();
                //             listOfOtherBoard.addAll(otherBoard.getPieces());

                //         }
                //         // System.out.println("test 3 ");
                //     }
                // }



               

                int max = this.x * this.y - 1;
                int x = p.getX();
                int y = p.getY();

                System.out.println("p=" + p);

                for(int j = x;j < this.x;j++){
                 
                    for (int j2 = y; j2 < this.y; j2++) {
                        if (otherBoard.translatePiece(p, j, j2)) {
                            // System.out.println("(" + j+ ":"+ j2+")");
                            r.add(new Capsule(otherBoard, otherBoard.calculateScore()));           
                            otherBoard = this.board.clone();
                            listOfOtherBoard.clear();
                            listOfOtherBoard.addAll(otherBoard.getPieces());
                            System.out.println("p2=" + p );
                        }
                    }
                    for (int j2 = y; j2 >= 0 ; j2--) {
                        if (otherBoard.translatePiece(p, j, j2)) {
                            // System.out.println("(" + j+ ":"+ j2+")");
                            r.add(new Capsule(otherBoard, otherBoard.calculateScore()));           
                            otherBoard = this.board.clone();
                            listOfOtherBoard.clear();
                            listOfOtherBoard.addAll(otherBoard.getPieces());
                            System.out.println("p2=" + p );
                        }
                    }
                }
                       
                for(int j = x;j >= 0;j--){
                  
                    for (int j2 = y; j2 < this.y; j2++) {
                        if (otherBoard.translatePiece(p, j, j2)) {
                            // System.out.println("(" + j+ ":"+ j2+")");
                            r.add(new Capsule(otherBoard, otherBoard.calculateScore()));           
                            otherBoard = this.board.clone();
                            listOfOtherBoard.clear();
                            listOfOtherBoard.addAll(otherBoard.getPieces());
                            System.out.println("p2=" + p );
                        }
                    }
                    for (int j2 = y; j2 >= 0 ; j2--) {
                        if (otherBoard.translatePiece(p, j, j2)) {
                            // System.out.println("(" + j+ ":"+ j2+")");
                            r.add(new Capsule(otherBoard, otherBoard.calculateScore()));           
                            otherBoard = this.board.clone();
                            listOfOtherBoard.clear();
                            listOfOtherBoard.addAll(otherBoard.getPieces());
                            System.out.println("p2=" + p );
                        }
                    }
                }   
          
            }      
        } catch (Exception e) {
            System.out.println(e);
        }
    
    
        return r;
    }

}
