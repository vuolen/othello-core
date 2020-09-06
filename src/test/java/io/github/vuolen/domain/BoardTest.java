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

    @Test
    public void toStringFormatting() {
        String[] rows = board.toString().split("\n");
        assertEquals("  a b c d e f g h", rows[0]);
        assertEquals("4| | | |○|●| | | |", rows[4]);
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
    
    /* @Test
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
