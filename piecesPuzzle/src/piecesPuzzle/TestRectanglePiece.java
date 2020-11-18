package piecesPuzzle;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

import junit.framework.TestCase;

public class TestRectanglePiece extends TestCase {
    public static final List<Piece> PIECES = TestPiece.PIECES;
    public static final Map<Integer, List<Integer>> COORDINATES = TestPiece.COORDINATES;
    public static final Map<Integer, List<Integer>> DIMENSIONS = TestPiece.DIMENSIONS;
    public static final int MAX_WIDTH = TestPiece.MAX_WIDTH;
    public static final int MAX_HEIGHT = TestPiece.MAX_HEIGHT;
    public static final int NUMBER_OF_PIECES = TestPiece.NUMBER_OF_PIECES;

    @Test
    public void testConstructor() {
        Random random = new Random();
        for (int i = 0; i < NUMBER_OF_PIECES; i++) {
            int x, y, width, height;
            x = random.nextInt(MAX_WIDTH * 2) - MAX_WIDTH;
            y = random.nextInt(MAX_HEIGHT * 2) - MAX_HEIGHT;
            width = random.nextInt(MAX_WIDTH - 1) + 1;
            height = random.nextInt(MAX_HEIGHT - 1) + 1;
            assertNotNull(new RectanglePiece(x, y, width, height));
        }
    }

    @Test
    public void testRotateWithRectanglePiece() {
        for (int i = 0; i < NUMBER_OF_PIECES; i++) {
            RectanglePiece piece = (RectanglePiece) PIECES.get(i);
            boolean[][] orientation1 = new boolean[piece.getHeight()][piece.getWidth()];
            boolean[][] orientation2 = new boolean[piece.getWidth()][piece.getHeight()];
            for (int j = 0; j < piece.getWidth(); j++) {
                for (int k = 0; k < piece.getHeight(); k++) {
                    orientation1[k][j] = true;
                    orientation2[j][k] = true;
                }
            }

            assertArrayEquals(orientation1, piece.getBoard());
            piece.rotate(Piece.Rotate.PLUS_90_DEGREES);
            assertArrayEquals(orientation2, piece.getBoard());
            piece.rotate(Piece.Rotate.PLUS_90_DEGREES);
            assertArrayEquals(orientation1, piece.getBoard());
            piece.rotate(Piece.Rotate.MINUS_90_DEGREES);
            assertArrayEquals(orientation2, piece.getBoard());
            piece.rotate(Piece.Rotate.MINUS_90_DEGREES);
            assertArrayEquals(orientation1, piece.getBoard());
        }
    }
}
