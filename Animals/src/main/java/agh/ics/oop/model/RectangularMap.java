package agh.ics.oop.model;


public class RectangularMap extends AbstractWorldMap {
    public static final Vector2d LOWER_EDGE = new Vector2d(0, 0);

    private final int width;
    private final int height;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }


    public String toString() {
        return super.draw(LOWER_EDGE, new Vector2d(width, height));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.precedes(new Vector2d(width, height)) && position.follows(LOWER_EDGE)) {
            return !isOccupied(position);
        }
        return false;
    }

}
