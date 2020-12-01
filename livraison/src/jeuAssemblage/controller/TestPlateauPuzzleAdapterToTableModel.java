package jeuAssemblage.controller;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import junit.framework.TestCase;
import jeuAssemblage.model.PlateauPuzzle;
import piecesPuzzle.RectanglePiece;
import piecesPuzzle.Piece.Rotate;

public class TestPlateauPuzzleAdapterToTableModel extends TestCase {
    private static PlateauPuzzle board = new PlateauPuzzle(10, 10, 30);
    private static PlateauPuzzleAdapterToTableModel adapter = new PlateauPuzzleAdapterToTableModel(board);

    @Test
    public void testGetColumnCount() {
        assertEquals(3, adapter.getColumnCount());
    }

    @Test
    public void testGetColumnName() {
        assertEquals("Type", adapter.getColumnName(0));
        assertEquals("Coordonnées", adapter.getColumnName(1));
        assertEquals("Dimensions", adapter.getColumnName(2));
        assertThrows(IndexOutOfBoundsException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                adapter.getColumnName(3);
            }
        });
    }

    @Test
    public void testAddPiece() {
        assertEquals(0, adapter.getRowCount());
        board.addPiece(new RectanglePiece(0, 0, 1, 1));
        assertEquals(1, adapter.getRowCount());
        board.addPiece(new RectanglePiece(5, 5, 2, 2));
        assertEquals(2, adapter.getRowCount());
        board.addPiece(new RectanglePiece(5, 5, 2, 2));
        assertEquals(2, adapter.getRowCount());
    }

    @Test
    public void testTranslatePiece() {
        board.addPiece(new RectanglePiece(0, 0, 1, 1));
        board.addPiece(new RectanglePiece(5, 5, 2, 2));
        String initialCoordinates = (String) adapter.getValueAt(0, 1);
        String initialDimensions = (String) adapter.getValueAt(0, 2);

        board.translatePiece(board.getPiece(0), 1, 1);
        assertNotEquals(initialCoordinates, adapter.getValueAt(0, 1));
        assertEquals("(1, 1)", adapter.getValueAt(0, 1));
        assertEquals(initialDimensions, adapter.getValueAt(0, 2));
        assertEquals(2, adapter.getRowCount());

        board.translatePiece(board.getPiece(0), 0, 0);
        assertNotEquals(initialCoordinates, adapter.getValueAt(0, 1));
        assertEquals("(1, 1)", adapter.getValueAt(0, 1));
        assertEquals(initialDimensions, adapter.getValueAt(0, 2));
        assertEquals(2, adapter.getRowCount());

        board.translatePiece(board.getPiece(0), -1, -1);
        assertEquals(initialCoordinates, adapter.getValueAt(0, 1));
        assertEquals("(0, 0)", adapter.getValueAt(0, 1));
        assertEquals(initialDimensions, adapter.getValueAt(0, 2));
        assertEquals(2, adapter.getRowCount());
    }

    @Test
    public void testRotatePiece() {
        board.addPiece(new RectanglePiece(0, 0, 1, 1));
        board.addPiece(new RectanglePiece(5, 5, 2, 2));
        String initialCoordinates = (String) adapter.getValueAt(0, 1);
        String initialDimensions = (String) adapter.getValueAt(0, 2);

        board.rotatePiece(board.getPiece(0), Rotate.PLUS_90_DEGREES);
        assertEquals(initialCoordinates, adapter.getValueAt(0, 1));
        assertEquals(initialDimensions, adapter.getValueAt(0, 2));
        assertEquals(2, adapter.getRowCount());

        board.rotatePiece(board.getPiece(0), Rotate.MINUS_90_DEGREES);
        assertEquals(initialCoordinates, adapter.getValueAt(0, 1));
        assertEquals(initialDimensions, adapter.getValueAt(0, 2));
        assertEquals(2, adapter.getRowCount());
    }
}
