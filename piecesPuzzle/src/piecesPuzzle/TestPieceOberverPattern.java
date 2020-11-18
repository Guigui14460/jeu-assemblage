package piecesPuzzle;

import piecesPuzzle.observer.ListenableModel;
import piecesPuzzle.observer.ModelListener;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class TestPieceOberverPattern extends TestCase {
    public static final int MAX_WIDTH = TestPiece.MAX_WIDTH;
    public static final int MAX_HEIGHT = TestPiece.MAX_HEIGHT;
    public static final PieceImplementation PIECE = new RectanglePiece(0, 0, MAX_WIDTH, MAX_HEIGHT);

    static class PieceListener implements ModelListener {
        public int nbCall = 0;

        @Override
        public void somethingHasChanged(Object source) {
            this.nbCall++;
        }

        public void resetCalls() {
            this.nbCall = 0;
        }
    }

    public static final PieceListener LISTENER = new PieceListener();

    @Before
    public void setUp() {
        PIECE.addModelListener(LISTENER);
    }

    @After
    public void tearDown() {
        LISTENER.resetCalls();
        PIECE.removeModelListener(LISTENER);
    }

    @Test
    public void testGetX() {
        PIECE.getX();
        assertEquals(0, LISTENER.nbCall);
    }

    @Test
    public void testGetY() {
        PIECE.getY();
        assertEquals(0, LISTENER.nbCall);
    }

    @Test
    public void testGetWidth() {
        PIECE.getWidth();
        assertEquals(0, LISTENER.nbCall);
    }

    @Test
    public void testGetHeight() {
        PIECE.getHeight();
        assertEquals(0, LISTENER.nbCall);
    }

    @Test
    public void testGetBoard() {
        PIECE.getBoard();
        assertEquals(0, LISTENER.nbCall);
    }

    @Test
    public void testOccupies() {
        PIECE.occupies(0, 0);
        assertEquals(0, LISTENER.nbCall);
    }

    @Test
    public void testOccupiesInBoard() {
        PIECE.occupiesInBoard(0, 0);
        assertEquals(0, LISTENER.nbCall);
    }

    @Test
    public void testGetPieceType() {
        PIECE.getPieceType();
        assertEquals(0, LISTENER.nbCall);
    }

    @Test
    public void testSetBoardValueAtPosition() {
        PIECE.setBoardValueAtPosition(0, 0, true);
        assertEquals(1, LISTENER.nbCall);

        LISTENER.resetCalls();

        PIECE.setBoardValueAtPosition(0, 0, true);
        PIECE.setBoardValueAtPosition(1, 0, true);
        PIECE.setBoardValueAtPosition(2, 0, true);
        PIECE.setBoardValueAtPosition(3, 0, true);
        assertEquals(4, LISTENER.nbCall);
    }

    @Test
    public void testTranslate() {
        PIECE.translate(0, 0);
        assertEquals(3, LISTENER.nbCall); // translate, setX, setY

        LISTENER.resetCalls();

        PIECE.translate(0, 0);
        PIECE.translate(1, 0);
        PIECE.translate(2, 0);
        assertEquals(3 * 3, LISTENER.nbCall);
    }

    @Test
    public void testRotate() {
        PIECE.rotate(Piece.Rotate.MINUS_90_DEGREES);
        assertEquals(4, LISTENER.nbCall); // rotate, setBoard, setWidth, setHeight

        LISTENER.resetCalls();

        PIECE.rotate(Piece.Rotate.PLUS_90_DEGREES);
        PIECE.rotate(Piece.Rotate.MINUS_90_DEGREES);
        PIECE.rotate(Piece.Rotate.PLUS_90_DEGREES);
        PIECE.rotate(Piece.Rotate.MINUS_90_DEGREES);
        PIECE.rotate(Piece.Rotate.PLUS_90_DEGREES);
        assertEquals(5 * 4, LISTENER.nbCall);
    }
}
