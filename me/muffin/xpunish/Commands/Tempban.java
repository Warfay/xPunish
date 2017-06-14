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

import me.muffin.xpunish.Miscs.TimeUnits;

public class Tempban implements CommandExecutor {

	public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		
		try {
			
			ymlConfig.save(ymlFile);
		
		} catch (IOException e) {
			
		e.printStackTrace();
		
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		
		if (cmd.getName().equalsIgnoreCase("tempban")) {
			
			if (!sender.hasPermission("xpunish.tempban")) {
				
				sender.sendMessage("§8[§cxPunish§8] §cYou are not permitted to this command!");
				
				return true;
				
			}
			
			int length = args.length;
			
			if (length == 0) {
				
				sender.sendMessage("§8[§cxPunish§8] §cInvalid syntax!\n§8[§cxPunish§8] §7/tempban <player> <time> <unit> [reason]");
				
				return true;
				
			}
			
			if (length == 1) {
				
				sender.sendMessage("§8[§cxPunish§8] §cInvalid syntax!\n§8[§cxPunish§8] §7/tempban <player> <time> <unit> [reason]");
				
				return true;
				
			}
			
			if (length == 2) {
				
				sender.sendMessage("§8[§cxPunish§8] §cInvalid syntax!\n§8[§cxPunish§8] §7/tempban <player> <time> <unit> [reason]");
				
				return true;
				
			}
			
			if (length == 3) {
				
				Player target = Bukkit.getPlayer(args[0]);
				String target2 = args[0];
				
				long banEnd = System.currentTimeMillis() + TimeUnits.getTicks(args[2], Integer.parseInt(args[1]));
				
				long now = System.currentTimeMillis();
				long diff = banEnd - now;
				
				FileConfiguration database = YamlConfiguration.loadConfiguration(new File("Database.yml"));
				
				if (diff > 0) {
					
					if (target == null) {
						
						database.set("Database." + target2 + ".Ban.Banned", true);
						database.set("Database." + target2 + ".Ban.Type", 1);
						database.set("Database." + target2 + ".Ban.End", banEnd);
						database.set("Database." + target2 + ".Ban.Sender", sender.getName());
						saveCustomYml(database, new File("Database.yml"));
						Bukkit.broadcast("§8[§cxPunish§8] §6Player §c" + target2 + " §6has been §4§lTemporarily Banned §6by §c" + sender.getName(), "xpunish.notfify");
						
						return true;
						
					} else {
						
						database.set("Database." + target.getName() + ".Ban.Banned", true);
						database.set("Database." + target.getName() + ".Ban.Type", 1);
						database.set("Database." + target.getName() + ".Ban.End", banEnd);
						database.set("Database." + target.getName() + ".Ban.Sender", sender.getName());
						saveCustomYml(database, new File("Database.yml"));
						
						Bukkit.broadcast("§8[§cxPunish§8] §6Player §c" + target.getName() + " §6has been §4§lTemporarily Banned §6by §c" + sender.getName(), "xpunish.notfify");
						target.kickPlayer("§4§lYou have been Temporarily Banned!\n\n§6§lEnd: §c" + getMessage(banEnd) + "\n§6§lBy: §c" + sender.getName() + "\n\n§8[§cxPunish§8]");
						
						return true;
						
					}
					
				} else {
					
					sender.sendMessage("§8[§cxPunish§8] §cInvalid unit or time!");
					
					return true;
					
				}
				
			} else if (length > 3) {
				
				Player target = Bukkit.getPlayer(args[0]);
				String target2 = args[0];
				String reason = args[3];
				
				int i = 0;
				
				for (i=3;i<length;i++) {
					
					reason = reason + " " + args[i];
					
				}
				
				long banEnd = System.currentTimeMillis() + TimeUnits.getTicks(args[2], Integer.parseInt(args[1]));
				
				long now = System.currentTimeMillis();
				long diff = banEnd - now;
				
				FileConfiguration database = YamlConfiguration.loadConfiguration(new File("Database.yml"));
				
				if (diff > 0) {
				
					if (target == null) {
					
						database.set("Database." + target2 + ".Ban.Banned", true);
						database.set("Database." + target2 + ".Ban.Type", 1);
						database.set("Database." + target2 + ".Ban.End", banEnd);
						database.set("Database." + target2 + ".Ban.Reason", reason);
						database.set("Database." + target2 + ".Ban.Sender", sender.getName());
						saveCustomYml(database, new File("Database.yml"));
						
						Bukkit.broadcast("§8[§cxPunish§8] §6Player §c" + target2 + " §6has been §4§lTemporarily Banned §6due to §c" + reason + " §6by §c" + sender.getName(), "xpunish.notfify");
					
						return true;
						
					} else {
					
						database.set("Database." + target.getName() + ".Ban.Banned", true);
						database.set("Database." + target.getName() + ".Ban.Type", 1);
						database.set("Database." + target.getName() + ".Ban.End", banEnd);
						database.set("Database." + target.getName() + ".Ban.Reason", reason);
						database.set("Database." + target.getName() + ".Ban.Sender", sender.getName());
						saveCustomYml(database, new File("Database.yml"));
						
						Bukkit.broadcast("§8[§cxPunish§8] §6Player §c" + target.getName() + " §6has been §4§lTemporarily Banned §6due to §c" + reason + " §6by §c" + sender.getName(), "xpunish.notfify");
						target.kickPlayer("§4§lYou have been Temporarily Banned!\n\n§6§lEnd: §c" + getMessage(banEnd) + "\n§6§lReason: §c" + reason + "\n§6§lBy: §c" + sender.getName() + "\n\n§8[§cxPunish§8]");
					
						return true;
						
					}
				
				} else {
					
					sender.sendMessage("§8[§cxPunish§8] §cInvalid unit or time!");
					
					return true;
					
				}
					
			}
			
		}
		
		return false;
		
	}
	
	public static String getMessage(long banEnd) {
		
		String message = "";
		
		long now = System.currentTimeMillis();
		long diff = banEnd - now;
		
		int seconds = (int) (diff / 1000);
		
		if (seconds >= 60*60*24) {
			
			int days = seconds / (60*60*24);
			seconds = seconds % (60*60*24);
			
			message += days + " Day(s) ";
			
		}
		
		if (seconds >= 60*60) {
			
			int hours = seconds / (60*60);
			seconds = seconds % (60*60);
			
			message += hours + " Hour(s) ";
			
		}
		
		if (seconds >= 60) {
			
			int min = seconds / 60;
			seconds = seconds % 60;
			
			message += min + " Minute(s) ";
			
		}	
		
		if (seconds >= 0) {
			
			message += seconds + " Second(s) ";
			
		}	
		
		return message;
		
	}
	
}
