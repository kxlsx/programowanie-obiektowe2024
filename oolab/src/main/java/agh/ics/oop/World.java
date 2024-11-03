package agh.ics.oop;

import java.util.List;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

public class World {
    public static final Vector2d LOWER_LEFT = new Vector2d(0, 0);
    public static final Vector2d UPPER_RIGHT = new Vector2d(4, 4);

    public static void main(String[] args) {
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(positions, directions); simulation.run();
    }
}
