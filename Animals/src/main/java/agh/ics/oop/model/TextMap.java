package agh.ics.oop.model;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class TextMap implements WorldMap<String, Integer> {
    private final Map<Integer, Word> strings = new HashMap<>();
    private int upperBond = -1;

    public TextMap() {
//        Nie wiem zbytnio co by miało się znajdować w konstruktorze i czy cokolwiek musi.
//        W skrypcie też nie jest nic takiego określone.
    }


    @Override
    public boolean place(String word) {
        upperBond++;
        strings.put(upperBond, new Word(word));
        return true;
    }

    @Override
    public void move(String word, MoveDirection direction) {
        Integer indexOfWordToMove = getIndexOfWord(word);
        System.out.println("Index of Word " + indexOfWordToMove);

        if (indexOfWordToMove != -1) {
            Word wordToMove = strings.get(indexOfWordToMove);
            wordToMove.changeOrientation(direction);


            Integer indexOfWordToExchange = switch (direction) {
                case FORWARD -> indexOfWordToMove + wordToMove.getOrientation().toUnitVector().getX();
                case BACKWARD -> indexOfWordToMove - wordToMove.getOrientation().toUnitVector().getX();
                case RIGHT, LEFT -> -1;
            };

            if (canMoveTo(indexOfWordToExchange) && wordToMove.canMoveWord()) {
//                System.out.println(indexOfWordToExchange);
//                System.out.println(wordToMove.getOrientation().toUnitVector().getX());
                Word wordToExchange = strings.get(indexOfWordToExchange);
                strings.remove(indexOfWordToExchange);
                strings.remove(indexOfWordToMove);
                strings.put(indexOfWordToExchange, wordToMove);
                strings.put(indexOfWordToMove, wordToExchange);
            }
        }
    }


    private Integer getIndexOfWord(String word) {
        for (Map.Entry<Integer, Word> ourWord : strings.entrySet()) {
            if (ourWord.getValue().getWord().equals(word)) {
                return ourWord.getKey();
            }
        }
        return -1;
    }


    @Override
    public boolean isOccupied(Integer index) {
        return canMoveTo(index);
//        Wywoluje canMoveTo, poniewaz jezeli możemy sie poruszyć na jakiś index, to oznacza, że znajduje się on
//        w naszej mapie i coś sie na nim znajduje
    }

    @Override
    public String objectAt(Integer index) {
        if (isOccupied(index)) {
            return strings.get(index).getWord();
        }
        return null;
    }

    //    W skrypcie nie było napisane nic odnosnie zmieniania MoveValidator, dlatego wyszedłem
//    z założenia, że to takze zalicza sie to do dostosowania metod w interfejsie WorldMap
    @Override
    public boolean canMoveTo(Integer index) {
        return index <= upperBond && index >= 0;
    }

}