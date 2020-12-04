package jeuAssemblage.model.arrangements;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import piecesPuzzle.InvertedLPiece;
import piecesPuzzle.LPiece;
import piecesPuzzle.Piece;
import piecesPuzzle.RectanglePiece;
import piecesPuzzle.TPiece;
import piecesPuzzle.Piece.Rotate;

/**
 * Classe ayant des arrangements de pièces pré-définis. Lors de chaque
 * génération d'arrangement, tirage aléatoire parmi les pré-définis.
 */
public class DefaultPieceArrangement implements PieceArrangement {
        /**
         * Liste d'arrangement préfaits.
         */
        private static List<Arrangement> DEFAULT_ARRANGEMENTS = new LinkedList<>();
        static {
                // Arrangement 1
                Arrangement arrangement1 = new Arrangement(5, 5, 10);
                arrangement1.addPieces(new TPiece(0, 0, 3, 3), new RectanglePiece(2, 2, 3, 1), new LPiece(2, 3, 2, 2),
                                new RectanglePiece(0, 4, 1, 1), new RectanglePiece(4, 1, 1, 1));

                // Arrangement 2
                Arrangement arrangement2 = new Arrangement(7, 5, 12);
                arrangement2.addPieces(new TPiece(0, 0, 3, 3), new RectanglePiece(3, 0, 1, 2),
                                new RectanglePiece(0, 2, 1, 1));
                Piece piece = new InvertedLPiece(2, 2, 3, 4);
                piece.rotate(Rotate.PLUS_90_DEGREES);
                arrangement2.addPieces(piece);
                piece = new TPiece(4, 0, 3, 3);
                piece.rotate(Rotate.MINUS_90_DEGREES);
                piece.rotate(Rotate.MINUS_90_DEGREES);
                arrangement2.addPieces(piece);

                // Arrangement 3
                Arrangement arrangement3 = new Arrangement(13, 9, 50);
                arrangement3.addPieces(new RectanglePiece(1, 0, 3, 1), new RectanglePiece(0, 1, 1, 2),
                                new RectanglePiece(2, 2, 2, 3), new RectanglePiece(7, 2, 1, 1),
                                new RectanglePiece(8, 1, 4, 1), new RectanglePiece(10, 8, 1, 1));
                arrangement3.addPieces(new TPiece(0, 6, 5, 3), new InvertedLPiece(9, 2, 2, 2),
                                new InvertedLPiece(4, 7, 2, 2), new InvertedLPiece(4, 4, 3, 2));
                piece = new LPiece(5, 0, 3, 3);
                piece.rotate(Rotate.PLUS_90_DEGREES);
                arrangement3.addPieces(piece);
                piece = new TPiece(8, 3, 5, 5);
                piece.rotate(Rotate.MINUS_90_DEGREES);
                arrangement3.addPieces(piece);

                // Arrangement 4
                Arrangement arrangement4 = new Arrangement(10, 9, 27);
                arrangement4.addPieces(new RectanglePiece(0, 5, 1, 1), new RectanglePiece(2, 7, 3, 1),
                                new RectanglePiece(4, 1, 2, 2), new RectanglePiece(5, 0, 4, 1),
                                new RectanglePiece(6, 7, 1, 2), new TPiece(0, 0, 3, 3), new TPiece(7, 2, 3, 7),
                                new InvertedLPiece(5, 4, 2, 2), new LPiece(2, 1, 2, 5));

                // Arrangement 5
                Arrangement arrangement5 = new Arrangement(10, 10, 69);
                arrangement5.addPieces(new LPiece(0, 1, 2, 3), new RectanglePiece(4, 0, 1, 1),
                                new RectanglePiece(4, 3, 1, 1), new LPiece(6, 0, 2, 2), new RectanglePiece(6, 2, 2, 2),
                                new InvertedLPiece(8, 0, 2, 3), new RectanglePiece(2, 4, 5, 2),
                                new RectanglePiece(8, 3, 1, 4), new RectanglePiece(9, 4, 1, 1),
                                new RectanglePiece(9, 9, 1, 1), new RectanglePiece(2, 6, 2, 2));
                piece = new InvertedLPiece(1, 5, 5, 6);
                piece.rotate(Rotate.PLUS_90_DEGREES);
                arrangement5.addPieces(piece);
                piece = new InvertedLPiece(5, 6, 2, 2);
                piece.rotate(Rotate.MINUS_90_DEGREES);
                arrangement5.addPieces(piece);
                piece = new TPiece(4, 7, 3, 5);
                piece.rotate(Rotate.PLUS_90_DEGREES);
                arrangement5.addPieces(piece);
                piece = new TPiece(2, 0, 3, 3);
                piece.rotate(Rotate.PLUS_90_DEGREES);
                piece.rotate(Rotate.PLUS_90_DEGREES);
                arrangement5.addPieces(piece);
                piece = new InvertedLPiece(0, 4, 2, 3);
                piece.rotate(Rotate.MINUS_90_DEGREES);
                piece.rotate(Rotate.MINUS_90_DEGREES);
                arrangement5.addPieces(piece);

                DEFAULT_ARRANGEMENTS.add(arrangement1);
                // DEFAULT_ARRANGEMENTS.add(arrangement2);
                // DEFAULT_ARRANGEMENTS.add(arrangement3);
                // DEFAULT_ARRANGEMENTS.add(arrangement4);
                // DEFAULT_ARRANGEMENTS.add(arrangement5);
        }

        @Override
        public Arrangement generateArrangement() {
                Random random = new Random();
                return DefaultPieceArrangement.DEFAULT_ARRANGEMENTS
                                .get(random.nextInt(DefaultPieceArrangement.DEFAULT_ARRANGEMENTS.size()));
        }
}
