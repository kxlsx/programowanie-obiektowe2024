package agh.ics.oop.model;

import agh.ics.oop.World;

public class Animal {
    MapDirection facing = MapDirection.NORTH;
    Vector2d pos = new Vector2d(2, 2);

    public Animal(Vector2d pos) {
        this.pos = pos;
    }

    public Animal() {}

    @Override
    public String toString() {
        return "dir: " + facing + " x: " + pos.x() + ", y: " + pos.y();
    }

    public boolean isAt(Vector2d position) {
        return pos.equals(position);
    }

    public boolean isFacing(MapDirection dir) {
        return facing == dir;
    }

    public void move(MoveDirection direction) {
        Vector2d by = new Vector2d(0, 0);
        switch (direction) {
            case FORWARD -> by = facing.toUnitVector();
            case BACKWARD -> by = facing.toUnitVector().opposite();
            case RIGHT -> facing = facing.next();
            case LEFT -> facing = facing.previous();
        }

        Vector2d newPos = pos.add(by);
        if(newPos.upperRight(World.UPPER_RIGHT).equals(World.UPPER_RIGHT)
        && newPos.lowerLeft(World.LOWER_LEFT).equals(World.LOWER_LEFT)) {
            pos = newPos;
        }
    }
}
