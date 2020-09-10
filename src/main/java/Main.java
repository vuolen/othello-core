
import io.github.vuolen.othello.api.OthelloBot;
import io.github.vuolen.othello.bots.OthelloHuman;
import io.github.vuolen.othello.ui.UI;

/**
 *
 * @author riikoro
 */

public class Main {
    
    public static void main(String[] args){
        if (args.length == 0) {
            UI.battle(new OthelloHuman(), new OthelloHuman());
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
        } else if (args.length == 2){
            try {
                //should have better exception handling?
                Class botClass1 = Class.forName("io.github.vuolen.othello.bots." + args[0]);
                OthelloBot bot1;
                
                Class botClass2 = Class.forName("io.github.vuolen.othello.bots." + args[0]);
                OthelloBot bot2;
                try {
                    bot1 = (OthelloBot) botClass1.getDeclaredConstructor().newInstance();
                    bot2 = (OthelloBot) botClass2.getDeclaredConstructor().newInstance();

                    UI.battle(bot1, bot2);
                } catch (Exception ex){
                    System.out.println("Error instantiating classes: ");
                    ex.printStackTrace();
                }
            } catch(ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("Too many arguments; USAGE: java -jar pathto.jar BotClass1 [BotClass2]");
        }
    }   
}
