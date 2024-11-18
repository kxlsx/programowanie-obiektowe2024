package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.Map;
import java.util.HashMap;

public class RectangularMap extends AbstractWorldMap {
    public RectangularMap(int width, int height) {
        super();

        lowerLeft = new Vector2d(0,0);
        upperRight = new Vector2d(width, height);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) && position.upperRight(upperRight).equals(upperRight)
           && position.lowerLeft(lowerLeft).equals(lowerLeft);
    }
}
