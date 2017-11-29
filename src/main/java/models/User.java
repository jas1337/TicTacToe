package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class User {

    private String name;
    private Character symbol;
    private HashMap answers = new HashMap();


    public User(String name, Character symbol) {
        this.name = name;
        this.symbol = symbol;
    }


    public static ArrayList<User> createUsers() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<User> players = new ArrayList<User>();
        for (int i = 1; i <= 2; i++) {
            char symbol = 'O';
            if (i == 2)
                symbol = 'X';

            System.out.println("<<< GAME:  Who is playing as '" + symbol + "' ??? >>>");
            String name = scanner.nextLine();
            User player = new User(name, symbol);
            players.add(player);
        }
        return players;
    }


    public void addAnswer(int index, char symbol) {
        this.answers.put(index, symbol);
    }


    public String getName() {
        return name;
    }

    public Character getSymbol() {
        return symbol;
    }

    public HashMap getAnswers() {
        return answers;
    }
}
