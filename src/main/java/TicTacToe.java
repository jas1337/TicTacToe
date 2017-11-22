import java.util.*;

import models.Game;
import models.GameBoard;
import models.User;


public class TicTacToe {

    public static void main(String[] args) {

        ArrayList<User> players = User.createUsers();

        GameBoard gameBoard = new GameBoard();

        gameBoard.printBoard();

        Game game=new Game(players,gameBoard);

    }
}
