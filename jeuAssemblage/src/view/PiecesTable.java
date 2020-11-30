package view;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.PlateauPuzzleAdapterToTableModel;
import model.PlateauPuzzle;

/**
 * Composant permettant d'afficher le tableau de pièce de puzzle.
 */
public class PiecesTable extends JTable {
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur par défaut.
     * 
     * @param board plateau à utiliser
     */
    public PiecesTable(PlateauPuzzle board, GraphicsPanel boardView) {
        super(new PlateauPuzzleAdapterToTableModel(board));
        // permet de sélectionner la pièce correspondant à la ligne de la table
        this.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (getSelectedRow() != -1) {
                    boardView.setSelectedPiece(board.getPiece(getSelectedRow()));
                    boardView.requestFocus(); // recentre le focus sur le plateau
                    repaint(); // évite l'erreur où la ligne sélectionnée n'est plus affichée
                }
            }
        });
        this.setShowHorizontalLines(true);
    }
}
