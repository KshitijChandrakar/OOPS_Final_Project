package Game;

import Handlers.KeyHandler;

public class Player extends Sprite {
    private int lives;

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height, "src/Images/rocket.png");
        this.lives = 3;
    }

    public int getLives() {
        return lives;
    }

    public boolean isAlive() {
        return lives > 0;
    }

    public void loseLife() {
        if (lives > 0) {
            lives--;
        }
    }

    public void moveLeft(int speed) {
        x -= speed;
        if (x < 0) {
            x = 0;
        }
    }

    public void moveRight(int speed, int maxWidth, int tileSize) {
        x += speed;
        if (x <= 0) {
            x = 0;
        } else if (x >= maxWidth - tileSize) {
            x = maxWidth - tileSize;
        }
    }

    public void playerMoving(KeyHandler keyHandler, int panelWidth, int tileSize, int speed) {
        if (keyHandler.leftPressed) {
            moveLeft(speed);
        } else if (keyHandler.rightPressed) {
            moveRight(speed, panelWidth, tileSize);
        }
    }
}
