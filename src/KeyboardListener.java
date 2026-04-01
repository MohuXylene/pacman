import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

public class KeyboardListener implements KeyListener {
    private final static HashSet<Integer> keys = new HashSet<>();
    private static Pacman pacman;
    private static Game game;

    public KeyboardListener(Pacman pacman, Game game) {
        this.pacman = pacman;
        this.game = game;
    }

    public static void checkKeys() {
        for (int key : keys) {
            if (key == KeyEvent.VK_W) {
                pacman.move(0, -Pacman.speed);
            } else if (key == KeyEvent.VK_S) {
                pacman.move(0, Pacman.speed);
            } else if (key == KeyEvent.VK_D) {
                pacman.move(Pacman.speed, 0);
            } else if (key == KeyEvent.VK_A) {
                pacman.move(-Pacman.speed, 0);
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_ESCAPE) {
            game.setIsPaused();
        } else {
            keys.add(key);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        keys.remove(key);
    }
}
