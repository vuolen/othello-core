
package io.github.vuolen.othello.api;

public interface OthelloBot {
    public final int EMPTY = 0;
    public final int PLAYER = 1;
    public final int OPPONENT = 2;

    void startGame(int color);

    int[] makeMove(int[][] board);
    
    boolean isHuman();
}