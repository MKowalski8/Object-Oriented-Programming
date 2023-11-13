package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Simulation {
    private List<Animal> animals = new ArrayList<>();
    private List<MoveDirection> directions;

    public Simulation(List<Vector2d> positions, List<MoveDirection> directions) {
        for (Vector2d position : positions) {
            animals.add(new Animal(position.getX(), position.getY()));
        }
        this.directions = directions;
    }

    public void run() {
        for (int j = 0; j < directions.size(); j++) {
            int i = j % animals.size();
            Animal animal = animals.get(i);
            animal.move(directions.get(j));

            System.out.printf(String.format("Zwierze %d : %s\n", i + 1, animal.toString()));
        }
    }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals);
    }
}
