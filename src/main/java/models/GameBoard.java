package models;


import java.util.*;

public class GameBoard extends HashMap {

    private int size;
    private int marksInLine;

    public GameBoard() {
        this.size= this.setSize();
         this.marksInLine= this.setMarksInLine(size);

        //Filling play board with indexes
        for (Integer i = 1; i <= size * size; i++)
            this.put(i, i);

    }

    public Integer setSize() {
         Scanner scanner = new Scanner(System.in);
         int size = 0;

        System.out.println("<<< GAME: Set the size of the play board... >>>");
        System.out.println("<<< Note: The total number of fields on the board will be equal to the square of that number >>>");

        //Validate play board size
        do {
            System.out.println("<<< GAME: Enter a numeric value that will be at least 3! >>>");
            try {
                size = scanner.nextInt();
            } catch (InputMismatchException error) {
                scanner.next();
            }
        } while (size < 3);
        return size;
    }

    private Integer setMarksInLine(int size) {
        Scanner scanner = new Scanner(System.in);
        int marksInLine = 0;

        System.out.println("<<< GAME: Set the number of required marks in the line... >>>");

        //Validate required fields in line for win
        do {
            System.out.println("<<< GAME: Enter a numeric value that will be at least 3 but no more than " + size + "! >>>");
            try {
                marksInLine = scanner.nextInt();
            } catch (InputMismatchException error) {
                scanner.next();
            }
        } while (marksInLine < 3 || marksInLine > size);
        return marksInLine;
    }

    public void printBoard() {

        for (int i = 1; i <= this.size(); i++) {
            if (this.get(i) instanceof Character)
                System.out.format("%-7c", this.get(i));
            else
                System.out.format("%-7d", this.get(i));

            if (i % Math.sqrt(this.size()) == 0)
                System.out.println();
        }
    }

    public int getSize() {
        return size;
    }
    public int getMarksInLine() {
        return marksInLine;
    }
}
