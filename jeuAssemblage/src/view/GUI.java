package view;

import javax.swing.JFrame;

import model.PlateauPuzzle;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;

/**
 * Fenêtre principale de notre application.
 */
public class GUI extends JFrame {
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur initialisant un plateau vide de dimension (10, 10).
     */
    public GUI() {
        this(new PlateauPuzzle(10, 10));
    }

    /**
     * Constructeur initialisant un plateau vide d'une taille ({@code width},
     * {@code height})
     * 
     * @param width  largeur du tableau à initialiser
     * @param height hauteur du tableau à initialiser
     */
    public GUI(int width, int height) {
        this(new PlateauPuzzle(width, height));
    }

    /**
     * Constructeur.
     * 
     * @param board plateau à utiliser
     */
    public GUI(PlateauPuzzle board) {
        super("Jeu assemblage de pièces");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(400, 250));

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new PiecesTable(board), BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);
    }
}
