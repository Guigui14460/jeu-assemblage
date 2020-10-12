package game;

import java.util.ArrayList;
import java.util.List;
import piecesPuzzle.*;

public class Board {


    private int board[][];
    private List<Piece> listOfPiece; 



    public Board(){
        this.board = new int[10][10];
        this.listOfPiece = new ArrayList<>();
    }

    public void placePiece(Piece piece,int number){
        try {
            int[][] pieceBoard = piece.getBoard();

            if(this.board[piece.getX()][piece.getY()] == 0){
                for(int i = piece.getX(); i < pieceBoard.length + piece.getX();i++){
                    for (int j = piece.getY(); j < pieceBoard[i].length + piece.getY(); j++) {
                        this.board[i][j] = number;
                       
                    }
                }

            }
            
           
        } catch (Exception e) {
            System.out.println("wrong place for the piece");
        }
       
       
    }


    public static Board createBoard(List<Piece> listOfPiece){
        Board board = new Board();
        int i = 1;
        
        for(Piece piece : listOfPiece){
            board.placePiece(piece, i);
            i++;
        }
        return board;
    }

    public int[][] getBoard(){
        return this.board;
    }

    public void addPiece(Piece p){
        this.listOfPiece.add(p);
    }




    
    








    
}
