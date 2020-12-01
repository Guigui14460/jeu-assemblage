package model;

import static org.junit.Assert.assertArrayEquals;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import junit.framework.TestCase;
import model.arrangements.Arrangement;
import model.arrangements.PlateauPuzzleFactory;
import piecesPuzzle.InvertedLPiece;
import piecesPuzzle.LPiece;
import piecesPuzzle.Piece;
import piecesPuzzle.RectanglePiece;
import piecesPuzzle.TPiece;
import piecesPuzzle.Piece.Rotate;
import settings.Settings;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPlateauPuzzleIO extends TestCase {
    private static final File FILE = new File("test." + Settings.BOARD_FILE_EXTENSION);
    private static final int BEST_SCORE = 10, NUMBER_ACTIONS = 20;
    private static final String BEST_PLAYER = "noBody";
    private static final Arrangement ARRANGEMENT = new Arrangement(13, 9, 50);
    static {
        ARRANGEMENT.addPieces(new RectanglePiece(1, 0, 3, 1), new RectanglePiece(0, 1, 1, 2),
                new RectanglePiece(2, 2, 2, 3), new RectanglePiece(7, 2, 1, 1), new RectanglePiece(8, 1, 4, 1),
                new RectanglePiece(10, 8, 1, 1));
        ARRANGEMENT.addPieces(new TPiece(0, 6, 5, 3), new InvertedLPiece(9, 2, 2, 2), new InvertedLPiece(4, 7, 2, 2),
                new InvertedLPiece(4, 4, 3, 2));
        Piece piece = new LPiece(5, 0, 3, 3);
        piece.rotate(Rotate.PLUS_90_DEGREES);
        ARRANGEMENT.addPieces(piece);
        piece = new TPiece(8, 3, 5, 5);
        piece.rotate(Rotate.MINUS_90_DEGREES);
        ARRANGEMENT.addPieces(piece);
    }
    private static final PlateauPuzzle BOARD = PlateauPuzzleFactory.getPlateauPuzzle(ARRANGEMENT);
    private PlateauPuzzleIO io;

    @Test
    public void testASaveBoardInTmpFile() {
        assertFalse(FILE.exists());
        try {
            io = PlateauPuzzleIO.saveBoardInTmpFile(BOARD);
        } catch (IOException e) {
        }
        assertFalse(FILE.exists());
    }

    @Test
    public void testBsaveConfigInFile() {
        assertFalse(FILE.exists());
        try {
            io = PlateauPuzzleIO.saveBoardInTmpFile(BOARD);
            io.saveConfigInFile(FILE, BEST_SCORE, BEST_PLAYER, NUMBER_ACTIONS);
            assertTrue(FILE.exists());
        } catch (IOException | ClassNotFoundException e) {
            assertTrue(FILE.exists());
        }
    }

    @Test
    public void testCLoadOldConfig() {
        try {
            io = PlateauPuzzleIO.saveBoardInTmpFile(BOARD);
            io.saveConfigInFile(FILE, BEST_SCORE, BEST_PLAYER, NUMBER_ACTIONS);
            PlateauPuzzleIO boardIOGot = PlateauPuzzleIO.loadOldConfig(FILE);
            assertNotNull(boardIOGot);
            assertEquals(BEST_SCORE, boardIOGot.getBestScore());
            assertEquals(BEST_PLAYER, boardIOGot.getPlayer());
            assertEquals(NUMBER_ACTIONS, boardIOGot.getNumberActions());
            PlateauPuzzle boardGot = boardIOGot.getBoard();
            assertNotNull(boardGot);

            assertEquals(BOARD.getWidth(), boardGot.getWidth());
            assertEquals(BOARD.getHeight(), boardGot.getHeight());
            assertEquals(BOARD.getLeftAvailableActions(), boardGot.getLeftAvailableActions());
            assertEquals(BOARD.getMaxAvailableActions(), boardGot.getMaxAvailableActions());
            assertEquals(BOARD.getNumberOfPiece(), boardGot.getNumberOfPiece());
            for (int i = 0; i < BOARD.getNumberOfPiece(); i++) {
                Piece exepectedPiece = BOARD.getPiece(i);
                Piece gottenPiece = boardGot.getPiece(i);
                assertEquals(exepectedPiece.getX(), gottenPiece.getX());
                assertEquals(exepectedPiece.getY(), gottenPiece.getY());
                assertEquals(exepectedPiece.getWidth(), gottenPiece.getWidth());
                assertEquals(exepectedPiece.getHeight(), gottenPiece.getHeight());
                assertEquals(exepectedPiece.getPieceType(), gottenPiece.getPieceType());
                assertArrayEquals(exepectedPiece.getBoard(), gottenPiece.getBoard());
            }
        } catch (ClassNotFoundException | IOException e) {
        }
    }

    @After
    public void tearDown() {
        FILE.delete();
    }
}
