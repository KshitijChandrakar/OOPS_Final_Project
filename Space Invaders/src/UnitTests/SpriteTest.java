package UnitTests;

import Game.Sprite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class SpriteTest {

    private Sprite sprite;

    @BeforeEach
    void setUp() {
        // Před každým testem vytvoříme novou instanci třídy Sprite
        int x = 100;
        int y = 100;
        int width = 32;
        int height = 32;
        String imagePath = "src/Images/sprite.png";
        sprite = new Sprite(x, y, width, height, imagePath);
    }

    @Test
    void testSpriteInitialization() {
        // Test inicializace správné pozice, rozměru a stavu destrukce
        assertEquals(100, sprite.getX());
        assertEquals(100, sprite.getY());
        assertEquals(32, sprite.getWidth());
        assertEquals(32, sprite.getHeight());
        assertFalse(sprite.isDestroyed());
    }

    @Test
    void testDraw() {
        // Test, zda metoda draw správně vykreslí sprite na plátno
        Graphics2D g = (Graphics2D) new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB).getGraphics();
        sprite.draw(g);
        assertNotNull(sprite.image);
    }

    @Test
    void testBounds() {
        // Test, zda metoda getBounds správně vrací ohraničení sprite
        Rectangle bounds = sprite.getBounds();
        assertEquals(100, bounds.x);
        assertEquals(100, bounds.y);
        assertEquals(32, bounds.width);
        assertEquals(32, bounds.height);
    }

    @Test
    void testCheckCollision() {
        // Test, zda metoda checkCollision správně detekuje kolizi s danými souřadnicemi střely
        assertTrue(sprite.checkCollision(100, 100, 16)); // Střela se nachází přímo na sprite
        assertFalse(sprite.checkCollision(50, 50, 16)); // Střela se nachází mimo sprite
    }

    @Test
    void testSetDestroyed() {
        // Test, zda metoda setDestroyed správně nastaví stav destrukce sprite
        assertFalse(sprite.isDestroyed()); // Před voláním metody
        sprite.setDestroyed(true);
        assertTrue(sprite.isDestroyed()); // Po volání metody
    }
}

