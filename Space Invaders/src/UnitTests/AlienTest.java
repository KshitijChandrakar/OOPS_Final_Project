package UnitTests;

import static org.junit.Assert.*;

import Game.Alien;
import Game.AlienShot;
import Game.Shot;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

public class AlienTest {

    @Test
    public void testMove() {
        CopyOnWriteArrayList<Alien> aliens = new CopyOnWriteArrayList<>();
        aliens.add(new Alien(0, 0, 50, 50));
        Alien.move(aliens, 200);
        assertEquals(2, aliens.get(0).getX());
    }




}

