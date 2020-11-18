package view;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.PlateauPuzzleAdapterToTableModel;
import model.PlateauPuzzle;

/**
 * Composant permettant d'afficher le tableau de pièce de puzzle.
 */
public class PiecesTable extends JScrollPane {
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur par défaut.
     * 
     * @param board plateau à utiliser
     */
    public PiecesTable(PlateauPuzzle board) {
        super(new JTable(new PlateauPuzzleAdapterToTableModel(board)));
    }
}
