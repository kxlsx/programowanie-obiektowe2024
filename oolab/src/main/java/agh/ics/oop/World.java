package agh.ics.oop;

import java.util.List;

import agh.ics.oop.model.*;

public class World {
    public final static int WIDTH = 4;
    public final static int HEIGHT = 4;

    public static void main(String[] args) {
        List<MoveDirection> directions;
        try {
             directions = OptionsParser.parse(args);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return;
        }
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        GrassField map = new GrassField(10);
        map.addObserver(new ConsoleMapDisplay());
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
    }
}
