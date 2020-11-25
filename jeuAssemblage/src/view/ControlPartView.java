package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileSystemView;

import model.PlateauPuzzle;
import settings.Settings;

/**
 * Classe permettant d'ajouter des contrôles sur l'application.
 */
public class ControlPartView extends JPanel {
    private static final long serialVersionUID = 1L;

    /**
     * Fichier temporaire où l'on sauvegarde le plateau.
     */
    private File tmpConfigFile;

    /**
     * Record de la configuration du plateau. Vaut {@code -1} si c'est une nouvelle
     * configuration.
     */
    private int bestScore;

    /**
     * Joueur associé au record de la configuration du plateau. Vaut {@code null} si
     * c'est une nouvelle configuration.
     */
    private String bestPlayer;

    /**
     * Ancien fichier de configuration. Vaut {@code null} si c'est une nouvelle
     * configuration.
     */
    private File oldFileConfig;

    /**
     * Constructeur. Créer un panneau avec une ancienne configuration sauvegardée
     * dans un fichier
     * 
     * @param gui           fenêtre principale
     * 
     * @param boardView     panneau où les pièces sont dessinées
     * 
     * @param board         plateau de jeu
     * @param bestScore     meilleur score
     * @param bestPlayer    joueur associé au meilleur score
     * @param oldFileConfig fichier de la configuration utilisée
     * @throws IOException levée lorsque l'on n'arrive pas à lire ou écrire dans les
     *                     fichiers
     */
    public ControlPartView(GUI gui, GraphicsPanel boardView, PlateauPuzzle board, int bestScore, String bestPlayer,
            File oldFileConfig) throws IOException {
        super(new GridLayout(5, 1, 0, 40));
        this.bestScore = bestScore;
        this.bestPlayer = bestPlayer;
        this.oldFileConfig = oldFileConfig;
        this.tmpConfigFile = File.createTempFile("tmpBoard", null);
        this.createElements(gui, boardView, board, bestScore, bestPlayer);
        this.saveBoardInTmpFile(board);
    }

    /**
     * Constructeur. Permet de créer un panneau où une configuration est utilisée.
     * 
     * @param gui       fenêtre principale
     * @param boardView panneau où les pièces sont dessinées
     * @param board     plateau de jeu
     * @throws IOException levée lorsque l'on n'arrive pas à lire ou écrire dans les
     *                     fichiers
     */
    public ControlPartView(GUI gui, GraphicsPanel boardView, PlateauPuzzle board) throws IOException {
        this(gui, boardView, board, -1, null, null);
    }

    /**
     * 
     * Méthode auxiliaire permettant de créer les éléments de ce panel. *
     * 
     * @param gui        fenêtre principale
     * @param boardView  panneau où les pièces sont dessinées
     * 
     * @param board      plateau de jeu
     * @param bestScore  meilleur score
     * @param bestPlayer joueur associé au meilleur score
     */
    private void createElements(GUI gui, GraphicsPanel boardView, PlateauPuzzle board, int bestScore,
            String bestPlayer) {
        // on initialise tous les composants
        JLabel bestScoreLabel = new JLabel("<html>Meilleur score : "
                + (bestScore == -1 ? "Aucun" : (bestScore + "<br>par " + bestPlayer)) + "</html>");
        bestScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel scoreLabel = new JLabel("");
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JButton saveConfig = new JButton("<html>Sauvegarder cette<br>configuration</html>");
        saveConfig.setHorizontalAlignment(SwingConstants.CENTER);
        saveConfig.setToolTipText("Terminez le jeu pour débloquer ce bouton");
        saveConfig.setEnabled(false);
        JButton scoreButton = new JButton("<html>Terminer la partie<br>et regarder le score</html>");
        scoreButton.setHorizontalAlignment(SwingConstants.CENTER);
        scoreButton.setToolTipText("Entrez un nom d'utilisateur pour activer ce bouton");
        scoreButton.setEnabled(false);
        JTextField usernameTextField = new JTextField("Username");

        // on leur met des listeners pour pouvoir les contrôler
        usernameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent arg0) {
            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {
                scoreButton.setEnabled(true);
            }

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                scoreButton.setEnabled(arg0.getOffset() != 0);
            }
        });
        scoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int score = board.calculateScore();
                scoreLabel.setText("<html>Score actuel : " + score + "</html>");
                if (saveScore(score, usernameTextField.getText())) {
                    scoreLabel.setForeground(Color.GREEN);
                    saveConfig.setEnabled(true);
                } else {
                    scoreLabel.setForeground(Color.RED);
                    saveConfig.setToolTipText("Votre score n'est pas un record et ne peut être enregistré.");
                }
                boardView.setEnabled(false);
            }
        });
        saveConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                File file = oldFileConfig;
                if (file == null) {
                    JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    chooser.setDialogTitle("Choisir un fichier pour enregistrer la configuration");
                    chooser.setMultiSelectionEnabled(false);
                    chooser.setFileFilter(Settings.FILENAME_FILTER);
                    int returnValue = chooser.showSaveDialog(null);

                    if (JFileChooser.APPROVE_OPTION == returnValue) {
                        file = new File(chooser.getSelectedFile().getAbsolutePath() + (chooser.getSelectedFile()
                                .getAbsolutePath().indexOf(Settings.BOARD_FILE_EXTENSION) != -1 ? ""
                                        : "." + Settings.BOARD_FILE_EXTENSION));
                    }
                }
                if (file != null) {
                    try {
                        saveConfigInFile(file);
                        remove(saveConfig);
                        remove(scoreButton);
                        JOptionPane.showMessageDialog(gui,
                                "La sauvegarde de votre partie s'est déroulée sans accroc :)", "Sauvegarde réussie",
                                JOptionPane.INFORMATION_MESSAGE);
                        gui.dispose();
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(gui,
                                "Une erreur est intervenue lors de la sauvegarde de votre partie :/",
                                "Erreur sauvegarde", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        this.add(bestScoreLabel);
        this.add(scoreLabel);
        this.add(usernameTextField);
        this.add(saveConfig);
        this.add(scoreButton);
    }

    /**
     * Sauvegarde le score s'il est meilleur, sinon n'effectue aucune action.
     * 
     * @param newScore  nouveau score
     * @param newPlayer joueur associé à ce score
     * @return si le nouveau score est un record ou non
     */
    private boolean saveScore(int newScore, String newPlayer) {
        if (this.bestScore == -1 || newScore < this.bestScore) {
            this.bestScore = newScore;
            this.bestPlayer = newPlayer;
            return true;
        }
        return false;
    }

    /**
     * Enregistre la configuration dans un fichier donné.
     * 
     * @param file fichier où sauvegarder
     * @throws IOException levée lorsque l'on ne peut pas écrire dans ce fichier
     */
    private void saveConfigInFile(File file) throws IOException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.tmpConfigFile));
            oos.writeObject(ois.readObject());
            ois.close();
            oos.writeObject(this.bestScore);
            oos.writeObject(this.bestPlayer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                oos.close();
            }
        }
        this.tmpConfigFile.deleteOnExit();
    }

    /**
     * Enregistre le plateau de jeu dans un fichier temporaire. Permet de garder la
     * configuration initiale telle qu'elle était lors de sa première génération.
     * 
     * @param board plateau de jeu
     * @throws IOException levée lorsque l'on ne peut pas écrire dans ce fichier
     */
    private void saveBoardInTmpFile(PlateauPuzzle board) throws IOException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(this.tmpConfigFile));
            oos.writeObject(board);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                oos.close();
            }
        }
    }
}
