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

import me.muffin.xpunish.xPunish;

public class Ban implements CommandExecutor {

	private String reason = "";
	@SuppressWarnings("unused")
	private xPunish main;
	public Ban(xPunish main) {
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
		
		if (cmd.getName().equalsIgnoreCase("ban")) {
			
			if (!sender.hasPermission("xpunish.ban")) {
				
				sender.sendMessage("§8[§cxPunihs§8] §cYou are not permitted to this command!");
				
				return true;
				
			}
			
			int length = args.length;
			
			if (length == 0) {
				
				sender.sendMessage("§8[§cxPunish§8] §cInvalid syntax!\n§8[§cxPunish§8] §7/ban <player> [reason]");
				
				return true;
				
			} else if (length == 1) {

				FileConfiguration database = YamlConfiguration.loadConfiguration(new File("Database.yml"));
				FileConfiguration players = YamlConfiguration.loadConfiguration(new File("Players.yml"));
				
				Player target = Bukkit.getServer().getPlayer(args[0]);
				
				String target2 = args[0];
				
				if (target == null) {
					
					String uuid = database.getString("Database." + target2 + ".UUID");
					String uuid2 = players.getString("Players." + target2);
					int bans = database.getInt("Database." + target2 + ".History.Bans.Count");
					
					if (uuid == uuid2) {
					
						database.set("Database." + target2 + ".Ban.Banned", true);
						database.set("Database." + target2 + ".Ban.Type", 0);
						database.set("Database." + target2 + ".Ban.Sender", sender.getName());
						database.set("Database." + target2 + ".History.Bans.Count", bans + 1);
						this.saveCustomYml(database, new File("Database.yml"));
						Bukkit.broadcast("§8[§cxPunish§8] §6Player §c" + target2 + " §6 has been §4§lBanned §6by §c" + sender.getName() + "§6!", "xpunish.notify");
						
						return true;
						
					}	
					
				} else {
					
					int bans = database.getInt("Database." + target.getName() + ".History.Bans.Count");
					
					target.kickPlayer("§4§lYou have been Permanently Banned!\n\n§6By: §c" + sender.getName() + "\n\n§8[§cxPunish§8]");
					
					database.set("Database." + target.getName() + ".Ban.Banned", true);
					database.set("Database." + target.getName() + ".Ban.Type", "Permanent");
					database.set("Database." + target.getName() + ".Ban.Sender", sender.getName());
					database.set("Database." + target.getName() + ".History.Bans.Count", bans + 1);
					this.saveCustomYml(database, new File("Database.yml"));
					Bukkit.broadcast("§8[§cxPunish§8]", "xpunish.notify");
				
					return true;
					
				}
				
			} else if (length > 1) {
				
				reason = args[1];
				
				int i = 0;
				
				for (i=2;i<length;i++) {
					
					reason = reason + " " + args[i];
					
				}
				
				FileConfiguration database = YamlConfiguration.loadConfiguration(new File("Database.yml"));
				FileConfiguration players = YamlConfiguration.loadConfiguration(new File("Players.yml"));
				
				Player target = Bukkit.getServer().getPlayer(args[0]);
				
				String target2 = args[0];
				
				if (target == null) {
					
					String uuid = database.getString("Database." + target2 + ".UUID");
					String uuid2 = players.getString("Players." + target2);
					int bans = database.getInt("Database." + target2 + ".History.Bans.Count");
					
					if (uuid == uuid2) {
						
						database.set("Database." + target2 + ".Ban.Banned", true);
						database.set("Database." + target2 + ".Ban.Type", "Permanent");
						database.set("Database." + target2 + ".Ban.Reason", reason);
						database.set("Database." + target2 + ".Ban.Sender", sender.getName());
						database.set("Database." + target2 + ".History.Bans.Count", bans + 1);
						this.saveCustomYml(database, new File("Database.yml"));
						Bukkit.broadcast("§8[§cxPunish§8] §6Player §c" + target2 + " §6has been §4§lBanned §6due to §c" + reason + " §6by §c" + sender.getName() + "§6!", "xpunish.notify");
						
						return true;
						
					}
					
				} else {
					
					int bans = database.getInt("Database." + target.getName() + ".History.Bans.Count");
					
					target.kickPlayer("§4§lYou have been Permanently Banned!\n\n§6§lReason: §c" + reason + "\n§6§lBy: §c" + sender.getName() + "\n\n§8[§cxPunish§8]");
					
					database.set("Database." + target.getName() + ".Ban.Banned", true);
					database.set("Database." + target.getName() + ".Ban.Type", "Permanent");
					database.set("Database." + target.getName() + ".Ban.Reason", reason);
					database.set("Database." + target.getName() + ".Ban.Sender", sender.getName());
					database.set("Database." + target.getName() + ".History.Bans.Count", bans + 1);
					this.saveCustomYml(database, new File("Database.yml"));
					Bukkit.broadcast("§8[§cxPunish§8] §6Player §c" + target + " §6has been §4§lBanned §6due to §c" + reason + " §6by §c" + sender.getName() + "§6!", "xpunish.notify");
					
					return true;
					
				}
				
			}
			
		}
		
		return false;
		
	}
	
}
