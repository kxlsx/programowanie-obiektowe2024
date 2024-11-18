package agh.ics.oop.model;

import agh.ics.oop.World;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractWorldMap implements WorldMap {
    protected Vector2d lowerLeft;
    protected  Vector2d upperRight;

    protected Map<Vector2d, Animal> animals;

    public AbstractWorldMap() {
        animals = new HashMap<>();
    }

    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
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

    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(lowerLeft, upperRight);
    }

    @Override
    public Collection<WorldElement> getElements() {
        return animals.values().stream().map(a -> (WorldElement) a).collect(Collectors.toList());
    }
}
