package agh.ics.oop.model;

import java.util.*;

public class GrassField extends AbstractWorldMap {
    private final Map<Vector2d, Grass> grass = new HashMap<>();


    public GrassField(int grassNumber) {
        grassPlanting(grassNumber);
//        System.out.println(grass);
    }

    public String toString() {
        Map<Vector2d, Animal> animals = this.getAnimals();
        Vector2d maxVector = new Vector2d(Integer.MIN_VALUE,Integer.MIN_VALUE);
        Vector2d minVector = new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE);

        for(Vector2d vector : animals.keySet()){
            minVector = minVector.lowerLeft(vector);
            maxVector = maxVector.upperRight(vector);
        }

        for(Vector2d vector : grass.keySet()){
            minVector = minVector.lowerLeft(vector);
            maxVector = maxVector.upperRight(vector);
        }

        return super.draw(minVector, maxVector);
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

    public Map<Vector2d, Grass> getGrass() {
        return Collections.unmodifiableMap(grass);
    }
}
