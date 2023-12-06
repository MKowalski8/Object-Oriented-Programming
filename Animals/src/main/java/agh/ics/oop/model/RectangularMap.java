package agh.ics.oop.model;


import agh.ics.oop.model.util.MapVisualizer;

public class RectangularMap extends AbstractWorldMap {
    public static final Vector2d LOWER_EDGE = new Vector2d(0, 0);

    private final int width;
    private final int height;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.precedes(new Vector2d(width, height)) && position.follows(LOWER_EDGE)) {
            return super.canMoveTo(position);
        }
        return false;
    }

    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(LOWER_EDGE, new Vector2d(width,height));
    }
}
