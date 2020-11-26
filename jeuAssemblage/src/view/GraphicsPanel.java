package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import model.PlateauPuzzle;
import model.chains.InBoardChain;
import piecesPuzzle.Piece;
import piecesPuzzle.RectanglePiece;
import piecesPuzzle.observer.ModelListener;
import settings.Settings;

/**
 * Classe représentant un panel où les pièces et les cases sont affichées via
 * une fenêtre graphique.
 */
public class GraphicsPanel extends JPanel implements ModelListener, KeyListener, MouseListener, MouseMotionListener {
    private static final long serialVersionUID = 1L;

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
        this.setPreferredSize(
                new Dimension(this.board.getWidth() * Settings.BOX_SIZE, this.board.getHeight() * Settings.BOX_SIZE));
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
                g.fillRect(i * Settings.BOX_SIZE + 1, j * Settings.BOX_SIZE + 1, Settings.BOX_SIZE - 2,
                        Settings.BOX_SIZE - 2);
            }
        }

        // permet de dessiner les pièces
        int colorListSize = Settings.PIECE_COLORS.size();
        for (int i = 0; i < this.board.getNumberOfPiece(); i++) {
            Piece piece = this.board.getPiece(i);
            if (piece.equals(this.selectedPiece)) {
                if (this.errorPiece) {
                    g.setColor(Settings.INVALID_POSITION_COLOR);
                } else {
                    g.setColor(Settings.SELECTED_PIECE_COLORS.get(i % colorListSize));
                }
            } else {
                g.setColor(Settings.PIECE_COLORS.get(i % colorListSize));
            }

            boolean[][] pieceBoard = piece.getBoard();
            for (int y = 0; y < piece.getHeight(); y++) {
                for (int x = 0; x < piece.getWidth(); x++) {
                    // on colore seulement les cases de la pièce, pas le tableau complet
                    if (pieceBoard[y][x] == true) {
                        int nextX = piece.getX() + x;
                        int nextY = piece.getY() + y;
                        // ne colorie seulement les cases se trouvant sur le plateau, et pas les cases
                        // qui dépassent de celui-ci
                        if (new InBoardChain(null).performAction(board, new RectanglePiece(nextX, nextY, 1, 1))) {
                            g.fillRect((nextX) * Settings.BOX_SIZE + 1, (nextY) * Settings.BOX_SIZE + 1,
                                    Settings.BOX_SIZE - 2, Settings.BOX_SIZE - 2);
                        }
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
    public void setEnabled(boolean enabled) {
        if (!enabled) {
            this.setFocusable(false);
            this.removeKeyListener(this);
            this.removeMouseListener(this);
            this.removeMouseMotionListener(this);
            this.board.removeModelListener(this);
        }
        super.setEnabled(enabled);
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
        if (this.selectedPiece != null) {
            int dx = arg0.getX() / Settings.BOX_SIZE - this.selectedPiece.getX();
            int dy = arg0.getY() / Settings.BOX_SIZE - this.selectedPiece.getY();
            this.selectedPiece.translate(dx, dy); // on la bouge pour que ça l'update à l'écran
            // permet de savoir s'il y a un problème avec les nouvelles coordonnées de la
            // pièce
            this.errorPiece = !this.board.getActionResponsabilityChain().performAction(this.board, this.selectedPiece);
            this.repaint(); // on update le panel
        }
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        int newX = arg0.getX() / Settings.BOX_SIZE;
        int newY = arg0.getY() / Settings.BOX_SIZE;
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
            this.board.update();
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
