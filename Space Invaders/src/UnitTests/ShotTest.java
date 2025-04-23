package UnitTests;

import Game.Player;
import Game.Shot;
import Handlers.KeyHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

class ShotTest {

    private Shot shot;
    private KeyHandler keyHandler;
    private Player player;

    @BeforeEach
    void setUp() {
        shot = new Shot();
        keyHandler = new KeyHandler();
        player = new Player(100, 100, 16, 16); // Vytvoření hráče pro testování
    }


}

