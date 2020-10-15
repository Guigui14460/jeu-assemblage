package game;

import java.util.ArrayList;
import java.util.List;
import piecesPuzzle.*;

public class PlateauPuzzle {

    private int board[][];
    private List<Piece> listOfPiece;

    public PlateauPuzzle() {
        this.board = new int[10][10];
        this.listOfPiece = new ArrayList<>();
    }

    public void placePiece(Piece piece, int number) {
        try {
            boolean[][] pieceBoard = piece.getBoard();
            
            
            if (this.board[piece.getX()][piece.getY()] == 0) {

                for (int i = piece.getX(), z = 0; i < pieceBoard.length + piece.getX(); i++, z++) {
                    for (int j = piece.getY(), y = 0; j < pieceBoard[z].length + piece.getY(); j++, y++) {

                        if(this.board[i][j] != 0){
                            throw new Exception("zezrez");
                        }

                        if(pieceBoard[z][y] == true){
                            this.board[i][j] = number;
                        }
                        
                    }
                }

            }

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("wrong place for the piece");
        }

    }

    public static PlateauPuzzle createBoard(List<Piece> listOfPiece) {
        PlateauPuzzle board = new PlateauPuzzle();
        int i = 1;

        for (Piece piece : listOfPiece) {
            board.placePiece(piece, i);
            i++;
        }
        return board;
    }

    public int[][] getBoard() {
        return this.board;
    }

    public void addPiece(Piece p) {
        this.listOfPiece.add(p);
    }

}