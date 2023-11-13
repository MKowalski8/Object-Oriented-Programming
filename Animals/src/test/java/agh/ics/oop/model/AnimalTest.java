package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void animalCanCheckPosition(){
        //given
        Vector2d positionCorrect = new Vector2d(2,4);
        Vector2d positionIncorrect = new Vector2d(-1,-1);
        Vector2d positionCorrectX = new Vector2d(2,3);
        Vector2d positionCorrectY = new Vector2d(0,4);

        //when
        Animal animal = new Animal(2,4);

        //then
        assertTrue(animal.isAt(positionCorrect));
        assertFalse(animal.isAt(positionIncorrect));
        assertFalse(animal.isAt(positionCorrectX));
        assertFalse(animal.isAt(positionCorrectY));
    }
    @Test
    void animalCanMove(){
        //given
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(3,2);
        Animal animal3 = new Animal(1,0);
        Animal animal4 = new Animal(3,4);
        Animal animal5 = new Animal(1,2);
        Animal animal6 = new Animal(4,1);

        //when
        animal1.move(MoveDirection.RIGHT);
        animal1.move(MoveDirection.BACKWARD);
        animal2.move(MoveDirection.LEFT);
        animal2.move(MoveDirection.FORWARD);
        animal3.move(MoveDirection.BACKWARD);
        animal4.move(MoveDirection.FORWARD);
        animal5.move(MoveDirection.FORWARD);
        animal6.move(MoveDirection.BACKWARD);


        //then
        assertEquals(1, animal1.getPosition().getX());
        assertEquals(2, animal1.getPosition().getY());
        assertEquals(MapDirection.EAST, animal1.getOrientation());
        assertEquals(2, animal2.getPosition().getX());
        assertEquals(2, animal2.getPosition().getY());
        assertEquals(MapDirection.WEST, animal2.getOrientation());
        assertEquals(1, animal3.getPosition().getX());
        assertEquals(0, animal3.getPosition().getY());
        assertEquals(3, animal4.getPosition().getX());
        assertEquals(4, animal4.getPosition().getY());
        assertEquals(1, animal5.getPosition().getX());
        assertEquals(3, animal5.getPosition().getY());
        assertEquals(4, animal6.getPosition().getX());
        assertEquals(0, animal6.getPosition().getY());
    }
}