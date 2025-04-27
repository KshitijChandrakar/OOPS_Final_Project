package Game;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Alien extends Sprite {
    static int speed = 2;

    public Alien(int x, int y, int width, int height) {
        super(x, y, width, height, "src/Images/alien.png");
    }

    public static void move(CopyOnWriteArrayList<Alien> aliens, int panelWidth) {
        boolean reverseDirection = false;

        for (Alien alien : aliens) {
            alien.x += speed;
            if (alien.x <= 0 || alien.x >= panelWidth - alien.width) {
                reverseDirection = true;
            }
        }

        if (reverseDirection) {
            speed *= -1;
            for (Alien alien : aliens) {
                alien.y += 20;
            }
        }
    }

    public void update(Shot shot, int tileSize) {
        if (shot.isShooting && checkCollision(shot.getX(), shot.getY(), tileSize)) {
            destroyed = true;
        }
    }

    public AlienShot shoot() {
        return new AlienShot(x + width / 2, y + height);
    }
}
