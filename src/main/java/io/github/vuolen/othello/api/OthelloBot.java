
package io.github.vuolen.othello.api;

public interface OthelloBot {
    final int EMPTY = 0;
    final int PLAYER = 1;
    final int OPPONENT = 2;

    void startGame();

    int[] makeMove(int[][] board);
}