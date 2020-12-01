package model.chains;

import org.junit.Test;

import junit.framework.TestCase;
import model.PlateauPuzzle;
import piecesPuzzle.RectanglePiece;
import piecesPuzzle.TPiece;

public class TestChains extends TestCase {
    private static final PlateauPuzzle board = new PlateauPuzzle(3, 3, 100);
    static {
        board.addPiece(new TPiece(0, 0, 3, 3));
    }

    @Test
    public void testInBoardChain() {
        Chain chain = new InBoardChain(null);
        assertTrue(chain.performAction(board, new RectanglePiece(0, 0, 1, 1)));
        assertTrue(chain.performAction(board, new RectanglePiece(0, 0, 3, 3)));
        assertFalse(chain.performAction(board, new RectanglePiece(-1, 0, 1, 1)));
        assertFalse(chain.performAction(board, new RectanglePiece(1, 0, 10, 10)));
    }

    @Test
    public void testCollisionChain() {
        Chain chain = new CollisionChain(null);
        PlateauPuzzle empty = new PlateauPuzzle(3, 3, 100);
        assertTrue(chain.performAction(empty, new RectanglePiece(0, 0, 1, 1)));
        assertTrue(chain.performAction(empty, new RectanglePiece(0, 0, 3, 3)));
        assertTrue(chain.performAction(empty, new RectanglePiece(-1, 0, 1, 1)));
        assertTrue(chain.performAction(empty, new RectanglePiece(1, 0, 10, 10)));

        assertTrue(chain.performAction(board, new RectanglePiece(0, 1, 1, 1)));
        assertFalse(chain.performAction(board, new RectanglePiece(0, 0, 3, 3)));
        assertTrue(chain.performAction(board, new RectanglePiece(-1, 0, 1, 1)));
        assertFalse(chain.performAction(board, new RectanglePiece(1, 0, 10, 10)));
    }

    @Test
    public void testCollisionAndInBoardChains() {
        PlateauPuzzle empty = new PlateauPuzzle(3, 3, 100);

        Chain chain1 = new InBoardChain(new CollisionChain(null));
        assertTrue(chain1.performAction(empty, new RectanglePiece(0, 0, 1, 1)));
        assertTrue(chain1.performAction(empty, new RectanglePiece(0, 0, 3, 3)));
        assertFalse(chain1.performAction(empty, new RectanglePiece(-1, 0, 1, 1)));
        assertFalse(chain1.performAction(empty, new RectanglePiece(1, 0, 10, 10)));

        assertTrue(chain1.performAction(board, new RectanglePiece(0, 1, 1, 1)));
        assertFalse(chain1.performAction(board, new RectanglePiece(0, 0, 3, 3)));
        assertFalse(chain1.performAction(board, new RectanglePiece(-1, 0, 1, 1)));
        assertFalse(chain1.performAction(board, new RectanglePiece(1, 0, 10, 10)));

        Chain chain2 = new CollisionChain(new InBoardChain(null));
        assertTrue(chain2.performAction(empty, new RectanglePiece(0, 0, 1, 1)));
        assertTrue(chain2.performAction(empty, new RectanglePiece(0, 0, 3, 3)));
        assertFalse(chain2.performAction(empty, new RectanglePiece(-1, 0, 1, 1)));
        assertFalse(chain2.performAction(empty, new RectanglePiece(1, 0, 10, 10)));

        assertTrue(chain2.performAction(board, new RectanglePiece(0, 1, 1, 1)));
        assertFalse(chain2.performAction(board, new RectanglePiece(0, 0, 3, 3)));
        assertFalse(chain2.performAction(board, new RectanglePiece(-1, 0, 1, 1)));
        assertFalse(chain2.performAction(board, new RectanglePiece(1, 0, 10, 10)));
    }
}