package model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;
import piecesPuzzle.Piece;

public class TestPieceFactory extends TestCase {
    private static final int MAX_X = 20, MAX_Y = 20, MAX_ROTATION = 8;
    private static final List<PieceAbstractFactory> ABSTRACT_FACTORIES = new ArrayList<>();

    static {
        ABSTRACT_FACTORIES.add(new RandomPieceFactory(MAX_X, MAX_Y));
        ABSTRACT_FACTORIES.add(new RandomRotatedPieceFactory(MAX_X, MAX_Y));
        ABSTRACT_FACTORIES.add(new RandomRotatedPieceFactory(MAX_X, MAX_Y, MAX_ROTATION));
    }

    @Test
    public void testCreatePiece() {
        for (PieceAbstractFactory abstractFactory : ABSTRACT_FACTORIES) {
            Piece piece;
            try {
                piece = PieceFactory.getPiece(abstractFactory);
                assertNotNull(piece);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
