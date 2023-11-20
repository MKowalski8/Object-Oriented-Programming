package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {
    @Test
    void canRunTheCorrectSimulation(){
        //given
        String[] correctArgs = new String[]{"l", "b", "r", "f", "r", "r"};
        RectangularMap map = new RectangularMap(4,4);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4), new Vector2d(0,0));

        //when
        List<MoveDirection> parsCorrectArgs = OptionsParser.getDirection(correctArgs);
        Simulation simulation = new Simulation(positions, parsCorrectArgs, map); simulation.run();
        List<Animal> animalsList = simulation.getAnimals();
        Map<Vector2d,Animal> animalsMap = map.getMap();

        //then
        assertEquals(MapDirection.WEST, animalsList.get(0).getOrientation());
        assertEquals(new Vector2d(1,2), animalsList.get(0).getPosition());
        assertTrue(animalsMap.containsKey(new Vector2d(1,2)));

        assertEquals(MapDirection.EAST, animalsList.get(1).getOrientation());
        assertEquals(new Vector2d(3,3), animalsList.get(1).getPosition());
        assertTrue(animalsMap.containsKey(new Vector2d(3,3)));

        assertEquals(MapDirection.SOUTH, animalsList.get(2).getOrientation());
        assertEquals(new Vector2d(0,0), animalsList.get(2).getPosition());
        assertTrue(animalsMap.containsKey(new Vector2d(0,0)));

    }
    @Test
    void canRunTheSimulationWithIncorrectArgs(){
        //given
        String[] withIncorrectArgs = new String[]{"b", "w", "r", "l", "b", "m", "ma", "b"};
        RectangularMap map = new RectangularMap(4,4);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4), new Vector2d(0,0));

        //when
        List<MoveDirection> parsWithIncorrectArgs = OptionsParser.getDirection(withIncorrectArgs);
        Simulation simulation = new Simulation(positions, parsWithIncorrectArgs, map); simulation.run();
        List<Animal> animals = simulation.getAnimals();
        Map<Vector2d,Animal> animalsMap = map.getMap();

        //them
        assertEquals(MapDirection.NORTH, animals.get(0).getOrientation());
        assertEquals(new Vector2d(2,0), animals.get(0).getPosition());
        assertTrue(animalsMap.containsKey(new Vector2d(2,0)));

        assertEquals(MapDirection.EAST, animals.get(1).getOrientation());
        assertEquals(new Vector2d(2,4), animals.get(1).getPosition());
        assertTrue(animalsMap.containsKey(new Vector2d(2,4)));

        assertEquals(MapDirection.WEST, animals.get(2).getOrientation());
        assertEquals(new Vector2d(0,0), animals.get(2).getPosition());
        assertTrue(animalsMap.containsKey(new Vector2d(0,0)));
    }

    @Test
    void canRunTheSimulationWereAnimalsCanNotDoMoves(){
        RectangularMap map = new RectangularMap(4,4);
        String[] movesThatCanNotBeDone = new String[]{"f", "f", "r", "l", "r", "b", "b", "r", "l", "b", "f", "b", "b"};
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4), new Vector2d(0,0));

        //when
        List<MoveDirection> parsToManyArgs = OptionsParser.getDirection(movesThatCanNotBeDone);
        Simulation simulation = new Simulation(positions, parsToManyArgs, map); simulation.run();
        List<Animal> animals = simulation.getAnimals();
        Map<Vector2d,Animal> animalsMap = map.getMap();

        //then
        assertEquals(MapDirection.WEST, animals.get(0).getOrientation());
        assertEquals(new Vector2d(4,3), animals.get(0).getPosition());
        assertTrue(animalsMap.containsKey(new Vector2d(4,3)));

        assertEquals(MapDirection.SOUTH, animals.get(1).getOrientation());
        assertEquals(new Vector2d(3,3), animals.get(1).getPosition());
        assertTrue(animalsMap.containsKey(new Vector2d(3,3)));

        assertEquals(MapDirection.NORTH, animals.get(2).getOrientation());
        assertEquals(new Vector2d(0,0), animals.get(2).getPosition());
        assertTrue(animalsMap.containsKey(new Vector2d(0,0)));
    }

    @Test
    void canRunTheSimulationWereAnimalsTryToStepOnEachOther(){
        RectangularMap map = new RectangularMap(4,4);
        String[] movesThatCanNotBeDone = new String[]{"l", "l", "f", "b", "f"};
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,2), new Vector2d(2,1));

        //when
        List<MoveDirection> parsToManyArgs = OptionsParser.getDirection(movesThatCanNotBeDone);
        Simulation simulation = new Simulation(positions, parsToManyArgs, map); simulation.run();
        List<Animal> animals = simulation.getAnimals();
        Map<Vector2d,Animal> animalsMap = map.getMap();

        //then
        assertEquals(MapDirection.WEST, animals.get(0).getOrientation());
        assertEquals(new Vector2d(2,2), animals.get(0).getPosition());
        assertTrue(animalsMap.containsKey(new Vector2d(2,2)));

        assertEquals(MapDirection.WEST, animals.get(1).getOrientation());
        assertEquals(new Vector2d(3,2), animals.get(1).getPosition());
        assertTrue(animalsMap.containsKey(new Vector2d(3,2)));

        assertEquals(MapDirection.NORTH, animals.get(2).getOrientation());
        assertEquals(new Vector2d(2,1), animals.get(2).getPosition());
        assertTrue(animalsMap.containsKey(new Vector2d(2,1)));
    }

}