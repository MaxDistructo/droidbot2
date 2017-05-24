package maxdistructo.droidbot2.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;

import java.util.List;

import sx.blah.discord.handle.impl.obj.Message;
import sx.blah.discord.handle.obj.*;

public class Debug implements CommandExecutor {
    @Command(aliases = {"/debug"}, description = "Shows debug info for making code for the bot.", usage = "/debug")
    public String onDebugCommand(String[] args, Message message) {
            IUser author = message.getAuthor();
            IChannel channel = message.getChannel();
            IGuild guild = message.getGuild();
            IUser owner = guild.getOwner();
            List<IRole> roles = guild.getRoles();



            return "You are: " + author + ". \n" + "Your channel is: " + channel + ". \n" +  "Your Owner is: " + owner + ". \n" + "Your server's roles are: " + roles;


        }
}
