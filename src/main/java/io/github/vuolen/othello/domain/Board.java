/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.regex.*;  

/**
 * next: modaa lautaa konstruktori testeille + addMove + checkWin ja gittiiii
 * @author riikoro
 */
public class Board {
    
    int[][] board;

    public Board() {
        this.board = new int[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                board[i][j]=0;
            }
        }
        board[3][3]=2;        
        board[3][4]=1;
        board[4][3]=1;
        board[4][4]=2;
    }
    
    public Board(int[][] initialState){
        this.board = initialState;
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
    public String toString(){
        String b = "  a b c d e f g h\n";
        for(int i = 0; i < board.length; i++){
            b += i+1;
            for(int j = 0; j < board[0].length; j++){
                b += "|";
                if(board[i][j]==0){
                    b+=" ";
                }else if(board[i][j]==1){
                    b+="○";
                }else{
                    b+="●";
                }
            }
            b += "|\n";
        }
        return b;
    }
    
    public void addMove(int row, int col, int turn){
        this.board[row][col] = turn;
    }

    boolean isMoveValid(String moveStr, int own) {
        if(isMoveStringValid(moveStr)){
            int opponent = own == 1 ? 2 : 1;

            //{rowindex, colindex}
            int[] move = convertStringToCoordinates(moveStr);
            int row = move[0];
            int col = move[1];

            //clockwise from top-left:
            int[][] directions = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
            for(int i = 0; i < 8; i++){
                int nextrow = row + directions[i][0];
                int nextcol = col + directions[i][1];
                if(isMoveInBounds(nextrow, nextcol)){
                    if(board[nextrow][nextcol] == opponent){
                        while(isMoveInBounds(nextrow, nextcol)){
                            nextrow = nextrow + directions[i][0];
                            nextcol = nextcol + directions[i][1];
                            if(board[nextrow][nextcol] == opponent){
                                continue;
                            } else if(board[nextrow][nextcol] == own){
                                return true;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private boolean isMoveInBounds(int row, int col) {
        if(row >= 0 && col >= 0 && row < board.length && col < board.length){
            return true;
        }
        return false;
    }
    
    public boolean isMoveStringValid(String move){
        return Pattern.matches("[a-h]{1}[1-8]{1}", move);
    }

    public int[] convertStringToCoordinates(String moveStr) {
        //a=10
        int[] coordinates = new int[2];
        coordinates[0] = Character.getNumericValue(moveStr.charAt(1))-1;
        coordinates[1] = Character.getNumericValue(moveStr.charAt(0))-10;
        return coordinates;
    }
    
    //for testing
    public void removeBottomLeftBlack(){
        this.board[4][3] = 0;
    }
    
}
