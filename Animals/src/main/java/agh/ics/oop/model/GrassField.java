package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public class GrassField extends AbstractWorldMap{

    private final int grassNumber;
    private Vector2d upperRight;
    private Vector2d lowerLeft;

    private final Map<Vector2d, Grass> grass = new HashMap<>();


    public GrassField(int grassNumber){
        this.grassNumber = grassNumber;

        int borderValue = (int)Math.ceil(Math.sqrt(grassNumber*10));
        this.upperRight = new Vector2d(borderValue, borderValue);
        this.lowerLeft =  new Vector2d(0, 0);
        grassPlanting();
//        System.out.println(grass);
    }

    public String toString() {
        return super.draw(lowerLeft, upperRight);
    }

    private void grassPlanting(){
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(upperRight.getX(), upperRight.getY(), grassNumber);
        for(Vector2d grassPosition : randomPositionGenerator) {
            grass.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public boolean place(Animal animal){
//        Nie wiem czy o to chodzi z dynamicznym wyznaczaniem skrajnych punktów, ale
//        uznałem to za rozwiązanie, które generuje jak najmniej powtarzających się operacji.
        if(super.place(animal)){
            extendMapBorders(animal.getPosition());
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction){
        super.move(animal, direction);
        extendMapBorders(animal.getPosition());
    }
    @Override
    public WorldElement objectAt(Vector2d position) {
        return super.objectAt(position) != null ? super.objectAt(position) : grass.get(position);
    }

    @Override
    public List<WorldElement> getElements(){
        List<WorldElement> elements = super.getElements();
        elements.addAll(grass.values());
        return elements;
    }

    private void extendMapBorders(Vector2d extender){
        upperRight = upperRight.upperRight(extender);
        lowerLeft = lowerLeft.lowerLeft(extender);
    }

    public Map<Vector2d, Grass> getGrass(){
        return Collections.unmodifiableMap(grass);
    }
}
