package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractWorldMapTest {

    @Test
    void getOrderedAnimal() {
        GrassField map = new GrassField(10);

        Vector2d animal1StartVector = new Vector2d(2, 0);
        Vector2d animal2StartVector = new Vector2d(3, 0);
        Vector2d animal3StartVector = new Vector2d(0, 2);
        Vector2d animal4StartVector = new Vector2d(0, 0);
        Vector2d animal5StartVector = new Vector2d(2, -1);

        Animal animal1 = new Animal(animal1StartVector);
        Animal animal2 = new Animal(animal2StartVector);
        Animal animal3 = new Animal(animal3StartVector);
        Animal animal4 = new Animal(animal4StartVector);
        Animal animal5 = new Animal(animal5StartVector);

        try{
            map.place(animal1);
            map.place(animal2);
            map.place(animal3);
            map.place(animal4);
            map.place(animal5);
        } catch(PositionAlreadyOccupiedException e){}


        //when
        List <Animal> animals = map.getOrderedAnimal();


        //then
//        animals.forEach((animal -> System.out.println(animal.getPosition())));
        assertEquals(animal4, animals.get(0));
        assertEquals(animal3, animals.get(1));
        assertEquals(animal5, animals.get(2));
        assertEquals(animal1, animals.get(3));
        assertEquals(animal2, animals.get(4));
        assertNotEquals(animal2, animals.get(0));
    }
}