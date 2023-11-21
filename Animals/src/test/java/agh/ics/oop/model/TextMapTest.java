package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TextMapTest {

    @Test
    void canPlaceWord() {
        //given
        TextMap map = new TextMap();

        //then
        assertTrue(map.place("ala"));
        assertTrue(map.place("ma"));
    }

    @Test
    void caneMoveWord() {
        //given
        TextMap map = new TextMap();
        map.place("ala" );
        map.place("ma");
        map.place("sowoniedźwiedzia");

        //when
//        change orientation to NORTH
        map.move("ala", MoveDirection.LEFT);
//        nothing's happened
        map.move("ala", MoveDirection.BACKWARD);
//        try to go out from table
        map.move("sowoniedźwiedzia", MoveDirection.FORWARD);
//        change orientation to west
        map.move("ma", MoveDirection.LEFT); map.move("ma", MoveDirection.LEFT);
//        exchange "ma" with "sowoniedziwedz"
        map.move("ma", MoveDirection.BACKWARD);

        //then
        assertEquals("ala", map.objectAt(0));
        assertEquals("sowoniedźwiedzia", map.objectAt(1));
        assertEquals("ma", map.objectAt(2));
    }

    @Test
    void caneMoveMoreWords() {
        //given
        TextMap map = new TextMap();
        map.place("ala" );
        map.place("ma");
        map.place("sowoniedźwiedzia");
        map.place("kota");

        //whe
//        Try to get out from map
        map.move("ala", MoveDirection.BACKWARD);
//        Exchange with next word
        map.move("ala", MoveDirection.FORWARD);
//        Exchange with next word
        map.move("sowoniedźwiedzia", MoveDirection.FORWARD);
//        change orientation to WEST
        map.move("ma", MoveDirection.LEFT); map.move("ma", MoveDirection.LEFT);
//        exchange "ma" with "ala"
        map.move("ma", MoveDirection.BACKWARD);

        //then
        assertEquals("ala", map.objectAt(0));
        assertEquals("sowoniedźwiedzia", map.objectAt(3));
        assertEquals("ma", map.objectAt(1));
        assertEquals("kota", map.objectAt(2));
    }



    @Test
    void giveObjectAt() {
        //given
        TextMap map = new TextMap();
        map.place("ala");
        map.place("kota");

        //then
        assertEquals("ala", map.objectAt(0));
        assertEquals("kota", map.objectAt(1));
        assertNull(map.objectAt(2));
    }

    @Test
    void checkIfCanMoveTo() {
        //given
        TextMap map = new TextMap();
        map.place("ala");
        map.place("kota");

        //then
        assertTrue(map.canMoveTo(0));
        assertTrue(map.canMoveTo(1));
        assertFalse(map.canMoveTo(-1));
        assertFalse(map.canMoveTo(2));
    }
}