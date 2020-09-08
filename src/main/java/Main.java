
import io.github.vuolen.othello.api.OthelloBot;
import static io.github.vuolen.othello.api.Tile.BLACK;
import static io.github.vuolen.othello.api.Tile.WHITE;
import io.github.vuolen.othello.domain.Board;
import io.github.vuolen.othello.bots.OthelloHuman;
import io.github.vuolen.othello.ui.UI;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author riikoro
 */

public class Main {
    
    public static void main(String[] args){
        if (args.length == 0) {
            UI.battle(new OthelloHuman(), new OthelloHuman());
            //System.out.println("USAGE: java -jar pathto.jar BotClass1 [BotClass2]");
        } else if (args.length == 1) {
            try {
                Class botClass = Class.forName("io.github.vuolen.othello.bots." + args[0]);
                OthelloBot bot;
                try {
                    bot = (OthelloBot) botClass.getDeclaredConstructor().newInstance();
                    UI.battle(new OthelloHuman(), bot);
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
}
