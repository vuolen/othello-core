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
        board[3][3] = BLACK;
        board[3][4] = WHITE;
        board[4][3] = WHITE;
        board[4][4] = BLACK;
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
        for (int i = 0; i < board.length; i++) {
            b += i + 1;
            for (int j = 0; j < board[0].length; j++) {
                b += "|";
                if (board[i][j] == EMPTY) {
                    b += " ";
                } else if (board[i][j] == WHITE) {
                    b += "○";
                } else {
                    b += "●";
                }
            }
            b += "|\n";
        }
        return b;
    }

    public boolean addMove(int row, int col, int color) {
        if (isMoveValid(row, col, color)) {
            this.board[row][col] = color;
            return true;
        }
        
        return false;
    }
    
    public int getTile(int row, int col) {
        return this.board[row][col];
    }

    private boolean isMoveValid(int row, int col, int color) {
        int opponent = color == WHITE ? BLACK : WHITE;

        //clockwise from top-left:
        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int[] direction : directions) {
            int nextrow = row + direction[0];
            int nextcol = col + direction[1];

            if (isMoveInBounds(nextrow, nextcol)) {
                if (board[nextrow][nextcol] == opponent) {
                    while (isMoveInBounds(nextrow, nextcol)) {
                        nextrow = nextrow + direction[0];
                        nextcol = nextcol + direction[1];
                        if (board[nextrow][nextcol] == opponent) {
                            continue;
                        } else if (board[nextrow][nextcol] == color) {
                            return true;
                        } else {
                            break;
                        }
                    }
                }
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
