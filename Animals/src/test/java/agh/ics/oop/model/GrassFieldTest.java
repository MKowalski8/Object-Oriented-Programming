package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void canVisualizeMap() {
        //given
        GrassField map = new GrassField(10);
       try{
           map.place(new Animal());
           map.place(new Animal(new Vector2d(4, 5)));
           map.place(new Animal(new Vector2d(-5, -5)));
       } catch (PositionAlreadyOccupiedException e){}


        //then
        System.out.println(map);
    }

    @Test
    void cacPlaceAnAnimalOnGrassField() {
        //given
        GrassField map = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(500, -20));
        Animal animal2 = new Animal(new Vector2d(-1, 0));
        Animal animal3 = new Animal(new Vector2d(-1, 0));

        //when
        Map<Vector2d, Grass> grass = map.getGrass();
        Vector2d grassPosition = grass.entrySet().iterator().next().getKey();
//        System.out.println(grassPosition);


        //then
        assertDoesNotThrow(() -> map.place(animal1));
        assertDoesNotThrow(() -> map.place(animal2));

        // Check if you can place an animal on grass
        assertDoesNotThrow(() -> map.place(new Animal(grassPosition)));

//        previous animal is here
        assertThrows(PositionAlreadyOccupiedException.class, () -> map.place(animal3));
    }

    @Test
    void canMoveAnAnimalOnMap() {
        //given
        GrassField map = new GrassField(10);

        Vector2d animal1StartVector = new Vector2d(2, 0);
        Vector2d animal2StartVector = new Vector2d(3, 0);
        Vector2d animal3StartVector = new Vector2d(1, 2);
        Vector2d animal4StartVector = new Vector2d(0, 0);

        Animal animal1 = new Animal(animal1StartVector);
        Animal animal2 = new Animal(animal2StartVector);
        Animal animal3 = new Animal(animal3StartVector);
        Animal animal4 = new Animal(animal4StartVector);

        try{
            map.place(animal1);
            map.place(animal2);
            map.place(animal3);
        } catch(PositionAlreadyOccupiedException e){}

//        I need this to check if previous positions
//        after moves were deleted
        Map<Vector2d, Animal> animals = map.getAnimals();

        //when
        map.move(animal2, MoveDirection.LEFT);
        map.move(animal2, MoveDirection.FORWARD);
        map.move(animal3, MoveDirection.RIGHT);
        map.move(animal3, MoveDirection.FORWARD);
        map.move(animal4, MoveDirection.FORWARD);
        System.out.println(map);

        //then
//        nothing changes, because another animal was there
        assertEquals(animal2, animals.get(animal2StartVector));
//        this should work
        assertEquals(animal3, animals.get(new Vector2d(2, 2)));
//        check if previous position was deleted
        assertFalse(animals.containsKey(animal3StartVector));
//        move of animal5 wasn't done, because it wasn't placed,
//        so check if it isn't on the old place or on the place where it would go
        assertFalse(animals.containsKey(animal4StartVector));
        assertFalse(animals.containsKey(new Vector2d(0, 1)));
    }

    @Test
    void canMoveAnAnimalOnGrass() {
        //given
        GrassField map = new GrassField(10);

        Map<Vector2d, Grass> grass = map.getGrass();
        Vector2d firstGrassPosition = grass.keySet().iterator().next();
        System.out.println(firstGrassPosition);

        Animal animal1 = new Animal(firstGrassPosition);
        Animal animal2 = new Animal(firstGrassPosition.add(new Vector2d(0, 1)));
        try{
            map.place(animal1);
            map.place(animal2);
        } catch(PositionAlreadyOccupiedException e){}

        //when
        map.move(animal1, MoveDirection.LEFT);
        map.move(animal1, MoveDirection.FORWARD);
        map.move(animal2, MoveDirection.BACKWARD);
        System.out.println(map);
        Map<Vector2d, Animal> animals = map.getAnimals();

        //then
//        Check if the first animal did his moves correct (he was placed on grass)
        assertEquals(animal1, animals.get(firstGrassPosition.add(new Vector2d(-1, 0))));
//        Check if the second animal move to grass position
        assertEquals(animal2, animals.get(firstGrassPosition));

    }

    @Test
    void checkWhichObjectIsAt() {
        //given
        GrassField map = new GrassField(1);
        Vector2d animal1Vector = new Vector2d(5, 5);
        Vector2d animal2Vector = new Vector2d(-10, 5);
        Animal animal1 = new Animal(animal1Vector);
        Animal animal2 = new Animal(animal2Vector);

        //when
        try{
            map.place(animal1);
            map.place(animal2);
        } catch(PositionAlreadyOccupiedException e){}

        Map<Vector2d, Grass> grass = map.getGrass();
        //then
        assertEquals(animal1, map.objectAt(animal1Vector));
        assertEquals(animal2, map.objectAt(animal2Vector));
        assertEquals(grass.values().iterator().next(),
                map.objectAt(grass.keySet().iterator().next()));
        assertNull(map.objectAt(new Vector2d(-100, -100)));

    }

    @Test
    void checkIfFieldIsOccupied() {
        //given
        GrassField map = new GrassField(1);
        Vector2d animalVector = new Vector2d(50, 50);

        //when
        try{
            map.place(new Animal(animalVector));
        } catch(PositionAlreadyOccupiedException e){}

        Map<Vector2d, Grass> grass = map.getGrass();


        //then
        assertFalse(map.isOccupied(new Vector2d(-100, -100)));
        assertTrue(map.isOccupied(animalVector));
        assertTrue(map.isOccupied(new Vector2d(50, 50)));
        assertTrue(map.isOccupied(grass.keySet().iterator().next()));
    }
}