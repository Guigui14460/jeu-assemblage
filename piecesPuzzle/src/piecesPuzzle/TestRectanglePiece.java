package piecesPuzzle;

import org.junit.Test;

import junit.framework.TestCase;

public class TestRectanglePiece extends TestCase {
    @Test
    public void testGetX() {
        Piece piece = new RectanglePiece(2, -4, 10, 3);
        assertEquals(2, piece.getX());
    }

    @Test
    public void testGetY() {
        Piece piece = new RectanglePiece(2, -4, 10, 3);
        assertEquals(-4, piece.getY());
    }
}
