package displayer;


import piecesPuzzle.*;
import game.*;

public class TerminalDisplayer implements Displayer {
   
    
    private Board board;

    public TerminalDisplayer(Board board){
        this.board = board;
    }

    public void show(){
        for(int i = 0; i < this.board.length; i++){
            System.out.print("[");
            for(int j = 0; j < this.board[i].length; j++){
                if(j == this.board[i].length - 1){
                    System.out.print(this.board[i][j]);
                } else {
                    System.out.print(this.board[i][j] + " | ");
                }
            }
            System.out.println("]");
        }
    }


}
