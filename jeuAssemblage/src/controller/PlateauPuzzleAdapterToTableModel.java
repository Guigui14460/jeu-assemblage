package controller;

import javax.swing.table.AbstractTableModel;

import game.PlateauPuzzle2;
import piecesPuzzle.Piece;
import piecesPuzzle.observer.ModelListener;

/**
 * Adaptateur de notre plateau vers une table.
 */
public class PlateauPuzzleAdapterToTableModel extends AbstractTableModel implements ModelListener {
    private static final long serialVersionUID = 1L;

    /**
     * Nombre de champs du tableau.
     */
    private final static int NB_FIELDS = 3;

    /**
     * Indice des différentes colonnes.
     */
    private static final int PIECE_TYPE = 0, PIECE_COORDINATES = 1, PIECE_DIMENSIONS = 2;

    /**
     * Noms des colonnes du tableau.
     */
    private static final String[] COL_NAME;
    static {
        COL_NAME = new String[NB_FIELDS];
        COL_NAME[PIECE_TYPE] = "Type";
        COL_NAME[PIECE_COORDINATES] = "Coordinates";
        COL_NAME[PIECE_DIMENSIONS] = "Dimensions";
    }

    /**
     * Plateau à utiliser.
     */
    private PlateauPuzzle2 board;

    /**
     * Constructeur par défaut.
     * @param board plateau à utiliser
     */
    public PlateauPuzzleAdapterToTableModel(PlateauPuzzle2 board){
        this.board = board;
        this.board.addModelListener(this); // on ajouter l'écouteur au plateau de jeu
    }

    @Override
    public int getColumnCount() {
        return NB_FIELDS;
    }

    @Override
    public int getRowCount() {
        return this.board.getNumberOfPiece();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Piece p = this.board.getPiece(rowIndex);
        switch (columnIndex) {
            case PIECE_TYPE:
                return p.getClass().toString();
            case PIECE_COORDINATES:
                return p.toString();
            case PIECE_DIMENSIONS:
                return "(" + p.getWidth() + ", " + p.getHeight() + ")";
        }
        return null;
    }

    @Override
    public String getColumnName(int col) {
        return COL_NAME[col];
    }

    @Override
    public void somethingHasChanged(Object source) {
        this.fireTableDataChanged();
    }
}
