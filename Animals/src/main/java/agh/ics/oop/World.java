package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.Arrays;

public class World {
    public static void main(String[] args) {
        System.out.println("System wystartowal");
        run(OptionsParser.getDirection(args));
        System.out.println("System zakonczyl dzialanie");
    }

    private static void run(MoveDirection[] Directions) {
//        System.out.println("Zwierzak idzie do przodu");

//        Checking if the program gives the right output
        for (MoveDirection direction : Directions) {
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

