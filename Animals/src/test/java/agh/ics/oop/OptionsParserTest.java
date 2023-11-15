package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void optionsCanBeParsed(){
        //given
        String[] correctArgs = new String[]{"f", "b", "l", "r"};
        String[] withIncorrectArgs = new String[]{"b", "w", "r", "l", "f", "m", "ma", "b"};
        List<MoveDirection> toCompareCorrect = List.of(new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT});
        List<MoveDirection> toCompareWithIncorrect = List.of(new MoveDirection[]{MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.BACKWARD});

        //when
        List<MoveDirection> correctArgsOutput = OptionsParser.getDirection(correctArgs);
        List<MoveDirection> withIncorrectArgsOutput = OptionsParser.getDirection(withIncorrectArgs);

        //then
        assertEquals(correctArgsOutput, toCompareCorrect);
        assertEquals(toCompareWithIncorrect, withIncorrectArgsOutput);
    }
}