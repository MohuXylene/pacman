import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelMenuFrame extends JFrame {
    private JPanel menuPanel;
    private Game game;

    public LevelMenuFrame(Game game) {
        this.game = game;
        this.setTitle("Choose level");
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(500, 700));

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.Y_AXIS));

        JLabel upperLabel = new JLabel("Choose level:");
        upperLabel.setFont(new Font("Arial", Font.BOLD, 60));
        upperLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        upperPanel.add(Box.createRigidArea(new Dimension(10, 70)));
        upperPanel.add(upperLabel);

        JPanel levelsPanel = new JPanel();
        levelsPanel.setLayout(new FlowLayout());

        ImageIcon img = new ImageIcon("img/level10x10.png");
        JButton level10x10Button = new JButton();
        level10x10Button.setIcon(new ImageIcon(img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        level10x10Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                levelButton(Levels.LEVEL_10x10);
            }
        });

        img = new ImageIcon("img/level13x13.png");
        JButton level13x13Button = new JButton();
        level13x13Button.setIcon(new ImageIcon(img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        level13x13Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                levelButton(Levels.LEVEL_13x13);
            }
        });

        img = new ImageIcon("img/level15x15.png");
        JButton level15x15Button = new JButton();
        level15x15Button.setIcon(new ImageIcon(img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        level15x15Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                levelButton(Levels.LEVEL_15x15);
            }
        });

        img = new ImageIcon("img/level11x11.png");
        JButton level11x11Button = new JButton();
        level11x11Button.setIcon(new ImageIcon(img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        level11x11Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                levelButton(Levels.LEVEL_11x11);
            }
        });

        img = new ImageIcon("img/level14x14.png");
        JButton level14x14Button = new JButton();
        level14x14Button.setIcon(new ImageIcon(img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        level14x14Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                levelButton(Levels.LEVEL_14x14);
            }
        });

        levelsPanel.add(level10x10Button);
        levelsPanel.add(level13x13Button);
        levelsPanel.add(level15x15Button);
        levelsPanel.add(level11x11Button);
        levelsPanel.add(level14x14Button);

        menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        menuPanel.add(upperPanel, BorderLayout.PAGE_START);
        menuPanel.add(levelsPanel, BorderLayout.CENTER);

        this.add(menuPanel, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void levelButton(Levels level) {
        game.showGameFrame(level);
    }
}
