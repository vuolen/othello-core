package io.github.vuolen.othello.bots;

import io.github.vuolen.othello.api.BoardAPI;
import io.github.vuolen.othello.api.OthelloBot;
import static io.github.vuolen.othello.api.Tile.*;
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
        //System.out.println("GAME STARTED");
        //System.out.println("YOUR COLOR IS " + playerToString(color));
    }

    @Override
    public int[] makeMove(BoardAPI board) {
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
}
