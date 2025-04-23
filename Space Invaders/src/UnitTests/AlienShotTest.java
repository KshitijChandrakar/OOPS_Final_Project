package UnitTests;

import static org.junit.Assert.*;

import Game.AlienShot;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AlienShotTest {

    @Test
    public void testMove() {
        AlienShot alienShot = new AlienShot(50, 50);
        int initialY = alienShot.y;

        alienShot.move();

        assertEquals(initialY + alienShot.speed, alienShot.y);
    }

    @Test
    public void testDraw() {
        AlienShot alienShot = new AlienShot(50, 50);
        Graphics2D g = (Graphics2D) new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream oldOut = System.out;
        System.setOut(printStream);

        alienShot.draw(g);

        String output = outputStream.toString().trim();
        assertTrue(output.contains("setColor(java.awt.Color[r=255,g=0,b=0])"));
        assertTrue(output.contains("fillRect(50, 50, 5, 10)"));
        System.setOut(oldOut);
    }

    @Test
    public void testGetBounds() {
        AlienShot alienShot = new AlienShot(50, 50);

        Rectangle bounds = alienShot.getBounds();

        assertEquals(new Rectangle(50, 50, 5, 10), bounds);
    }
}

