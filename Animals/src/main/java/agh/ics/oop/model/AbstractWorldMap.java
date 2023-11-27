package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    private final Map<Vector2d, Animal> animals = new HashMap<>();

    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (isOccupied(animal.getPosition()) && objectAt(animal.getPosition()).equals(animal)) {
            Vector2d oldPosition = animal.getPosition();
            animal.move(direction, this);

            if (!animal.getPosition().equals(oldPosition)) {
                animals.remove(oldPosition, animal);
                animals.put(animal.getPosition(), animal);
            }
        }
    }

    protected String draw(Vector2d lowerLeft, Vector2d upperRight) {
        MapVisualizer map = new MapVisualizer(this);
        return map.draw(lowerLeft, upperRight);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
    }

    @Override
    public List<WorldElement> getElements() {
        return new ArrayList<>(animals.values());
    }

    public Map<Vector2d, Animal> getAnimals() {
        return Collections.unmodifiableMap(animals);
    }
}
