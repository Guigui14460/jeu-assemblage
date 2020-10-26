import piecesPuzzle.*;
import piecesPuzzle.Piece.Rotate;
import view.TerminalView;
import game.*;
import model.PieceFactory;
import model.RandomPieceFactory;

import java.util.ArrayList;

import displayer.*;

public class Main {
    public static void main(String[] args) throws Exception {
        RectanglePiece rec = new RectanglePiece(1, 1, 2, 3);
        TPiece tp = new TPiece(0, 1, 3, 5);

        // ArrayList<Piece> list = new ArrayList<>();
        // list.add(rec);
        // list.add(tp);

        // long time = System.nanoTime();
        // PlateauPuzzle board = PlateauPuzzle.createBoard(list);
        // time = System.nanoTime() - time;
        // System.out.println("Tps exec partie Coco : " + time);

        // Displayer display = new TerminalDisplayer(board);
        // display.show();

        // partie Guigui
        // time = System.nanoTime();
        // PlateauPuzzle2 board2 = new PlateauPuzzle2(10, 10);
        // board2.addPiece(rec);
        // board2.addPiece(tp);
        // time = System.nanoTime() - time;
        // System.out.println("Tps exec partie Guigui : " + time);
        // RectanglePiece rec2 = new RectanglePiece(6, 2, 1, 3);
        // board2.addPiece(rec2);
        // TPiece tp2 = new TPiece(5, 0, 3, 3);
        // System.out.println(board2.addPiece(tp2));
        // System.out.println(board2.rotatePiece(tp2, Rotate.PLUS_90_DEGREES));
        // System.out.println(board2.rotatePiece(tp2, Rotate.PLUS_90_DEGREES));
        // board2.showBoard();

        // PlateauPuzzle2 board3 = new PlateauPuzzle2(10, 10);
        // TerminalView view = new TerminalView(board3);
        // System.out.println(board3.addPiece(rec));
        // System.out.println(board3.addPiece(tp));
        // System.out.println(board3.addPiece(new RectanglePiece(6, 2, 1, 3)));
        // TPiece tp2 = new TPiece(5, 0, 3, 3);
        // System.out.println(board3.addPiece(tp2));
        // System.out.println(board3.rotatePiece(tp2, Rotate.PLUS_90_DEGREES));
        // System.out.println(board3.rotatePiece(tp2, Rotate.PLUS_90_DEGREES));

        Piece test1 = PieceFactory.getPiece(new RandomPieceFactory(1, 0, 5, 8));
        test1.showCaracteristics();
        test1.showBoard();
        Piece test2 = PieceFactory.getPiece(new RandomPieceFactory(1, 0, 5, 8));
        test2.showCaracteristics();
        test2.showBoard();
        Piece test3 = PieceFactory.getPiece(new RandomPieceFactory(3, 6));
        test3.showCaracteristics();
        test3.showBoard();
    }
}
