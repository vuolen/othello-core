
package io.github.vuolen.othello.api;

public interface OthelloBot {

    void startGame(int color);

    int[] makeMove(int[][] board);
    
    boolean isHuman();
}