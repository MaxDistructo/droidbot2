package maxdistructo.droidbot2.commands.casino;

import maxdistructo.droidbot2.BaseBot;
import maxdistructo.droidbot2.background.Listener;
import maxdistructo.droidbot2.background.message.Message;
import org.json.JSONObject;
import org.json.JSONTokener;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CasinoConfig {

    public static String PLAYER = "null";
    public static int CHIPS;
    public static String MEMBERSHIP;
    public static String[] trivia = new String[2];

    public static void newCasino(IMessage message){
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        IUser user = message.getAuthor();
        String stringUser = user.getName();
        File f = new File(s+"/droidbot/config/" + message.getGuild().getLongID() +"/casino/"+ user.getLongID() + ".txt");
        f.getParentFile().mkdirs(); //Create Directories for File
        try {
            f.createNewFile(); //Create file
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject newUser = new JSONObject();
        newUser.put("User",stringUser);
        newUser.put("Chips",100);
        newUser.put("Membership","null");

        try (FileWriter file = new FileWriter(s+"/droidbot/config/" + message.getGuild().getLongID() +"/casino/"+ user.getLongID() + ".txt")) {
            file.write(newUser.toString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + newUser);
        } catch (IOException e) {
            BaseBot.LOGGER.warn("Config.newCasino Error.");
            Message.sendDM(BaseBot.client.getApplicationOwner(), e.toString());
            e.printStackTrace();
        }

    }
    public static void newCasino(IUser user, IGuild guild){
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String stringUser = user.getName();
        File f = new File(s+"/droidbot/config/" + guild.getLongID() +"/casino/"+ user.getLongID() + ".txt");
        f.getParentFile().mkdirs(); //Create Directories for File
        try {
            f.createNewFile(); //Create file
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject newUser = new JSONObject();
        newUser.put("User",stringUser);
        newUser.put("Chips",100);
        newUser.put("Membership","null");

        try (FileWriter file = new FileWriter(s+"/droidbot/config/" + guild.getLongID() +"/casino/"+ user.getLongID() + ".txt")) {
            file.write(newUser.toString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + newUser);
        } catch (IOException e) {
            BaseBot.LOGGER.warn("Config.newCasino Error.");
            Message.sendDM(BaseBot.client.getApplicationOwner(), e.toString());
            e.printStackTrace();
        }

    }
    public static Object[] readCasino(IMessage message){
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        IUser user = message.getAuthor();
        String stringUser = user.getName();

        File file = new File (s+"/droidbot/config/" + message.getGuild().getLongID() +"/casino/"+ user.getLongID() + ".txt");
        if(file.exists()) {
            URI uri = file.toURI();
            JSONTokener tokener = null;
            try {
                tokener = new JSONTokener(uri.toURL().openStream());
                System.out.println("Successfully read file " + stringUser + ".txt");
            } catch (IOException e) {
                Message.sendDM(BaseBot.client.getApplicationOwner(), e.toString());
                e.printStackTrace();
            }
            JSONObject root = new JSONObject(tokener);
            System.out.println("Converted JSON file to JSONObject");
            Object[] casinoValues = {root.getString("User"), root.getInt("Chips"), root.getString("Membership")};
            System.out.println("Successfully read values from file.");
            return casinoValues;
        }
        else{
            Casino.onCasinoCommand(new Object[] {Listener.prefix + "casino", "join"},message,null);
        }
        return null;
    }

    public static void readCasino(IUser user, IGuild guild){
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String stringUser = user.getName();

        File file = new File (s+"/droidbot/config/" + guild.getLongID() +"/casino/"+ user.getLongID() + ".txt");
        if(file.exists()) {
            URI uri = file.toURI();
            JSONTokener tokener = null;
            try {
                tokener = new JSONTokener(uri.toURL().openStream());
                System.out.println("Successfully read file " + stringUser + ".txt");
            } catch (IOException e) {
                Message.sendDM(BaseBot.client.getApplicationOwner(), e.toString());
                e.printStackTrace();
            }
            JSONObject root = new JSONObject(tokener);
            System.out.println("Converted JSON file to JSONObject");
            PLAYER = root.getString("User");
            CHIPS = root.getInt("Chips");
            MEMBERSHIP = root.getString("Membership");
            System.out.println("Successfully read values from file.");
        }
        else{
            newCasino(user,guild);
            // Casino.onCasinoCommand(new Object[] {Listener.prefix + "casino", "join"}, message,null);
        }

    }
    public static void writeCasino(IMessage message){
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        IUser user = message.getAuthor();
        String stringUser = user.getName();

        File file = new File (s+"/droidbot/config/" + message.getGuild().getLongID() +"/casino/"+ user.getLongID() + ".txt");
        URI uri = file.toURI();
        JSONTokener tokener = null;
        try {
            tokener = new JSONTokener(uri.toURL().openStream());
            System.out.println("Successfully read file " + stringUser + ".txt");
        } catch (IOException e) {
            Message.sendDM(BaseBot.client.getApplicationOwner(), e.toString());
            e.printStackTrace();
        }
        JSONObject newUser = new JSONObject(tokener);
        newUser.put("User", PLAYER);
        newUser.put("Chips", CHIPS);
        newUser.put("Membership",MEMBERSHIP);

        try (FileWriter fileWriter = new FileWriter(s+"/droidbot/config/" + message.getGuild().getLongID() +"/casino/"+ user.getLongID() + ".txt")) {
            fileWriter.write(newUser.toString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + newUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeCasino(IUser user, IGuild guild){
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String stringUser = user.getName();

        File file = new File (s+"/droidbot/config/" + guild.getLongID() +"/casino/"+ user.getLongID() + ".txt");
        URI uri = file.toURI();
        JSONTokener tokener = null;
        try {
            tokener = new JSONTokener(uri.toURL().openStream());
            System.out.println("Successfully read file " + stringUser + ".txt");
        } catch (IOException e) {
            Message.sendDM(BaseBot.client.getApplicationOwner(), e.toString());
            e.printStackTrace();
        }
        JSONObject newUser = new JSONObject(tokener);
        newUser.put("User", PLAYER);
        newUser.put("Chips", CHIPS);
        newUser.put("Membership",MEMBERSHIP);

        try (FileWriter fileWriter = new FileWriter(s+"/droidbot/config/" + guild.getLongID() +"/casino/"+ user.getLongID() + ".txt")) {
            fileWriter.write(newUser.toString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + newUser);
        } catch (IOException e) {
            Message.sendDM(BaseBot.client.getApplicationOwner(), e.toString());
            e.printStackTrace();
        }
    }
    public static void resetBJ(IMessage message){
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        File file = new File(s + "/droidbot/config/" + message.getGuild().getLongID() + "/blackjack/" + message.getAuthor().getLongID());
        file.delete();
    }

}
