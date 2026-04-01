import javax.swing.*;
import java.awt.*;

public class UpperGamePanel extends JPanel {
    private final JPanel scorePanel;
    private final JPanel highScorePanel;
    private final JLabel scoreLabel;
    private final JLabel highScoreLabel;
    private final JLabel highScoreValueLabel;
    private final JPanel timerPanel;
    private final TimerLabel timerLabel;

    public UpperGamePanel(Game game) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(500, 60));
        this.setBackground(Color.BLACK);

        scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.setPreferredSize(new Dimension(150, this.getHeight()));
        scorePanel.setBackground(Color.BLACK);

        scoreLabel = new JLabel("0", JLabel.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(Color.WHITE);

        scorePanel.add(Box.createVerticalGlue());
        scorePanel.add(scoreLabel);
        scorePanel.add(Box.createRigidArea(new Dimension(150, 10)));

        highScorePanel = new JPanel();
        highScorePanel.setLayout(new BoxLayout(highScorePanel, BoxLayout.Y_AXIS));
        highScorePanel.setSize(new Dimension(200, this.getHeight()));
        highScorePanel.setBackground(Color.BLACK);

        highScoreLabel = new JLabel("High Score", JLabel.CENTER);
        highScoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        highScoreLabel.setForeground(Color.WHITE);
        highScoreLabel.setAlignmentX(CENTER_ALIGNMENT);

        highScoreValueLabel = new JLabel("0", JLabel.CENTER);
        highScoreValueLabel.setFont(new Font("Arial", Font.BOLD, 20));
        highScoreValueLabel.setForeground(Color.WHITE);
        highScoreValueLabel.setAlignmentX(CENTER_ALIGNMENT);
        highScoreValueLabel.setText(String.valueOf(GameFrame.getHighScore()));

        highScorePanel.add(Box.createVerticalGlue());
        highScorePanel.add(highScoreLabel);
        highScorePanel.add(highScoreValueLabel);
        highScorePanel.add(Box.createRigidArea(new Dimension(50, 10)));

        timerPanel = new JPanel();
        timerPanel.setLayout(new BoxLayout(timerPanel, BoxLayout.Y_AXIS));
        timerPanel.setBackground(Color.BLACK);

        timerLabel = new TimerLabel(game);
        new Thread(timerLabel).start();

        timerPanel.add(Box.createVerticalGlue());
        timerPanel.add(timerLabel);
        timerPanel.add(Box.createRigidArea(new Dimension(150, 10)));

        this.add(scorePanel, BorderLayout.WEST);
        this.add(highScorePanel, BorderLayout.CENTER);
        this.add(timerPanel, BorderLayout.EAST);
    }

    public void setHighScore(int score) {
        highScoreValueLabel.setText(String.valueOf(score));
    }

    public void setScore(int score) {
        this.scoreLabel.setText(Integer.toString(score));
    }

    public void setTimer(String time) {
        this.timerLabel.setText(time);
    }
}
