package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Simulation {
    private final List<Animal> animals = new ArrayList<>();
    private final List<MoveDirection> directions;
    private final WorldMap map;

    public Simulation(List<Vector2d> positions, List<MoveDirection> directions, WorldMap map) {
        this.directions = directions;
        this.map = map;
        createAnimals(positions);
    }

    private void createAnimals(List<Vector2d> positions) {
        for (Vector2d position : positions) {
            Animal nextAnimal = new Animal(position);
            try{
                map.place(nextAnimal);
                animals.add(nextAnimal);
            } catch (PositionAlreadyOccupiedException e) {
                System.out.println(e);
            }
        }
    }

    public void run() {
        for (int j = 0; j < directions.size(); j++) {
            int i = j % animals.size();
            Animal animal = animals.get(i);
            map.move(animal, directions.get(j));
        }
    }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals);
    }
}
