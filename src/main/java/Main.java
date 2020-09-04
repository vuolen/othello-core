/**
 *
 * @author riikoro
 */
import io.github.vuolen.othello.domain.Board;
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
            /*if(board.isMoveValid(move, turnInt)){
                System.out.println("cool is ok!!");
                break;
            }else{
                System.out.println("Invalid move");
                continue;
            }*/
        }
    }
    
}
