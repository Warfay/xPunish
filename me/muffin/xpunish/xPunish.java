package me.muffin.xpunish;

import java.io.File;
import java.io.IOException;

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
	
	@SuppressWarnings("unused")
	private xPunish main;
	public xPunish(xPunish main) {
		this.main = main;
	}
	
	public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		
		try {
			
			ymlConfig.save(ymlFile);
		
		} catch (IOException e) {
			
		e.printStackTrace();
		
		}
		
	}
	
	public void onEnable() {
		
		startUp();
		
	}
	
	public void onDisable() {
		
		
		
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
		FileConfiguration database = YamlConfiguration.loadConfiguration(new File("Database.yml"));
		FileConfiguration players = YamlConfiguration.loadConfiguration(new File("Player.yml"));
		
	}
	
	private void messagesFile() {
		
		File msg = new File(getDataFolder() + "messages.yml");
		
		if (!msg.exists()) {
			
			try {
				
				msg.createNewFile();
				
			} catch (IOException ex) {
				
				log("ERROR: Failed to create messages.yml file!");
				ex.printStackTrace();
				
			}
			
			FileConfiguration msgfc = YamlConfiguration.loadConfiguration(msg);
			
			//Default values
			msgfc.addDefault("Messages.Prefix", "&8[&cxPunish&8]");
			msgfc.addDefault("Messages.NoPerm", "&cYou are not permitted to this command!");
			
			try {
				
				msgfc.save(msg);
				
			} catch (IOException exception) {
				
				log("ERROR: Failed to save messages.yml file!");
				exception.printStackTrace();
				
			}
			
			log("Successfully created messages.yml file!");
			
		}
		
	}
	
	private void registerConfig() {
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		log("Configuration file has been registered!");
		
	}
	
	public static void log(String message) {
		
		System.out.println("[xPunish] " + message);
		
	}
	
}
