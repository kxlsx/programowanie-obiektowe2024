package agh.ics.oop.model;

import agh.ics.oop.World;
import agh.ics.oop.model.util.MapVisualizer;
import agh.ics.oop.model.util.RandomPositionGenerator;

import java.util.*;
import java.util.stream.IntStream;

public class GrassField extends AbstractWorldMap {
    HashMap<Vector2d, Grass> grassClumps;

    public GrassField(int grassClumpCount) {
        this(grassClumpCount, new Random());
    }

    public GrassField(int grassClumpCount, Random rnd){
        super();

        int upperLimit = (int)Math.sqrt(grassClumpCount * 10);

        grassClumps = new HashMap<>();

        RandomPositionGenerator generator = new RandomPositionGenerator(upperLimit, upperLimit, grassClumpCount, rnd);
        generator.forEach(v -> grassClumps.put(v, new Grass(v)));

        lowerLeft = new Vector2d(0,0);
        upperRight = new Vector2d(upperLimit, upperLimit);
    }

    @Override
    public boolean place(Animal animal){
        boolean res = super.place(animal);
        if(res) updateBounds(animal.getPos());
        return res;
    }

    @Override
    public void move(Animal animal, MoveDirection direction){
        super.move(animal, direction);
        updateBounds(animal.getPos());
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        WorldElement animal = super.objectAt(position);
        if(animal == null) {
            return grassClumps.get(position);
        } else {
            return animal;
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
       return animals.get(position) == null;
    }

    @Override
    public Collection<WorldElement> getElements() {
        Collection<WorldElement> col = super.getElements();
        col.addAll(grassClumps.values());
        return col;
    }

    void updateBounds(Vector2d position) {
        if(position.getX() > upperRight.getX()) {
            upperRight = new Vector2d(position.getX(), upperRight.getY());
        } else if (position.getX() < lowerLeft.getX()) {
            lowerLeft = new Vector2d(position.getX(), lowerLeft.getY());
        }
        if(position.getY() > upperRight.getY()) {
            upperRight = new Vector2d(upperRight.getX(), position.getY());
        } else if (position.getY() < lowerLeft.getY()) {
            lowerLeft = new Vector2d(lowerLeft.getX(), position.getY());
        }
    }
}
