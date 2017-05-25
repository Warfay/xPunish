package me.muffin.xpunish.Commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.muffin.xpunish.xPunish;
import me.muffin.xpunish.Miscs.TranslateCC;

public class Kick implements CommandExecutor {

	private String reason = "";
	@SuppressWarnings("unused")
	private xPunish main;
	public Kick(xPunish main) {
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
		
		if (cmd.getName().equalsIgnoreCase("kick")) {
			
			if (!(sender.hasPermission("xpunish.kick"))) {
				
				sender.sendMessage("§8[§cxPunish§8] §cYou are not permitted to this command!");
				
				return true;
				
			}
			
			int length = args.length;
			
			if (length == 0) {
				
				sender.sendMessage("§8[§cxPunish§8] §cInvalid syntax!\n§8[§cxPunish§8] §7/kick <player> [reason]");
			
				return true;
				
			} else if (length == 1) {
				
				Player target = Bukkit.getServer().getPlayer(args[0]);
				
				if (target == null) {
					
					sender.sendMessage("§8[§cxPunish§8] §6Could not find player §c" + args[0] + "§6!");
					
					return true;
					
				} else {
				
					File db = new File("Database.yml");
					FileConfiguration database = YamlConfiguration.loadConfiguration(db);
					FileConfiguration messages = YamlConfiguration.loadConfiguration(new File(main.getDataFolder() + "messages.yml"));
					int kicks = database.getInt("Database." + target.getName() + ".History.Kicks.Count");
					
					String prefix = messages.getString("Messages.Prefix");
					TranslateCC.useAlternateColorCodes("&", prefix);
					database.set("Database." + target.getName() + ".History.Kicks.Count", kicks + 1);
					saveCustomYml(database, db);
					
					Bukkit.broadcast(prefix + " §6Player §c" + target.getName() + " §6has been §4§lKicked §6by §c" + sender.getName() + "§6!", "xpunish.notify");
				
					target.kickPlayer("§4§lYou have been Kicked!\n\n§6§lBy: §c" + sender.getName() + "\n\n§8[§cxPunish§8]");
				
					return true;
				
				}
				
			} else if (length > 1) {
			
				reason = args[1];
			
				int i = 0;
			
				for (i=2;i<length;i++) {
				
					reason = reason + " " + args[i];
				
				}
			
				Player target = Bukkit.getServer().getPlayer(args[0]);
			
				if (target == null) {
				
					sender.sendMessage("§8[§cxPunish§8] §6Could not find player §c" + args[0] + "§6!");
				
					return true;
				
				} else {
			
					Bukkit.broadcastMessage("§8[§cxPunish§8] §6Player §c" + target.getName() + " §6has been §4§lKicked §6due to §c" + reason + " §6by §c" + sender.getName() + "§6!");
					target.kickPlayer("§4§lYou have been Kicked!\n\n§6§lReason: §c" + reason + "\n§6§lBy: §c" + sender.getName() + "\n\n§8[§cxPunish§8]");
					reason = null;
			
					return true;
				
				}
			}
			
		}
		
		return false;
		
	}
	
}
