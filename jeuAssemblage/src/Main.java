import piecesPuzzle.*;

import game.*;

import java.util.ArrayList;

import displayer.*;

public class Main {
    public static void main(String[] args) throws Exception {


        RectanglePiece rec = new RectanglePiece(1,1,2,3);
        TPiece tp = new TPiece(0,1);
        ArrayList<Piece> list = new ArrayList<>();
        list.add(rec);
        list.add(tp);

        PlateauPuzzle board = PlateauPuzzle.createBoard(list);

        Displayer display = new TerminalDisplayer(board);
        display.show();

    }
}
