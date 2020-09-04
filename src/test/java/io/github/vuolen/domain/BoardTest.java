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
        assertEquals(board.getTile(2, 3), 0);
        assertEquals(board.getTile(3, 3), 2);
        assertEquals(board.getTile(4, 3), 1);
    }

    @Test
    public void toStringFormatting() {
        String[] rows = board.toString().split("\n");
        assertEquals("  a b c d e f g h", rows[0]);
        assertEquals("4| | | |●|○| | | |", rows[4]);
    }
    
    private boolean isBoardChanged(Board board1, Board board2) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
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
        board.addMove(0, 0, WHITE);
        assertFalse(board.addMove(0, 0, WHITE));
    }

    /*
    @Test
    public void noNeighboringOpponentWontChangeBoard() {
        board.addMove(2, 5, WHITE);
        assertFalse(isBoardChanged(board, new Board()));
    }

    @Test
    public void rightOpeningMove() {
        assert (board.isMoveValid("d3", 1));
    } */

    /* @Test
    public void moveAdding(){
        board.addMove(2, 3, 1);
        assertEquals(1, board.getBoard()[2][3]);
        assertEquals(1, board.getBoard()[3][3]);
    } */
}
