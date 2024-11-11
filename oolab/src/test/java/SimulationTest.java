import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.World;
import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class SimulationTest {
    WorldMap<Animal, Vector2d> map = new RectangularMap(4, 4);

    List<MoveDirection> moves = OptionsParser.parse(new String[]{"f", "f", "f", "r", "b", "l", "l", "l", "l"});
    List<Animal> animalsSingle = List.of(new Animal(new Vector2d(2,2)));
    List<Animal> animalsMult = List.of(new Animal(new Vector2d(0, 0)), new Animal(new Vector2d(2,2)));

    @Test
    public void runSinglePos() {
        Simulation<Animal, Vector2d> sim = new Simulation<Animal, Vector2d>(animalsSingle, moves, map);

        assertTrue(sim.move_next().isAt(new Vector2d(2, 3)));
        assertTrue(sim.move_next().isAt(new Vector2d(2, 4)));
        assertTrue(sim.move_next().isAt(new Vector2d(2, 4)));

        assertTrue(sim.move_next().isAt(new Vector2d(2, 4)));

        assertTrue(sim.move_next().isAt(new Vector2d(1, 4)));

        assertTrue(sim.move_next().isAt(new Vector2d(1, 4)));
        assertTrue(sim.move_next().isAt(new Vector2d(1, 4)));
        assertTrue(sim.move_next().isAt(new Vector2d(1, 4)));
        assertTrue(sim.move_next().isAt(new Vector2d(1, 4)));
    }

    @Test
    public void runSingleDir() {
        Simulation<Animal, Vector2d> sim = new Simulation<Animal, Vector2d>(animalsSingle, moves, map);

        assertTrue(sim.move_next().isFacing(MapDirection.NORTH));
        assertTrue(sim.move_next().isFacing(MapDirection.NORTH));
        assertTrue(sim.move_next().isFacing(MapDirection.NORTH));

        assertTrue(sim.move_next().isFacing(MapDirection.EAST));

        assertTrue(sim.move_next().isFacing(MapDirection.EAST));

        assertTrue(sim.move_next().isFacing(MapDirection.NORTH));
        assertTrue(sim.move_next().isFacing(MapDirection.WEST));
        assertTrue(sim.move_next().isFacing(MapDirection.SOUTH));
        assertTrue(sim.move_next().isFacing(MapDirection.EAST));
    }

    @Test
    public void runMultiplePos() {
        Simulation<Animal, Vector2d> sim = new Simulation<Animal, Vector2d>(animalsMult, moves, map);

        assertTrue(sim.move_next().isAt(new Vector2d(0, 1)));
        assertTrue(sim.move_next().isAt(new Vector2d(2, 3)));
        assertTrue(sim.move_next().isAt(new Vector2d(0, 2)));

        assertTrue(sim.move_next().isAt(new Vector2d(2, 3)));

        assertTrue(sim.move_next().isAt(new Vector2d(0, 1)));

        assertTrue(sim.move_next().isAt(new Vector2d(2, 3)));
        assertTrue(sim.move_next().isAt(new Vector2d(0, 1)));
        assertTrue(sim.move_next().isAt(new Vector2d(2, 3)));
        assertTrue(sim.move_next().isAt(new Vector2d(0, 1)));
    }

    @Test
    public void runMultipleDir() {
        Simulation<Animal, Vector2d> sim = new Simulation<Animal, Vector2d>(animalsMult, moves, map);

        assertTrue(sim.move_next().isFacing(MapDirection.NORTH));
        assertTrue(sim.move_next().isFacing(MapDirection.NORTH));
        assertTrue(sim.move_next().isFacing(MapDirection.NORTH));

        assertTrue(sim.move_next().isFacing(MapDirection.EAST));

        assertTrue(sim.move_next().isFacing(MapDirection.NORTH));

        assertTrue(sim.move_next().isFacing(MapDirection.NORTH));
        assertTrue(sim.move_next().isFacing(MapDirection.WEST));
        assertTrue(sim.move_next().isFacing(MapDirection.WEST));
        assertTrue(sim.move_next().isFacing(MapDirection.SOUTH));
    }
}
