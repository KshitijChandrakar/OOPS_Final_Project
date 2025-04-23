package UnitTests;

import static org.junit.Assert.*;

import Game.Player;
import Handlers.KeyHandler;
import org.junit.Test;

public class PlayerTest {

    @Test
    public void testGetLives() {
        Player player = new Player(0, 0, 50, 50);
        assertEquals(3, player.getLives());
    }

    @Test
    public void testIsAlive() {
        Player player = new Player(0, 0, 50, 50);
        assertTrue(player.isAlive());
    }


    @Test
    public void testLoseLife() {
        Player player = new Player(0, 0, 50, 50);
        player.loseLife();
        assertEquals(2, player.getLives());
    }

    @Test
    public void testMoveLeft() {
        Player player = new Player(100, 100, 50, 50);
        player.moveLeft(10);
        assertEquals(90, player.getX());
    }

    @Test
    public void testMoveRight() {
        Player player = new Player(100, 100, 50, 50);
        player.moveRight(10, 200, 50);
        assertEquals(110, player.getX());
    }

    @Test
    public void testPlayerMoving_Left() {
        Player player = new Player(100, 100, 50, 50);
        KeyHandler keyHandler = new KeyHandler();
        keyHandler.leftPressed = true;
        player.playerMoving(keyHandler, 200, 50, 10);
        assertEquals(90, player.getX());
    }

    @Test
    public void testPlayerMoving_Right() {
        Player player = new Player(100, 100, 50, 50);
        KeyHandler keyHandler = new KeyHandler();
        keyHandler.rightPressed = true;
        player.playerMoving(keyHandler, 200, 50, 10);
        assertEquals(110, player.getX());
    }
}
