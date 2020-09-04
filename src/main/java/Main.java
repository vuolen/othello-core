/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author riikoro
 */
import java.util.Scanner;
public class Main {
    
    public static void main(String[] args){
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        int turnInt = 1;
        String turnStr = "black";
        while(true){
            System.out.println(board);
            System.out.println("Turn: "+turnStr);
            System.out.print("Insert move (format: a1): ");
            String move = scanner.nextLine();
            if(board.isMoveValid(move, turnInt)){
                System.out.println("cool is ok!!");
                break;
            }else{
                System.out.println("Invalid move");
                continue;
            }
        }
    }
    
}
