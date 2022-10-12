import java.util.*;
import java.io.*;

public class DotConnect {
    static int[][] board;
    static boolean playerTurn = true;// true=player1 & false=player2
    static int boardRow;
    static int boardCol;
    static int col;
    static int row;
    static int playerAPoints = 0;
    static int playerBPoints = 0;

    static void printBoard() {
        for (int i = 0; i < boardRow; i++) {
            for (int j = 0; j < boardCol; j++) {
                if (board[i][j] == -1)
                    System.out.print("|");
                else if (board[i][j] == -2)
                    System.out.print("-");
                else if (board[i][j] == 0)
                    System.out.print(" ");
                else if (board[i][j] == -3)
                    System.out.print("P1");
                else if (board[i][j] == -4)
                    System.out.print("P2");
                else
                    System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    static boolean checkBoard() {
        for (int i = 0; i < boardRow; i++) {
            for (int j = 0; j < boardCol; j++) {
                if (board[i][j] == 0)
                    return true;
            }
        }
        return false;
    }


    static void announceWinner(){
        System.out.print("Game Over!!!\nWinner : Player ");
        if(playerAPoints==playerBPoints)
            System.out.println("Tied!!!");
        if(playerAPoints>playerBPoints)
            System.out.println("P1");
        else 
            System.out.println("P2");
    }

    static int checkConnection(int rowA, int colA, int rowB, int colB) {

        // top
        if (rowB == rowA - 2 && colB == colA)
            return 0;
        // bottom
        if (rowB == rowA + 2 && colB == colA)
            return 2;
        // left
        if (rowB == rowA && colB == colA - 2)
            return 3;
        // right
        if (rowB == rowA && colB == colA + 2)
            return 1;
        return -1;
    }

    static void drawConnection(int direction, int rowA, int colA) {
        // top
        if (direction == 0)
            board[rowA - 1][colA] = -1;
        // right
        if (direction == 1)
            board[rowA][colA + 1] = -2;
        // bottom
        if (direction == 2)
            board[rowA + 1][colA] = -1;
        // left
        if (direction == 3)
            board[rowA][colA - 1] = -2;

    }

    static boolean checkBox(int direction, int rowA, int colA, int rowB, int colB) {

        int pathCount = 0;
        boolean status = false;
        if (direction == 0) {

            // LEFT

            // <-
            // |
            if (colB > 1 && board[rowB][colB - 1] == -2)
                pathCount++;

            // |
            // <-
            if (colA > 1 && rowA > 0 && board[rowA - 1][colA - 2] == -1)
                pathCount++;

            // <-
            if (colA > 1 && board[rowA][colA - 1] == -2)
                pathCount++;

            if (pathCount > 2) {
                board[rowA - 1][colA - 1] = playerTurn ? -3 : -4;
                status = true;
            }

            pathCount = 0;

            // RIGHT

            // ->
            // |
            if (colB < boardCol - 2 && board[rowB][colB + 1] == -2)
                pathCount++;

            // |
            // ->
            if (rowA > 1 && colA < boardCol - 2 && board[rowA - 1][colA + 2] == -1)
                pathCount++;

            // ->
            if (colA < boardCol - 2 && board[rowA][colA + 1] == -2)
                pathCount++;

            if (pathCount > 2) {
                board[rowA - 1][colA + 1] = playerTurn ? -3 : -4;
                status = true;
            }
        }

        else if (direction == 2) {

            // bottom

            // LEFT

            // |
            // <-
            if (colB > 1 && board[rowB][colB - 1] == -2)
                pathCount++;

            // <-
            // |
            if (rowA < boardRow - 2 && colA > 1 && board[rowA + 1][colA - 2] == -1)
                pathCount++;

            // <-
            if (colA > 1 && board[rowA][colA - 1] == -2)
                pathCount++;

            if (pathCount > 2) {
                board[rowA + 1][colA - 1] = playerTurn ? -3 : -4;
                status = true;
            }
            pathCount = 0;

            // RIGHT

            // |
            // ->
            if (colB < boardCol - 2 && board[rowB][colB + 1] == -2)
                pathCount++;

            // ->
            // |
            if (colA < boardCol - 2 && rowA < boardRow - 2 && board[rowA + 1][colA + 2] == -1)
                pathCount++;

            // ->
            if (colA < boardCol - 2 && board[rowA][colA + 1] == -2)
                pathCount++;

            if (pathCount > 2) {
                board[rowA + 1][colA + 1] = playerTurn ? -3 : -4;
                status = true;
            }
        }

        else if (direction == 1) {

            // right
            // BOTTOM

            // ->
            // |
            if (rowB < boardRow - 2 && board[rowB + 1][colB] == -1)
                pathCount++;

            // |
            // ->
            if (rowA < boardRow - 2 && colA < boardCol - 2 && board[rowA + 2][colA + 1] == -2)
                pathCount++;

            // |
            if (rowA < boardRow - 2 && board[rowA + 1][colA] == -1)
                pathCount++;

            if (pathCount > 2) {
                board[rowA + 1][colA + 1] = playerTurn ? -3 : -4;
                status = true;
            }

            pathCount = 0;

            // TOP

            // |
            // ->
            if (rowB > 1 && board[rowB - 1][colB] == -1)
                pathCount++;

            // ->
            // |
            if (rowA > 1 && colA < boardCol - 2 && board[rowA - 2][colA + 1] == -2)
                pathCount++;

            // |
            if (rowA > 1 && board[rowA - 1][colA] == -1)
                pathCount++;

            if (pathCount > 2) {
                board[rowA - 1][colA + 1] = playerTurn ? -3 : -4;
                status = true;
            }

        }

        else if (direction == 3) {

            // left

            // BOTTOM

            // <-
            // |
            if (rowB < boardRow - 2 && board[rowA + 1][colA] == -1)
                pathCount++;

            // |
            // <-
            if (rowA < boardRow - 2 && colA > 1 && board[rowA + 2][colA - 1] == -2)
                pathCount++;

            // |
            if (rowA < boardRow - 2 && board[rowA + 1][colA] == -1)
                pathCount++;

            if (pathCount > 2) {
                board[rowA + 1][colA - 1] = playerTurn ? -3 : -4;
                status = true;
            }

            pathCount = 0;

            // RIGHT

            // |
            // <-
            if (rowB > 1 && board[rowB - 1][colB] == -1)
                pathCount++;

            // <-
            // |
            if (rowA > 1 && colA > 1 && board[rowA - 2][colA - 1] == -2)
                pathCount++;

            // |
            if (rowA > 1 && board[rowA - 1][colA] == -1)
                pathCount++;

            if (pathCount > 2) {
                board[rowA - 1][colA - 1] = playerTurn ? -3 : -4;
                status = true;
            }

        }

        return status;

    }

    static void playGame() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Input for Player" + (playerTurn ? "P1" : "P2"));
        int pointA = sc.nextInt();
        int pointB = sc.nextInt();

        if (pointA == pointB) {
            System.out.println("Same Point Try again");
            playGame();
        }

        if (pointA > row * col || pointB > row * col) {
            System.out.println("Out of Board! Try again");
            playGame();
        }

        int rowA = -1, colA = -1, rowB = -1, colB = -1;

        int gotPoints = -2;
        for (int i = 0; i < boardRow; i++) {
            for (int j = 0; j < boardCol; j++) {
                if (board[i][j] == pointA) {
                    rowA = i;
                    colA = j;
                    gotPoints++;
                }
                if (board[i][j] == pointB) {
                    rowB = i;
                    colB = j;
                    gotPoints++;
                }
                if (gotPoints == 0)
                    break;
            }
            if (gotPoints == 0)
                break;
        }
        if (gotPoints != 0) {
            System.out.println("No Such Point");
            playGame();
        }
        // for testing
        // System.out.print(rowA+" ");
        // System.out.println(colA);
        // System.out.print(rowA+" ");
        // System.out.println(colB);
        int direction = checkConnection(rowA, colA, rowB, colB);

        if (direction == -1) {
            System.out.println("No Connection Possible");
            playGame();
        }

        drawConnection(direction, rowA, colA);

        boolean boxIsPresent = checkBox(direction, rowA, colA, rowB, colB);

        printBoard();

        if (boxIsPresent) {
            if (playerTurn)
                playerAPoints++;
            else
                playerBPoints++;

            System.out.println("\n****************\n P1 : "+playerAPoints+"\n P2 : "+playerBPoints+"\n****************");
        }

        if (!boxIsPresent)
            playerTurn = !playerTurn;

        if (checkBoard()) {
            playGame();
        }   
        else 
            announceWinner();

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Board Size : ");

        row = sc.nextInt();

        col = sc.nextInt();

        boardRow = 2 * row - 1;
        boardCol = 2 * col - 1;

        board = new int[boardRow][boardCol];

        int boardIndex = 1;
        for (int i = 0; i < boardRow; i += 2) {
            for (int j = 0; j < boardCol; j += 2) {
                board[i][j] = boardIndex++;
            }
        }
        playGame();

    }
}