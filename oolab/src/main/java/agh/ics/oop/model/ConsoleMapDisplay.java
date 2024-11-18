package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {
    int events = 0;

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        System.out.println(message);
        System.out.println(worldMap);
        events += 1;
        System.out.println("Number of events so far: " + events);
    }
}
