import piecesPuzzle.Piece;
import piecesPuzzle.RectanglePiece;
import game.*;

import java.util.ArrayList;

import displayer.*;
public class Main {
    public static void main(String[] args) throws Exception {


        RectanglePiece rec = new RectanglePiece(2, 2,3,3);
        ArrayList<Piece> list = new ArrayList<>();
        list.add(rec);


        Board board = Board.createBoard(list);


        Displayer display = new TerminalDisplayer(board);
        display.show();

    }
}
