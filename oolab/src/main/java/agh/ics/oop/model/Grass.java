package agh.ics.oop.model;

public class Grass implements WorldElement{
    private final Vector2d pos;

    public Grass(Vector2d position) {
        this.pos = position;
    }

    public Vector2d getPos() {
        return this.pos;
    }

    public boolean isAt(Vector2d pos){
        return pos.equals(this.pos);
    }

    @Override
    public String toString() {
        return "*";
    }
}
