package jeuAssemblage.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import jeuAssemblage.Settings;
import jeuAssemblage.model.PlateauPuzzle;
import jeuAssemblage.model.PlateauPuzzleIO;
import jeuAssemblage.model.arrangements.DefaultPieceArrangement;
import jeuAssemblage.model.arrangements.PieceArrangement;
import jeuAssemblage.model.arrangements.PlateauPuzzleFactory;

/**
 * Fenêtre principale de notre application.
 */
public class GUI extends JFrame {
    private static final long serialVersionUID = 1L;

    /**
     * Arrangement de pièces pour la génération du plateau.
     */
    private PieceArrangement arrangement;

    /**
     * Constructeur.
     * 
     * @param arrangement arrangement de pièces pour la génération du plateau
     * @throws IOException levée lorsque l'on n'arrive pas à lire ou écrire dans les
     *                     fichiers
     */
    public GUI(PieceArrangement arrangement) throws IOException {
        super("Jeu assemblage de pièces");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.arrangement = arrangement;
        this.homeView();
    }

    /**
     * Constructeur par défaut.
     * 
     * @throws IOException levée lorsque l'on n'arrive pas à lire ou écrire dans les
     *                     fichiers
     */
    public GUI() throws IOException {
        this(null);
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
                    this.dispose();
                } catch (ClassNotFoundException | IOException e) {
                    JOptionPane.showMessageDialog(this, "Une erreur est intervenue lors du chargement de votre partie.",
                            "Erreur chargement", JOptionPane.ERROR_MESSAGE);
                    this.dispose();
                }
            } else {
                this.dispose();
            }
        } else if (n == 1) {
            this.createNewConfig();
        } else {
            this.dispose();
        }
    }

    /**
     * Créer une nouvelle configuration de plateau.
     */
    private void createNewConfig() {
        if (this.arrangement == null) {
            this.arrangement = new DefaultPieceArrangement();
        }
        this.gameView(PlateauPuzzleFactory.generatePlateauPuzzle(this.arrangement), -1, null, -1, null);
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
        PlateauPuzzleIO boardIO = PlateauPuzzleIO.loadOldConfig(file);
        this.gameView(boardIO.getBoard(), boardIO.getBestScore(), boardIO.getPlayer(), boardIO.getNumberActions(),
                file);
    }

    /**
     * Remplace les éléments de la fenêtre principale.
     * 
     * @param board                plateau de jeu
     * @param score                meilleur score
     * @param player               meilleur joueur
     * @param nbActionsOfBestScore nombre d'actions associé au score
     * @param file                 fichier d'où les informations proviennent
     */
    private void gameView(PlateauPuzzle board, int score, String player, int nbActionsOfBestScore, File file) {
        try {
            GraphicsPanel boardView = new GraphicsPanel(board);
            Container contentPane = this.getContentPane();
            contentPane.removeAll();
            contentPane.add(boardView, BorderLayout.CENTER);
            contentPane.add(new PiecesTable(board, boardView), BorderLayout.SOUTH);
            contentPane.add(new ControlPartView(this, boardView, board, score, player, nbActionsOfBestScore, file),
                    BorderLayout.EAST);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.reloadWindow();
    }
}
