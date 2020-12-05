package jeuAssemblage.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import jeuAssemblage.model.aiAlgorithms.AI;
import jeuAssemblage.model.PlateauPuzzle;

/**
 * Fenêtre principale de notre application.
 */
public class GUI extends JFrame {
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur par défaut.
     * 
     * @param board                plateau de jeu
     * @param score                meilleur score
     * @param player               meilleur joueur
     * @param nbActionsOfBestScore nombre d'actions associé au score
     * @param file                 fichier d'où les informations proviennent
     * @throws IOException levée lorsque qu'il y a une erreur de lecture
     */
    public GUI(PlateauPuzzle board, int score, String player, int nbActionsOfBestScore, File file, AI algorithm)
            throws IOException {
        super("Jeu assemblage de pièces");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        try {
            GraphicsPanel boardView = new GraphicsPanel(board);
            Container contentPane = this.getContentPane();
            PiecesTable pieceTable = new PiecesTable(board, boardView);
            contentPane.removeAll();
            contentPane.add(boardView, BorderLayout.CENTER);
            contentPane.add(pieceTable, BorderLayout.SOUTH);
            contentPane.add(new ControlPartView(this, boardView, board, score, player, nbActionsOfBestScore, file,
                    pieceTable, algorithm), BorderLayout.EAST);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
}
