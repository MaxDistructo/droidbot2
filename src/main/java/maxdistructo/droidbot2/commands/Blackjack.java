package maxdistructo.droidbot2.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import maxdistructo.droidbot2.background.Config;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;


public class Blackjack implements CommandExecutor {

    @Command(aliases = {"/blackjack", "/bj"}, description = "Blackjack Game", usage = "/blackjack|bj <bet>")
    public String onBlackjackCommand(int[] args, Message message) {
        User author = message.getAuthor();
        long authorIdLong = author.getIdLong();
        Config.readCasino(authorIdLong);
        boolean end = true;
        boolean initialDraw = true;
        int[] cards = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        int scoreDealer = 0;
        int scorePlayer = 0;
        String blackjackProgress
        if(Config.PLAYER == 0){
            return author + "Please join the casino by using /casino to play BlackJack";
        }
        else if (args.length == 0) {
            return "You must specify a bet. Please enter /blackjack|bj <bet> to play.";
        } 
        else if(args.length == 1 && args[0].equals("hit")){
            return blackjackProgress;
        }
        else if(args.length == 1 && )
        else{
            Config.CHIPS = Config.CHIPS - args[0];
            double win = args[0] + args[0] + Config.CHIPS;

            if (args[0] < 50 || args[0] > 500) {
                Config.CHIPS += args[0];
                return "You have entered an invalid bet. Please enter a valid bet between 50 and 500";
            } 
            else {
                
            }

            }
        return "DroidBot has errored. Please report this issue to @MaxDistructo.";
    }
}
