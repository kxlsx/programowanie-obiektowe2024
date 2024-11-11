package agh.ics.oop;

import java.util.List;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.Vector2d;

public class World {
    public final static int WIDTH = 4;
    public final static int HEIGHT = 4;

    public static void main(String[] args) {
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(positions, directions, new RectangularMap(WIDTH, HEIGHT));
        simulation.run();
    }
}
