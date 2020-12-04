package jeuAssemblage;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import jeuAssemblage.model.PlateauPuzzleIO;
import jeuAssemblage.model.arrangements.DefaultPieceArrangement;
import jeuAssemblage.model.arrangements.PieceArrangement;
import jeuAssemblage.model.arrangements.PlateauPuzzleFactory;
import jeuAssemblage.view.GUI;

/**
 * Class principale à lancer.
 */
public class Main {
    /**
     * Le générateur d'arrangement de pièces.
     */
    private static PieceArrangement arrangement = new DefaultPieceArrangement();

    /**
     * Méthode principale appelée pour démarrer l'application.
     * 
     * @param args arguments données via le terminal
     * @throws Exception levée lorsqu'une erreur est levée dans l'application
     */
    public static void main(String[] args) throws Exception {
        Object[] options = { "Charger une partie", "Créer une nouvelle partie" };
        int n = JOptionPane.showOptionDialog(null, "Quel choix voulez-vous faire ?", "Début de partie",
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
                    PlateauPuzzleIO boardIO = PlateauPuzzleIO.loadOldConfig(chooser.getSelectedFile());
                    new GUI(boardIO.getBoard(), boardIO.getBestScore(), boardIO.getPlayer(), boardIO.getNumberActions(),
                            chooser.getSelectedFile());
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "Le fichier choisi n'existe pas ou plus.", "Fichier non trouvé",
                            JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException | IOException e) {
                    JOptionPane.showMessageDialog(null, "Une erreur est intervenue lors du chargement de votre partie.",
                            "Erreur chargement", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (n == 1) {
            if (arrangement == null) {
                arrangement = new DefaultPieceArrangement();
            }
            new GUI(PlateauPuzzleFactory.generatePlateauPuzzle(arrangement), -1, null, -1, null);
        }
    }
}
