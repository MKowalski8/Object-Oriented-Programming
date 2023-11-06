package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void vectorCanBeAdded() {
        // given
        Vector2d vector1 = new Vector2d(2, 4);
        Vector2d vector2 = new Vector2d(1, 4);

        // when
        Vector2d vector3 = vector1.add(vector2);

        // then
        assertEquals(3, vector3.getX());
        assertEquals(8, vector3.getY());
    }

    @Test
    void vectorCanBeSubtracted() {
        // given
        Vector2d vector1 = new Vector2d(2, 4);
        Vector2d vector2 = new Vector2d(1, 4);

        // when
        Vector2d vector3 = vector1.subtract(vector2);

        // then
        assertEquals(1, vector3.getX());
        assertEquals(0, vector3.getY());
    }

    @Test
    void vectorCanBeCompared() {
        // given
        Vector2d vector1 = new Vector2d(2, 4);
        Vector2d vector2 = new Vector2d(1, 4);
        Vector2d vector3 = new Vector2d(2, 4);

        // when
        boolean isEqualF = vector1.equals(vector2);
        boolean isEqualT = vector1.equals(vector3);

        // then
        assertFalse(isEqualF);
        assertTrue(isEqualT);
    }

    @Test
    void vectorCanBeWrittenAsString() {
        // given
        Vector2d vector1 = new Vector2d(2, 4);
        // when
        String vectorAsString = vector1.toString();

        // then
        assertEquals("(2,4)", vectorAsString);
    }

    @Test
    void vectorCanBePreceded() {
        // given
        Vector2d vector1 = new Vector2d(3, 4);
        Vector2d vector2 = new Vector2d(1, 4);
        Vector2d vector3 = new Vector2d(3, 5);

        // when
        boolean ifPrecedesF = vector1.precedes(vector2);
        boolean ifPrecedesT = vector1.precedes(vector3);

        // then
        assertFalse(ifPrecedesF);
        assertTrue(ifPrecedesT);
    }

    @Test
    void vectorCanBeFollowed() {
        // given
        Vector2d vector1 = new Vector2d(3, 4);
        Vector2d vector2 = new Vector2d(5, 4);
        Vector2d vector3 = new Vector2d(3, 4);

        // when
        boolean ifFollowsF = vector1.follows(vector2);
        boolean ifFollowsT = vector1.follows(vector3);

        // then
        assertFalse(ifFollowsF);
        assertTrue(ifFollowsT);
    }

    @Test
    void vectorCanSelectUpperRight() {
        // given
        Vector2d vector1 = new Vector2d(1, 5);
        Vector2d vector2 = new Vector2d(3, 8);
        Vector2d vector3 = new Vector2d(2, 3);
        Vector2d vector4 = new Vector2d(-1, -1);

        // when
        Vector2d vectorU1 = vector1.upperRight(vector2);
        Vector2d vectorU2 = vector1.upperRight(vector3);
        Vector2d vectorU3 = vector1.upperRight(vector4);

        // then
        assertEquals(3, vectorU1.getX());
        assertEquals(8, vectorU1.getY());
        assertEquals(2, vectorU2.getX());
        assertEquals(5, vectorU2.getY());
        assertEquals(1, vectorU3.getX());
        assertEquals(5, vectorU3.getY());
    }

    @Test
    void vectorCanSelectLowerLeft() {
        // given
        Vector2d vector1 = new Vector2d(1, 5);
        Vector2d vector2 = new Vector2d(3, 8);
        Vector2d vector3 = new Vector2d(2, 3);
        Vector2d vector4 = new Vector2d(-1, -1);

        // when
        Vector2d vectorL1 = vector1.lowerLeft(vector2);
        Vector2d vectorL2 = vector1.lowerLeft(vector3);
        Vector2d vectorL3 = vector1.lowerLeft(vector4);

        // then
        assertEquals(1, vectorL1.getX());
        assertEquals(5, vectorL1.getY());
        assertEquals(1, vectorL2.getX());
        assertEquals(3, vectorL2.getY());
        assertEquals(-1, vectorL3.getX());
        assertEquals(-1, vectorL3.getY());
    }

    @Test
    void vectorHasOpposite() {
        // given
        Vector2d vector1 = new Vector2d(3, 9);
        Vector2d vector2 = new Vector2d(-11, -23);
        Vector2d vector3 = new Vector2d(-7, 5);

        //when
        Vector2d vectorOp1 = vector1.opposite();
        Vector2d vectorOp2 = vector2.opposite();
        Vector2d vectorOp3 = vector3.opposite();

        //then
        assertEquals(-3, vectorOp1.getX());
        assertEquals(-9, vectorOp1.getY());
        assertEquals(11, vectorOp2.getX());
        assertEquals(23, vectorOp2.getY());
        assertEquals(7, vectorOp3.getX());
        assertEquals(-5, vectorOp3.getY());
    }
}