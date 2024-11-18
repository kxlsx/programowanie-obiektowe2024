package agh.ics.oop.model;

import agh.ics.oop.World;
import agh.ics.oop.model.exception.IncorrectPositionException;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractWorldMap implements WorldMap {
    protected Boundary bounds;
    protected Map<Vector2d, Animal> animals;

    private final List<MapChangeListener> observers;

    public AbstractWorldMap() {
        animals = new HashMap<>();
        observers = new LinkedList<>();
    }

    public void addObserver(MapChangeListener observer) {
        observers.add(observer);
    }

    public void removeObserver(MapChangeListener observer) {
        observers.remove(observer);
    }

    protected void mapChanged(String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, message);
        }
    }

    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public void place(Animal animal) throws IncorrectPositionException {
        if(!canMoveTo(animal.getPos())) {
            throw new IncorrectPositionException(animal.getPos());
        }

        animals.put(animal.getPos(), animal);
        mapChanged("Animal placed at " + animal.getPos() + ".");
    }

    public void move(Animal animal, MoveDirection direction) {
        if(objectAt(animal.getPos()) != animal) {
            return;
        }

        Vector2d previousPosition = animal.getPos();
        animals.remove(animal.getPos());
        animal.move(direction, this);
        animals.put(animal.getPos(), animal);
        mapChanged("Animal at " + previousPosition + " moved to " + animal.getPos() + ".");
    }

    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(bounds.lowerLeft(), bounds.upperRight());
    }

    @Override
    public Collection<WorldElement> getElements() {
        return animals.values().stream().map(a -> (WorldElement) a).collect(Collectors.toList());
    }

    @Override
    public Boundary getCurrentBounds() {
        return bounds;
    }
}
