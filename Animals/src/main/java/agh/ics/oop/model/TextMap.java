package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

public class TextMap implements WorldMap<String, Integer> {
//   Posługuje się tutaj objektem klasy Word. Po to, aby pamiętać orientacje naszego słowa
//   Wstępnie jest ona ustawiona na EAST, czyli gdy poruszamy się do przodu to zamieniamy się ze słowem na prawo
//   od naszego, a gdy poruszamy się od tyłu, to zmieniamy sie ze słowem na lewo od naszego.
//   Jednak rozumiem, że słowa też mogą mieć swoją orientację, więc posłużenie się nową klasą, było najprostsze.

//    private final Map<Integer, Word> strings = new HashMap<>();
    private final List<Word> strings = new ArrayList<>();
    private int upperBond = -1;

    public TextMap() {
//        Nie wiem zbytnio co by miało się znajdować w konstruktorze i czy cokolwiek musi.
//        W skrypcie też nie jest nic takiego określone.
    }


    @Override
    public boolean place(String word) {
        upperBond++;
//        strings.put(upperBond, new Word(word));
        strings.add(new Word(word));
        return true;
    }

    @Override
    public void move(String word, MoveDirection direction) {
        int indexOfWordToMove = getIndexOfWord(word);
//        System.out.println("Index of Word " + indexOfWordToMove);

        if (indexOfWordToMove != -1) {
            Word wordToMove = strings.get(indexOfWordToMove);
            wordToMove.changeOrientation(direction);


            int indexOfWordToExchange = switch (direction) {
                case FORWARD -> indexOfWordToMove + wordToMove.getOrientation().toUnitVector().getX();
                case BACKWARD -> indexOfWordToMove - wordToMove.getOrientation().toUnitVector().getX();
                case RIGHT, LEFT -> -1;
            };

            if (canMoveTo(indexOfWordToExchange) && wordToMove.canMoveWord()) {
//                System.out.println(indexOfWordToExchange);
//                System.out.println(wordToMove.getOrientation().toUnitVector().getX());
                Word wordToExchange = strings.get(indexOfWordToExchange);
                strings.set(indexOfWordToMove, wordToExchange);
                strings.set(indexOfWordToExchange, wordToMove);
            }
        }
    }


//    Funkcja potrzebna do uzyskania indeksu, na którym znajduje się nasze słowo, które chcemy przesunąć
//    W zwykłej array liście też musielibyśmy szukać po całałości, więć użycie tutaj haszmapy i przeiterowanie
//    po niej w celu znalezenia odpowiedniego klucza, nie jest takie złe.
    private int getIndexOfWord(String word) {
        for (int i = 0; i < upperBond+1; i++ ) {
            if (strings.get(i).getWord().equals(word)) {
                return i;
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