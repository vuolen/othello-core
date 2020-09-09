package io.github.vuolen.domain;

import static io.github.vuolen.othello.api.Tile.*;
import io.github.vuolen.othello.domain.Board;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author riikoro
 */
public class BoardTest {

    private Board board;

    public BoardTest() {
    }
    
    /*
    for debug
    public String boardToString(int[][] boardToPrint) {
        String b = "  a b c d e f g h\n";
        for (int i = 0; i < boardToPrint.length; i++) {
            b += i + 1;
            for (int j = 0; j < boardToPrint[0].length; j++) {
                b += "|";
                if (boardToPrint[i][j] == 0) {
                    b += " ";
                } else if (boardToPrint[i][j] == 1) {
                    b += "○";
                } else {
                    b += "●";
                }
            }
            b += "|\n";
        }
        return b;
    }

    /*@BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
     */
    @Before
    public void setUp() {
        this.board = new Board();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void initialStateCorrect() {
        assertEquals(board.getTile(2, 3), EMPTY);
        assertEquals(board.getTile(3, 3), WHITE);
        assertEquals(board.getTile(4, 3), BLACK);
    }

    
    private boolean isBoardChanged(Board board1, Board board2) {
        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                if (board1.getTile(row, col) != board2.getTile(row, col)) {
                    return true;
                }
            }
        }  
        return false;
    }

    @Test
    public void openingMoveOnEdgeWontChangeBoard() {
        board.addMove(0, 0, WHITE);
        assertFalse(isBoardChanged(board, new Board()));
    }
    
    @Test
    public void openingMoveOnEdgeReturnsFalse() {
        assertFalse(board.addMove(0, 0, WHITE));
    }

    
    @Test
    public void noNeighboringOpponentWontChangeBoard() {
        board.addMove(2, 5, WHITE);
        assertFalse(isBoardChanged(board, new Board()));
    }
    
    @Test
    public void noNeighboringOpponentReturnsFalse() {
        assertFalse(board.addMove(2, 5, WHITE));
    }

    @Test
    public void rightOpeningMoveReturnsTrue() {
        assertTrue(board.addMove(3, 2, BLACK));
    }
   
    @Test
    public void takenTileMoveReturnsFalse() {
        board.addMove(3, 2, BLACK);
        assertFalse(board.addMove(3, 2, BLACK));
    }
    
    @Test
    public void outOfBoundsMoveReturnsFalse() {
        assertFalse(board.addMove(-1, -1, BLACK));
    }
    
    @Test
    public void addingMoveFlipsCorrectPieces(){
        int[][] initial = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 1, 0},
            {0, 0, 0, 2, 0, 2, 0, 0},
            {0, 0, 0, 2, 2, 0, 0, 0},
            {0, 0, 1, 0, 2, 2, 1, 0},
            {0, 0, 2, 2, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };
        this.board = new Board(initial);
        board.addMove(4, 3, BLACK);
        
        int[][] expected = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 0},
            {0, 0, 2, 2, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };
        Board expectedBoard = new Board(expected);
        assert(board.equals(expectedBoard));
    }
    
    @Test
    public void simpleMoveIsGameOverDoesNotReturnError() {
        // This produced an ArrayIndexOutOfBounds error
        board.addMove(3, 2, BLACK);
        board.addMove(3, 1, WHITE);
        board.addMove(3, 0, BLACK);
        assertFalse(board.isGameOver());
    }
    
    @Test
    public void onePossibleMoveLeftGameNotOver() {
        
    }
    
    @Test
    public void noPossibleMovesGameOver() {
        
    }
    
    @Test
    public void onlyOnePlayerHasMovesLeftHasMoveLeftRecognizes(){
        //bug case from before, problem in ui
        int[][] initial = {
            {2, 2, 2, 0, 0, 0, 0, 0},
            {2, 2, 1, 0, 0, 0, 0, 0},
            {2, 1, 1, 1, 0, 0, 0, 0},
            {2, 1, 1, 1, 1, 0, 0, 0},
            {2, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };
        Board initialBoard = new Board(initial);
        assert(initialBoard.hasValidMovesLeft(WHITE));
        assert(!initialBoard.hasValidMovesLeft(BLACK));
    }
    
    @Test
    public void theOneWithMorePiecesAtEndOfGameWins(){
        
    }
    
    /*@Test
    public void rightOpeningMoveChangesBoard() {
        board.addMove(3, 2, BLACK);
        assertEquals(board.getTile(3, 3), BLACK);
    } */

    /* @Test
    public void moveAdding(){
        board.addMove(2, 3, 1);
        assertEquals(1, board.getBoard()[2][3]);
        assertEquals(1, board.getBoard()[3][3]);
    } */
}
