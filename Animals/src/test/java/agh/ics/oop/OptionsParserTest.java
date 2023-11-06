package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void optionsCanBeParsed(){
        //given
        String[] correctArgs = new String[]{"f", "b", "l", "r"};
        String[] withIncorrectArgs = new String[]{"b", "w", "r", "l", "f", "m", "ma", "b"};
        MoveDirection[] toCompareCorrect = new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT};
        MoveDirection[] toCompareWithIncorrect = new MoveDirection[]{MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.BACKWARD};

        //when
        MoveDirection[] correctArgsOutput = OptionsParser.getDirection(correctArgs);
        MoveDirection[] withIncorrectArgsOutput = OptionsParser.getDirection(withIncorrectArgs);

        //then
        assertArrayEquals(correctArgsOutput, toCompareCorrect);
        assertArrayEquals(toCompareWithIncorrect, withIncorrectArgsOutput);
    }
}