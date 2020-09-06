package domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 *
 * @author riikoro
 */
public class Board {

    int[][] board;
    int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public Board() {
        this.board = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = 0;
            }
        }
        board[3][3] = 2;
        board[3][4] = 1;
        board[4][3] = 1;
        board[4][4] = 2;
    }

    //for testing
    public Board(int[][] initialState) {
        this.board = initialState;
    }
    
    //for testing
    public void editBoard(int i, int j, int value) {
        this.board[i][j] = value;
    }

    public int[][] getBoard() {
        return board;
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
                if (board[i][j] == 0) {
                    b += " ";
                } else if (board[i][j] == 1) {
                    b += "○";
                } else {
                    b += "●";
                }
            }
            b += "|\n";
        }
        return b;
    }
    
    public boolean equals(Board other){
        for(int i = 0; i < this.board.length; i++){
            for(int j = 0; j < this.board.length; j++){
                if(this.board[i][j]!=other.board[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public void addMove(int x, int y, int own) {
        int opponent = own == 1 ? 2 : 1;

        this.board[x][y] = own;

        for (int i = 0; i < 8; i++) {
            int nextrow = x + directions[i][0];
            int nextcol = y + directions[i][1];

            if (withinBoard(nextrow, nextcol) && board[nextrow][nextcol] == opponent) {
                while (withinBoard(nextrow, nextcol)) {
                    nextrow = nextrow + directions[i][0];
                    nextcol = nextcol + directions[i][1];
                    if (board[nextrow][nextcol] == opponent) {
                        continue;
                    } else if (board[nextrow][nextcol] == own) {
                        // go back
                        while (!(nextrow == x && nextcol == y)) {
                            nextrow = nextrow - directions[i][0];
                            nextcol = nextcol - directions[i][1];
                            board[nextrow][nextcol] = own;
                        }
                        break;
                    } else {
                        break;
                    }
                }    
            }
        }
    }

    public boolean checkMoveValid(int x, int y, int own) {
        int opponent = own == 1 ? 2 : 1;

        //clockwise from top-left:
        for (int i = 0; i < 8; i++) {
            int nextrow = x + directions[i][0];
            int nextcol = y + directions[i][1];
            if (withinBoard(nextrow, nextcol) && board[nextrow][nextcol] == opponent) {
                while (withinBoard(nextrow, nextcol)) {
                    nextrow = nextrow + directions[i][0];
                    nextcol = nextcol + directions[i][1];
                    if (board[nextrow][nextcol] == opponent) {
                        continue;
                    } else if (board[nextrow][nextcol] == own) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }     
        return false;
    }

    private boolean withinBoard(int row, int col) {
        if (row >= 0 && col >= 0 && row < board.length && col < board.length) {
            return true;
        }
        return false;
    }   
    
    public boolean checkWin(int nextToMove){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(checkMoveValid(i, j, nextToMove)){
                    return false;
                }
            }
        }
        return true;
    }
    
    public int winner(){
        int blackScore = 0;
        int whiteScore = 0;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[i][j]==1){
                    blackScore++;
                } else {
                    whiteScore++;
                }
            }
        }
        if(blackScore > whiteScore){
            return 1;
        } else if(whiteScore < blackScore){
            return 2;
        } else {
            return 0;
        }
    }
}
