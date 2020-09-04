/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.vuolen.othello.ui;

import java.util.regex.Pattern;

/**
 *
 * @author Lennu Vuolanne <vuolanne.lennu@gmail.com>
 */
public class CLI {

    public boolean isMoveStringValid(String move) {
        return Pattern.matches("[a-h]{1}[1-8]{1}", move);
    }

    public int[] convertStringToCoordinates(String moveStr) {
        //a=10
        int[] coordinates = new int[2];
        coordinates[0] = Character.getNumericValue(moveStr.charAt(1)) - 1;
        coordinates[1] = Character.getNumericValue(moveStr.charAt(0)) - 10;
        return coordinates;
    }
}
