package Game;

import javax.swing.*;
import java.awt.*;

public class AlienShot {
    int x;
    public int y;
    public int speed = 5;
    boolean isShooting = true;

    public AlienShot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        y += speed;

    }

    public void draw(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 5, 10);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 5, 10); 
    }
}
