package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {

    private int ctr = 0;

    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        System.out.println(worldMap.getID());
        System.out.println(message);
        System.out.println(worldMap);
        System.out.println(ctr);
        ctr++;
    }
}
