package Game;

import Handlers.KeyHandler;

import java.awt.*;

public class Shot {
    int x, y;
    boolean isShooting;

    public Shot() {
        isShooting = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getBounds(int width, int height) {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics2D g, int width, int height) {
        g.setColor(Color.CYAN);
        g.fillRect(x, y, width, height);
    }

    public void shooting(KeyHandler keyHandler, Player player, int shotSpeed) {
        if (keyHandler.shotPressed) {
            if (!isShooting) {
                isShooting = true;
                this.x = player.getX();
                this.y = player.getY();
            }
        }
        if (isShooting) {
            this.y -= shotSpeed;
            if (this.y < 0) {
                isShooting = false;
            }
        }
    }
}
