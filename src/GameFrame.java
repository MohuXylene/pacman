import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public static final int maxLifes = 3;
    private static int highScore = 0;

    private int maxPoints = 0;
    private int points = 0;
    private int score = 0;
    private int lifes = maxLifes;
    private int scoreMultiplier = 1;
    private int[][] level;
    private Game game;

    private final UpperGamePanel upperGamePanel;
    private final JPanel gamePanel;
    private final LowerGamePanel lowerGamePanel;
    private Pacman pacman;

    private Levels chosenLevel;

    public GameFrame(int[][] level, Game game) {
        loadLevel(level);
        this.game = game;
        maxPoints = getMaxPoints();

        this.setSize(new Dimension(500, 700));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        gamePanel = new JPanel();
        gamePanel.setBackground(Color.BLACK);
        gamePanel.setLayout(new GridLayout(level.length, level[0].length, 0, 0));

        upperGamePanel = new UpperGamePanel(game);
        lowerGamePanel = new LowerGamePanel(this);

        initMaze();
        placePoints();
        placePowerUps();
        initPacman();
        initGhosts();

        this.add(upperGamePanel, BorderLayout.PAGE_START);
        this.add(gamePanel, BorderLayout.CENTER);
        this.add(lowerGamePanel, BorderLayout.PAGE_END);

        this.addKeyListener(new KeyboardListener(pacman, game));

        this.setBackground(Color.BLACK);
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setPause(boolean isPaused) {
        lowerGamePanel.showPause(isPaused);
    }

    public void addPoint(int amount) {
        if (amount == 20) {
            points++;
        }
        score += amount * scoreMultiplier;
        if(score > GameFrame.highScore) {
            GameFrame.highScore = score;
            upperGamePanel.setHighScore(highScore);
        }
        upperGamePanel.setScore(score);
        if(points >= maxPoints) {
            this.resetGame();
            points = 0;
        }
    }

    public void usePowerUp(int powerUp) {
        switch (powerUp) {
            case 6:
                Ghost.confuseGhosts();
                break;
            case 7:
                addScoreMultiplier(2);
                break;
            case 8:
                addScoreMultiplier(3);
                break;
            case 9:
                Ghost.freezeGhosts();
                break;
            case 10:
                Ghost.killRandomGhost();
                break;
        }
    }

    public void resetGame() {
        pacman.resetPacman();
        loadLevel(Game.getLevel());
        placePoints();
        placePowerUps();
        Ghost.respawnGhosts();
        pacman.reSpawn();
//        System.out.println(Arrays.deepToString(level).replaceAll("[\\[,]", "").replaceAll("\\]", "\n"));
    }

    public void finishGame() {
        Ghost.removeGhosts();
        GridPanel.removeArray();
        game.showMenuFrame();
    }

    public void loadLevel(int[][] levelArray) {
        this.level = new int[levelArray.length][levelArray[0].length];
        for(int i = 0; i < levelArray.length; i++) {
            for(int j = 0; j < levelArray[0].length; j++) {
                this.level[i][j] = levelArray[i][j];
            }
        }
    }

    public void addScoreMultiplier(int amount) {
        new Thread(new ScoreMultiplierCycle(this, amount)).start();
    }

    public void removeLife() {
        pacman.kill();
        lifes--;
        if(lifes <= 0) {
            lowerGamePanel.showPause(true);
            game.showEndFrame();
        } else {
            lowerGamePanel.removeLife();
            Ghost.respawnGhosts();
            pacman.reSpawn();
        }
    }

    public void initMaze() {
        for(int y = 0; y < level.length; y++) {
            for(int x = 0; x < level[0].length; x++) {
                GridPanel tile = new GridPanel(x, y, level[y][x], this);
                gamePanel.add(tile);
            }
        }
    }

    public void initPacman() {
        for(int y = 0; y < level.length; y++) {
            for(int x = 0; x < level[0].length; x++) {
                if (level[y][x] == 4) {
                    pacman = new Pacman(x, y, this);
                }
            }
        }
    }
    public void initGhosts() {
        for(int y = 0; y < level.length; y++) {
            for(int x = 0; x < level[0].length; x++) {
                if (level[y][x] == 5) {
                    new Ghost(x, y, this, game);
//                    level[y][x] = 0;
                }
            }
        }
    }

    public void placePoints() {
        for(int y = 0; y < level.length; y++) {
            for(int x = 0; x < level[0].length; x++) {
                if (level[y][x] == 3) {
                    GridPanel.getPanel(x, y).placePoint();
                }
            }
        }
    }

    public void placePowerUps() {
        for(int y = 0; y < level.length; y++) {
            for(int x = 0; x < level[0].length; x++) {
                if (level[y][x] >= 6) {
                    GridPanel.getPanel(x, y).placePowerUp(level[y][x]);
                }
            }
        }
    }

    public int getMaxPoints() {
        int result = 0;
        for(int y = 0; y < level.length; y++) {
            for(int x = 0; x < level[0].length; x++) {
                if (level[y][x] == 3) {
                    result++;
                }
            }
        }
        return result;
    }

    public JPanel getPanel(){
        return gamePanel;
    }

    public int getScore() {
        return score;
    }
    public int[][] getLevel(){
        return level;
    }
    public static int getHighScore() {
        return highScore;
    }
    public void setScoreMultiplier(int scoreMultiplier) {
        this.scoreMultiplier = scoreMultiplier;
    }
    public int getScoreMultiplier() {
        return scoreMultiplier;
    }

}

