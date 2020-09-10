/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.vuolen.othello.ui;

import io.github.vuolen.othello.api.OthelloBot;
import static io.github.vuolen.othello.api.OthelloBot.EMPTY;
import static io.github.vuolen.othello.api.Tile.BLACK;
import static io.github.vuolen.othello.api.Tile.WHITE;
import io.github.vuolen.othello.domain.Board;
import static io.github.vuolen.othello.domain.Board.SIZE;

/**
 *
 * @author riikoro
 */
public class UI {

    public static void battle(OthelloBot bot1, OthelloBot bot2) {
        Board board = new Board();
        int turn = 1;
        OthelloBot[] contestants = new OthelloBot[]{bot1, bot2};
        int[] colors = new int[]{BLACK, WHITE};
        bot1.startGame(colors[0]);
        bot2.startGame(colors[1]);

        /*
        UI BUGFIX:
        line w hasValidMovesLeft called with param 0 for black, 1 for white,
        ui turns changed to 1/2 like in board to avoid similar bugs
        UI CHANGE TODO:
        - Humans mistype moves. Added isHuma() to bot interface for later timeout
        implementation & disqualification only for bot if move invalid!
        - Disqualified player should lose the game
        */
        System.out.println("GAME STARTED");
        while (!board.isGameOver()) {
            System.out.println("-------------------------------");
            System.out.println(boardToString(board));
            System.out.println("turn: " + colorToMark(colors[turn-1]));
            if (board.hasValidMovesLeft(turn)) {
                int[] move = contestants[turn-1].makeMove(board.getAsArray());
                if (!board.addMove(move[0], move[1], colors[turn-1])) {
                    System.out.println("INVALID MOVE - DISQUALIFIED");
                    break;
                }
            } else {
                System.out.println("No possible moves");
            }

            int opponent = turn == 1 ? 2 : 1;
            turn = opponent;
        }
        System.out.println("GAME OVER");
        System.out.println("WINNER: " + colorToMark(board.winner()));

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
    public static String boardToString(Board board) {
        String b = "  a b c d e f g h\n";
        for (int y = 0; y < SIZE; y++) {
            b += y + 1;
            for (int x = 0; x < SIZE; x++) {
                b += "|";
                if (board.getTile(x, y) == EMPTY) {
                    b += " ";
                } else {
                    b += colorToMark(board.getTile(x, y));
                }
            }
            b += "|\n";
        }
        return b;
    }

    private static String colorToMark(int color) {
        if (color == BLACK) {
            return "#";
        } else {
            return "○";
        }
    }
}
