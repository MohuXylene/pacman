import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Ghost extends JLabel {
    public final static int speed = 1;

    private static ArrayList<Ghost> ghosts = new ArrayList<>();

    private final static String[] imgs = {
            "img/ghost0.png", "img/ghost1.png", "img/ghost2.png"
    };
    private static int counter = 0;

    private final GameFrame gameFrame;
    private final Game game;
    private final int startX;
    private final int startY;
    private int[][] level;
    private int imgId;
    private int posX;
    private int posY;
    private int velX;
    private int velY;
    private boolean isFresh;
    private boolean canMove;
    private boolean isConfused;
    private boolean isFrozen;

    public Ghost(int x, int y, GameFrame gameFrame, Game game) {
        this.gameFrame = gameFrame;
        this.game = game;
        level = gameFrame.getLevel();
        startX = x;
        startY = y;
        posX = startX;
        posY = startY;
        velY = -speed;
        velX = 0;
        isFresh = true;
        canMove = true;
        isConfused = false;
        isFrozen = false;
        imgId = counter % 3;
        counter++;

        ghosts.add(this);
        this.changeImage(imgs[imgId]);
        GridPanel.getPanel(posX, posY).placeGhost(this);

        initPowerUpCycle();
    }

    public void changeImage(String path) {
        ImageIcon pacmanImg = new ImageIcon(path);
        this.setIcon(new ImageIcon(pacmanImg.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
    }

    public void move() {
        if(!canMove)
            return;
        if(isConfused) {
            moveConfused();
            return;
        }
        int nextX = posX + velX;
        int nextY = posY + velY;
        int prevX = posX;
        int prevY = posY;
        int tile = level[nextY][nextX];

        double random = Math.random();
        if(random < (double)1/5 || tile == 1 || tile == 2 && !isFresh ) {
            ArrayList<Directions> possibleDirections = new ArrayList<Directions>();
            if(velX != 0) {
                if(level[posY - 1][posX] != 1) {
                    possibleDirections.add(Directions.UP);
                }
                if(level[posY + 1][posX] != 1) {
                    possibleDirections.add(Directions.DOWN);
                }
            }
            if(velY != 0) {
                if(level[posY][posX - 1] != 1) {
                    possibleDirections.add(Directions.LEFT);
                }
                if(level[posY][posX + 1] != 1) {
                    possibleDirections.add(Directions.RIGHT);
                }
            }
            if(!possibleDirections.isEmpty()) { // change direction
                int size = possibleDirections.size();
                int index = (int)(Math.random() * size);
                Directions newDirection = possibleDirections.get(index);

                changeDirection(newDirection);
            } else if(tile == 1) { // go back
                velX = -velX;
                velY = -velY;
            }
        }
        posX += velX;
        posY += velY;
        GridPanel.getPanel(posX, posY).placeGhost(this);

        if(tile == 2) {
            isFresh = false;
        }
        if(level[posY][posX] == 4 || level[prevY][prevX] == 4) {
            gameFrame.removeLife();
        }
    }

    public void moveConfused() {
        int nextX = posX + velX;
        int nextY = posY + velY;
        int prevX = posX;
        int prevY = posY;
        int tile = level[nextY][nextX];

        double random = Math.random();
        if(random < (double)1/5 || tile == 1 || tile == 2 && !isFresh) {
            ArrayList<Directions> possibleDirections = new ArrayList<Directions>();
            if (level[posY - 1][posX] != 1) {
                possibleDirections.add(Directions.UP);
            }
            if (level[posY + 1][posX] != 1) {
                possibleDirections.add(Directions.DOWN);
            }
            if (level[posY][posX - 1] != 1) {
                possibleDirections.add(Directions.LEFT);
            }
            if (level[posY][posX + 1] != 1) {
                possibleDirections.add(Directions.RIGHT);
            }
            int size = possibleDirections.size();
            int index = (int) (Math.random() * size);
            Directions newDirection = possibleDirections.get(index);

            changeDirection(newDirection);
        }
        posX += velX;
        posY += velY;
        GridPanel.getPanel(posX, posY).placeGhost(this);
        if(level[posY][posX] == 4 || level[prevY][prevX] == 4) {
            gameFrame.addPoint(400);
            this.reSpawn();
        }
    }

    private void initPowerUpCycle() {
        Runnable powerUpCycle = () -> {
            while(game.isRunning() && !game.isPaused()) {
                double random = Math.random();
                if (canMove && !isConfused && level[posY][posX] == 0 && random < (double) 1 / 5) {
                    int type = (int) (Math.random() * 6) + 6;
                    level[posY][posX] = type;
                    GridPanel.getPanel(posX, posY).placePowerUp(type);
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        new Thread(powerUpCycle).start();
    }

    public void reSpawn() {
        this.changeImage(imgs[imgId]);
        posX = startX;
        posY = startY;
        GridPanel.getPanel(posX, posY).placeGhost(this);
        canMove = false;
        Runnable reSpawnCycle = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                canMove = true;
                isFresh = true;
                isConfused = false;
            }
        };
        new Thread(reSpawnCycle).start();
    }

    public void changeDirection(Directions direction) {
        velX = 0;
        velY = 0;
        switch (direction) {
            case LEFT: velX = -speed; break;
            case RIGHT: velX = speed; break;
            case UP: velY = -speed; break;
            case DOWN: velY = speed; break;
        }
    }


    public static void removeGhosts() {
        ghosts = new ArrayList<Ghost>();
        counter = 0;
    }

    public static void respawnGhosts() {
        for(Ghost ghost : ghosts) {
            ghost.level = ghost.gameFrame.getLevel();
            ghost.reSpawn();
        }
    }

    public static void killRandomGhost() {
        int index = (int)(Math.random() * ghosts.size());
        Ghost ghost = ghosts.get(index);
        if(!ghost.isConfused && !ghost.isFrozen); {
            ghosts.get(index).reSpawn();
        }
    }

    public static void confuseGhosts() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(Ghost ghost : ghosts) {
                    if(!ghost.isConfused && !ghost.isFrozen) {
                        ghost.isConfused = true;
                        ghost.changeImage("img/ghost_confused.png");
                    }
                }
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    for(Ghost ghost : ghosts) {
                        ghost.isConfused = false;
                        if(!ghost.isFrozen) {
                            ghost.changeImage(imgs[ghost.imgId]);
                        }
                    }
                }
            }
        }).start();
    }

    public static void freezeGhosts() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(Ghost ghost : ghosts) {
                    if(!ghost.isConfused && !ghost.isFrozen) {
                        ghost.canMove = false;
                        ghost.isFrozen = true;
                        ghost.changeImage("img/ghost_frozen.png");
                    }
                }
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    for(Ghost ghost : ghosts) {
                        ghost.canMove = true;
                        ghost.isFrozen = false;
                        if(!ghost.isConfused) {
                            ghost.changeImage(imgs[ghost.imgId]);
                        }
                    }
                }

            }
        }).start();
    }

    public static void moveGhosts() {
        for(Ghost ghost : ghosts) {
            ghost.move();
        }
    }

}
