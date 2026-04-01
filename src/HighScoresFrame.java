import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class HighScoresFrame extends JFrame {
    private final Game game;
    private JPanel scoresPanel;
    private DefaultListModel highScoresModel = new DefaultListModel();
    private JList<ArrayList<String>> highScoresList;
    private ArrayList<String> highScoresArray = new ArrayList<>();
    private JScrollPane scrollPanel;
    private JPanel highScoresPanel;

    public HighScoresFrame(Game game) {
        this.game = game;
        this.setTitle("High Scores");
        this.setSize(500, 700);

        highScoresPanel = new JPanel();
        highScoresPanel.setLayout(new BoxLayout(highScoresPanel, BoxLayout.Y_AXIS));

        scoresPanel = new JPanel();
        scoresPanel.setLayout(new BoxLayout(scoresPanel, BoxLayout.Y_AXIS));

        JLabel scoresLabel = new JLabel("High Scores:");
        scoresLabel.setFont(new Font("Arial", Font.BOLD, 30));
        scoresLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        loadHighScores();

        highScoresList = new JList(highScoresModel);
        highScoresList.setFont(new Font("Arial", Font.PLAIN, 20));
        
        scrollPanel = new JScrollPane(highScoresList);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.addActionListener((e) -> {
                exitButton();
        });

        highScoresPanel.add(Box.createRigidArea(new Dimension(10, 30)));
        highScoresPanel.add(scoresLabel);
        highScoresPanel.add(Box.createRigidArea(new Dimension(10, 30)));
        highScoresPanel.add(scrollPanel);
        highScoresPanel.add(Box.createRigidArea(new Dimension(10, 30)));
        highScoresPanel.add(exitButton);
        highScoresPanel.add(Box.createRigidArea(new Dimension(10, 30)));

        this.add(highScoresPanel);

        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void exitButton() {
        game.showMenuFrame();
    }

    private ArrayList<String> readHighScores() {
        try {
            FileInputStream fis = new FileInputStream("high-scores.ser");
            ObjectInputStream istream = new ObjectInputStream(fis);
            ArrayList<ScoreRecord> scoresData = (ArrayList<ScoreRecord>) istream.readObject();

            ArrayList<String> result = new ArrayList<>();
            int counter = 0;
            for(ScoreRecord record : scoresData) {
                result.add(++counter + ". " + record.toString());
            }
            fis.close();
            istream.close();

            return result;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadHighScores() {
        highScoresArray = readHighScores();
        highScoresModel.removeAllElements();
        highScoresModel.addAll(highScoresArray);
    }
}
