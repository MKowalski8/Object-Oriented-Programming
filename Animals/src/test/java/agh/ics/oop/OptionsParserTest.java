package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void optionsCanBeParsedCorrect(){
        //given
        String[] correctArgs = new String[]{"f", "b", "l", "r"};
        List<MoveDirection> toCompareCorrect = List.of(new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT});

        //when
        List<MoveDirection> correctArgsOutput = OptionsParser.getDirection(correctArgs);

        //then
        assertEquals(correctArgsOutput, toCompareCorrect);
    }

    @Test
    void optionParserCanThrowException(){
        //given
        String[] withIncorrectArgs = new String[]{"b", "w", "r", "l", "f", "m", "ma", "b"};

        //when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> OptionsParser.getDirection(withIncorrectArgs));

        //then
        assertEquals("w argument is invalid", exception.getMessage());
    }
}