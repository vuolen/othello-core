package io.github.vuolen.domain;

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
        assertEquals(board.getBoard()[2][3], 0);
        assertEquals(board.getBoard()[3][3], 2);
        assertEquals(board.getBoard()[4][3], 1);
    }

    @Test
    public void toStringFormatting() {
        String[] rows = board.toString().split("\n");
        assertEquals("  a b c d e f g h", rows[0]);
        assertEquals("4| | | |●|○| | | |", rows[4]);
    }

    @Test
    public void inputStringChecker() {
        assert (!board.isMoveStringValid(":D"));
        assert (!board.isMoveStringValid("dd22"));
        assert (!board.isMoveStringValid("p9"));
        assert (board.isMoveStringValid("h1"));
    }

    @Test
    public void stringToCoordinateConversion() {
        assertEquals(6, board.convertStringToCoordinates("a7")[0]);
        assertEquals(2, board.convertStringToCoordinates("c1")[1]);
    }

    @Test
    public void openingMoveOnEdgeWontValidate() {
        assert (!board.isMoveValid("h8", 1));
    }

    @Test
    public void noNeighboringOpponentWontValidate() {
        assert (!board.isMoveValid("f3", 1));
    }

    @Test
    public void rightOpeningMove() {
        assert (board.isMoveValid("d3", 1));
    }

    /* @Test
    public void moveAdding(){
        board.addMove(2, 3, 1);
        assertEquals(1, board.getBoard()[2][3]);
        assertEquals(1, board.getBoard()[3][3]);
    } */
}
