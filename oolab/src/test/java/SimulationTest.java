import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class SimulationTest {
    List<MoveDirection> moves = OptionsParser.parse(new String[]{"f", "f", "f", "r", "b", "l", "l", "l", "l"});
    List<Vector2d> animalsSingle = List.of(new Vector2d(2,2));
    List<Vector2d> animalsMult = List.of(new Vector2d(0, 0), new Vector2d(2,2));

    @Test
    public void runSinglePos() {
        Simulation sim = new Simulation(animalsSingle, moves);

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
        Simulation sim = new Simulation(animalsSingle, moves);

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
        Simulation sim = new Simulation(animalsMult, moves);

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
        Simulation sim = new Simulation(animalsMult, moves);

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
