package view;

import javax.swing.JFrame;

import model.PlateauPuzzle;

import java.awt.Container;
import java.awt.BorderLayout;

/**
 * Fenêtre principale de notre application.
 */
public class GUI extends JFrame {
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur par défaut.
     * 
     * @param board plateau à utiliser
     */
    public GUI(PlateauPuzzle board) {
        super("Jeu assemblage de pièces");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicsPanel boardView = new GraphicsPanel(board);

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(boardView, BorderLayout.CENTER);
        contentPane.add(new PiecesTable(board, boardView), BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
}
