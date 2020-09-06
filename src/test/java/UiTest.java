/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.*;
import static org.junit.Assert.*;
import ui.Ui;

/**
 *
 * @author riikoro
 */
public class UiTest {
    
    @Test
    public void inputStringChecker(){
        assert(!Ui.InputFormatOk(":D"));
        assert(!Ui.InputFormatOk("dd22"));
        assert(!Ui.InputFormatOk("p9"));
        assert(Ui.InputFormatOk("h1"));
    }
    
    @Test
    public void stringToCoordinateConversion(){
        assertEquals(6, Ui.parseInputToCoordinates("a7")[0]);
        assertEquals(2, Ui.parseInputToCoordinates("c1")[1]);
    }
}
