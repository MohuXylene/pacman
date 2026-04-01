import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LowerGamePanel extends JPanel {
    private final JPanel lifesPanel;
    private final JPanel pausePanel;

    private final ArrayList<JLabel> lifesLabels = new ArrayList<>();
    private final GameFrame gameFrame;

    public LowerGamePanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        this.setPreferredSize(new Dimension(500, 100));
        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());

        lifesPanel = new JPanel();
        lifesPanel.setLayout(new BoxLayout(lifesPanel, BoxLayout.X_AXIS));
        lifesPanel.setBackground(Color.BLACK);

        for(int i = 0; i < GameFrame.maxLifes; i++) {
            JLabel lifeLabel = new JLabel();
            ImageIcon pacmanImg = new ImageIcon(Pacman.img);
            lifeLabel.setIcon(new ImageIcon(pacmanImg.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));

            lifesLabels.add(lifeLabel);
            lifesPanel.add(lifeLabel);
        }

        JPanel powerUpsPanel = new JPanel();
        powerUpsPanel.setLayout(new BoxLayout(powerUpsPanel, BoxLayout.X_AXIS));
        powerUpsPanel.setBackground(Color.BLACK);

        pausePanel = new JPanel();
        pausePanel.setLayout(new BoxLayout(pausePanel, BoxLayout.Y_AXIS));
        pausePanel.setOpaque(false);
        pausePanel.setVisible(false);

        JLabel pauseLabel = new JLabel("Paused", JLabel.CENTER);
        pauseLabel.setFont(new Font("Arial", Font.BOLD, 25));
        pauseLabel.setAlignmentX(CENTER_ALIGNMENT);
        pauseLabel.setForeground(Color.WHITE);
        pauseLabel.setOpaque(false);

        JButton pasueExitButton = new JButton("Exit");
        pasueExitButton.setFont(new Font("Arial", Font.BOLD, 15));
        pasueExitButton.setAlignmentX(CENTER_ALIGNMENT);


        pasueExitButton.addActionListener((e) -> {
            pauseExitButton();
        });

        pausePanel.add(pauseLabel);
        pausePanel.add(Box.createRigidArea(new Dimension(10, 15)));
        pausePanel.add(pasueExitButton);

        this.add(lifesPanel, BorderLayout.WEST);
        this.add(pausePanel, BorderLayout.CENTER);
        this.add(powerUpsPanel, BorderLayout.EAST);
    }

    public void removeLife() {
        lifesLabels.removeLast();
        lifesPanel.remove(lifesLabels.size() - 1);
    }

    public void addPowerUp() {

    }

    public void showPause(boolean isPaused) {
        pausePanel.setVisible(isPaused);
    }

    public void pauseExitButton() {
        gameFrame.finishGame();
    }
}
