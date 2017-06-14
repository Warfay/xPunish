package me.muffin.xpunish;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.muffin.xpunish.Commands.Ban;
import me.muffin.xpunish.Commands.Commands;
import me.muffin.xpunish.Commands.GUICommand;
import me.muffin.xpunish.Commands.History;
import me.muffin.xpunish.Commands.Kick;
import me.muffin.xpunish.Commands.Report;
import me.muffin.xpunish.Commands.Tempban;
import me.muffin.xpunish.Commands.Unban;
import me.muffin.xpunish.Commands.Warn;
import me.muffin.xpunish.GUI.GUI;
import me.muffin.xpunish.GUI.ReportGUI;
import me.muffin.xpunish.Miscs.BanListener;

public class xPunish extends JavaPlugin {

	public void onEnable() {

		startUp();

	}

	public void onDisable() {

	}
	
	public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		
		try {
			
			ymlConfig.save(ymlFile);
		
		} catch (IOException e) {
			
		e.printStackTrace();
		
		}
		
	}
	
	@SuppressWarnings("unused")
	public void startUp() {
		
		GUICommand gui = new GUICommand();
		getLogger().info("xPunish has been enabled!");
		getCommand("xpunish").setExecutor(new Commands(this));
		getCommand("kick").setExecutor(new Kick(this));
		getCommand("ban").setExecutor(new Ban(this));
		getCommand("warn").setExecutor(new Warn());
		getCommand("tempban").setExecutor(new Tempban());
		getCommand("unban").setExecutor(new Unban(this));
		getCommand("punish").setExecutor(gui);
		getCommand("pu").setExecutor(gui);
		getCommand("punishgui").setExecutor(gui);
		getCommand("history").setExecutor(new History());
		getCommand("report").setExecutor(new Report());
		Bukkit.getPluginManager().registerEvents(new BanListener(this), this);
		Bukkit.getPluginManager().registerEvents(new GUI(), this);
		Bukkit.getPluginManager().registerEvents(new ReportGUI(), this);
		registerConfig();
		messagesFile();
		FileConfiguration messages = YamlConfiguration.loadConfiguration(new File(getDataFolder() + "messages.yml"));
		
	}
	
	private String getCurrentTime() {
		
		long ms = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date(ms);
		String result = sdf.format(date);
		
		return result;
		
	}
	
	private void messagesFile() {
		
		File msg = new File(getDataFolder() + File.separator + "messages.yml");
		
		if (!msg.exists()) {
			
			try {
				
				msg.createNewFile();
				
			} catch (IOException ex) {
				
				log("ERROR: Failed to create messages.yml file!", this);
				ex.printStackTrace();
				
			}
			
			FileConfiguration msgfc = YamlConfiguration.loadConfiguration(msg);
			
			/*Default values*/
			
			//General
			msgfc.set("Messages.Prefix", "&8[&cxPunish&8]");
			msgfc.set("Messages.NoPerm", "&cYou are not permitted to this command!");
			//Syntax errors
			msgfc.set("Messages.Syntax.KickSyntax", "&cInvalid syntax!\n&8[&cxPunish&8] &7/kick <player> [reason]");
			msgfc.set("Messages.Syntax.PermaBanSyntax", "&cInvalid syntax!\n&8[&cxPunish&8] &7/ban <player> [reason]");
			msgfc.set("Messages.Syntax.TempBanSyntax", "&cInvalid syntax!\n&8[&cxPunish&8] &7/tempban <player> <time> <unit> [reason]");
			//Errors
			msgfc.set("Messages.Errors.PlayerNotFound", "&6Could not find player &c%player%&6!");
			msgfc.set("Messages.Errors.AlreadyBanned", "&6That player is already banned!");
			//Punish messages
			msgfc.set("Messages.Punishment.PermaBanWithoutReason", "&6Player &c%player% &6has been &4&lPermanently Banned &6by &c%sender%&6!");
			msgfc.set("Messages.Punishment.PermaBanWithReason", "&6Player &c%player% &6has been &4&lPermanently Banned &6due to &c%reason% &6by &c%sender%&6!");
			msgfc.set("Messages.Punishment.TempBanWithoutReason", "&6Player &c%player% &6has been &4&lTemporarily Banned &6by &c%sender%&6!");
			msgfc.set("Messages.Punishment.TempBanWithReason", "&6Player &c%player% &6has been &4&lTemporarily Banned &6due to &c%reason% &6by &c%sender%&6!");
			msgfc.set("Messages.Punishment.KickWithoutReason", "&6Player &c%player% &6has been &4&lKicked &6by &c%sender%&6!");
			msgfc.set("Messages.Punishment.KickWithReason", "&6Player &c%player% &6has been &4&lKicked &6due to &c%reason% &6by &c%sender%&6!");
			//Punish screen
			msgfc.set("Messages.PunishmentScreen.KickWithoutReason", "&4&lYou have been kicked!\n\n&6Reason: &bNo reason specified\n&6Operator: &c%sender%\n\n%prefix%");
			msgfc.set("Messages.PunishmentScreen.KickWithReason", "&4&lYou have been kicked!\n\n&6Reason: &c%reason%\n&6Operator: &c%sender%\n\n%prefix%");
			msgfc.set("Messages.PunishmentScreen.PermaBanWithoutReason", arg1);
			
			try {
				
				saveCustomYml(msgfc, msg);
				
			} catch (Exception exception) {
				
				log("[ERROR] " + getCurrentTime() + ": Failed to save messages.yml file!", this);
				exception.printStackTrace(); 
			}
			
			log("[INFO] " + getCurrentTime() + ": Successfully created messages.yml file!", this);
			
		}
		
	}
	
	private void registerConfig() {
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		log("[INFO] " + getCurrentTime() + ": Configuration file has been registered!", this);
		
	}
	
	public static void log(String message, xPunish main) {

		main.getLogger().info("[xPunish] " + message);
		
        try {
        	
            File file = new File(main.getDataFolder() + File.separator + "Database" + File.separator + "_log.txt");
            
            if (!file.exists()) {
            	
                file.createNewFile();
                
            }
 
 
            FileWriter fw = new FileWriter(file, true);
 
            PrintWriter pw = new PrintWriter(fw);
 
            pw.println("[LOG] " + message);
            pw.flush();
            pw.close();
 
        } catch (IOException e) {
 
            e.printStackTrace();
 
        }
 
    }
	
}