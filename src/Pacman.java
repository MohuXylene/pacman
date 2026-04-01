import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static java.lang.Thread.sleep;

public class Pacman extends JLabel{
    public final static int speed = 1;
    public final static String img = "img/pacman.png";
    private final static String[] eatingSequence = {"img/pacman-eating0.png", "img/pacman-eating2.png"};
    private final static String[] dyingSequence = {"img/pacman-dying0.png", "img/pacman-dying1.png",
        "img/pacman-dying2.png", "img/pacman-dying3.png", "img/pacman-dying4.png", "img/pacman-dying5.png"};

    private final ImageIcon eating0Img;
    private final ImageIcon pacmanImg;
    private final ImageIcon eating2Img;

    private final ImageIcon[] dyingSequenceImgs;

    private final GameFrame gameframe;
    private int posX;
    private int posY;
    private final int startX;
    private final int startY;
    private boolean canMove = true;
    private boolean eatingAnimation = false;
    private boolean dyingAnimation = false;

    public Pacman(int x, int y, GameFrame gameframe) {
        this.gameframe = gameframe;
        posX = x;
        posY = y;
        startX = x;
        startY = y;

        ImageIcon img0 = new ImageIcon(eatingSequence[0]);
        eating0Img = new ImageIcon(img0.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

        ImageIcon img2 = new ImageIcon(eatingSequence[1]);
        eating2Img = new ImageIcon(img2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

        ImageIcon img1 = new ImageIcon(img);
        pacmanImg = new ImageIcon(img1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

        ImageIcon[] dyingImgs = new ImageIcon[dyingSequence.length];
        dyingSequenceImgs = new ImageIcon[dyingSequence.length];
        for(int i = 0; i < dyingSequence.length; i++) {
            dyingImgs[i] = new ImageIcon(dyingSequence[i]);
            dyingSequenceImgs[i] = new ImageIcon(dyingImgs[i].getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        }

        this.setIcon(pacmanImg);
        GridPanel.getPanel(x, y).placePacman(this);
    }

    public void kill() {
        canMove = false;
        gameframe.getLevel()[posY][posX] = 0;
        posX = startX;
        posY = startY;
        gameframe.getLevel()[posY][posX] = 4;
        dyingAnimation();
    }

    public void resetPacman() {
        canMove = false;
        gameframe.getLevel()[posY][posX] = 0;
        posX = startX;
        posY = startY;
        gameframe.getLevel()[posY][posX] = 4;
    }

    public void reSpawn() {
        Runnable reSpawnCycle = () -> {
            try {
                sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                GridPanel.getPanel(startX, startY).placePacman(this);
                this.setIcon(pacmanImg);
                canMove = true;
            }
        };
        new Thread(reSpawnCycle).start();
    }

    public void move(int x, int y) {
        if(!canMove)
            return;
        int nextX = posX + x;
        int nextY = posY + y;
        int tile = gameframe.getLevel()[nextY][nextX];

        if(tile == 0 || tile == 3 || tile == 4 || tile >= 6) {
            if(tile == 3) {
                gameframe.getLevel()[nextY][nextX] = 0;
                GridPanel.getPanel(nextX, nextY).clearTile();
                gameframe.addPoint(20);
                eatingAnimation();
            }
            if(tile >= 6) {
                gameframe.getLevel()[nextY][nextX] = 0;
                GridPanel.getPanel(nextX, nextY).clearTile();
                gameframe.usePowerUp(tile);
                eatingAnimation();
            }
            gameframe.getLevel()[posY][posX] = 0;
            posX += x;
            posY += y;
            gameframe.getLevel()[posY][posX] = 4;
            GridPanel.getPanel(nextX, nextY).placePacman(this);
        }
    }

    public void eatingAnimation() {
        Runnable animation = () -> {
            try {
                this.setIcon(eating2Img);
                Thread.sleep(100);
                this.setIcon(pacmanImg);
                Thread.sleep(100);
                this.setIcon(eating0Img);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                this.setIcon(pacmanImg);
            }
        };
        new Thread(animation).start();
    }

    public void dyingAnimation() {
        Runnable animation = () -> {
            try {
                for(int i = 0; i < dyingSequence.length - 1; i++) {
                    this.setIcon(dyingSequenceImgs[i]);
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                this.setIcon(dyingSequenceImgs[dyingSequence.length - 1]);
            }
        };
        new Thread(animation).start();
    }

}
