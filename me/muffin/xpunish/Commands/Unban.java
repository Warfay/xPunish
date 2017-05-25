package me.muffin.xpunish.Commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.muffin.xpunish.xPunish;

public class Unban implements CommandExecutor {

	@SuppressWarnings("unused")
	private xPunish main;
	public Unban(xPunish main) {
		this.main = main;
	}
	
	public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		
		try {
			
			ymlConfig.save(ymlFile);
		
		} catch (IOException e) {
			
		e.printStackTrace();
		
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		
		if (cmd.getName().equalsIgnoreCase("unban")) {
			
			if (!(sender.hasPermission("xpunish.unban.use"))) {
				
				sender.sendMessage("§8[§cxPunish§8] §cYou are not permitted to this command!");
				
				return true;
				
			}
			
			int length = args.length;
			
			if (length == 0) {
				
				sender.sendMessage("§8[§cxPunish§8] §cInvalid syntax!\n§8[§cxPunish§8] §7/unban <player>");
				
				return true;
				
			} else if (length == 1) {
				
				String target = args[0];
				FileConfiguration database = YamlConfiguration.loadConfiguration(new File("Database.yml"));
				
				boolean banned = database.getBoolean("Database." + target + ".Ban.Banned");
				
				if (banned != true) {
					
					sender.sendMessage("§8[§cxPunish§8] §6Player §c" + target + " §6is not banned!");
					
					return true;
					
				} else if (banned == true) {
					
					database.set("Database." + target + ".Ban.Banned", false);
					database.set("Database." + target + ".Ban.Type", null);
					database.set("Database." + target + ".Ban.Reason", null);
					database.set("Database." + target + ".Ban.Sender", null);
					
					this.saveCustomYml(database, new File("Database.yml"));
					
					sender.sendMessage("§8[§cxPunish§8] §6Player §c" + target + " §6has been unbanned!");
					
					return true;
					
				}
				
			} else if (length > 1) {
				
				sender.sendMessage("§8[§cxPunish§8] §cInvalid syntax!\n§8[§cxPunish§8] §7/unban <player>");
				
				return true;
				
			}
			
		}
		return false;
		
	}
	
}
