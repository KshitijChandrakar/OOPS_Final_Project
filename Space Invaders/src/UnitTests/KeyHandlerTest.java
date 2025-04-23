package UnitTests;

import Handlers.KeyHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import static org.junit.jupiter.api.Assertions.*;

class KeyHandlerTest {

    private KeyHandler keyHandler;

    @BeforeEach
    void setUp() {
        keyHandler = new KeyHandler();
    }

    @Test
    void testKeyPressLeft() {
        KeyEvent keyEvent = new KeyEvent(new JFrame(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        keyHandler.keyPressed(keyEvent);
        assertTrue(keyHandler.leftPressed);
    }

    @Test
    void testKeyPressRight() {
        KeyEvent keyEvent = new KeyEvent(new JFrame(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');
        keyHandler.keyPressed(keyEvent);
        assertTrue(keyHandler.rightPressed);
    }

    @Test
    void testKeyPressShot() {
        KeyEvent keyEvent = new KeyEvent(new JFrame(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_SPACE, ' ');
        keyHandler.keyPressed(keyEvent);
        assertTrue(keyHandler.shotPressed);
    }

    @Test
    void testKeyPressMenu() {
        KeyEvent keyEvent = new KeyEvent(new JFrame(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ESCAPE, ' ');
        keyHandler.keyPressed(keyEvent);
        assertTrue(keyHandler.menuPressed);
    }

    @Test
    void testKeyReleaseLeft() {
        keyHandler.leftPressed = true;
        KeyEvent keyEvent = new KeyEvent(new JFrame(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        keyHandler.keyReleased(keyEvent);
        assertFalse(keyHandler.leftPressed);
    }

    @Test
    void testKeyReleaseRight() {
        keyHandler.rightPressed = true;
        KeyEvent keyEvent = new KeyEvent(new JFrame(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');
        keyHandler.keyReleased(keyEvent);
        assertFalse(keyHandler.rightPressed);
    }

    @Test
    void testKeyReleaseShot() {
        keyHandler.shotPressed = true;
        KeyEvent keyEvent = new KeyEvent(new JFrame(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_SPACE, ' ');
        keyHandler.keyReleased(keyEvent);
        assertFalse(keyHandler.shotPressed);
    }

    @Test
    void testKeyReleaseMenu() {
        keyHandler.menuPressed = true;
        KeyEvent keyEvent = new KeyEvent(new JFrame(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_ESCAPE, ' ');
        keyHandler.keyReleased(keyEvent);
        assertFalse(keyHandler.menuPressed);
    }
}

