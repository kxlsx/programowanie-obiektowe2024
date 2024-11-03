package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {
    @Test
    public void next() {
        MapDirection d = MapDirection.NORTH;
        d = d.next();
        assertEquals(MapDirection.EAST, d);
        d = d.next();
        assertEquals(MapDirection.SOUTH, d);
        d = d.next();
        assertEquals(MapDirection.WEST, d);
        d = d.next();
        assertEquals(MapDirection.NORTH, d);
    }

    @Test
    public void previous() {
        MapDirection d = MapDirection.NORTH;
        d = d.previous();
        assertEquals(MapDirection.WEST, d);
        d = d.previous();
        assertEquals(MapDirection.SOUTH, d);
        d = d.previous();
        assertEquals(MapDirection.EAST, d);
        d = d.previous();
        assertEquals(MapDirection.NORTH, d);
    }
}
