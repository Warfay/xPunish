package me.muffin.xpunish.Commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Warn implements CommandExecutor {

	public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		
		try {
			
			ymlConfig.save(ymlFile);
		
		} catch (IOException e) {
			
		e.printStackTrace();
		
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		
		if (cmd.getName().equalsIgnoreCase("warn")) {
			
			if (!sender.hasPermission("xpunish.warn")) {
				
				sender.sendMessage("§8[§cxPunish§8] §cYou are not permitted to this command!");
				
				return true;
				
			} else {
				
				int length = args.length;
				
				if (length == 0) {
					
					sender.sendMessage("§8[§cxPunish§8] §cInvalid syntax!\n§8[§cxPunish§8] §7/warn <player> [reason]");
					
					return true;
					
				} else if (length == 1) {
					
					File db = new File("Database.yml");
					Player target = Bukkit.getServer().getPlayer(args[0]);
					FileConfiguration database = YamlConfiguration.loadConfiguration(db);
					
					if (target == null) {
						
						sender.sendMessage("§8[§cxPunish§8] §6You can only warn online players!");
						
						return true;
						
					} else {
						
						int warns = database.getInt("Database." + target.getName() + ".History.Warns");
						
						database.set("Database." + target.getName() + ".History.Warns", warns + 1);
						saveCustomYml(database, db);
						
						Bukkit.broadcast("§8[§cxPunish§8] §6Player §c" + target.getName() + " §6has been §4§lWarned §6by §c" + sender.getName() + "§6!", "xpunish.notify");
					
						return true;
					
					}
					
				} else if (length > 1) {
					
					File db = new File("Database.yml");
					Player target = Bukkit.getServer().getPlayer(args[0]);
					FileConfiguration database = YamlConfiguration.loadConfiguration(db);
					String reason = args[1];
					int i = 0;
					
					for (i=2;i<length;i++) {
						
						reason = reason + " " + args[i];
						
					}
					
					int warns = database.getInt("Database." + target.getName() + ".History.Warns.Count");
					
					database.set("Database." + target.getName() + ".History.Warns.Count", warns + 1);
					database.set("Database." + target.getName() + ".History.Warns.Reason", reason);
					saveCustomYml(database, db);
					
					Bukkit.broadcast("§8[§cxPunish§8] §6Player §c" + target.getName() + " §6has been §4§lWarned §6due to §c" + reason + " §6by §c" + sender.getName() + "§6!", "xpunish.notify");
					
					return true;
					
				}
				
			}
			
		}
		
		return false;
		
	}
	
}
