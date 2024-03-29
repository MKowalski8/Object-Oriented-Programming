package agh.ics.oop.model;

public class Animal implements WorldElement {
    public static final Vector2d UPPER_EDGE = new Vector2d(4, 4);
    private MapDirection orientation;
    private Vector2d position;
    public Animal() {
        this(new Vector2d(2,2));
    }

    public Animal(Vector2d position) {
        this.orientation = MapDirection.NORTH;
        this.position = position;
    }

    public String toString() {
        return switch(orientation){
            case NORTH -> "N";
            case SOUTH -> "S";
            case WEST -> "W";
            case EAST -> "E";
        };
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public MapDirection getOrientation() {
        return orientation;
    }

   @Override
    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String getImageURL() {
        return switch(orientation){
            case NORTH -> "/down.png";
            case SOUTH -> "/up.png";
            case WEST -> "/left.png";
            case EAST -> "/right.png";
        };
    }

    @Override
    public String getInfo() {
        return getPosition().toString();
    }

//    @Override
//    public WorldElementBox getElementBox() {
//        return elementBox;
//    }

    void move(MoveDirection direction, MoveValidator validator) {
        orientation = switch(direction){
            case RIGHT -> orientation.next();
            case LEFT -> orientation.previous();
            case FORWARD, BACKWARD -> orientation;
        };

        Vector2d newPosition = switch (direction) {
            case FORWARD -> position.add(orientation.toUnitVector());
            case BACKWARD -> position.add(orientation.toUnitVector().opposite());
            case RIGHT, LEFT -> position;
      };

        if (validator.canMoveTo(newPosition)) {
            position = newPosition;
        }
    }
}
