package piecesPuzzle;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import junit.framework.TestCase;

public class TestTPiece extends TestCase {
    public static final List<Piece> PIECES = new ArrayList<>();
    public static final Map<Integer, List<Integer>> COORDINATES = TestPiece.COORDINATES;
    public static final Map<Integer, List<Integer>> DIMENSIONS = TestPiece.DIMENSIONS;
    public static final int MAX_WIDTH = TestPiece.MAX_WIDTH;
    public static final int MAX_HEIGHT = TestPiece.MAX_HEIGHT;
    public static final int NUMBER_OF_PIECES = TestPiece.NUMBER_OF_PIECES;

    static {
        Random random = new Random();
        int x, y, width, height;
        for (int i = 0; i < NUMBER_OF_PIECES; i++) {
            x = random.nextInt(MAX_WIDTH * 2) - MAX_WIDTH;
            y = random.nextInt(MAX_HEIGHT * 2) - MAX_HEIGHT;
            COORDINATES.put(i, new ArrayList<>(Arrays.asList(x, y)));

            width = random.nextInt(MAX_WIDTH - 1) + 1;
            height = random.nextInt(MAX_HEIGHT - 1) + 1;
            if (width % 2 != 1) {
                width += 1;
            }
            if (height % 2 != 1) {
                height += 1;
            }
            DIMENSIONS.put(i, new ArrayList<>(Arrays.asList(width, height)));

            PIECES.add(new TPiece(x, y, width, height));
        }
    }

    @Test
    public void testConstructor() {
        Random random = new Random();
        for (int i = 0; i < NUMBER_OF_PIECES; i++) {
            int x, y, width, height;
            x = random.nextInt(MAX_WIDTH * 2) - MAX_WIDTH;
            y = random.nextInt(MAX_HEIGHT * 2) - MAX_HEIGHT;

            width = random.nextInt(MAX_WIDTH - 1) + 1;
            height = random.nextInt(MAX_HEIGHT - 1) + 1;

            if (width % 2 != 1 || height % 2 != 1) {
                assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
                    public void run() {
                        new TPiece(x, y, width, height);
                    }
                });
            } else {
                assertNotNull(new TPiece(x, y, width, height));
            }
        }
    }

    @Test
    public void testRotateWithTPiece() {
        for (int i = 0; i < NUMBER_OF_PIECES; i++) {
            TPiece piece = (TPiece) PIECES.get(i);
            boolean[][] orientation1 = new boolean[piece.getHeight()][piece.getWidth()];
            boolean[][] orientation2 = new boolean[piece.getWidth()][piece.getHeight()];
            boolean[][] orientation3 = new boolean[piece.getHeight()][piece.getWidth()];
            boolean[][] orientation4 = new boolean[piece.getWidth()][piece.getHeight()];
            for (int j = 0; j < piece.getWidth(); j++) {
                orientation1[0][j] = true;
                orientation3[piece.getHeight() - 1][j] = true;

                orientation2[j][piece.getHeight() - 1] = true;
                orientation4[j][0] = true;
            }
            for (int j = 0; j < piece.getHeight(); j++) {
                orientation1[j][piece.getWidth() / 2] = true;
                orientation3[j][piece.getWidth() / 2] = true;

                orientation2[piece.getWidth() / 2][j] = true;
                orientation4[piece.getWidth() / 2][j] = true;
            }

            assertArrayEquals(orientation1, piece.getBoard());
            piece.rotate(Piece.Rotate.PLUS_90_DEGREES);
            assertArrayEquals(orientation2, piece.getBoard());
            piece.rotate(Piece.Rotate.PLUS_90_DEGREES);
            assertArrayEquals(orientation3, piece.getBoard());
            piece.rotate(Piece.Rotate.PLUS_90_DEGREES);
            assertArrayEquals(orientation4, piece.getBoard());
            piece.rotate(Piece.Rotate.MINUS_90_DEGREES);
            assertArrayEquals(orientation3, piece.getBoard());
            piece.rotate(Piece.Rotate.MINUS_90_DEGREES);
            assertArrayEquals(orientation2, piece.getBoard());
            piece.rotate(Piece.Rotate.MINUS_90_DEGREES);
            assertArrayEquals(orientation1, piece.getBoard());
        }
    }
}
