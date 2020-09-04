package io.github.vuolen.othello.domain;

import static io.github.vuolen.othello.api.Tile.*;
import java.util.regex.*;

/**
 * next: modaa lautaa konstruktori testeille + addMove + checkWin ja gittiiii
 *
 * @author riikoro
 */
public class Board {

    private int[][] board;

    public Board() {
        this.board = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = 0;
            }
        }
        board[3][3] = WHITE;
        board[3][4] = BLACK;
        board[4][3] = BLACK;
        board[4][4] = WHITE;
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
    public String toString() {
        String b = "  a b c d e f g h\n";
        for (int y = 0; y < board.length; y++) {
            b += y + 1;
            for (int x = 0; x < board[0].length; x++) {
                b += "|";
                if (this.getTile(x, y) == EMPTY) {
                    b += " ";
                } else if (this.getTile(x, y) == WHITE) {
                    b += "○";
                } else {
                    b += "●";
                }
            }
            b += "|\n";
        }
        return b;
    }

    public boolean addMove(int x, int y, int color) {
        if (isMoveValid(x, y, color)) {
            this.board[x][y] = color;
            return true;
        }
        
        return false;
    }
    
    public int getTile(int x, int y) {
        return this.board[x][y];
    }

    private boolean isMoveValid(int x, int y, int color) {
        int opponent = color == WHITE ? BLACK : WHITE;

        //clockwise from top-left:
        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int[] direction : directions) {
            int nextx = x + direction[0];
            int nexty = y + direction[1];
            
            if (!isMoveInBounds(nextx, nexty)
                    || this.getTile(nextx, nexty) != opponent) {
                continue;
            }
            
            while (isMoveInBounds(nextx, nexty) 
                    && this.getTile(nextx, nexty) == opponent) {
                nextx += direction[0];
                nexty += direction[1];
            }
            
            if (this.getTile(nextx, nexty) == color) {
                return true;
            }
        }
        return false;
    }

    private boolean isMoveInBounds(int row, int col) {
        if (row >= 0 && col >= 0 && row < board.length && col < board.length) {
            return true;
        }
        return false;
    }

}
