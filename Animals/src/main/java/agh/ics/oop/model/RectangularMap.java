package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class RectangularMap implements WorldMap<Animal,Vector2d> {
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    public static final Vector2d LOWER_EDGE = new Vector2d(0, 0);

    private final int width;
    private final int height;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }


    public String toString() {
        MapVisualizer map = new MapVisualizer(this);
        return map.draw(LOWER_EDGE,new Vector2d(width,height));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.precedes(new Vector2d(width, height)) && position.follows(LOWER_EDGE)) {
            return !isOccupied(position);
        }
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (animals.containsKey(animal.getPosition())) {
            Vector2d oldPosition = animal.getPosition();
            animal.move(direction, this);

            if (animal.getPosition() != oldPosition){
                animals.remove(oldPosition, animal);
                animals.put(animal.getPosition(), animal);
            }
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        if (isOccupied(position)) {
            return animals.get(position);
        } else {
            return null;
        }
    }
//    This getter is for tests
    public Map<Vector2d, Animal> getMap() {
        return Collections.unmodifiableMap(animals);
    }
}
