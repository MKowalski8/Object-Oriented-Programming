package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class World {
    public static void main(String[] args) {
        try {
            List<MoveDirection> directions = OptionsParser.getDirection(args);
            List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
            List<Simulation> simulations = new ArrayList<>();
            MapChangeListener observer = new ConsoleMapDisplay();
//            for (int i = 0; i < 100; i++) {
            MapChangeListener fileObserver = new FileMapDisplay();
                simulations.addAll(getSimulations(positions, directions, observer, fileObserver));
//            }
            SimulationEngine simulationEngine = new SimulationEngine(simulations);
//            simulationEngine.runSync();
//            simulationEngine.runAsync();
            simulationEngine.runAsyncInThreadPool();
            simulationEngine.awaitSimulationEnd();
            System.out.println("System zakonczyl dzialanie");
        } catch (IllegalArgumentException | InterruptedException e) {
            System.err.println(e.getMessage());
        }

    }

    private static List<Simulation> getSimulations(List<Vector2d> positions, List<MoveDirection> directions, MapChangeListener observer, MapChangeListener fileObserver) {
        RectangularMap map1 = new RectangularMap(5, 5);
        GrassField map2 = new GrassField(10);
        map1.addObserver(observer);
        map2.addObserver(observer);
        map1.addObserver(fileObserver);
        map2.addObserver(fileObserver);
        Simulation simulation1 = new Simulation(positions, directions, map1);
        Simulation simulation2 = new Simulation(positions, directions, map2);
        return List.of(simulation1, simulation2);
    }
}

