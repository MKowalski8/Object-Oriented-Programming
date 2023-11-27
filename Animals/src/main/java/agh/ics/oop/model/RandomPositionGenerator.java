package agh.ics.oop.model;

import java.util.*;


public class RandomPositionGenerator implements Iterable<Vector2d> {
    private final int maxWidth;
    private final int maxHeight;
    private final int grassCount;
    private final List<Vector2d> possiblePositions = new LinkedList<>();
    private final List<Vector2d> grassPositions = new ArrayList<>();

    public RandomPositionGenerator(int maxWidth, int maxHeight, int grassCount) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.grassCount = grassCount;

        addPossiblePositions();
        generateGrassPositions();
    }

    private void addPossiblePositions() {
        for (int i = 0; i < maxHeight; i++) {
            for (int j = 0; j < maxWidth; j++) {
                possiblePositions.add(new Vector2d(i, j));
            }
        }
    }

    private void generateGrassPositions() {
        for (int i = 0; i < grassCount; i++) {
            int positionInList = (int) (Math.random() * possiblePositions.size());
            grassPositions.add(possiblePositions.get(positionInList));

//            System.out.println("Position in list: " + positionInList + " Size: " +
//            possiblePositions.size() + " " + possiblePositions.get(positionInList));

            possiblePositions.remove(positionInList);
        }
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return new Iterator<>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < grassPositions.size();
            }

            @Override
            public Vector2d next() {
                return grassPositions.get(currentIndex++);
            }
        };
//        Można by też zrobić return grassPositions.iterator(), ale chyba powyższa implementacja wystarcza
//        nam na nasze potrzeby.
    }
}
