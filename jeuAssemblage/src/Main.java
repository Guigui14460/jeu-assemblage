import piecesPuzzle.*;

import game.*;

import java.util.ArrayList;

import displayer.*;

public class Main {
    public static void main(String[] args) throws Exception {


        RectanglePiece rec = new RectanglePiece(1, 1, 2, 3);
        TPiece tp = new TPiece(0, 1, 3, 5);
        ArrayList<Piece> list = new ArrayList<>();
        list.add(rec);
        list.add(tp);

        long time = System.nanoTime();
        PlateauPuzzle board = PlateauPuzzle.createBoard(list);
        time = System.nanoTime() - time;
        System.out.println("Tps exec partie Coco : " + time);

        Displayer display = new TerminalDisplayer(board);
        display.show();


        // partie Guigui
        time = System.nanoTime();
        PlateauPuzzle2 board2 = new PlateauPuzzle2(10, 10);
        board2.addPiece(rec);
        board2.addPiece(tp);
        time = System.nanoTime() - time;
        System.out.println("Tps exec partie Guigui : " + time);
        board2.showBoard();
    }
}
