package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;
import java.util.stream.Stream;

public abstract class AbstractWorldMap implements WorldMap {
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final List<MapChangeListener> observers = new ArrayList<>();

    private final UUID id = UUID.randomUUID();

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
        if (objectAt(animal.getPosition()).isPresent()) {
            if (objectAt(animal.getPosition()).get().equals(animal)) {
                Vector2d oldPosition = animal.getPosition();
                animal.move(direction, this);

                if (!animal.getPosition().equals(oldPosition)) {
                    animals.remove(oldPosition, animal);
                    animals.put(animal.getPosition(), animal);
                    mapChange("Animal has moved from " + oldPosition + " to " + animal.getPosition());
                } else {
                    mapChange("Animal has turned");
                }
            }
        }
    }

    public String toString() {
        MapVisualizer map = new MapVisualizer(this);
        Boundary bounds = getCurrentBounds();
        return map.draw(bounds.lowerLeft(), bounds.upperRight());
    }

    @Override
    public Optional<WorldElement> objectAt(Vector2d position) {
        return Optional.ofNullable(animals.get(position));
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

    private void mapChange(String message) {
        observers.forEach(observer -> observer.mapChanged(this, message));
    }

    @Override
    public UUID getID() {
        return id;
    }

    @Override
    public List<Animal> getOrderedAnimal() {
        return animals.values().stream()
                .sorted(Comparator.comparing(Animal::getPosition, Comparator.comparingInt(Vector2d::getX)
                        .thenComparingInt(Vector2d::getY)))
                .toList();
    }
}
