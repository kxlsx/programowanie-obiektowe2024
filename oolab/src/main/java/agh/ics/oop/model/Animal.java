package agh.ics.oop.model;

import agh.ics.oop.World;

public class Animal {
    private MapDirection facing = MapDirection.NORTH;
    private Vector2d pos = new Vector2d(2, 2);

    public Animal(Vector2d pos) {
        this.pos = pos;
    }

    public Animal() {}

    public Vector2d getPos() {
        return pos;
    }

    public MapDirection getFacing() {
        return facing;
    }

    @Override
    public String toString() {
        return switch(facing) {
            case NORTH -> "^";
            case WEST -> "<";
            case EAST -> ">";
            case SOUTH -> "v";
        };
    }

    public boolean isAt(Vector2d position) {
        return pos.equals(position);
    }

    public boolean isFacing(MapDirection dir) {
        return facing == dir;
    }

    public void move(MoveDirection direction, MoveValidator validator) {
        Vector2d by = new Vector2d(0, 0);
        switch (direction) {
            case FORWARD -> by = facing.toUnitVector();
            case BACKWARD -> by = facing.toUnitVector().opposite();
            case RIGHT -> facing = facing.next();
            case LEFT -> facing = facing.previous();
        }

        Vector2d newPos = pos.add(by);
        if(validator.canMoveTo(newPos)) {
            pos = newPos;
        }
    }
}
