package view;

import javax.swing.JPanel;

import model.PlateauPuzzle;
import piecesPuzzle.Piece;
import piecesPuzzle.observer.ModelListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un panel où les pièces et les cases sont affichées via
 * une fenêtre graphique.
 */
public class GraphicsPanel extends JPanel implements ModelListener, KeyListener, MouseListener, MouseMotionListener {
    private static final long serialVersionUID = 1L;

    /**
     * Taille de chaque case en pixels.
     */
    public static final int BOX_SIZE = 40;

    /**
     * Liste de couleurs pour les pièces.
     */
    private static final List<Color> PIECE_COLORS = new ArrayList<>(6);

    /**
     * Liste de couleurs pour les pièces sélectionnées.
     */
    private static final List<Color> SELECTED_PIECE_COLORS = new ArrayList<>(6);
    static {
        PIECE_COLORS.add(new Color(45, 23, 235)); // bleu foncé
        PIECE_COLORS.add(new Color(224, 199, 34)); // jaune
        PIECE_COLORS.add(new Color(232, 14, 203)); // rose
        PIECE_COLORS.add(new Color(14, 196, 232)); // bleu clair
        PIECE_COLORS.add(new Color(230, 117, 25)); // orange
        PIECE_COLORS.add(new Color(141, 9, 230)); // violet

        SELECTED_PIECE_COLORS.add(new Color(45, 23, 235, 127)); // bleu foncé
        SELECTED_PIECE_COLORS.add(new Color(224, 199, 34, 127)); // jaune
        SELECTED_PIECE_COLORS.add(new Color(232, 14, 203, 127)); // rose
        SELECTED_PIECE_COLORS.add(new Color(14, 196, 232, 127)); // bleu clair
        SELECTED_PIECE_COLORS.add(new Color(230, 117, 25, 127)); // orange
        SELECTED_PIECE_COLORS.add(new Color(141, 9, 230, 127)); // violet
    }

    /**
     * Couleur de pièce à afficher en cas d'erreur.
     */
    private static final Color INVALID_POSITION_COLOR = new Color(224, 31, 31, 191);

    /**
     * Plateau de jeu.
     */
    private PlateauPuzzle board;

    /**
     * Pièce sélectionnée.
     */
    private Piece selectedPiece = null;

    /**
     * Coordonnées initiales de la pièce sélectionnée.
     */
    private int initialXSelectedPiece = -1, initialYSelectedPiece = -1;

    /**
     * Nombre de rotation à +90 degrès effectuées.
     */
    private int numberOfPlus90DegreeRotation = 0;

    /**
     * Booléen représentant une erreur sur la pièce sélectionnée.
     */
    private boolean errorPiece = false;

    /**
     * Constructeur par défaut.
     * 
     * @param board plateau de jeu
     */
    public GraphicsPanel(PlateauPuzzle board) {
        this.board = board;
        this.board.addModelListener(this);
        this.setPreferredSize(new Dimension(this.board.getWidth() * BOX_SIZE, this.board.getHeight() * BOX_SIZE));
        this.setBackground(Color.WHITE);
        this.setForeground(Color.BLACK);
        // listeners pour interagir
        this.setFocusable(true);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /**
     * Permet de modifier la pièce sélectionnée. Modifie aussi les variables
     * contenant les coordonnées initiales de la pièce sélectionnée, une variable
     * disant le nombre de rotation et une variable montrant une éventuelle erreur
     * au niveau visuel pour informer l'utilisateur.
     * 
     * @param piece pièce à sélectionner
     * @see #selectedPiece
     * @see #initialXSelectedPiece
     * @see #initialYSelectedPiece
     * @see #errorPiece
     * @see #numberOfPlus90DegreeRotation
     */
    public void setSelectedPiece(Piece piece) {
        this.selectedPiece = piece;
        if (piece != null) { // on garde les coordonnées
            this.initialXSelectedPiece = piece.getX();
            this.initialYSelectedPiece = piece.getY();
        } else { // on réinitialise tout
            this.initialXSelectedPiece = -1;
            this.initialYSelectedPiece = -1;
            this.errorPiece = false;
            this.numberOfPlus90DegreeRotation = 0;
        }
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    /**
     * Permet de dessiner les pièces sur la planche graphique fournit par
     * l'interface graphique.
     * 
     * @param g planche graphique
     */
    private void doDrawing(Graphics g) {
        // permet de représenter les bordures des cases et les cases vides
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.WHITE);
        for (int i = 0; i < this.board.getWidth(); i++) {
            for (int j = 0; j < this.board.getHeight(); j++) {
                g.fillRect(i * BOX_SIZE + 1, j * BOX_SIZE + 1, BOX_SIZE - 2, BOX_SIZE - 2);
            }
        }

        // permet de dessiner les pièces
        int colorListSize = PIECE_COLORS.size();
        for (int i = 0; i < this.board.getNumberOfPiece(); i++) {
            Piece piece = this.board.getPiece(i);
            if (piece.equals(this.selectedPiece)) {
                if (this.errorPiece) {
                    g.setColor(INVALID_POSITION_COLOR);
                } else {
                    g.setColor(SELECTED_PIECE_COLORS.get(i % colorListSize));
                }
            } else {
                g.setColor(PIECE_COLORS.get(i % colorListSize));
            }

            boolean[][] pieceBoard = piece.getBoard();
            for (int y = 0; y < piece.getHeight(); y++) {
                for (int x = 0; x < piece.getWidth(); x++) {
                    // on colore seulement les cases de la pièce, pas le tableau complet
                    if (pieceBoard[y][x] == true) {
                        g.fillRect((piece.getX() + x) * BOX_SIZE + 1, (piece.getY() + y) * BOX_SIZE + 1, BOX_SIZE - 2,
                                BOX_SIZE - 2);
                    }
                }
            }
        }
    }

    @Override
    public void somethingHasChanged(Object source) {
        this.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
        if (this.selectedPiece != null) {
            int dx = arg0.getX() / BOX_SIZE - this.selectedPiece.getX();
            int dy = arg0.getY() / BOX_SIZE - this.selectedPiece.getY();
            this.selectedPiece.translate(dx, dy); // on la bouge pour que ça l'update à l'écran
            // permet de savoir s'il y a un problème avec les nouvelles coordonnées de la
            // pièce
            this.errorPiece = !this.board.getActionResponsabilityChain().performAction(this.board, this.selectedPiece);
            this.repaint(); // on update le panel
        }
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        int newX = arg0.getX() / BOX_SIZE;
        int newY = arg0.getY() / BOX_SIZE;
        if (this.selectedPiece == null) {
            for (int i = 0; i < this.board.getNumberOfPiece(); i++) {
                Piece piece = this.board.getPiece(i);
                if (piece.occupiesInBoard(newX, newY)) {
                    this.setSelectedPiece(piece);
                    break;
                }
            }
        } else {
            if (this.errorPiece) {
                this.selectedPiece.translate(this.initialXSelectedPiece - newX, this.initialYSelectedPiece - newY); // on
                                                                                                                    // la
                                                                                                                    // remet
                                                                                                                    // à
                                                                                                                    // sa
                                                                                                                    // place
                                                                                                                    // initiale
                for (int i = 0; i < this.numberOfPlus90DegreeRotation; i++) {
                    this.selectedPiece.rotate(Piece.Rotate.MINUS_90_DEGREES);
                }
            }

            // on remet toutes les variables de sélection à des valeurs nulles
            this.setSelectedPiece(null);
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        if (this.selectedPiece != null) {
            char keyChar = arg0.getKeyChar();
            if (keyChar == 'a') { // tourne la pièce à gauche
                this.selectedPiece.rotate(Piece.Rotate.MINUS_90_DEGREES);
                if (this.numberOfPlus90DegreeRotation == 0) {
                    this.numberOfPlus90DegreeRotation = 3;
                } else {
                    this.numberOfPlus90DegreeRotation--;
                }
            } else if (keyChar == 'e') { // tourne la pièce à droite
                this.selectedPiece.rotate(Piece.Rotate.PLUS_90_DEGREES);
                this.numberOfPlus90DegreeRotation++;
            }

            // permet de ne pas avoir de problème pour les calculs ou les rotations
            if (numberOfPlus90DegreeRotation == 4) {
                this.numberOfPlus90DegreeRotation = 0;
            }
            // permet de savoir s'il y a un problème avec les nouvelles coordonnées de la
            // pièce
            this.errorPiece = !this.board.getActionResponsabilityChain().performAction(this.board, this.selectedPiece);
            this.repaint(); // on update le panel
        }
    }
}
