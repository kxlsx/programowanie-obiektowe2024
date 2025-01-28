package agh.ics.oop;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import agh.ics.oop.model.*;

public class World {
    public final static int WIDTH = 4;
    public final static int HEIGHT = 4;
    public final static int SIM_COUNT = 1000;

    public static void main(String[] args) {
        List<MoveDirection> directions;
        try {
             directions = OptionsParser.parse(Arrays.stream(args).toList());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return;
        }

        ConsoleMapDisplay observer = new ConsoleMapDisplay();
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));

        List<Simulation> simulations = new LinkedList<>();
        for(int i = 0; i < SIM_COUNT / 2; i++) {
            AbstractWorldMap map1 = new GrassField(10);
            map1.addObserver(observer);
            AbstractWorldMap map2 =  new RectangularMap(WIDTH, HEIGHT);
            map2.addObserver(observer);

            simulations.add(new Simulation(positions, directions, map1));
            simulations.add(new Simulation(positions, directions, map2));
        }

        SimulationEngine engine = new SimulationEngine(simulations);
        engine.runAsyncInThreadPool();
        try {
            engine.awaitSimulationsEnd();
        } catch (InterruptedException e) {}
        System.out.println("System zakończył działanie");
    }
}
