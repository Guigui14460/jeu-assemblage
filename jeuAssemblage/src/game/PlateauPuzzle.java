package game;

import java.util.ArrayList;
import java.util.List;

import piecesPuzzle.*;

/**
 * Classe représentant le plateau d'un jeu de pièce.
 */
public class PlateauPuzzle {
    /**
     * Plateau de jeu.
     */
    private int board[][];

    /**
     * Liste contenant les pièces.
     */
    private List<Piece> listOfPiece;

    /**
     * Constructeur par défaut.
     */
    public PlateauPuzzle() {
        this.board = new int[10][10];
        this.listOfPiece = new ArrayList<>();
    }

    /**
     * Place une pièce sur le plateau.
     * @param piece pièce à placer
     * @param number nombre représentant la pièce
     */
    public void placePiece(Piece piece, int number) {
        List<Integer> coor = new ArrayList<>();
        try {
            boolean[][] pieceBoard = piece.getBoard();
            if (this.board[piece.getX()][piece.getY()] == 0) {
                for (int i = piece.getX(), z = 0; i < pieceBoard.length + piece.getX(); i++, z++) {
                    for (int j = piece.getY(), y = 0; j < pieceBoard[z].length + piece.getY(); j++, y++) {
                        if(this.board[i][j] != 0){
                            throw new Exception("whala la place est prise");
                        }
                        if(pieceBoard[z][y] == true){
                            coor.add(i);
                            coor.add(j);
                            this.board[i][j] = number;
                        }
                    }
                }
            }
        } catch (Exception e) {
            while(!coor.isEmpty()){
                this.board[coor.get(0)][coor.get(1)] = 0;
                coor.remove(0);
                coor.remove(0);              
            }
        }
    }

    /**
     * Créer le plateau de jeu.
     * @param listOfPiece liste de pièce pour initialiser le plateau de jeu
     * @return plateau de jeu
     */
    public static PlateauPuzzle createBoard(List<Piece> listOfPiece) {
        PlateauPuzzle board = new PlateauPuzzle();
        int i = 1;
        for (Piece piece : listOfPiece) {
            board.placePiece(piece, i);
            i++;
        }
        return board;
    }

    /**
     * Récupère le plateau de jeu.
     * @return plateau
     */
    public int[][] getBoard() {
        return this.board;
    }

    /**
     * Ajoute une pièce.
     * @param p pièce à ajouter
     */
    public void addPiece(Piece p) {
        this.listOfPiece.add(p);
    }

}
