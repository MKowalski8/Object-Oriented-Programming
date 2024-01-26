package agh.ics.oop.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileMapDisplay implements MapChangeListener{
    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        File file = new File(String.format("map_%s.log", worldMap.getID()));
        try(FileWriter writer = new FileWriter(file, true)) {
            writer.write(message + '\n');
            writer.write(worldMap.toString() + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
