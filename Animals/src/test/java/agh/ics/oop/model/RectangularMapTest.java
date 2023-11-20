package agh.ics.oop.model;

import org.junit.jupiter.api.Test;


import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {


    @Test
    void checkIfFieldIsOccupied() {
        //given
        RectangularMap map = new RectangularMap(5, 5);
        Vector2d animalVector = new Vector2d(2, 3);

        //when
        map.place(new Animal(animalVector));

        //then
        assertFalse(map.isOccupied(new Vector2d(1, 1)));
        assertTrue(map.isOccupied(animalVector));

    }

    @Test
    void checkIfAnimalCanMoveTo() {
        //given
        RectangularMap map = new RectangularMap(5, 5);
        Vector2d animalVector = new Vector2d(2, 3);
        //when
        map.place(new Animal(animalVector));

        //then
//        out of map
        assertFalse(map.canMoveTo(new Vector2d(-1, 1)));
        assertFalse(map.canMoveTo(new Vector2d(5, 6)));
//        another animal is already there
        assertFalse(map.canMoveTo(animalVector));
//        it should work
        assertTrue(map.canMoveTo(new Vector2d(3, 4)));


    }

    @Test
    void canVisualizeMap() {
        //given
        RectangularMap map = new RectangularMap(5, 5);
        map.place(new Animal());
        map.place(new Animal(new Vector2d(4, 5)));

        //then
        System.out.println(map);
    }


    @Test
    void placeAnAnimalOnMap() {
        //given
        RectangularMap map = new RectangularMap(6, 5);
        Vector2d animalVector = new Vector2d(2, 3);

        //then
//        out of map
        assertFalse(map.place(new Animal(new Vector2d(5, -2))));
        assertFalse(map.place(new Animal(new Vector2d(-1, 0))));
//        it is okay
        assertTrue(map.place(new Animal(animalVector)));
//        previous animal is here
        assertFalse(map.place(new Animal(animalVector)));
    }

    @Test
    void move() {
        //given
        RectangularMap map = new RectangularMap(4, 4);
        Vector2d animal1StartVector = new Vector2d(2, 0);
        Vector2d animal2StartVector = new Vector2d(3, 4);
        Vector2d animal3StartVector = new Vector2d(3, 0);
        Vector2d animal4StartVector = new Vector2d(1, 2);
        Animal animal1 = new Animal(animal1StartVector);
        Animal animal2 = new Animal(animal2StartVector);
        Animal animal3 = new Animal(animal3StartVector);
        Animal animal4 = new Animal(animal4StartVector);
        map.place(animal1);
        map.place(animal2);
        map.place(animal3);
        map.place(animal4);
//        I need this to check if previous positions
//        after move were deleted
        Map<Vector2d, Animal> animals = map.getMap();

        //when
        map.move(animal1, MoveDirection.BACKWARD);
        map.move(animal2, MoveDirection.FORWARD);
        map.move(animal3, MoveDirection.LEFT);
        map.move(animal3, MoveDirection.FORWARD);
        map.move(animal4, MoveDirection.RIGHT);
        map.move(animal4, MoveDirection.FORWARD);
        System.out.println(map);

        //then
//        nothing changes, because it would be out of map
        assertEquals(animal1, animals.get(animal1StartVector));
        assertEquals(animal2, animals.get(animal2StartVector));
//        nothing changes, because another animal was there
        assertEquals(animal3, animals.get(animal3StartVector));
//        this should work
        assertEquals(animal4, animals.get(new Vector2d(2,2)));
//        check if previous position was deleted
        assertFalse(animals.containsKey(animal4StartVector));
    }


    @Test
    void checkWhichObjectIsAt() {
        //given
        RectangularMap map = new RectangularMap(6, 5);
        Vector2d animal1Vector = new Vector2d(2, 3);
        Vector2d animal2Vector = new Vector2d(6, 5);
        Animal animal1 = new Animal(animal1Vector);
        Animal animal2 = new Animal(animal2Vector);

        //when
        map.place(animal1);
        map.place(animal2);

        //then
        assertEquals(animal1, map.objectAt(animal1Vector));
        assertEquals(animal2, map.objectAt(animal2Vector));
        assertNull(map.objectAt(new Vector2d(1,1)));
    }

}
