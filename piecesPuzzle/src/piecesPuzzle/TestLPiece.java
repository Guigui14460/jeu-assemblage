package piecesPuzzle;

import org.junit.Test;

import junit.framework.TestCase;

public class TestLPiece extends TestCase {
    @Test
    public void testGetX() {
        Piece piece = new LPiece(2, -4, 7, 3);
        assertEquals(2, piece.getX());
    }

    @Test
    public void testGetY() {
        Piece piece = new LPiece(2, -4, 7, 3);
        assertEquals(-4, piece.getY());
    }
}
