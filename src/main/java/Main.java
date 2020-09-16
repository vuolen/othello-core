
import io.github.vuolen.othello.api.OthelloBot;
import io.github.vuolen.othello.bots.OthelloHuman;
import io.github.vuolen.othello.ui.UI;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author riikoro
 */

public class Main {
    
    public static void main(String[] args){
        if (args.length == 0) {
            UI.battle(new OthelloHuman(), new OthelloHuman(), true);
        } else if (args.length == 1) {
            OthelloBot bot = createBotFromClassName("io.github.vuolen.othello.bots." + args[0]);
            UI.battle(new OthelloHuman(), bot, true);
        } else if (args.length >= 2){
            OthelloBot bot1 = createBotFromClassName("io.github.vuolen.othello.bots." + args[0]);
            OthelloBot bot2 = createBotFromClassName("io.github.vuolen.othello.bots." + args[1]);
            
            if (bot1 == null || bot2 == null) {
                System.out.println("Failed to load bots");
                return;
            }
            
            if (args.length == 3) {
                int numberOfGames = Integer.parseInt(args[2]);
                UI.tournament(bot1, bot2, numberOfGames);
            } else {
                UI.battle(bot1, bot2, true);
            }

        } else {
            System.out.println("Invalid arguments; USAGE: java -jar pathto.jar BotClass1 [BotClass2] [NumberOfGames]");
        }
    }
    
    public static OthelloBot createBotFromClassName(String className) {
        try {
            Class botClass = Class.forName(className);
            OthelloBot bot = (OthelloBot) botClass.getDeclaredConstructor().newInstance();
            return bot;
        } catch (ClassNotFoundException ex) {
            System.out.println("CLASS " + className + " NOT FOUND");
            return null;
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException ex) {
            System.out.println("FAILED TO CREATE INSTANCE FROM BOT CLASS " + className);
            ex.printStackTrace(System.out);
            return null;
        }
    }
}
