import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {
    private Game game;
    private JPanel menuPanel;

    public MenuFrame(Game game) {
        this.game = game;
        this.setTitle("Menu");
        this.setSize(new Dimension(500, 700));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        JLabel menuLabel = new JLabel("Pacman");
        menuLabel.setFont(new Font("Arial", Font.BOLD, 60));
        menuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font("Arial", Font.BOLD, 20));
        newGameButton.setAlignmentX(CENTER_ALIGNMENT);
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newGameButton();
            }
        });

        JButton highScoresButton = new JButton("High Scores");
        highScoresButton.setFont(new Font("Arial", Font.BOLD, 20));
        highScoresButton.setAlignmentX(CENTER_ALIGNMENT);
        highScoresButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                highScoresButton();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JLabel authorLabel = new JLabel("Autor: M. Kuśmierz");
        authorLabel.setFont(new Font("Arial", Font.BOLD, 15));
        authorLabel.setAlignmentX(CENTER_ALIGNMENT);

        menuPanel.add(Box.createRigidArea(new Dimension(10, 70)));
        menuPanel.add(menuLabel);
        menuPanel.add(Box.createRigidArea(new Dimension(10, 70)));
        menuPanel.add(newGameButton);
        menuPanel.add(Box.createRigidArea(new Dimension(10, 30)));
        menuPanel.add(highScoresButton);
        menuPanel.add(Box.createRigidArea(new Dimension(10, 30)));
        menuPanel.add(exitButton);
        menuPanel.add(Box.createRigidArea(new Dimension(10, 70)));
        menuPanel.add(authorLabel);


        this.add(menuPanel, BorderLayout.CENTER);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void newGameButton() {
        game.showLevelMenuFrame();
    }

    public void highScoresButton() {
        game.showHighScoresFrame();
    }
}
