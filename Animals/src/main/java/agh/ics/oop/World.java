package agh.ics.oop;

import agh.ics.oop.model.*;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class World {
    public static void main(String[] args) {
        try{
            List<MoveDirection> directions = OptionsParser.getDirection(args);
            List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
            RectangularMap map = new RectangularMap(5, 5);
            MapChangeListener observer = new ConsoleMapDisplay();
            map.addObserver(observer);
            Simulation simulation = new Simulation(positions, directions, map);
            simulation.run();
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}

