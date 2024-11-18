import agh.ics.oop.World;
import agh.ics.oop.model.Animal;
import agh.ics.oop.model.GrassField;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldElement;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {
    int GRASS_COUNT = 10;
    int SEED = 71830;

    @Test
    void placing_animals() {
        GrassField map = new GrassField(GRASS_COUNT, new Random(SEED));

        Collection<WorldElement> grassClumps = map.getElements();
        assertEquals(grassClumps.size(), 10);
        for(WorldElement grass : grassClumps) {
            assertTrue(map.place(new Animal(grass.getPos())));
        }

        assertTrue(map.place(new Animal(new Vector2d(0, 2))));
        assertTrue(map.isOccupied(new Vector2d(0, 2)));
        assertFalse(map.canMoveTo(new Vector2d(0, 2)));
        assertFalse(map.place(new Animal(new Vector2d(0, 2))));

        assertTrue(map.place(new Animal(new Vector2d(1, 1))));
        assertTrue(map.isOccupied(new Vector2d(1, 1)));
        assertFalse(map.canMoveTo(new Vector2d(1, 1)));
        assertTrue(map.place(new Animal(new Vector2d(7, 1))));
        assertTrue(map.isOccupied(new Vector2d(7, 1)));
        assertFalse(map.canMoveTo(new Vector2d(7, 1)));

        assertEquals(map.getElements().size(), 23);
    }
}
