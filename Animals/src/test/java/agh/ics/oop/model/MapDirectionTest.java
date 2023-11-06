package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapDirectionTest {

    @Test
    void canGiveNextMapDirection() {
        // given
//        MapDirection directionN = MapDirection.NORTH;
//        MapDirection directionS = MapDirection.SOUTH;
//        MapDirection directionW = MapDirection.WEST;
//        MapDirection directionE = MapDirection.EAST;

        //when
        MapDirection directionNToE = MapDirection.NORTH.next();
        MapDirection directionSToW = MapDirection.SOUTH.next();
        MapDirection directionWToN = MapDirection.WEST.next();
        MapDirection directionEToS = MapDirection.EAST.next();

        //then
        assertEquals(MapDirection.EAST, directionNToE);
        assertEquals(MapDirection.WEST, directionSToW);
        assertEquals(MapDirection.NORTH, directionWToN);
        assertEquals(MapDirection.SOUTH, directionEToS);
    }

    @Test
    void canGivePreviousMapDirection() {
        // given
//        MapDirection directionN = MapDirection.NORTH;
//        MapDirection directionS = MapDirection.SOUTH;
//        MapDirection directionW = MapDirection.WEST;
//        MapDirection directionE = MapDirection.EAST;

        //when
        MapDirection directionNToW = MapDirection.NORTH.previous();
        MapDirection directionSToE = MapDirection.SOUTH.previous();
        MapDirection directionWToS = MapDirection.WEST.previous();
        MapDirection directionEToN = MapDirection.EAST.previous();

        //then
        assertEquals(MapDirection.EAST, directionSToE);
        assertEquals(MapDirection.WEST, directionNToW);
        assertEquals(MapDirection.NORTH, directionEToN);
        assertEquals(MapDirection.SOUTH, directionWToS);
    }
}
