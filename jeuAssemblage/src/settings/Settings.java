package settings;

import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;

/**
 * Classe conservant toutes les constantes et paramètres de l'application.
 */
public class Settings {
    /**
     * Taille de chaque case en pixels.
     */
    public static final int BOX_SIZE = 40;

    /**
     * Liste de couleurs pour les pièces.
     */
    public static final List<Color> PIECE_COLORS = new ArrayList<>(9);

    /**
     * Liste de couleurs pour les pièces sélectionnées.
     */
    public static final List<Color> SELECTED_PIECE_COLORS = new ArrayList<>(9);
    static {
        PIECE_COLORS.add(new Color(45, 23, 235)); // bleu foncé
        PIECE_COLORS.add(new Color(224, 199, 34)); // jaune
        PIECE_COLORS.add(new Color(232, 14, 203)); // rose
        PIECE_COLORS.add(new Color(14, 196, 232)); // bleu clair
        PIECE_COLORS.add(new Color(230, 117, 25)); // orange
        PIECE_COLORS.add(new Color(141, 9, 230)); // violet
        PIECE_COLORS.add(new Color(28, 252, 3)); // vert clair
        PIECE_COLORS.add(new Color(95, 163, 99)); // vert foncé
        PIECE_COLORS.add(new Color(75, 75, 75)); // gris

        SELECTED_PIECE_COLORS.add(new Color(45, 23, 235, 127)); // bleu foncé
        SELECTED_PIECE_COLORS.add(new Color(224, 199, 34, 127)); // jaune
        SELECTED_PIECE_COLORS.add(new Color(232, 14, 203, 127)); // rose
        SELECTED_PIECE_COLORS.add(new Color(14, 196, 232, 127)); // bleu clair
        SELECTED_PIECE_COLORS.add(new Color(230, 117, 25, 127)); // orange
        SELECTED_PIECE_COLORS.add(new Color(141, 9, 230, 127)); // violet
        SELECTED_PIECE_COLORS.add(new Color(28, 252, 3, 127)); // vert clair
        SELECTED_PIECE_COLORS.add(new Color(95, 163, 99, 127)); // vert foncé
        SELECTED_PIECE_COLORS.add(new Color(75, 75, 75, 127)); // gris
    }

    /**
     * Couleur de pièce à afficher en cas d'erreur.
     */
    public static final Color INVALID_POSITION_COLOR = new Color(224, 31, 31, 191);

    /**
     * Extension utilisée pour sauvegarder et charger des configurations.
     */
    public static final String BOARD_FILE_EXTENSION = "board_config";

    /**
     * Filtre d'extensions à ajouter dans des JFileChooser.
     */
    public static final FileNameExtensionFilter FILENAME_FILTER = new FileNameExtensionFilter(
            "Configurations de plateau (." + BOARD_FILE_EXTENSION + ")", BOARD_FILE_EXTENSION);
}
