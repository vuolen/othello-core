package io.github.vuolen.othello.bots;

import io.github.vuolen.othello.api.OthelloBot;
import static io.github.vuolen.othello.api.Tile.BLACK;
import static io.github.vuolen.othello.api.Tile.EMPTY;
import static io.github.vuolen.othello.api.Tile.WHITE;

/**
 * Chooses the first possible move it stumbles upon.
 * @author Lennu Vuolanne <vuolanne.lennu@gmail.com>
 */
public class SimpleBot implements OthelloBot {

    private int color;
    public final boolean isHuman = false;
    
    @Override
    public void startGame(int color) {
        this.color = color;
    }

    @Override
    public int[] makeMove(int[][] board) {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                if (isMoveValid(x, y, board)) {
                    return new int[]{x, y};
                }
            }
        }
        return new int[]{0, 0};
    }
    
    // The following two methods are basically ripped from the Board implementation.
    // However the bot you create is expected to provide these kind of methods for yourself.
    
    private boolean isMoveValid(int x, int y, int[][] board) {
        if (!isMoveInBounds(x, y, board)
                || board[x][y] != EMPTY) {
            return false;
        }

        int opponent = this.color == WHITE ? BLACK : WHITE;

        //clockwise from top-left:
        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int[] direction : directions) {
            int nextx = x + direction[0];
            int nexty = y + direction[1];

            if (!isMoveInBounds(nextx, nexty, board)
                    || board[nextx][nexty] != opponent) {
                continue;
            }

            while (isMoveInBounds(nextx, nexty, board)
                    && board[nextx][nexty] == opponent) {
                nextx += direction[0];
                nexty += direction[1];
            }
            
            if (!isMoveInBounds(nextx, nexty, board)) {
                continue;
            }

            if (board[nextx][nexty] == this.color) {
                return true;
            }
        }
        return false;
    }

     private boolean isMoveInBounds(int x, int y, int[][] board) {
        if (x >= 0 && y >= 0 && x < board.length && y < board.length) {
            return true;
        }
        return false;
    }
    
     public boolean isHuman(){
        return isHuman;
    }
}
