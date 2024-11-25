package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.Map;
import java.util.HashMap;

public class RectangularMap extends AbstractWorldMap {
    public RectangularMap(int width, int height) {
        super();

        bounds = new Boundary(
                new Vector2d(0,0),
                new Vector2d(width, height)
        );
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position)
                && position.upperRight(bounds.upperRight()).equals(bounds.upperRight())
                && position.lowerLeft(bounds.lowerLeft()).equals(bounds.lowerLeft());
    }
}
