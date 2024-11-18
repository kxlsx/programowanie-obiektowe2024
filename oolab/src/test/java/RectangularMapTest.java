import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.exception.IncorrectPositionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {
    private final static int WIDTH = 4;
    private final static int HEIGHT = 4;

    @Test
    void placing_animals() throws IncorrectPositionException {
        RectangularMap map = new RectangularMap(WIDTH, HEIGHT);

        map.place(new Animal(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertThrows(IncorrectPositionException.class,
                () -> map.place(new Animal(new Vector2d(2, 2)))
        );

        assertFalse(map.canMoveTo(new Vector2d(4, 5)));
        assertThrows(IncorrectPositionException.class,
                () -> map.place(new Animal(new Vector2d(4, 5)))
        );

        assertFalse(map.canMoveTo(new Vector2d(-1, 4)));
        assertThrows(IncorrectPositionException.class,
                () -> map.place(new Animal(new Vector2d(-1, 4)))
        );
    }

    @Test
    void moving_animals() throws IncorrectPositionException {
        RectangularMap map = new RectangularMap(WIDTH, HEIGHT);

        Animal a1 = new Animal(new Vector2d(2, 2));
        Animal a2 = new Animal(new Vector2d(2, 3));

        map.place(a1);
        map.place(a2);

        map.move(a1, MoveDirection.FORWARD);
        map.move(a2, MoveDirection.BACKWARD);
        assertEquals(a1.getPos(), new Vector2d(2, 2));
        assertEquals(a2.getPos(), new Vector2d(2, 3));

        map.move(a1, MoveDirection.BACKWARD);
        map.move(a2, MoveDirection.BACKWARD);
        assertEquals(a1.getPos(), new Vector2d(2, 1));
        assertEquals(a2.getPos(), new Vector2d(2, 2));

        map.move(a1, MoveDirection.BACKWARD);
        map.move(a1, MoveDirection.BACKWARD);
        map.move(a2, MoveDirection.FORWARD);
        map.move(a2, MoveDirection.FORWARD);
        map.move(a2, MoveDirection.FORWARD);
        assertEquals(a1.getPos(), new Vector2d(2, 0));
        assertEquals(a2.getPos(), new Vector2d(2, 4));
    }
}
