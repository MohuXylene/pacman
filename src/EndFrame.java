import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class EndFrame extends JFrame {
    private final GameFrame gameFrame;
    private final String score;
    private final JTextField nameField;

    public EndFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        this.setTitle("Game Over");
        this.setSize(500, 600);
        this.setLayout(new BorderLayout());

        JPanel endPanel = new JPanel();
        endPanel.setLayout(new BoxLayout(endPanel, BoxLayout.Y_AXIS));

        JLabel endLabel = new JLabel("Game Over");
        endLabel.setFont(new Font("Arial", Font.BOLD, 60));
        endLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        score = String.valueOf(gameFrame.getScore());
        JLabel scoreLabel = new JLabel("your score: " + score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("choose your name");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setAlignmentX(CENTER_ALIGNMENT);

        JPanel nameFieldPanel = new JPanel();
        nameFieldPanel.setLayout(new FlowLayout());
        nameFieldPanel.setAlignmentX(CENTER_ALIGNMENT);

        nameField = new JTextField(14);
        nameField.setFont(new Font("Arial", Font.BOLD, 20));

        nameFieldPanel.add(nameField);

        JButton submitButton = new JButton("submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 20));
        submitButton.setAlignmentX(CENTER_ALIGNMENT);

        submitButton.addActionListener((e) -> {
            submitButton();
        });

        JButton continueButton = new JButton("continue");
        continueButton.setFont(new Font("Arial", Font.BOLD, 20));
        continueButton.setAlignmentX(CENTER_ALIGNMENT);

        continueButton.addActionListener((e) -> {
            continueButton();
        });

        endPanel.add(Box.createRigidArea(new Dimension(10, 70)));
        endPanel.add(endLabel);
        endPanel.add(Box.createRigidArea(new Dimension(10, 70)));
        endPanel.add(scoreLabel);
        endPanel.add(Box.createRigidArea(new Dimension(10, 70)));
        endPanel.add(nameLabel);
        endPanel.add(Box.createRigidArea(new Dimension(10, 20)));
        endPanel.add(nameFieldPanel);
        endPanel.add(Box.createRigidArea(new Dimension(10, 20)));
        endPanel.add(submitButton);
        endPanel.add(Box.createRigidArea(new Dimension(10, 40)));
        endPanel.add(continueButton);
        endPanel.add(Box.createRigidArea(new Dimension(10, 40)));

        this.add(endPanel, BorderLayout.CENTER);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void continueButton() {
        gameFrame.finishGame();
    }

    public void submitButton()  {
        String name = nameField.getText();
        if(name.length() <= 0 || name.length() > 16) {
            JOptionPane.showMessageDialog(this, "name must be between 1 and 16 characters");
            return;
        }
        if(!name.matches("[a-zA-Z0-9]+")) {
            JOptionPane.showMessageDialog(this, "name must contain only letters and numbers");
            return;
        }
        overwriteHighScores(name);
        gameFrame.finishGame();
    }

    public void overwriteHighScores(String name) {
        boolean found = false;
        try {
            String newRecord = name + ":\t" + score;
            ArrayList<ScoreRecord> scores;

            FileInputStream fis = new FileInputStream("high-scores.ser");
            ObjectInputStream istream = new ObjectInputStream(fis);
            scores = (ArrayList<ScoreRecord>) istream.readObject();

            fis.close();
            istream.close();
            for(int i = 0; i < scores.size(); i++) {
                if(scores.get(i).getName().equals(name)) {
                    if(Integer.parseInt(score) > scores.get(i).getScore()) {
                        scores.set(i, new ScoreRecord(newRecord));
                    }
                    found = true;
                }
            }
            if(!found) {
                scores.add(new ScoreRecord(newRecord));
            }
            Collections.sort(scores);

            FileOutputStream fos = new FileOutputStream("high-scores.ser");
            ObjectOutputStream ostream = new ObjectOutputStream(fos);
            ostream.writeObject(scores);

            ostream.close();
            fos.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
