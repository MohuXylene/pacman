public class ScoreMultiplierCycle implements Runnable {
    private final GameFrame gameFrame;
    private final int multiplier;

    public ScoreMultiplierCycle(GameFrame gameFrame, int multiplier) {
        this.gameFrame = gameFrame;
        this.multiplier = multiplier;
    }
    @Override
    synchronized public void run() {
        int scoreMultiplier = gameFrame.getScoreMultiplier();
        if(scoreMultiplier == multiplier || scoreMultiplier == 6)
            return;
        gameFrame.setScoreMultiplier(gameFrame.getScoreMultiplier() * multiplier);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            gameFrame.setScoreMultiplier(gameFrame.getScoreMultiplier() / multiplier);
        }
    }
}
