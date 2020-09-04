/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {}
    
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
    public void inputStringChecker(){
        assert(!board.checkMoveString(":D"));
        assert(!board.checkMoveString("dd22"));
        assert(!board.checkMoveString("p9"));
        assert(board.checkMoveString("h1"));
    }
    
    @Test
    public void stringToCoordinateConversion(){
        assertEquals(6, board.convertStringToCoordinates("a7")[0]);
        assertEquals(2, board.convertStringToCoordinates("c1")[1]);
    }
    
    @Test
    public void openingMoveOnEdgeWontValidate(){
        assert(!board.checkMoveValid("h8", 1));
    }
    
    @Test
    public void noNeighboringOpponentWontValidate(){
        assert(!board.checkMoveValid("f3", 1));
    }
    
    @Test
    public void rightOpeningMove(){
        assert(board.checkMoveValid("d3", 1));
    }
    
    @Test
    public void noOwnDiscBehindOpponentWontValidate(){
        board.removeBottomLeftBlack();
        assert(!board.checkMoveValid("d3", 1));
    }
    
    /* @Test
    public void moveAdding(){
        board.addMove(2, 3, 1);
        assertEquals(1, board.getBoard()[2][3]);
        assertEquals(1, board.getBoard()[3][3]);
    } */
}



