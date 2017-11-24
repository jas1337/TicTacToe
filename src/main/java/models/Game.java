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

                        if (gameBoard.get(answerIndex).getClass() == player.getSymbol().getClass())
                            answerIndex = 0;

                        if (answerIndex >= 1 && answerIndex <= gameBoard.size()) {

                            gameBoard.put(answerIndex, player.getSymbol());
                            player.addAnswer(answerIndex, player.getSymbol());
                            gameBoard.printBoard();
                            gameShouldContinueCheck(player, gameBoard);
                        }
                    } catch (InputMismatchException error) {
                        scanner.next();
                    }
                    if (answerIndex < 1 || answerIndex > gameBoard.size()) {
                        gameBoard.printBoard();
                        System.out.println("<<< WARNING: Field index is invalid or already unavailable! >>>");
                    }
                    if (players.get(0).getAnswers().size() + players.get(1).getAnswers().size() == gameBoard.size()) {
                        System.out.println("<<< GAME: The game ends with a deadlock, no one wins! >>>");
                        setContinueGame(false);
                    }
                }
                if (!continueGame) break;
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
                        //Checks only rows
                        ((answerIndex + i >= answerIndex - rest1
                                && answerIndex + i < answerIndex - rest1 + size
                                && answersSubmited.containsKey(answerIndex + i)
                                && answersSubmited.containsKey(answerIndex + i - 1)
                                //Checks only columns
                        ) || (answersSubmited.containsKey(answerIndex + i * size)
                                && answersSubmited.containsKey(answerIndex + (i - 1) * size)
                                //Checks only main diagonal
                        ) || (answersSubmited.containsKey(answerIndex + i * (size + 1))
                                && answersSubmited.containsKey(answerIndex + (i - 1) * (size + 1))
                                && rest1 <= size - marksInLine
                                //Checks only minor diagonal
                        ) || (answersSubmited.containsKey(answerIndex + i * (size - 1))
                                && answersSubmited.containsKey(answerIndex + (i - 1) * (size - 1))
                                && rest1 >= marksInLine - 1)
                                //Checks if required length has been reached
                        ) && i + 1 == marksInLine
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