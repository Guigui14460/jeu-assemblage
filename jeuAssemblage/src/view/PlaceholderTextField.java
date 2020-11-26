package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextField;

/**
 * Classe permettant d'utiliser un JTextField possédant un placeholder.
 * 
 * Source : <a href=
 * "https://stackoverflow.com/questions/16213836/java-swing-jtextfield-set-placeholder">stackoverflow</a>
 */
public class PlaceholderTextField extends JTextField {
    private static final long serialVersionUID = 1L;

    /**
     * Text à afficher en arrière-plan.
     */
    private String placeholder;

    /**
     * Constructeur par défaut.
     */
    public PlaceholderTextField() {
    }

    /**
     * Constructeur.
     * 
     * @param initialText texte initial
     */
    public PlaceholderTextField(String initialText) {
        super(initialText);
    }

    /**
     * Récupère le placeholder.
     * 
     * @return placeholder
     */
    public String getPlaceholder() {
        return this.placeholder;
    }

    /**
     * Remplace le placeholder.
     * 
     * @param newPlaceholder nouveau placeholder
     */
    public void setPlaceholder(String newPlaceholder) {
        this.placeholder = newPlaceholder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.placeholder == null || this.placeholder.length() == 0 || this.getText().length() > 0) {
            return;
        }

        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setColor(this.getDisabledTextColor());
        g2D.drawString(this.placeholder, this.getInsets().left,
                g.getFontMetrics().getMaxAscent() + this.getInsets().top);
    }
}
