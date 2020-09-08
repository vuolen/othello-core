/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.vuolen.othello.ui;

import io.github.vuolen.othello.api.BoardAPI;
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
        int turn = 0;
        OthelloBot[] contestants = new OthelloBot[]{bot1, bot2};
        int[] colors = new int[]{BLACK, WHITE};
        bot1.startGame(colors[0]);
        bot2.startGame(colors[1]);
                
        System.out.println("GAME STARTED");
        while (!board.isGameOver()) {
            System.out.println("-------------------------------");
            System.out.println(boardToString(board));
            System.out.println("turn: "+ playerToString(colors[turn]));
            int[] move = contestants[turn].makeMove(board);
            if (!board.addMove(move[0], move[1], colors[turn])) {
                System.out.println("INVALID MOVE - DISQUALIFIED");
                break;
            }
            
            int opponent = turn == 0 ? 1 : 0;
            if (board.hasValidMovesLeft(colors[opponent])) {
                turn = opponent;
            }
        }
        System.out.println("GAME OVER");
        
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
    public static String boardToString(BoardAPI board) {
        String b = "  a b c d e f g h\n";
        for (int y = 0; y < SIZE; y++) {
            b += y + 1;
            for (int x = 0; x < SIZE; x++) {
                b += "|";
                if (board.getTile(x, y) == EMPTY) {
                    b += " ";
                } else if (board.getTile(x, y) == WHITE) {
                    b += "●";
                } else {
                    b += "○";
                }
            }
            b += "|\n";
        }
        return b;
    }
    
    private static String playerToString(int player) {
        if (player == BLACK) {
            return "BLACK";
        } else {
            return "WHITE";
        }
    }
}