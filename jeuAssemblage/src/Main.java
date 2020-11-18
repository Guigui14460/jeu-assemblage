import model.PieceFactory;
import model.PlateauPuzzle;
import model.RandomPieceFactory;
import piecesPuzzle.Piece;
import piecesPuzzle.Piece.Rotate;
import piecesPuzzle.RectanglePiece;
import piecesPuzzle.TPiece;
import view.GUI;
import view.TerminalView;

public class Main {
    public static void main(String[] args) throws Exception {
        RectanglePiece rec = new RectanglePiece(1, 1, 2, 3);
        TPiece tp = new TPiece(0, 1, 3, 5);

        PlateauPuzzle board2 = new PlateauPuzzle(10, 10);
        board2.addPiece(rec);
        board2.addPiece(tp);
        RectanglePiece rec2 = new RectanglePiece(6, 2, 1, 3);
        board2.addPiece(rec2);
        TPiece tp2 = new TPiece(5, 0, 3, 3);
        System.out.println(board2.addPiece(tp2));
        System.out.println(board2.rotatePiece(tp2, Rotate.PLUS_90_DEGREES));
        System.out.println(board2.rotatePiece(tp2, Rotate.PLUS_90_DEGREES));
        board2.showBoard();
        System.out.println("\n\n");

        PlateauPuzzle board3 = new PlateauPuzzle(10, 10);
        TerminalView view = new TerminalView(board3);
        System.out.println(board3.addPiece(rec));
        System.out.println(board3.addPiece(tp));
        System.out.println(board3.addPiece(new RectanglePiece(6, 2, 1, 3)));
        TPiece tp3 = new TPiece(5, 0, 3, 3);
        System.out.println(board3.addPiece(tp3));
        System.out.println(board3.rotatePiece(tp3, Rotate.PLUS_90_DEGREES));
        System.out.println(board3.rotatePiece(tp3, Rotate.PLUS_90_DEGREES));

        Piece test1 = PieceFactory.getPiece(new RandomPieceFactory(1, 0, 5, 8));
        test1.showCaracteristics();
        test1.showBoard();
        Piece test2 = PieceFactory.getPiece(new RandomPieceFactory(1, 0, 5, 8));
        test2.showCaracteristics();
        test2.showBoard();
        Piece test3 = PieceFactory.getPiece(new RandomPieceFactory(3, 6));
        test3.showCaracteristics();
        test3.showBoard();

        new GUI(board3);
    }
}
