package agh.ics.oop.model;

import java.util.*;

public class GrassField extends AbstractWorldMap {
    private final Map<Vector2d, Grass> grass = new HashMap<>();


    public GrassField(int grassNumber) {
        grassPlanting(grassNumber);
//        System.out.println(grass);
    }

    private void grassPlanting(int grassNumber) {
        int borderValue = (int) Math.ceil(Math.sqrt(grassNumber * 10));

        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(borderValue, borderValue, grassNumber);
        for (Vector2d grassPosition : randomPositionGenerator) {
            grass.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        WorldElement worldElement = super.objectAt(position);
        return worldElement != null ? worldElement : grass.get(position);
    }

    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> elements = super.getElements();
        elements.addAll(grass.values());
        return elements;
    }

    @Override
    public Boundary getCurrentBounds() {
        List<WorldElement> worldElements = getElements();
        Vector2d maxVector = new Vector2d(Integer.MIN_VALUE,Integer.MIN_VALUE);
        Vector2d minVector = new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE);

        for(WorldElement worldElement : worldElements){
            minVector = minVector.lowerLeft(worldElement.getPosition());
            maxVector = maxVector.upperRight(worldElement.getPosition());
        }

        return new Boundary(minVector, maxVector);
    }

    public Map<Vector2d, Grass> getGrass() {
        return Collections.unmodifiableMap(grass);
    }
}
