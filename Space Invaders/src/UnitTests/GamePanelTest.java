package UnitTests;

import static org.junit.Assert.*;

import Game.AlienShot;
import Game.GamePanel;
import Game.Player;
import Game.Shot;
import org.junit.Before;
import org.junit.Test;

public class GamePanelTest {

    private GamePanel gamePanel;

    @Before
    public void setUp() {
        gamePanel = new GamePanel();
    }



    @Test
    public void testCheckCollision() {
        Player player = new Player(50, 50, 50, 50);
        AlienShot alienShot = new AlienShot(50, 50);
        boolean collision = gamePanel.checkCollision(alienShot, player);
        assertTrue(collision);
    }
    @Test
    public void testUpdate_PlayerAlive() {
        // Arrange
        gamePanel.setPlayerAlive(true);

        // Act
        gamePanel.update();

        // Assert
        assertFalse(gamePanel.isGameOver());
    }

    @Test
    public void testUpdate_PlayerDead() {
        // Arrange
        gamePanel.setPlayerAlive(false);

        // Act
        gamePanel.update();

        // Assert
        assertTrue(gamePanel.isGameOver());
    }
}

