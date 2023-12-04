package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final List<MapChangeListener> observers = new ArrayList<>();

    public void place(Animal animal) throws PositionAlreadyOccupiedException {
        if (canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            mapChange("Animal was placed");
        } else {
            throw new PositionAlreadyOccupiedException(animal.getPosition());
        }
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (isOccupied(animal.getPosition()) && objectAt(animal.getPosition()).equals(animal)) {
            Vector2d oldPosition = animal.getPosition();
            animal.move(direction, this);

            if (!animal.getPosition().equals(oldPosition)) {
                animals.remove(oldPosition, animal);
                animals.put(animal.getPosition(), animal);
                mapChange("Animal has moved from " + oldPosition + " to " + animal.getPosition());
            }
        }
    }

    public String toString() {
        MapVisualizer map = new MapVisualizer(this);
        Boundary bounds = getCurrentBounds();
        return map.draw(bounds.lowerLeft(), bounds.upperRight());
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

    public void addObserver(MapChangeListener listener) {
        observers.add(listener);
    }

    public void removeObserver(MapChangeListener listener) {
        observers.remove(listener);
    }

    public void mapChange(String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, message);
        }
    }
}
