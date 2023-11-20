package agh.ics.oop.model;

public class Word {

    private MapDirection orientation;
    private final String word;

    public Word(String word){
        this(word, MapDirection.EAST);
    }
    public Word(String word, MapDirection orientation){
        this.word = word;
        this.orientation = orientation;
    }


    public void changeOrientation(MoveDirection direction) {
        orientation = switch(direction){
            case RIGHT -> orientation.next();
            case LEFT -> orientation.previous();
            case FORWARD, BACKWARD -> orientation;
        };
    }

    public boolean canMoveWord(){
        return orientation == MapDirection.EAST || orientation == MapDirection.WEST;
    }
    public MapDirection getOrientation() {
        return orientation;
    }

    public String getWord() {
        return word;
    }
}
