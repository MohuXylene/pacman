import javax.swing.*;
import java.awt.*;

public class TimerLabel extends JLabel implements Runnable {
    private Game game;
    private int counter;
    private int hours;
    private int minutes;
    private int seconds;

    public TimerLabel(Game game) {
        this.game = game;
        counter = 1;
        this.setText("00:00:00");
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setForeground(Color.WHITE);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    @Override
    public void run() {
        while(game.isRunning()) {
            if(!game.isPaused()) {
                String textSconds;
                String textMinutes;

                seconds = counter % 60;
                if(counter % 60 == 0)
                    minutes++;
                if(counter % 3600 == 0)
                    hours++;
                if(seconds < 10)
                    textSconds = "0" + seconds;
                else
                    textSconds = "" + seconds;
                if(minutes < 10)
                    textMinutes = "0" + minutes;
                else
                    textMinutes = "" + minutes;

                String time = "0" + hours + ":" + textMinutes + ":" + textSconds;
                this.setText(time);
                counter++;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
