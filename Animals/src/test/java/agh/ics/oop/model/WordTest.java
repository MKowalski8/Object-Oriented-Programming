package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordTest {

    @Test
    void canChangeOrientation() {
        //given
        Word word1 = new Word("ala");
        Word word2 = new Word("ma");

        //when
        word1.changeOrientation(MoveDirection.RIGHT);
        word2.changeOrientation(MoveDirection.LEFT);

        //then
        assertEquals(MapDirection.SOUTH, word1.getOrientation());
        assertEquals(MapDirection.NORTH, word2.getOrientation());
    }
    @Test

    void checkIfCanMoveWord() {
        //given
        Word word1 = new Word("ala", MapDirection.EAST);
        Word word2 = new Word("ma", MapDirection.WEST);
        Word word3 = new Word("kota", MapDirection.NORTH);
        Word word4 = new Word("sowoniedźwiedzia", MapDirection.SOUTH);

        //then
        assertTrue(word1.canMoveWord());
        assertTrue(word2.canMoveWord());
        assertFalse(word3.canMoveWord());
        assertFalse(word4.canMoveWord());

    }
}