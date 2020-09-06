package io.github.vuolen.othello.domain;

import static io.github.vuolen.othello.api.Tile.*;
import java.util.Arrays;
import java.util.regex.*;

/**
 *
 * @author riikoro
 */
public class Board {
    
    public static final int SIZE = 8;

    private int[][] board;

    public Board() {
        this.board = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = 0;
            }
        }
        board[3][3] = WHITE;
        board[3][4] = BLACK;
        board[4][3] = BLACK;
        board[4][4] = WHITE;
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

    public boolean isGameOver() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (isMoveValid(x, y, BLACK)
                        || isMoveValid(x, y, WHITE)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int winner() {
        int blackScore = 0, whiteScore = 0;
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (this.getTile(x, y) == BLACK) {
                    blackScore++;
                } else {
                    whiteScore++;
                }
            }
        }
        if (blackScore > whiteScore) {
            return BLACK;
        } else if (whiteScore < blackScore) {
            return WHITE;
        } else {
            return EMPTY;
        }
    }
    
    public boolean hasValidMovesLeft(int player) {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (this.isMoveValid(x, y, player)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isMoveValid(int x, int y, int color) {
        if (!isMoveInBounds(x, y)
                || this.getTile(x, y) != EMPTY) {
            return false;
        }

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
    @Override
    public String toString() {
        String b = "  a b c d e f g h\n";
        for (int y = 0; y < SIZE; y++) {
            b += y + 1;
            for (int x = 0; x < SIZE; x++) {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Board other = (Board) obj;
        if (!Arrays.deepEquals(this.board, other.board)) {
            return false;
        }
        return true;
    }

}
