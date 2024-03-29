package agh.ics.oop.model;

public class Grass implements WorldElement {

    private final static String GRASS_SYMBOL = "*";
    private final Vector2d position;

    public Grass(Vector2d position) {
        this.position = position;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String getImageURL() {
        return "/grass.png";
    }

    @Override
    public String getInfo() {
        return "Grass";
    }

    @Override
    public String toString() {
        return GRASS_SYMBOL;
    }
}


