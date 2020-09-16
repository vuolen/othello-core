/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.vuolen.othello.ui;

import io.github.vuolen.othello.api.OthelloBot;
import static io.github.vuolen.othello.api.Tile.EMPTY;
import static io.github.vuolen.othello.api.Tile.BLACK;
import static io.github.vuolen.othello.api.Tile.WHITE;
import io.github.vuolen.othello.domain.Board;
import static io.github.vuolen.othello.domain.Board.SIZE;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author riikoro
 */
public class UI {

    public static void tournament(OthelloBot bot1, OthelloBot bot2, int numberOfGames) {
        System.out.println("Executing tournament of " + numberOfGames + " games...");

        int winsBot1 = 0;
        int winsBot2 = 0;

        int i = 0;
        while (i < numberOfGames) {
            int winner = battle(bot1, bot2, false);
            if (winner == BLACK) {
                winsBot1++;
            } else if (winner == WHITE) {
                winsBot2++;
            }
            i++;
        }

        System.out.println("Tournament over, results:");
        System.out.println("Player 1: " + winsBot1 + " wins");
        System.out.println("Player 2: " + winsBot2 + " wins");
    }

    public static int battle(OthelloBot bot1, OthelloBot bot2, boolean printsOn) {
        Board board = new Board();
        int turn = 0;
        int winner = 0;

        OthelloBot[] contestants = new OthelloBot[]{bot1, bot2};
        int[] colors = new int[]{BLACK, WHITE};
        bot1.startGame(colors[0]);
        bot2.startGame(colors[1]);

        print("GAME STARTED", printsOn);
        while (!board.isGameOver()) {
            print("-------------------------------", printsOn);
            print(boardToString(board), printsOn);
            print("turn: " + colorToMark(colors[turn]), printsOn);

            int opponent = turn == 0 ? 1 : 0;

            if (board.hasValidMovesLeft(colors[turn])) {
                int[] move;
                if (contestants[turn].isHuman()) {
                    move = contestants[turn].makeMove(board.getAsArray());
                } else {
                    move = makeMoveWithTimeout(contestants[turn], board.getAsArray());
                }
                
                if (move == null) {
                    print("TIMEOUT - DISQUALIFIED", printsOn);
                    winner = opponent;
                    break;
                }

                boolean moveValid = board.addMove(move[0], move[1], colors[turn]);
                if (!moveValid && !contestants[turn].isHuman()) {
                    print("INVALID MOVE - DISQUALIFIED", printsOn);
                    winner = opponent;
                    break;
                } else if (!moveValid && contestants[turn].isHuman()) {
                    print("Invalid move, please try again", printsOn);
                    continue;
                }
            } else {
                print("No possible moves", printsOn);
            }

            turn = opponent;
        }
        
        print("-------------------------------", printsOn);
        print(boardToString(board), printsOn);

        print("GAME OVER", printsOn);
        int winnerColor = board.winner();
        if (winnerColor == EMPTY) {
            System.out.println("THE GAME IS A TIE");
        } else {
            
        }
        print("WINNER: " + colorToMark(winnerColor), printsOn);
        
        return winner;
    }
    
    public static int[] makeMoveWithTimeout(final OthelloBot bot, final int[][] board) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        
        final Future<int[]> handler = executor.submit(new Callable() {
            @Override
            public int[] call() throws Exception {
                return bot.makeMove(board);
            }
        });
        
        
        try {
            handler.get(1000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            handler.cancel(true);
            e.printStackTrace(System.out);
            return null;
        }
        
        return bot.makeMove(board);
    }

    public static void print(String stringToPrint, boolean printsEnabled) {
        if (printsEnabled) {
            System.out.println(stringToPrint);
        }
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
