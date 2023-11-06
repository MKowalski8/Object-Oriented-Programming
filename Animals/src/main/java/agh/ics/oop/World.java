package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.MapDirection;

import java.util.Arrays;

public class World {
    public static void main(String[] args) {
        System.out.println("System wystartowal");
        run(OptionsParser.getDirection(args));
        System.out.println("System zakonczyl dzialanie");


        Vector2d position1 = new Vector2d(1, 2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2, 1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        System.out.println();

        System.out.println(MapDirection.NORTH);
        System.out.println(MapDirection.SOUTH.next());
        System.out.println(MapDirection.WEST.previous());
        System.out.println(MapDirection.EAST.toUnitVector());
    }

    private static void run(MoveDirection[] directions) {
//        System.out.println("Zwierzak idzie do przodu");

//        Checking if the program gives the right output
        for (MoveDirection direction : directions) {
            String message = switch (direction) {
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tylu";
                case RIGHT -> "Zwierzak skreca w prawo";
                case LEFT -> "Zwierzak skreca w lewo";
            };
            System.out.println(message);
        }
    }
}

