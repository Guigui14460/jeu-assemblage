package piecesPuzzle;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import junit.framework.TestCase;

public class TestPiece extends TestCase {
    public static final List<Piece> PIECES = new ArrayList<>();
    public static final Map<Integer, List<Integer>> COORDINATES = new HashMap<>();
    public static final Map<Integer, List<Integer>> DIMENSIONS = new HashMap<>();
    public static final int MAX_WIDTH = 11;
    public static final int MAX_HEIGHT = 11;
    public static final int NUMBER_OF_PIECES = 1000;

    static {
        Random random = new Random();
        int x, y, width, height;
        for (int i = 0; i < NUMBER_OF_PIECES; i++) {
            x = random.nextInt(MAX_WIDTH * 2) - MAX_WIDTH;
            y = random.nextInt(MAX_HEIGHT * 2) - MAX_HEIGHT;
            COORDINATES.put(i, new ArrayList<>(Arrays.asList(x, y)));

            width = random.nextInt(MAX_WIDTH - 1) + 1;
            height = random.nextInt(MAX_HEIGHT - 1) + 1;
            DIMENSIONS.put(i, new ArrayList<>(Arrays.asList(width, height)));

            PIECES.add(new RectanglePiece(x, y, width, height));
        }
    }

    @Test
    public void testGetX() {
        for (int i = 0; i < NUMBER_OF_PIECES; i++) {
            assertEquals((int) COORDINATES.get(i).get(0), PIECES.get(i).getX());
        }
    }

    @Test
    public void testGetY() {
        for (int i = 0; i < NUMBER_OF_PIECES; i++) {
            assertEquals((int) COORDINATES.get(i).get(1), PIECES.get(i).getY());
        }
    }

    @Test
    public void testGetWidth() {
        for (int i = 0; i < NUMBER_OF_PIECES; i++) {
            assertEquals((int) DIMENSIONS.get(i).get(0), PIECES.get(i).getWidth());
        }
    }

    @Test
    public void testGetHeight() {
        for (int i = 0; i < NUMBER_OF_PIECES; i++) {
            assertEquals((int) DIMENSIONS.get(i).get(1), PIECES.get(i).getHeight());
        }
    }

    @Test
    public void testGetBoard() {
        for (int i = 0; i < NUMBER_OF_PIECES; i++) {
            assertNotNull(PIECES.get(i).getBoard());
            assertEquals((int) DIMENSIONS.get(i).get(0), PIECES.get(i).getBoard()[0].length);
            assertEquals((int) DIMENSIONS.get(i).get(1), PIECES.get(i).getBoard().length);
        }
    }

    @Test
    public void testSetBoardValueAtPosition() {
        for (int i = 0; i < NUMBER_OF_PIECES; i++) {
            Piece piece = PIECES.get(i);
            for (int j = -MAX_WIDTH; j <= MAX_WIDTH; j++) {
                for (int k = -MAX_HEIGHT; k <= MAX_HEIGHT; k++) {
                    int x = j, y = k;
                    if (x < 0 || x >= DIMENSIONS.get(i).get(0) || y < 0 || y >= DIMENSIONS.get(i).get(1)) {
                        assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
                            public void run() {
                                piece.setBoardValueAtPosition(x, y, false);
                            }
                        });
                        assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
                            public void run() {
                                piece.setBoardValueAtPosition(x, y, true);
                            }
                        });
                    } else {
                        piece.setBoardValueAtPosition(x, y, false);
                        assertEquals(piece.getBoard()[y][x], false);
                        piece.setBoardValueAtPosition(x, y, true);
                        assertEquals(piece.getBoard()[y][x], true);
                    }
                }
            }
        }
    }

    @Test
    public void testCopy() {
        for (int i = 0; i < NUMBER_OF_PIECES; i++) {
            Piece piece = PIECES.get(i);
            Piece copy = piece.copy();
            assertNotNull(copy);
            assertNotEquals(copy, piece);
            assertEquals(piece.getX(), copy.getX());
            assertEquals(piece.getY(), copy.getY());
            assertEquals(piece.getWidth(), copy.getWidth());
            assertEquals(piece.getHeight(), copy.getHeight());
            assertEquals(piece.getPieceType(), copy.getPieceType());
            assertArrayEquals(piece.getBoard(), copy.getBoard());
        }
    }

    @Test
    public void testClone() {
        for (int i = 0; i < NUMBER_OF_PIECES; i++) {
            try {
                Piece piece = PIECES.get(i);
                boolean verifWidthHeight = !(piece.getWidth() == piece.getHeight());
                Piece cloned = (Piece) piece.clone();
                assertNotNull(cloned);
                assertNotEquals(cloned, piece);
                assertEquals(piece.getX(), cloned.getX());
                assertEquals(piece.getY(), cloned.getY());
                if (verifWidthHeight) {
                    assertEquals(piece.getWidth(), cloned.getWidth());
                    assertEquals(piece.getHeight(), cloned.getHeight());
                }
                assertEquals(piece.getPieceType(), cloned.getPieceType());
                assertNotEquals(piece.getBoard(), cloned.getBoard());
                assertArrayEquals(piece.getBoard(), cloned.getBoard());
                cloned.translate(2, 2);
                cloned.rotate(Piece.Rotate.PLUS_90_DEGREES);
                assertNotEquals(piece.getX(), cloned.getX());
                assertNotEquals(piece.getY(), cloned.getY());
                if (verifWidthHeight) {
                    assertNotEquals(piece.getWidth(), cloned.getWidth());
                    assertNotEquals(piece.getHeight(), cloned.getHeight());
                }
                assertEquals(piece.getPieceType(), cloned.getPieceType());
                assertNotEquals(piece.getBoard(), cloned.getBoard());
            } catch (CloneNotSupportedException e) {
                System.err.println(e);
            }
        }
    }

    @Test
    public void testTranslate() {
        Random random = new Random();
        for (int i = 0; i < NUMBER_OF_PIECES; i++) {
            int dx = random.nextInt(2 * MAX_WIDTH) - MAX_WIDTH;
            int dy = random.nextInt(2 * MAX_HEIGHT) - MAX_HEIGHT;
            PIECES.get(i).translate(dx, dy);
            assertEquals(COORDINATES.get(i).get(0) + dx, PIECES.get(i).getX());
            assertEquals(COORDINATES.get(i).get(1) + dy, PIECES.get(i).getY());
        }
    }

    @Test
    public void testOccupies() {
        for (int i = 0; i < NUMBER_OF_PIECES; i++) {
            Piece piece = PIECES.get(i);
            for (int j = -MAX_WIDTH; j <= MAX_WIDTH; j++) {
                for (int k = -MAX_HEIGHT; k <= MAX_HEIGHT; k++) {
                    int x = j, y = k;
                    if (x < 0 || x >= DIMENSIONS.get(i).get(0) || y < 0 || y >= DIMENSIONS.get(i).get(1)) {
                        assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
                            public void run() {
                                piece.occupies(x, y);
                            }
                        });
                    } else {
                        assertEquals(piece.getBoard()[y][x], piece.occupies(x, y));
                    }
                }
            }
        }
    }

    @Test
    public void testOccupiesInBoard() {
        for (int i = 0; i < NUMBER_OF_PIECES; i++) {
            Piece piece = PIECES.get(i);
            int X = COORDINATES.get(i).get(0);
            int Y = COORDINATES.get(i).get(1);
            for (int j = -MAX_WIDTH; j <= MAX_WIDTH; j++) {
                for (int k = -MAX_HEIGHT; k <= MAX_HEIGHT; k++) {
                    int x = j, y = k;
                    if (x < X || x >= DIMENSIONS.get(i).get(0) + X || y < Y || y >= DIMENSIONS.get(i).get(1) + Y) {
                        assertFalse(piece.occupiesInBoard(x, y));
                    } else {
                        assertEquals(piece.getBoard()[y - Y][x - X], piece.occupiesInBoard(x, y));
                    }
                }
            }
        }
    }

    @Test
    public void testRotate() {
        for (int i = 0; i < NUMBER_OF_PIECES; i++) {
            Piece piece = PIECES.get(i);
            piece.rotate(Piece.Rotate.PLUS_90_DEGREES);
            assertEquals((int) DIMENSIONS.get(i).get(1), piece.getWidth());
            assertEquals((int) DIMENSIONS.get(i).get(0), piece.getHeight());
            piece.rotate(Piece.Rotate.PLUS_90_DEGREES);
            assertEquals((int) DIMENSIONS.get(i).get(0), piece.getWidth());
            assertEquals((int) DIMENSIONS.get(i).get(1), piece.getHeight());
            piece.rotate(Piece.Rotate.MINUS_90_DEGREES);
            assertEquals((int) DIMENSIONS.get(i).get(1), piece.getWidth());
            assertEquals((int) DIMENSIONS.get(i).get(0), piece.getHeight());
            piece.rotate(Piece.Rotate.MINUS_90_DEGREES);
            assertEquals((int) DIMENSIONS.get(i).get(0), piece.getWidth());
            assertEquals((int) DIMENSIONS.get(i).get(1), piece.getHeight());
        }
    }
}
