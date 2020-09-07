package io.github.vuolen.othello.ui;

import io.github.vuolen.othello.api.BoardAPI;
import io.github.vuolen.othello.api.OthelloBot;
import static io.github.vuolen.othello.api.Tile.*;
import io.github.vuolen.othello.domain.Board;
import static io.github.vuolen.othello.domain.Board.SIZE;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author riikoro
 */
public class OthelloHuman implements OthelloBot {

    Scanner scanner;
    
    public OthelloHuman() {
        scanner = new Scanner(System.in);
    }
    
    @Override
    public void startGame(int color) {
        System.out.println("GAME STARTED");
        System.out.println("YOUR COLOR IS " + playerToString(color));
    }

    @Override
    public int[] makeMove(BoardAPI board) {
        System.out.println("-------------------------------");
        System.out.println(boardToString(board));
        while (true) {
            System.out.println("Insert move (format: a1): ");
            String move = scanner.nextLine();
            if (isInputFormatValid(move)) {
                int x, y;
                int[] coordinates = parseInputToCoordinates(move);
                return coordinates;
            } else {
                System.out.println("Invalid move format");
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

    private static boolean isInputFormatValid(String input) {
        return Pattern.matches("[a-h]{1}[1-8]{1}", input);
    }

    private static int[] parseInputToCoordinates(String input) {
        //a=10
        int[] coordinates = new int[2];
        coordinates[0] = Character.getNumericValue(input.charAt(0)) - 10;
        coordinates[1] = Character.getNumericValue(input.charAt(1)) - 1;
        return coordinates;
    }
    
    /* String representation of board, white unicode U+25CF
        a b c d e f g h 
      1| | | | | | | | |
      2| | | | | | | | |
      3| | | | | | | | |
      4| | | |●|○| | | |
      5| | | |○|●| | | |
      6| | | | | | | | |
      7| | | | | | | | |
      8| | | | | | | | |
     */
    public String boardToString(BoardAPI board) {
        String b = "  a b c d e f g h\n";
        for (int y = 0; y < SIZE; y++) {
            b += y + 1;
            for (int x = 0; x < SIZE; x++) {
                b += "|";
                if (board.getTile(x, y) == EMPTY) {
                    b += " ";
                } else if (board.getTile(x, y) == WHITE) {
                    b += "○";
                } else {
                    b += "●";
                }
            }
            b += "|\n";
        }
        return b;
    }
}
