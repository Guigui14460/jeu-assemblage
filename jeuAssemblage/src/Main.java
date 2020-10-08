import piecesPuzzle.Piece;
import game.*;

import java.util.ArrayList;
import java.util.ArrayList;
import displayer.*;
public class Main {
    public static void main(String[] args) throws Exception {


        Board board = Board.createBoard(new ArrayList<Piece>(new Piece(1,2)));


        Displayer display = new TerminalDisplayer(board);

    }
}
