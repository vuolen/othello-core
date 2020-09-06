/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import domain.Board;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author riikoro
 */
public class Ui {
    public static void launchGame(){
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        int turnInt = 1;
        String turnStr = "black";
        while(true){
            System.out.println(board);
            System.out.println("Turn: "+turnStr);
            System.out.print("Insert move (format: a1): ");
            String move = scanner.nextLine();
            
            if(InputFormatOk(move)){
                int[] coordinates = parseInputToCoordinates(move);
                int x = coordinates[0];
                int y = coordinates[1];
                if(board.checkMoveValid(x, y, turnInt)){
                    board.addMove(x, y, turnInt);
                    turnInt = turnInt == 1 ? 2 : 1;
                    turnStr = turnStr.equals("black") ? "white" : "black";
                    if(board.checkWin(turnInt)){
                        System.out.println("Game over!!");
                        String winner = board.winner() == 1 ? "black" : "white";
                        System.out.println("Winner: " + winner);
                    }
                    continue;
                } else {
                    System.out.println("Invalid move");
                    continue;
                }
            } else {
                System.out.println("Invalid move");
                continue;
            }  
        }   
    }
    
    public static boolean InputFormatOk(String input){
        return Pattern.matches("[a-h]{1}[1-8]{1}", input);
    }
    
    public static int[] parseInputToCoordinates(String input){
        //a=10
        int[] coordinates = new int[2];
        coordinates[0] = Character.getNumericValue(input.charAt(1)) - 1;
        coordinates[1] = Character.getNumericValue(input.charAt(0)) - 10;
        return coordinates;
    }
}
