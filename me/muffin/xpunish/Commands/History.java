package me.muffin.xpunish.Commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class History implements CommandExecutor {

	public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		
		try {
			
			ymlConfig.save(ymlFile);
		
		} catch (IOException e) {
			
		e.printStackTrace();
		
		}
		
	}
	
	@SuppressWarnings("unused")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		
		if (cmd.getName().equalsIgnoreCase("history")) {
			
			if (!sender.hasPermission("xpunish.history")) {
				
				sender.sendMessage("§8[§cxPunish§8] §cYou're not permitted to this command!");
				
				return true;
				
			} else {
				
				int length = args.length;
				
				if (length == 0) {
					
					sender.sendMessage("§8[§cxPunish§8] §cInvalid syntax!\n§8[§cxPunish§8] §7/history <player> [clear]");
					
					return true;
					
				} else if (length == 1) {
					
					FileConfiguration database = YamlConfiguration.loadConfiguration(new File("Database.yml"));
					int bans = database.getInt("Database." + args[0] + ".History.Bans.Count");
					int warns = database.getInt("Database." + args[0] + ".History.Warns.Count");
					int tempbans = database.getInt("Database." + args[0] + ".History.Tempbans.Count");
					int kicks = database.getInt("Database." + args[0] + ".History.Kicks.Count");
					
					sender.sendMessage("§7@--------------------History--------------------@\n    §6§lBans: §c" + bans + "\n    §6§lWarns: §c" + warns + "\n    §c/history " + args[0] + " clear §a>> Clear " + args[0] + "'s history\n§7@--------------------History--------------------@");
					
					return true;
					
				} else if (length == 2) {
					
					if (args[1].equalsIgnoreCase("clear")) {
						
						FileConfiguration database = YamlConfiguration.loadConfiguration(new File("Database.yml"));
						database.set("Database." + args[0] + ".History", null);
						saveCustomYml(database, new File("Database.yml"));
						
						sender.sendMessage("§8[§cxPunish§8] §6History of §c" + args[0] + " §6has been cleared!");
						
						return true;
						
					} else {
						
						sender.sendMessage("§8[§cxPunish§8] §6Did you mean /history <player> clear?");
						
						return true;
						
					}
					
				} else {
					
					sender.sendMessage("§8[§cxPunish§8] §cInvalid syntax!\n§8[§cxPunish§8] §7/history <player> [clear]");
					
					return true;
					
				}
				
			}
			
		}
		
		return false;
		
	}
	
}
