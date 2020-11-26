package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import settings.Settings;

/**
 * Classe permettant d'effectuer des action I/O en lien avec la classe
 * {@link PlateauPuzzle}.
 */
public class PlateauPuzzleIO {
    /**
     * Plateau de jeu.
     */
    private PlateauPuzzle board;

    /**
     * Meilleur score enregistré associé au plateau.
     */
    private int bestScore;

    /**
     * Joueur associé au meilleur score.
     */
    private String player;

    /**
     * Fichier temporaire.
     */
    private File tmpFile;

    /**
     * Constructeur permettant de rejouer une ancienne configuration sauvegardée.
     * 
     * @param board     plateau de jeu
     * @param bestScore meilleur score
     * @param player    joueur associé au record
     */
    private PlateauPuzzleIO(PlateauPuzzle board, int bestScore, String player) {
        this.board = board;
        this.bestScore = bestScore;
        this.player = player;
    }

    /**
     * Constructeur permettant de créer une nouvelle configuration.
     * 
     * @param tmpFile fichier temporaire
     */
    private PlateauPuzzleIO(File tmpFile) {
        this(null, -1, "");
        this.tmpFile = tmpFile;
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
     * @return instance permettant des actions I/O en lien avec
     *         {@link PlateauPuzzle}
     */
    public static PlateauPuzzleIO loadOldConfig(File file)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        PlateauPuzzle board = (PlateauPuzzle) ois.readObject();
        int score = (int) ois.readObject();
        String player = (String) ois.readObject();
        ois.close();
        return new PlateauPuzzleIO(board, score, player);
    }

    /**
     * Enregistre le plateau de jeu dans un fichier temporaire. Permet de garder la
     * configuration initiale telle qu'elle était lors de sa première génération.
     * 
     * @param board plateau de jeu
     * @throws IOException levée lorsque l'on ne peut pas écrire dans ce fichier
     * @return instance permettant des actions I/O en lien avec
     *         {@link PlateauPuzzle}
     */
    public static PlateauPuzzleIO saveBoardInTmpFile(PlateauPuzzle board) throws IOException {
        File file = File.createTempFile("tmpBoard", Settings.BOARD_FILE_EXTENSION);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(board);
        oos.close();
        return new PlateauPuzzleIO(file);
    }

    /**
     * Enregistre la configuration dans un fichier donné.
     * 
     * @param file   fichier où sauvegarder
     * @param score  score à sauvegarder
     * @param player joueur associé au score à sauvegarder
     * @throws FileNotFoundException  levée lorsque le fichier recherché n'est pas
     *                                trouvé
     * @throws IOException            levée lorsque qu'il y a une erreur de lecture
     * @throws ClassNotFoundException levée lorsque qu'on ne trouve pas la classe
     *                                d'où proviennent les objets sauvegardés
     */
    public void saveConfigInFile(File file, int score, String player)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.tmpFile));
        oos.writeObject(ois.readObject());
        ois.close();
        oos.writeObject(score);
        oos.writeObject(player);
        oos.close();
        this.tmpFile.deleteOnExit();
    }

    /**
     * Récupère le plateau de jeu.
     * 
     * @return plateau de jeu
     */
    public PlateauPuzzle getBoard() {
        return this.board;
    }

    /**
     * Récupère le record du plateau.
     * 
     * @return record
     */
    public int getBestScore() {
        return this.bestScore;
    }

    /**
     * Récupère le joueur associé au record.
     * 
     * @return joueur associé au record
     */
    public String getPlayer() {
        return this.player;
    }
}
