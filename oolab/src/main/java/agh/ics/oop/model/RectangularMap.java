package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.Map;
import java.util.HashMap;

public class RectangularMap implements WorldMap {
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;

    private final Map<Vector2d, Animal> animals;

    public RectangularMap(int width, int height) {
        lowerLeft = new Vector2d(0,0);
        upperRight = new Vector2d(width, height);
        animals = new HashMap<>();
    }

    public boolean place(Animal animal) {
        if(!canMoveTo(animal.getPos())) {
            return false;
        }

        animals.put(animal.getPos(), animal);
        return true;
    }

    public void move(Animal animal, MoveDirection direction) {
        if(objectAt(animal.getPos()) != animal) {
            return;
        }

        animals.remove(animal.getPos());
        animal.move(direction, this);
        animals.put(animal.getPos(), animal);
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }
    
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    public boolean canMoveTo(Vector2d position) {
        if(isOccupied(position)) {
            return false;
        }
        return position.upperRight(upperRight).equals(upperRight)
           && position.lowerLeft(lowerLeft).equals(lowerLeft);
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(lowerLeft, upperRight);
    }
}
