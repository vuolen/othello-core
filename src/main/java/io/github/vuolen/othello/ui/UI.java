package io.github.vuolen.othello.ui;

import static io.github.vuolen.othello.api.Tile.*;
import io.github.vuolen.othello.domain.Board;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author riikoro
 */
public class UI {

    public static void launchGame() {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        int turn = BLACK;
        while (true) {
            System.out.println("-------------------------------");
            System.out.println(board);
            System.out.println("Turn: " + playerToString(turn));
            System.out.print("Insert move (format: a1): ");
            String move = scanner.nextLine();

            if (isInputFormatValid(move)) {
                int[] coordinates = parseInputToCoordinates(move);
                int x = coordinates[0];
                int y = coordinates[1];
                if (board.addMove(x, y, turn)) {
                    if (board.isGameOver()) {
                        System.out.println("Game over!");
                        String winner = playerToString(board.winner());
                        System.out.println("Winner: " + winner);
                        break;
                    } else {
                        int opponent = turn == BLACK ? WHITE : BLACK;
                        if (board.hasValidMovesLeft(opponent)) {
                            turn = opponent;
                        }
                    }
                } else {
                    System.out.println("Invalid move");
                }
            } else {
                System.out.println("Invalid move");
            }
        }
    }
    
    private static String playerToString(int player) {
        if (player == BLACK) {
            return "BLACK";
        } else {
            return "WHITE";
        }
    }

    public static boolean isInputFormatValid(String input) {
        return Pattern.matches("[a-h]{1}[1-8]{1}", input);
    }

    public static int[] parseInputToCoordinates(String input) {
        //a=10
        int[] coordinates = new int[2];
        coordinates[0] = Character.getNumericValue(input.charAt(0)) - 10;
        coordinates[1] = Character.getNumericValue(input.charAt(1)) - 1;
        return coordinates;
    }
}
