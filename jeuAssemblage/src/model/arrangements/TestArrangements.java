package model.arrangements;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;
import model.PlateauPuzzle;
import model.pieceFactories.RandomPieceFactory;
import model.pieceFactories.RandomRotatedPieceFactory;
import piecesPuzzle.RectanglePiece;

public class TestArrangements extends TestCase {
    private static final int MAX_X = 20, MAX_Y = 20, MAX_WIDTH = 20, MAX_HEIGHT = 20, MAX_ROTATION = 8,
            MAX_AVAILABLE_ACTIONS = 20;
    private static final List<PieceArrangement> ABSTRACT_FACTORIES = new ArrayList<>();

    static {
        ABSTRACT_FACTORIES.add(new RandomPieceArrangement(new RandomPieceFactory(MAX_X, MAX_Y), MAX_WIDTH, MAX_HEIGHT));
        ABSTRACT_FACTORIES.add(new RandomPieceArrangement(new RandomPieceFactory(MAX_X, MAX_Y, MAX_WIDTH, MAX_HEIGHT),
                MAX_WIDTH, MAX_HEIGHT));
        ABSTRACT_FACTORIES
                .add(new RandomPieceArrangement(new RandomRotatedPieceFactory(MAX_X, MAX_Y), MAX_WIDTH, MAX_HEIGHT));
        ABSTRACT_FACTORIES.add(new RandomPieceArrangement(new RandomRotatedPieceFactory(MAX_X, MAX_Y, MAX_ROTATION),
                MAX_WIDTH, MAX_HEIGHT));
        ABSTRACT_FACTORIES.add(new RandomPieceArrangement(
                new RandomRotatedPieceFactory(MAX_X, MAX_Y, MAX_WIDTH, MAX_HEIGHT), MAX_WIDTH, MAX_HEIGHT));
        ABSTRACT_FACTORIES.add(new RandomPieceArrangement(
                new RandomRotatedPieceFactory(MAX_X, MAX_Y, MAX_WIDTH, MAX_HEIGHT, MAX_ROTATION), MAX_WIDTH,
                MAX_HEIGHT));
        ABSTRACT_FACTORIES.add(new DefaultPieceArrangement());
    }

    @Test
    public void testArrangement() {
        Arrangement arrangement = new Arrangement(MAX_WIDTH, MAX_HEIGHT, MAX_AVAILABLE_ACTIONS);
        assertNotNull(arrangement);

        assertEquals(MAX_WIDTH, arrangement.getWidth());
        assertEquals(MAX_HEIGHT, arrangement.getHeight());
        assertEquals(MAX_AVAILABLE_ACTIONS, arrangement.getMaxAvailableActions());
        assertNotNull(arrangement.getPieces());
        assertEquals(0, arrangement.getPieces().size());

        arrangement.addPieces(new RectanglePiece(0, 0));
        assertEquals(1, arrangement.getPieces().size());
    }

    @Test
    public void testGetArrangement() {
        Arrangement arrangement;
        for (PieceArrangement factory : ABSTRACT_FACTORIES) {
            try {
                arrangement = factory.generateArrangement();
                assertNotNull(arrangement);
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    @Test
    public void testGetPlateauPuzzle() {
        Arrangement arrangement;
        PlateauPuzzle board;
        for (PieceArrangement factory : ABSTRACT_FACTORIES) {
            try {
                arrangement = PlateauPuzzleFactory.getArrangement(factory);
                assertNotNull(arrangement);

                board = PlateauPuzzleFactory.getPlateauPuzzle(arrangement);
                assertNotNull(board);
                assertEquals(arrangement.getWidth(), board.getWidth());
                assertEquals(arrangement.getHeight(), board.getHeight());
                assertEquals(arrangement.getMaxAvailableActions(), board.getMaxAvailableActions());
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
