package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CopyOnWriteArrayList;
public class Sprite {
    protected int x, y, width, height;
    public Image image;
    protected boolean destroyed;

    public Sprite(int x, int y, int width, int height, String imagePath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = new ImageIcon(imagePath).getImage();
        this.destroyed = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void draw(Graphics2D g) {
        if (!destroyed) {
            g.drawImage(image, x, y, width, height, null);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public boolean checkCollision(int shotX, int shotY, int tileSize) {
        Rectangle spriteRect = new Rectangle(x, y, width, height);
        Rectangle shotRect = new Rectangle(shotX, shotY, tileSize, tileSize);
        return spriteRect.intersects(shotRect);
    }
}
