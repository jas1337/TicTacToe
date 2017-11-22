package models;


import java.util.*;

public class Game {

    private boolean continueGame = true;

    public Game(ArrayList<User> players, GameBoard gameBoard) {
        playGame(players, gameBoard);
    }

    private void playGame(ArrayList<User> players, GameBoard gameBoard) {
        Scanner scanner = new Scanner(System.in);
        while (continueGame) {
            for (User player : players) {
                int answerIndex = 0;
                while (answerIndex < 1 || answerIndex > gameBoard.size()) {
                    System.out.println("<<< GAME: Your turn " + player.getName() + "! Where to put '" + player.getSymbol() + "' ??? >>>");
                    try {
                        answerIndex = scanner.nextInt();
                        if (answerIndex >= 1 && answerIndex <= gameBoard.size()) {
                            gameBoard.put(answerIndex, player.getSymbol());
                            player.addAnswer(answerIndex, player.getSymbol());
                            gameBoard.printBoard();
                            gameShouldContinueCheck(player, gameBoard);
                        }
                    } catch (InputMismatchException error) {
                        scanner.next();
                    }
                    if (answerIndex < 1 || answerIndex > gameBoard.size())
                        System.out.println("<<< WARNING: The field index is invalid! >>>");
                }
            }
        }
        System.out.println("<<< GAME OVER! >>>");
    }


    private void gameShouldContinueCheck(User player, GameBoard gameBoard) {

        HashMap<Integer, Character> answersSubmited = player.getAnswers();

        for (Map.Entry<Integer, Character> answer : answersSubmited.entrySet()) {

            int answerIndex = answer.getKey();
            int size = gameBoard.getSize();
            int marksInLine = gameBoard.getMarksInLine();
            int rest1 = (answerIndex - 1) % size;  //rest from answerIndex modulo divide


            for (int i = 0; i < marksInLine; i++) {
                if (i > 0 &&
                        ((answerIndex + i >= answerIndex - rest1
                                && answerIndex + i < answerIndex - rest1 + size
                                && answersSubmited.containsKey(answerIndex + i)
                                && answersSubmited.containsKey(answerIndex + i - 1)
                        ) || (answersSubmited.containsKey(answerIndex + i * size)
                                && answersSubmited.containsKey(answerIndex + (i - 1) * size)
                        ) || (answersSubmited.containsKey(answerIndex + i * (size + 1))
                                && answersSubmited.containsKey(answerIndex + (i - 1) * (size + 1))
                        ) || (answersSubmited.containsKey(answerIndex + i * (size - 1))
                                && answersSubmited.containsKey(answerIndex + (i - 1) * (size - 1)))
                        ) &&
                        i + 1 == marksInLine
                        ) {
                    System.out.println("<<< GAME: " + player.getName() + " won the game and became a new world champion! >>>");
                    setContinueGame(false);
                }
            }
        }
    }

    private void setContinueGame(boolean continueGame) {
        this.continueGame = continueGame;
    }
}

