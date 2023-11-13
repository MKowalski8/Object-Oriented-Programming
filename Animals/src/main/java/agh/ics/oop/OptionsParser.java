package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    public static List<MoveDirection> getDirection(String[] args) {

        List<MoveDirection> directions = new ArrayList<>();

        for (String arg : args) {
            MoveDirection value = switch (arg) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "r" -> MoveDirection.RIGHT;
                case "l" -> MoveDirection.LEFT;
                default -> null;
            };

            if (value != null) {
                directions.add(value);
            }
        }
//        return Directions
        return directions;
    }
}

