package UnitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vendor.BasicBlocks;

import java.awt.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BasicBlocksTest {

    private BasicBlocks basicBlocks;

    @BeforeEach
    void setUp() {
        basicBlocks = new BasicBlocks();
    }

    @Test
    void testBasicBlocksInitialization() {
        ArrayList<Rectangle> wall = basicBlocks.wall;
        assertEquals(98, wall.size()); // Počet bloků ve zdi
    }

    @Test
    void testRow() {
        ArrayList<Rectangle> row = new ArrayList<>();
        basicBlocks.row(5, 0, 0);
        for (int i = 0; i < 5; i++) {
            row.add(new Rectangle(i * 3, 0, 3, 3));
        }
        assertEquals(row, basicBlocks.wall.subList(0, 5));
    }

}
