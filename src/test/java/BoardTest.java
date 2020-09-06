/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domain.Board;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author riikoro
 */
public class BoardTest {
    
    private Board board;
    
    @Before
    public void setUp() {
        this.board = new Board();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void initialStateCorrect(){
        assertEquals(board.getBoard()[2][3], 0);
        assertEquals(board.getBoard()[3][3], 2);
        assertEquals(board.getBoard()[4][3], 1);
    }
    
    @Test
    public void toStringFormatting(){
        String[] rows = board.toString().split("\n");
        assertEquals("  a b c d e f g h", rows[0]);
        assertEquals("4| | | |●|○| | | |", rows[4]);           
    }
     
    @Test
    public void openingMoveOnEdgeWontValidate(){
        assert(!board.checkMoveValid(7, 7, 1));
    }
    
    @Test
    public void noNeighboringOpponentWontValidate(){
        assert(!board.checkMoveValid(2, 5, 1));
    }
    
    @Test
    public void rightOpeningMove(){
        assert(board.checkMoveValid(2, 3, 1));
    }
    
    @Test
    public void noOwnDiscBehindOpponentWontValidate(){
        board.editBoard(4,3,0);
        assert(!board.checkMoveValid(2, 3, 1));
    }
    
    @Test
    public void moveAddingOneDiscFlips(){
        board.addMove(2, 3, 1);
        assertEquals(1, board.getBoard()[2][3]);
        assertEquals(1, board.getBoard()[3][3]);
    }
    
    @Test
    public void differentBoardObjectsWithSameBoardEqual(){
        Board otherBoard = new Board();
        assert(otherBoard.equals(board));
    }
    
    @Test
    public void boardsWithDifferentBoardArraysDontEqual(){
        Board otherBoard = new Board();
        otherBoard.editBoard(4, 3, 5);
        assert(!otherBoard.equals(board));
    }
    
    @Test
    public void moveAddingMultipleDiscsInRowFlip(){
        int[][] init = {
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,2,0,0,0},
            {0,0,0,1,2,0,0,0},
            {0,0,0,2,2,0,0,0},
            {0,0,0,0,1,0,0,0},
            {0,0,0,0,0,0,0,0}
        };
        Board initialBoard = new Board(init);
        initialBoard.addMove(2, 4, 1);
        
        int[][] expected = {
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,1,0,0,0},
            {0,0,0,0,1,0,0,0},
            {0,0,0,1,1,0,0,0},
            {0,0,0,2,1,0,0,0},
            {0,0,0,0,1,0,0,0},
            {0,0,0,0,0,0,0,0}};
        Board expectedBoard = new Board(expected);    
        assert(initialBoard.equals(expectedBoard));
}
    
    @Test
    public void moveAddingDiscsFlipInMultipleDirections(){
        int[][] init = {{0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,2,0,2,0,0,0},
            {0,0,0,1,1,0,0,0},
            {0,0,0,0,0,1,1,2},
            {0,0,0,2,1,0,0,0},
            {0,0,0,0,1,0,0,0},
            {0,0,0,0,0,0,0,0}
        };
        Board initialBoard = new Board(init);
        initialBoard.addMove(4, 4, 2);
        
        int[][] expected = {
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,2,0,2,0,0,0},
            {0,0,0,2,2,0,0,0},
            {0,0,0,0,2,2,2,2},
            {0,0,0,2,1,0,0,0},
            {0,0,0,0,1,0,0,0},
            {0,0,0,0,0,0,0,0}
        };
        Board expectedBoard = new Board(expected);
        assert(initialBoard.equals(expectedBoard));
    }
}