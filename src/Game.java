import javax.swing.*;

public class Game {
    // 0 - nothing
    // 1 - wall
    // 2 - ghost door
    // 3 - point
    // 4 - pacman
    // 5 - ghost
    // 6 - anti ghost power up
    // 7 - 2x points power up
    // 8 - 3x points power up
    // 9 - freeze power up
    // 10 - kill random ghost
    private static final int[][] level_10x10 = {
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 10, 3, 3, 3, 3, 3, 3, 10, 1 },
            { 1, 3, 1, 4, 3, 3, 3, 1, 3, 1 },
            { 1, 3, 3, 1, 3, 3, 1, 3, 3, 1 },
            { 1, 3, 3, 3, 3, 3, 3, 3, 3, 1 },
            { 1, 3, 3, 3, 3, 3, 3, 3, 3, 1 },
            { 1, 3, 3, 1, 3, 3, 1, 3, 3, 1 },
            { 1, 3, 1, 1, 2, 2, 1, 1, 3, 1 },
            { 1, 1, 1, 1, 5, 5, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
    };
    private static final int[][] level_13x13 = {
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 7, 3, 3, 3, 3, 3, 3, 3, 3, 3, 6, 1 },
            { 1, 3, 1, 1, 3, 1, 3, 1, 3, 1, 1, 3, 1 },
            { 1, 3, 1, 1, 3, 1, 3, 1, 3, 1, 1, 3, 1 },
            { 1, 3, 3, 3, 3, 3, 8, 3, 3, 3, 3, 3, 1 },
            { 1, 3, 1, 3, 1, 1, 2, 1, 1, 3, 1, 3, 1 },
            { 1, 3, 3, 3, 1, 5, 5, 5, 1, 3, 3, 3, 1 },
            { 1, 3, 1, 3, 1, 1, 1, 1, 1, 3, 1, 3, 1 },
            { 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1 },
            { 1, 3, 1, 1, 3, 1, 3, 1, 3, 1, 1, 3, 1 },
            { 1, 3, 1, 1, 3, 1, 4, 1, 3, 1, 1, 3, 1 },
            { 1, 9, 3, 3, 3, 3, 3, 3, 3, 3, 3, 7, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
    };
    private static final int[][] level_15x15 = {
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 7, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 7, 1 },
            { 1, 3, 1, 1, 3, 1, 1, 3, 1, 1, 3, 1, 1, 3, 1 },
            { 1, 3, 1, 1, 3, 1, 1, 3, 1, 1, 3, 1, 1, 3, 1 },
            { 1, 3, 3, 3, 3, 1, 1, 4, 1, 1, 3, 3, 3, 3, 1 },
            { 1, 3, 1, 1, 3, 3, 3, 3, 3, 3, 3, 1, 1, 3, 1 },
            { 1, 6, 3, 3, 3, 1, 1, 3, 1, 1, 3, 3, 3, 3, 1 },
            { 1, 3, 1, 1, 3, 1, 1, 3, 1, 1, 3, 1, 1, 3, 1 },
            { 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 6, 1 },
            { 1, 3, 1, 1, 3, 1, 1, 3, 1, 1, 3, 1, 1, 3, 1 },
            { 1, 3, 3, 3, 3, 1, 1, 3, 1, 1, 3, 3, 3, 3, 1 },
            { 1, 3, 1, 1, 3, 1, 1, 8, 1, 1, 3, 1, 1, 3, 1 },
            { 1, 3, 1, 1, 3, 1, 1, 2, 1, 1, 3, 1, 1, 3, 1 },
            { 1, 7, 3, 3, 3, 1, 5, 5, 5, 1, 3, 3, 3, 7, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
    };
    private static final int[][] level_11x11 = {
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 8, 3, 3, 1, 5, 1, 3, 3, 7, 1 },
            { 1, 3, 1, 3, 1, 2, 1, 3, 1, 3, 1 },
            { 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1 },
            { 1, 3, 1, 3, 3, 1, 3, 3, 1, 3, 1 },
            { 1, 3, 3, 3, 1, 1, 1, 3, 3, 4, 1 },
            { 1, 3, 1, 3, 3, 1, 3, 3, 1, 3, 1 },
            { 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1 },
            { 1, 3, 1, 3, 1, 2, 1, 3, 1, 3, 1 },
            { 1, 7, 3, 3, 1, 5, 1, 3, 3, 6, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
    };
    private static final int[][] level_14x14 = {
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1 },
            { 1, 3, 1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 3, 1 },
            { 1, 3, 7, 3, 1, 3, 3, 1, 3, 1, 3, 7, 3, 1 },
            { 1, 3, 1, 3, 1, 3, 3, 3, 3, 1, 3, 1, 3, 1 },
            { 1, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 4, 3, 1 },
            { 1, 3, 1, 3, 1, 3, 1, 1, 3, 1, 3, 1, 3, 1 },
            { 1, 3, 1, 3, 3, 3, 8, 7, 3, 3, 3, 1, 3, 1 },
            { 1, 3, 3, 3, 1, 3, 1, 3, 3, 1, 3, 3, 3, 1 },
            { 1, 3, 1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 3, 1 },
            { 1, 6, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 6, 1 },
            { 1, 1, 2, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1 },
            { 1, 5, 5, 5, 1, 3, 3, 3, 3, 1, 0, 0, 0, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
    };

    private static int[][] level;
    private static GameFrame gameFrame;
    private static MenuFrame menuFrame;
    private static LevelMenuFrame levelMenuFrame;
    private static HighScoresFrame highScoresFrame;
    private static EndFrame endFrame;

    private boolean isPaused = false;
    private boolean isRunning = true;

    public Game(Levels level) {
        menuFrame = new MenuFrame(this);
    }

    public void showLevelMenuFrame() {
        menuFrame.setVisible(false);

        if(levelMenuFrame == null)
            levelMenuFrame = new LevelMenuFrame(this);
        levelMenuFrame.setVisible(true);
    }

    public void showEndFrame() {
        isPaused = true;
        SwingUtilities.invokeLater( () ->{
            endFrame = new EndFrame(gameFrame);
        });
    }

    public void showMenuFrame() {
        if(endFrame != null)
            endFrame.setVisible(false);
        if(gameFrame != null)
            gameFrame.setVisible(false);
        if(highScoresFrame != null)
            highScoresFrame.setVisible(false);

        isRunning = false;
        menuFrame.setVisible(true);
    }

    public void showHighScoresFrame() {
        menuFrame.setVisible(false);
        if(highScoresFrame == null) {
            highScoresFrame = new HighScoresFrame(this);
        } else {
            highScoresFrame.loadHighScores();
        }
        highScoresFrame.setVisible(true);
    }

    public void showGameFrame(Levels chosenLevel) {
        levelMenuFrame.setVisible(false);

        switch (chosenLevel) {
            case Levels.LEVEL_10x10: Game.level = level_10x10; break;
            case Levels.LEVEL_13x13: Game.level = level_13x13; break;
            case Levels.LEVEL_15x15: Game.level = level_15x15; break;
            case Levels.LEVEL_11x11: Game.level = level_11x11; break;
            case Levels.LEVEL_14x14: Game.level = level_14x14; break;
        }

        isRunning = true;
        isPaused = false;
        gameFrame = new GameFrame(Game.level, this);
        gameFrame.setVisible(true);

        Runnable gameLoop = new Runnable() {
            public void run() {
                while (isRunning) {
                    if(!isPaused) {
                        KeyboardListener.checkKeys();
                        Ghost.moveGhosts();
                        gameFrame.getPanel().updateUI();
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        new Thread(gameLoop).start();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setIsPaused() {
        isPaused = !isPaused;
        gameFrame.setPause(isPaused);
    }

    public boolean isPaused() {
        return isPaused;
    }

    public static int[][] getLevel() {
        return level;
    }
    public static GameFrame getGameFrame() {
        return gameFrame;
    }
}
