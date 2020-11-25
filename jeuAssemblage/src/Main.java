import model.PieceFactory;
import model.RandomPieceFactory;
import piecesPuzzle.Piece;
import view.GUI;

public class Main {
    public static void main(String[] args) throws Exception {
        Piece test1 = PieceFactory.getPiece(new RandomPieceFactory(1, 0, 5, 8));
        test1.showCaracteristics();
        test1.showBoard();
        Piece test2 = PieceFactory.getPiece(new RandomPieceFactory(1, 0, 5, 8));
        test2.showCaracteristics();
        test2.showBoard();
        Piece test3 = PieceFactory.getPiece(new RandomPieceFactory(3, 6));
        test3.showCaracteristics();
        test3.showBoard();

        new GUI();
    }
}
