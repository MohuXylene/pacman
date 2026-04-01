import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GridPanel extends JPanel {
    private static ArrayList<GridPanel> panels = new ArrayList<GridPanel>();

    private final GameFrame gameFrame;
    private final int tileX;
    private final int tileY;
    private int id;

    public GridPanel(int x, int y, int id, GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        this.id = id;
        this.tileX = x;
        this.tileY = y;

        switch (id) {
            case 1:
                this.setBackground(Color.BLUE);
            break;
            case 2:
                this.setBackground(Color.WHITE);
            break;
            default:
                this.setBackground(Color.BLACK);
                break;
        }
        this.setLayout(new BorderLayout());
        panels.add(this);
    }

    public static GridPanel getPanel(int x, int y) {
        for(GridPanel panel : panels) {
            if(panel.getTileX() == x && panel.getTileY() == y) {
                return panel;
            }
        }
        return null;
    }

    public static void removeArray() {
        panels = new ArrayList<GridPanel>();
    }

    public void placePacman(Pacman pacman){
        this.add(pacman, BorderLayout.CENTER);
    }

    public void placeGhost(Ghost ghost){
        this.add(ghost, BorderLayout.CENTER);
    }

    public void placePoint() {
        JLabel point = new JLabel("*", JLabel.CENTER);
        point.setFont(new Font("Arial", Font.BOLD, 18));
        point.setForeground(Color.WHITE);
        this.add(point, BorderLayout.CENTER);
    }

    public void placePowerUp(int type) {
        switch (type) {
            case 6:
                placeConfusionPowerUp();
                break;
            case 7:
                place2xMultiplierPowerUp();
                break;
            case 8:
                place3xMultiplierPowerUp();
                break;
            case 9:
                placeFreezingPowerUp();
                break;
            case 10:
                placeKillingPowerUp();
                break;
        }

    }

    public void placeConfusionPowerUp() {
        ImageIcon img = new ImageIcon("img/circle.png");
        img = new ImageIcon(img.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        JLabel powerUp = new JLabel();
        powerUp.setIcon(img);
        this.add(powerUp, BorderLayout.CENTER);
    }

    public void place2xMultiplierPowerUp() {
        ImageIcon img = new ImageIcon("img/2x.png");
        img = new ImageIcon(img.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        JLabel powerUp = new JLabel();
        powerUp.setIcon(img);
        this.add(powerUp, BorderLayout.CENTER);
    }

    public void place3xMultiplierPowerUp() {
        ImageIcon img = new ImageIcon("img/3x.png");
        img = new ImageIcon(img.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        JLabel powerUp = new JLabel();
        powerUp.setIcon(img);
        this.add(powerUp, BorderLayout.CENTER);
    }

    public void placeFreezingPowerUp() {
        ImageIcon img = new ImageIcon("img/ice-cube.png");
        img = new ImageIcon(img.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        JLabel powerUp = new JLabel();
        powerUp.setIcon(img);
        this.add(powerUp, BorderLayout.CENTER);
    }

    public void placeKillingPowerUp() {
        JLabel point = new JLabel("X", JLabel.CENTER);
        point.setFont(new Font("Arial", Font.BOLD, 18));
        point.setForeground(Color.WHITE);
        this.add(point, BorderLayout.CENTER);
    }

    public void clearTile() {
        this.removeAll();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public int getId() {
        return id;
    }
}
