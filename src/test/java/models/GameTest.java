package models;

import models.GameBoard;
import models.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GameTest {


    String name1;
    Character symbol1;
    int answerIndex;
    int size;
    int marksInLine;
    int rest1;

    @Before
    public void initialize() {
        name1 = "Bolec";
        symbol1 = 'B';
        answerIndex = 5;
        size = 3;
        marksInLine = 3;
        rest1 = (answerIndex - 1) % size;

    }

    @Test
    public void testWinConditionRows() {
        HashMap<Integer, Character> answersSubmited = new HashMap<Integer, Character>();

        answersSubmited.put(4, symbol1);
        answersSubmited.put(6, symbol1);

        assertTrue("More or equal to the minimum value in a row", answerIndex >= answerIndex - rest1);
        assertTrue("Less than minimum value in next row", answerIndex < answerIndex - rest1 + size);
        assertTrue("Next index in row should exist", answersSubmited.containsKey(answerIndex + 1));
        assertTrue("Previous index in row should exist", answersSubmited.containsKey(answerIndex - 1));
    }

    @Test
    public void testWinConditionColumns() {
        HashMap<Integer, Character> answersSubmited = new HashMap<Integer, Character>();

        answersSubmited.put(2, symbol1);
        answersSubmited.put(8, symbol1);

        assertTrue("Next index in column should exist", answersSubmited.containsKey(answerIndex + size));
        assertTrue("Previous index in column should exist", answersSubmited.containsKey(answerIndex - size));
    }

    @Test
    public void testWinConditionMainDiagonal() {
        HashMap<Integer, Character> answersSubmited = new HashMap<Integer, Character>();

        answersSubmited.put(1, symbol1);
        answersSubmited.put(9, symbol1);


        assertTrue("Next index in diagonal should exist", answersSubmited.containsKey(answerIndex + (size + 1)));
        assertTrue("Previous index in diagonal should exist", answersSubmited.containsKey(answerIndex - (size + 1)));
        assertTrue("Rest from modulo divide of previous index must be less than size - marksInLine", rest1-1 <= size - marksInLine);
    }
    @Test
    public void testWinConditionMinorDiagonal() {
        HashMap<Integer, Character> answersSubmited = new HashMap<Integer, Character>();

        answersSubmited.put(3, symbol1);
        answersSubmited.put(7, symbol1);


        assertTrue("Next index in diagonal should exist", answersSubmited.containsKey(answerIndex + (size - 1)));
        assertTrue("Previous index in diagonal should exist", answersSubmited.containsKey(answerIndex - (size - 1)));
        assertTrue("Rest from modulo divide of previous index must be greather than marksInLine - 1", rest1 +1>= marksInLine - 1);
    }


    @Test
    public void testAddAnswear() {
        int index = 1;
        HashMap answers = new HashMap();

        answers.put(index, symbol1);

        assertEquals(1, answers.size());
        assertEquals(symbol1, answers.get(index));
    }

    @Test
    public void testCreateUsers() {

        String name2 = "Macius";
        Character symbol2 = 'M';
        ArrayList<User> players = new ArrayList<User>();
        User player1 = new User(name1, symbol1);
        User player2 = new User(name2, symbol2);

        players.add(player1);
        players.add(player2);

        assertEquals(2, players.size());
        assertEquals(player1, players.get(0));
        assertEquals(symbol1, (players.get(0).getSymbol()));
        assertEquals(name1, (players.get(0).getName()));
    }

}