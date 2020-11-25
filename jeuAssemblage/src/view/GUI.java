package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import model.PieceFactory;
import model.PlateauPuzzle;
import model.RandomRotatedPieceFactory;
import settings.Settings;

/**
 * Fenêtre principale de notre application.
 */
public class GUI extends JFrame {
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur par défaut.
     * 
     * @throws IOException levée lorsque l'on n'arrive pas à lire ou écrire dans les
     *                     fichiers
     */
    public GUI() throws IOException {
        super("Jeu assemblage de pièces");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.homeView();
    }

    /**
     * Permet de charger ou recharger la fenêtre principale.
     */
    public void reloadWindow() {
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public void homeView() {
        Object[] options = { "Charger une partie", "Créer une nouvelle partie" };
        int n = JOptionPane.showOptionDialog(this, "Quel choix voulez-vous faire ?", "Début de partie",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        if (n == 0) {
            JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setDialogTitle("Choisir un fichier pour enregistrer la configuration");
            chooser.setMultiSelectionEnabled(false);
            chooser.setFileFilter(Settings.FILENAME_FILTER);

            int returnValue = chooser.showOpenDialog(null);
            if (JFileChooser.APPROVE_OPTION == returnValue) {
                try {
                    loadOldConfig(chooser.getSelectedFile());
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(this, "Le fichier choisi n'existe pas ou plus.", "Fichier non trouvé",
                            JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException | IOException e) {
                    JOptionPane.showMessageDialog(this, "Une erreur est intervenue lors du chargement de votre partie.",
                            "Erreur chargement", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (n == 1) {
            createNewConfig();
        } else {
            this.dispose();
        }
    }

    /**
     * Créer une nouvelle configuration de plateau.
     */
    private void createNewConfig() {
        Random random = new Random();
        PlateauPuzzle board = new PlateauPuzzle(random.nextInt(6) + 6, random.nextInt(6) + 6);
        for (int i = 0; i < 6; i++) {
            try {
                board.addPiece(PieceFactory.getPiece(new RandomRotatedPieceFactory(10, 10, 5, 5, 3)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.gameView(board, -1, null, null);
    }

    /**
     * Charge une ancienne configuration de plateau.
     * 
     * @param file fichier
     * @throws FileNotFoundException  levée lorsque le fichier recherché n'est pas
     *                                trouvé
     * @throws IOException            levée lorsque qu'il y a une erreur de lecture
     * @throws ClassNotFoundException levée lorsque qu'on ne trouve pas la classe
     *                                d'où proviennent les objets sauvegardés
     */
    private void loadOldConfig(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        PlateauPuzzle board = (PlateauPuzzle) ois.readObject();
        int score = (int) ois.readObject();
        String player = (String) ois.readObject();
        ois.close();
        this.gameView(board, score, player, file);
    }

    /**
     * Remplace les éléments de la fenêtre principale.
     * 
     * @param board  plateau de jeu
     * @param score  meilleur score
     * @param player meilleur joueur
     * @param file   fichier d'où les informations proviennent
     */
    private void gameView(PlateauPuzzle board, int score, String player, File file) {
        try {
            GraphicsPanel boardView = new GraphicsPanel(board);
            Container contentPane = this.getContentPane();
            contentPane.removeAll();
            contentPane.add(boardView, BorderLayout.CENTER);
            contentPane.add(new PiecesTable(board, boardView), BorderLayout.SOUTH);
            contentPane.add(new ControlPartView(this, boardView, board, score, player, file), BorderLayout.EAST);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.reloadWindow();
    }
}
