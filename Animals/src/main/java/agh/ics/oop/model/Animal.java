package agh.ics.oop.model;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;

    public Animal() {
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }

    public Animal(int x, int y) {
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(x, y);
    }

    public String toString() {
        return String.format("%s %s", position.toString(), orientation.toString());
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> {
                Vector2d newPosition = position.add(orientation.toUnitVector());
                if (newPosition.precedes(new Vector2d(4, 4)) && newPosition.follows(new Vector2d(0, 0))) {
                    position = newPosition;
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = position.add(orientation.toUnitVector().opposite());
                if (newPosition.precedes(new Vector2d(4, 4)) && newPosition.follows(new Vector2d(0, 0))) {
                    position = newPosition;
                }
            }
        }
    }
}
