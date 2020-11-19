package model;

import org.junit.Test;

import junit.framework.TestCase;
import piecesPuzzle.RectanglePiece;
import piecesPuzzle.TPiece;
import piecesPuzzle.Piece.Rotate;

public class TestPlateauPuzzle extends TestCase {
    private static final int WIDTH = 10, HEIGHT = 10;

    @Test
    public void testAddPiece() {
        PlateauPuzzle board = new PlateauPuzzle(WIDTH, HEIGHT);
        RectanglePiece p1 = new RectanglePiece(1, 1, 2, 3);
        TPiece p2 = new TPiece(0, 1, 3, 5);
        RectanglePiece p3 = new RectanglePiece(6, 2, 1, 3);
        assertTrue(board.addPiece(p1));
        assertFalse(board.addPiece(p2));
        assertTrue(board.addPiece(p3));
    }

    @Test
    public void testRotatePiece() {
        PlateauPuzzle board = new PlateauPuzzle(WIDTH, HEIGHT);
        RectanglePiece p1 = new RectanglePiece(1, 1, 2, 3);
        RectanglePiece p2 = new RectanglePiece(6, 2, 1, 3);
        RectanglePiece p3 = new RectanglePiece(5, 4, 1, 5);
        board.addPiece(p1);
        board.addPiece(p2);
        board.addPiece(p3);
        assertFalse(board.rotatePiece(p3, Rotate.PLUS_90_DEGREES));
        assertTrue(board.rotatePiece(p2, Rotate.PLUS_90_DEGREES));
        assertTrue(board.rotatePiece(p3, Rotate.PLUS_90_DEGREES));
    }

    @Test
    public void testTranslatePiece() {
        PlateauPuzzle board = new PlateauPuzzle(WIDTH, HEIGHT);
        RectanglePiece p1 = new RectanglePiece(1, 1, 2, 3);
        RectanglePiece p2 = new RectanglePiece(6, 2, 1, 3);
        RectanglePiece p3 = new RectanglePiece(5, 4, 1, 6);
        board.addPiece(p1);
        board.addPiece(p2);
        board.addPiece(p3);
        board.rotatePiece(p2, Rotate.PLUS_90_DEGREES);
        assertTrue(board.translatePiece(p3, -1, 0));
        board.rotatePiece(p3, Rotate.PLUS_90_DEGREES);

        assertFalse(board.translatePiece(p1, -10, 2));
        assertTrue(board.translatePiece(p1, 3, 4));
    }
}
