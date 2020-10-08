package Board;

import java.util.ArrayList;
import java.util.List;
import piecesPuzzle.*;

public class Board {


    private int board[][];
    private List<Piece> listOfPiece; 



    public Board(){
        this.board = new int[50][50];
        this.listOfPiece = new ArrayList<>();
    }

    public Board placePiece(Piece piece,int number){
        this.board[piece.getX()][piece.getY()] = number;
    }


    public static void createBoard(List<Piece> listOfPiece){
        Board board = new Board();
        int i = 0;
        for(Pïece piece : listOfPiece){
            board.placePiece(piece, i);
            i++;
        }

    }





    
    








    
}
