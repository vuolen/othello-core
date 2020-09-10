package io.github.vuolen.othello.domain;

import static io.github.vuolen.othello.api.Tile.*;
import java.util.Arrays;

/**
 *
 * @author riikoro
 */
public class Board {

    public static final int SIZE = 8;
    public static final int[][] DIRECTIONS = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    private int[][] board;

    public Board() {
        this.board = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.setTile(i, j, EMPTY);
            }
        }
        this.setTile(3, 3, WHITE);
        this.setTile(4, 3, BLACK);
        this.setTile(3, 4, BLACK);
        this.setTile(4, 4, WHITE);
    }

    //for tests
    public Board(int[][] initialBoard) {
        this.board = initialBoard;
    }

    public boolean addMove(int x, int y, int color) {
        if (isMoveValid(x, y, color)) {
            this.setTile(x, y, color);
            int opponent = color == WHITE ? BLACK : WHITE;

            //flip pieces surrounded by opponent
            for (int[] direction : DIRECTIONS) {
                int nextx = x + direction[0];
                int nexty = y + direction[1];

                if (isMoveInBounds(nextx, nexty) && this.getTile(nextx, nexty) == opponent) {
                    while (isMoveInBounds(nextx, nexty) && this.getTile(nextx, nexty) == opponent) {
                        nextx = nextx + direction[0];
                        nexty = nexty + direction[1];
                    }

                    if (!isMoveInBounds(nextx, nexty)) {
                        continue;
                    }

                    if (this.getTile(nextx, nexty) == color) {
                        while (nextx != x || nexty != y) {
                            nextx = nextx - direction[0];
                            nexty = nexty - direction[1];
                            this.setTile(nextx, nexty, color);
                        }
                    }
                }
            }
            return true;
        }

        return false;
    }

    public int getTile(int x, int y) {
        return this.board[x][y];
    }

    private void setTile(int x, int y, int value) {
        this.board[x][y] = value;
    }

    public boolean isGameOver() {
        if(!hasValidMovesLeft(BLACK)&&!hasValidMovesLeft(WHITE)){
            return true;
        }
        return false;
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
        } else if (whiteScore > blackScore) {
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
        for (int[] direction : DIRECTIONS) {
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

            if (!isMoveInBounds(nextx, nexty)) {
                continue;
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

    public int size() {
        return SIZE;
    }

    public int[][] getAsArray() {
        int[][] copyOfBoard = new int[SIZE][SIZE];
        
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                copyOfBoard[i][j] = this.board[i][j];
            }
        }
        
        return copyOfBoard;
    }
}
