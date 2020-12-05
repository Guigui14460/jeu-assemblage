package jeuAssemblage.view;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import jeuAssemblage.controller.PlateauPuzzleAdapterToTableModel;
import jeuAssemblage.model.PlateauPuzzle;

/**
 * Composant permettant d'afficher le tableau de pièce de puzzle.
 */
public class PiecesTable extends JTable {
    private static final long serialVersionUID = 1L;

    /**
     * Ecouteur pour la sélection des pièces dans le tableau.
     */
    private ListSelectionListener listener;

    /**
     * Constructeur par défaut.
     * 
     * @param board plateau à utiliser
     */
    public PiecesTable(PlateauPuzzle board, GraphicsPanel boardView) {
        super(new PlateauPuzzleAdapterToTableModel(board));
        // permet de sélectionner la pièce correspondant à la ligne de la table
        this.listener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (getSelectedRow() != -1) {
                    boardView.setSelectedPiece(board.getPiece(getSelectedRow()));
                    boardView.requestFocus(); // recentre le focus sur le plateau
                    repaint(); // évite l'erreur où la ligne sélectionnée n'est plus affichée
                }
            }
        };
        this.getSelectionModel().addListSelectionListener(listener);
        this.setShowHorizontalLines(true);
    }

    /**
     * Permet de changer de plateau facilement. Utilisé pour afficher la solution de
     * l'algorithme d'IA.
     * 
     * @param board nouveau plateau
     */
    public void reset(PlateauPuzzle board, GraphicsPanel boardView) {
        this.getSelectionModel().removeListSelectionListener(this.listener);
        this.setModel(new PlateauPuzzleAdapterToTableModel(board));
        this.listener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (getSelectedRow() != -1) {
                    boardView.setSelectedPiece(board.getPiece(getSelectedRow()));
                    boardView.requestFocus(); // recentre le focus sur le plateau
                    repaint(); // évite l'erreur où la ligne sélectionnée n'est plus affichée
                }
            }
        };
        this.getSelectionModel().addListSelectionListener(listener);
    }
}
