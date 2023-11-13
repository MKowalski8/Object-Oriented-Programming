package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;
import agh.ics.oop.model.Animal;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {
    @Test
    void canRunTheSimulation(){
        //given
        String[] correctArgs = new String[]{"l", "b", "r", "f", "r", "r"};
        String[] withIncorrectArgs = new String[]{"b", "w", "r", "l", "b", "m", "ma", "b"};
        String[] movesThatCanNotBeDone = new String[]{"f", "f", "r", "l", "r", "b", "b", "r", "l", "b", "f", "b", "b"};
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4), new Vector2d(0,0));

        //when
        List<MoveDirection> parsCorrectArgs = OptionsParser.getDirection(correctArgs);
        List<MoveDirection> parsWithIncorrectArgs = OptionsParser.getDirection(withIncorrectArgs);
        List<MoveDirection> parsToManyArgs = OptionsParser.getDirection(movesThatCanNotBeDone);
        Simulation simulation1 = new Simulation(positions, parsCorrectArgs); simulation1.run();
        Simulation simulation2 = new Simulation(positions, parsWithIncorrectArgs); simulation2.run();
        Simulation simulation3 = new Simulation(positions, parsToManyArgs); simulation3.run();
        List<Animal> animals1 = simulation1.getAnimals();
        List<Animal> animals2 = simulation2.getAnimals();
        List<Animal> animals3 = simulation3.getAnimals();

        //then
//        simulation1
        assertEquals(MapDirection.WEST, animals1.get(0).getOrientation());
        assertEquals(1, animals1.get(0).getPosition().getX());
        assertEquals(2, animals1.get(0).getPosition().getY());
        assertEquals(MapDirection.EAST, animals1.get(1).getOrientation());
        assertEquals(3, animals1.get(1).getPosition().getX());
        assertEquals(3, animals1.get(1).getPosition().getY());
        assertEquals(MapDirection.SOUTH, animals1.get(2).getOrientation());
        assertEquals(0, animals1.get(2).getPosition().getX());
        assertEquals(0, animals1.get(2).getPosition().getY());
//        simulation2
        assertEquals(MapDirection.NORTH, animals2.get(0).getOrientation());
        assertEquals(2, animals2.get(0).getPosition().getX());
        assertEquals(0, animals2.get(0).getPosition().getY());
        assertEquals(MapDirection.EAST, animals2.get(1).getOrientation());
        assertEquals(2, animals2.get(1).getPosition().getX());
        assertEquals(4, animals2.get(1).getPosition().getY());
        assertEquals(MapDirection.WEST, animals2.get(2).getOrientation());
        assertEquals(0, animals2.get(2).getPosition().getX());
        assertEquals(0, animals2.get(2).getPosition().getY());
        //        simulation3
        assertEquals(MapDirection.WEST, animals3.get(0).getOrientation());
        assertEquals(4, animals3.get(0).getPosition().getX());
        assertEquals(3, animals3.get(0).getPosition().getY());
        assertEquals(MapDirection.SOUTH, animals3.get(1).getOrientation());
        assertEquals(3, animals3.get(1).getPosition().getX());
        assertEquals(3, animals3.get(1).getPosition().getY());
        assertEquals(MapDirection.NORTH, animals3.get(2).getOrientation());
        assertEquals(0, animals3.get(2).getPosition().getX());
        assertEquals(0, animals3.get(2).getPosition().getY());
    }

}