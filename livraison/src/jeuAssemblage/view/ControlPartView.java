package jeuAssemblage.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileSystemView;

import jeuAssemblage.Settings;
import jeuAssemblage.model.PlateauPuzzle;
import jeuAssemblage.model.PlateauPuzzleIO;
import jeuAssemblage.model.aiAlgorithms.AI;
import jeuAssemblage.model.aiAlgorithms.Capsule;
import jeuAssemblage.view.widgets.PlaceholderTextField;
import piecesPuzzle.Piece;
import piecesPuzzle.observer.ModelListener;

/**
 * Classe permettant d'ajouter des contrôles sur l'application.
 */
public class ControlPartView extends JPanel implements ModelListener, ActionListener, DocumentListener, KeyListener {
    private static final long serialVersionUID = 1L;

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
     * Nombre de coups utilisés pour le record.
     */
    private int nbActionsOfBestScore;

    /**
     * Ancien fichier de configuration. Vaut {@code null} si c'est une nouvelle
     * configuration.
     */
    private File oldFileConfig;

    /**
     * Instance permettant d'écrire ou lire dans un fichier spécialement pour une
     * instance de {@link PlateauPuzzle}.
     */
    private PlateauPuzzleIO boardIO;

    /**
     * Plateau de jeu.
     */
    private PlateauPuzzle board;

    /**
     * Algorithme d'intelligence artificielle à utiliser.
     */
    private AI algorithm;

    private JLabel bestScoreLabel, scoreLabel;
    private JButton saveConfigButton, scoreButton, aiButton;
    private PlaceholderTextField playerNameTextField;
    private GraphicsPanel boardView;
    private GUI gui;
    private PiecesTable piecesTable;

    /**
     * Constructeur. Créer un panneau avec une ancienne configuration sauvegardée
     * dans un fichier
     * 
     * @param gui                  fenêtre principale
     * 
     * @param boardView            panneau où les pièces sont dessinées
     * 
     * @param board                plateau de jeu
     * @param bestScore            meilleur score
     * @param bestPlayer           joueur associé au meilleur score
     * @param nbActionsOfBestScore nombre d'actions associées au meilleur score
     * @param oldFileConfig        fichier de la configuration utilisée
     * @param piecesTable          table de l'interface graphique
     * @param algorithm            algorithme d'intelligence artificielle à utiliser
     * @throws IOException levée lorsque l'on n'arrive pas à lire ou écrire dans les
     *                     fichiers
     */
    public ControlPartView(GUI gui, GraphicsPanel boardView, PlateauPuzzle board, int bestScore, String bestPlayer,
            int nbActionsOfBestScore, File oldFileConfig, PiecesTable piecesTable, AI algorithm) throws IOException {
        super(new GridLayout(6, 1, 0, 40));
        this.bestScore = bestScore;
        this.bestPlayer = bestPlayer;
        this.nbActionsOfBestScore = nbActionsOfBestScore;
        this.oldFileConfig = oldFileConfig;
        this.boardIO = PlateauPuzzleIO.saveBoardInTmpFile(board);
        this.board = board;
        this.board.addModelListener(this);
        this.algorithm = algorithm;
        this.boardView = boardView;
        this.gui = gui;
        this.piecesTable = piecesTable;
        this.createElements();
    }

    /**
     * Constructeur. Permet de créer un panneau où une configuration est utilisée.
     * 
     * @param gui         fenêtre principale
     * @param boardView   panneau où les pièces sont dessinées
     * @param board       plateau de jeu
     * @param piecesTable table de l'interface graphique
     * @param algorithm   algorithme d'intelligence artificielle à utiliser
     * @throws IOException levée lorsque l'on n'arrive pas à lire ou écrire dans les
     *                     fichiers
     */
    public ControlPartView(GUI gui, GraphicsPanel boardView, PlateauPuzzle board, PiecesTable piecesTable, AI algorithm)
            throws IOException {
        this(gui, boardView, board, -1, null, -1, null, piecesTable, algorithm);
    }

    /**
     * Méthode auxiliaire permettant de créer les éléments de ce panel.
     */
    private void createElements() {
        // on initialise tous les composants
        this.bestScoreLabel = new JLabel("<html>Meilleur score : " + (this.bestScore == -1 ? "Aucun"
                : (this.bestScore + "<br>par " + this.bestPlayer + " en " + this.nbActionsOfBestScore + " coups"))
                + "</html>");
        this.bestScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.scoreLabel = new JLabel(
                "<html>Il vous reste " + this.board.getLeftAvailableActions() + "<br>actions disponibles</html>");
        this.scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.playerNameTextField = new PlaceholderTextField();
        this.playerNameTextField.setPlaceholder("Entrez un nom de joueur");
        this.saveConfigButton = new JButton("<html>Sauvegarder cette<br>configuration</html>");
        this.saveConfigButton.setHorizontalAlignment(SwingConstants.CENTER);
        this.saveConfigButton.setToolTipText("Terminez le jeu pour débloquer ce bouton");
        this.saveConfigButton.setEnabled(false);
        this.scoreButton = new JButton("<html>Terminer la partie<br>et regarder le score</html>");
        this.scoreButton.setHorizontalAlignment(SwingConstants.CENTER);
        this.scoreButton.setToolTipText("Entrez un nom d'utilisateur pour activer ce bouton");
        this.scoreButton.setEnabled(false);
        this.aiButton = new JButton("Laisser l'IA jouer");
        this.aiButton.setHorizontalAlignment(SwingConstants.CENTER);

        // on leur met des listeners pour pouvoir les contrôler
        this.playerNameTextField.getDocument().addDocumentListener(this);
        this.playerNameTextField.addKeyListener(this);
        this.saveConfigButton.addActionListener(e -> this.saveConfig());
        this.scoreButton.addActionListener(e -> this.finishGame());
        this.aiButton.addActionListener(this);

        this.add(this.bestScoreLabel);
        this.add(this.scoreLabel);
        this.add(this.playerNameTextField);
        this.add(this.saveConfigButton);
        this.add(this.aiButton);
        this.add(this.scoreButton);
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

    @Override
    public void somethingHasChanged(Object arg0) {
        this.scoreLabel.setText("<html>Il vous reste " + ((PlateauPuzzle) arg0).getLeftAvailableActions()
                + "<br>actions disponibles</html>");
        if (this.board.isFinished()) {
            this.aiButton.setEnabled(false);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent arg0) {
    }

    @Override
    public void insertUpdate(DocumentEvent arg0) {
        this.scoreButton.setEnabled(true);
    }

    @Override
    public void removeUpdate(DocumentEvent arg0) {
        this.scoreButton.setEnabled(arg0.getOffset() != 0);
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.boardView.requestFocus(); // permet de retourner sur le plateau
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

    /**
     * Méthode permettant de sauvegarder la configuration de cette partie.
     */
    public void saveConfig() {
        File file = this.oldFileConfig;
        if (file == null) {
            JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setDialogTitle("Choisir un fichier pour enregistrer la configuration");
            chooser.setMultiSelectionEnabled(false);
            chooser.setFileFilter(Settings.FILENAME_FILTER);
            int returnValue = chooser.showSaveDialog(null);

            if (JFileChooser.APPROVE_OPTION == returnValue) {
                file = new File(chooser.getSelectedFile().getAbsolutePath()
                        + (chooser.getSelectedFile().getAbsolutePath().indexOf(Settings.BOARD_FILE_EXTENSION) != -1 ? ""
                                : "." + Settings.BOARD_FILE_EXTENSION));
            }
        }
        if (file != null) {
            try {
                this.boardIO.saveConfigInFile(file, this.bestScore, this.bestPlayer,
                        this.board.getMaxAvailableActions() - this.board.getLeftAvailableActions());
                remove(this.saveConfigButton);
                remove(this.scoreButton);
                JOptionPane.showMessageDialog(gui, "La sauvegarde de votre partie s'est déroulée sans accroc :)",
                        "Sauvegarde réussie", JOptionPane.INFORMATION_MESSAGE);
                this.gui.dispose();
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(this.gui,
                        "Une erreur est intervenue lors de la sauvegarde de votre partie :/", "Erreur sauvegarde",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Cette méthode permet de terminer la partie et d'effectuer les changements
     * visuels liés à cette action.
     */
    public void finishGame() {
        int score = this.board.calculateScore();
        this.scoreLabel.setText("<html>Score actuel : " + score + "<br>en "
                + (this.board.getMaxAvailableActions() - this.board.getLeftAvailableActions()) + " coups</html>");
        if (saveScore(score, playerNameTextField.getText())) {
            this.scoreLabel.setForeground(Color.GREEN);
            this.saveConfigButton.setEnabled(true);
        } else {
            this.scoreLabel.setForeground(Color.RED);
            this.saveConfigButton.setToolTipText("Votre score n'est pas un record et ne peut être enregistré.");
        }
        this.boardView.setEnabled(false);
        this.scoreButton.setEnabled(false);
        this.aiButton.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            this.boardView.requestFocus(); // on remet le focus sur le plateau
            Capsule solution = this.algorithm.start();
            if (solution != null) { // si on a une solution
                this.piecesTable.reset(solution.getBoard(), boardView); // on change la table
                this.boardView.changeBoard(solution.getBoard()); // on change aussi le visuel du plateau
                this.board = solution.getBoard();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Une erreur est intervenue lors de l'exécution de l'algorithme.",
                    "Erreur algorithme", JOptionPane.ERROR_MESSAGE);
        }
    }
}
