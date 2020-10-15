package displayer;


import piecesPuzzle.*;
import game.*;

public class TerminalDisplayer implements Displayer {
   
    
    private int[][] board;

    public TerminalDisplayer(PlateauPuzzle board){
        this.board = board.getBoard();
        
    }
// \u001b[0m
// \u001b[1m

    public void show(){
        for(int i = 0; i < this.board.length; i++){
            System.out.print("[");
            for(int j = 0; j < this.board[i].length; j++){



                if(j == this.board[i].length - 1){
                    System.out.print("\u001b[1m" + this.board[i][j] + "\u001b[0m");
                } else {
                    System.out.print(this.board[i][j] + " | ");
                }
            }
            System.out.println("]");
        }
    }


}
