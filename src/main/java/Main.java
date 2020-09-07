
import io.github.vuolen.othello.api.OthelloBot;
import static io.github.vuolen.othello.api.Tile.BLACK;
import static io.github.vuolen.othello.api.Tile.WHITE;
import io.github.vuolen.othello.domain.Board;
import io.github.vuolen.othello.ui.OthelloHuman;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author riikoro
 */

public class Main {
    
    public static void main(String[] args){
        if (args.length == 0) {
            System.out.println("USAGE: java -jar pathto.jar BotClass1 [BotClass2]");
        } else if (args.length == 1) {
            try {
                Class botClass = Class.forName("io.github.vuolen.othello.bots." + args[0]);
                OthelloBot bot;
                try {
                    bot = (OthelloBot) botClass.getDeclaredConstructor().newInstance();
                    battle(new OthelloHuman(), bot);
                } catch (NoSuchMethodException ex) {
                    System.out.println("Invalid class " + args[0]);
                } catch (Exception ex) {
                    System.out.println("Could not instantiate class " + args[0]);
                    ex.printStackTrace();
                }
            } catch (ClassNotFoundException ex) {
                System.out.println("Invalid class name " + args[0]);
            }
        }
    }
    
    public static void battle(OthelloBot bot1, OthelloBot bot2) {
        Board board = new Board();
        int turn = 0;
        OthelloBot[] contestants = new OthelloBot[]{bot1, bot2};
        int[] colors = new int[]{BLACK, WHITE};
        bot1.startGame(colors[0]);
        bot2.startGame(colors[1]);
                
        while (!board.isGameOver()) {
            int[] move = contestants[turn].makeMove(board);
            if (!board.addMove(move[0], move[1], colors[turn])) {
                System.out.println("INVALID MOVE - DISQUALIFIED");
                break;
            }
            
            int opponent = turn == 0 ? 1 : 0;
            if (board.hasValidMovesLeft(colors[opponent])) {
                turn = opponent;
            }
        }
    }
    
}
