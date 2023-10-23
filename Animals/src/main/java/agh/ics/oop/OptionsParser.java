package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.Arrays;

public class OptionsParser {
    public static MoveDirection[] getDirection(String[] args) {
//        int n = 0;
//        for (String arg : args) {
//            if (arg.equals("f") || arg.equals("b") || arg.equals("l") || arg.equals("r")) {n++;}
//        }
        MoveDirection[] Directions = new MoveDirection[args.length];

        int i = 0;
        for (String arg : args) {
            MoveDirection value = switch (arg) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "r" -> MoveDirection.RIGHT;
                case "l" -> MoveDirection.LEFT;
                default -> null;
            };

            if (value != null) {
                Directions[i] = value;
                i++;
            }
        }
//        return Directions
        return Arrays.copyOfRange(Directions, 0, i);
    }
}

