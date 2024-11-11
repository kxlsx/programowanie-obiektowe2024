package agh.ics.oop;

import java.util.List;

import agh.ics.oop.model.*;

public class World {
    public final static int WIDTH = 4;
    public final static int HEIGHT = 4;

    public static void main(String[] args) {
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Animal> positions = List.of(new Animal(new Vector2d(2,2)), new Animal(new Vector2d(3,4)));
        Simulation<Animal, Vector2d> simulation = new Simulation<Animal, Vector2d>(positions, directions, new RectangularMap(WIDTH, HEIGHT));
        simulation.run();

        List<String> texts = List.of("Ala", "ma", "sowoniedźwiedzia");
        Simulation<String, Integer> simText = new Simulation<>(texts, directions, new TextMap());
        simText.run();
    }
}
