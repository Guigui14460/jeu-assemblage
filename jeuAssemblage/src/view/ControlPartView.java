package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.PlateauPuzzle;

public class ControlPartView extends JPanel {
    private static final long serialVersionUID = 1L;

    public ControlPartView(PlateauPuzzle board, int bestScore, String bestPlayer) {
        super();
        this.setLayout(new GridLayout(5, 1, 0, 40));

        JLabel bestScoreLabel = new JLabel("<html>Meilleur score : " + bestScore + "<br>par " + bestPlayer + "</html>");
        JLabel scoreLabel = new JLabel("");

        JButton saveConfig = new JButton("<html>Sauvegarder cette<br>configuration</html>");
        JButton scoreButton = new JButton("<html>Terminer la partie<br>et regarder le score</html>");
        scoreButton.setEnabled(false);

        JTextField usernameTextField = new JTextField();
        usernameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent arg0) {
                warn(arg0);
            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {
                warn(arg0);
            }

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                warn(arg0);
            }

            public void warn(DocumentEvent arg0) {
                scoreButton.setEnabled(arg0.getOffset() != 0);
            }
        });

        scoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                scoreLabel.setText("Score actuel : " + board.calculateScore());
                // TODO: faire en sorte de terminer la partie
            }
        });

        saveConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO: sauvegarder le score et le plateau ici
            }
        });

        this.add(bestScoreLabel);
        this.add(scoreLabel);
        this.add(usernameTextField);
        this.add(saveConfig);
        this.add(scoreButton);
    }

}
