import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
        PlateauPuzzle board3 = new PlateauPuzzle(10, 10);
        RectanglePiece rec = new RectanglePiece(1, 1, 2, 3);
        TPiece tp = new TPiece(0, 1, 3, 5);
        board3.addPiece(rec);
        board3.addPiece(tp);
        board3.addPiece(new RectanglePiece(6, 2, 1, 3));
        TPiece tp3 = new TPiece(5, 0, 3, 3);
        board3.addPiece(tp3);
        board3.rotatePiece(tp3, Rotate.PLUS_90_DEGREES);
        board3.rotatePiece(tp3, Rotate.PLUS_90_DEGREES);

        // Piece test1 = PieceFactory.getPiece(new RandomPieceFactory(1, 0, 5, 8));
        // test1.showCaracteristics();
        // test1.showBoard();
        // Piece test2 = PieceFactory.getPiece(new RandomPieceFactory(1, 0, 5, 8));
        // test2.showCaracteristics();
        // test2.showBoard();
        // Piece test3 = PieceFactory.getPiece(new RandomPieceFactory(3, 6));
        // test3.showCaracteristics();
        // test3.showBoard();

        // ObjectOutputStream oos = null;
        // FileOutputStream fos = null;
        // try {
        // fos = new FileOutputStream("config.txt", true);
        // oos = new ObjectOutputStream(fos);
        // oos.writeObject(board3);
        // } catch (Exception e) {
        // e.printStackTrace();
        // } finally {
        // if (oos != null) {
        // oos.close();
        // }
        // }

        // ObjectInputStream ois = null;
        // FileInputStream fis = null;
        // PlateauPuzzle board3 = null;
        // try {
        // fis = new FileInputStream("config.txt");
        // ois = new ObjectInputStream(fis);
        // board3 = (PlateauPuzzle) ois.readObject();
        // } catch (Exception e) {
        // e.printStackTrace();
        // } finally {
        // if (ois != null) {
        // ois.close();
        // }
        // }

        new GUI(board3);
    }
}
