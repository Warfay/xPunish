package me.muffin.xpunish.Commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.muffin.xpunish.xPunish;
import me.muffin.xpunish.Miscs.Punish;
import me.muffin.xpunish.Miscs.getMessagesFile;

public class Kick implements CommandExecutor {

	private String reason = "";
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
		
		String prefix = getMessagesFile.getPath("Messages.Prefix", this.main);
		prefix = ChatColor.translateAlternateColorCodes('&', prefix);
		
		if (cmd.getName().equalsIgnoreCase("kick")) {
			
			if (!(sender.hasPermission("xpunish.kick"))) {
				
				String noperm = getMessagesFile.getPath("Messages.NoPerm", this.main);
				noperm = ChatColor.translateAlternateColorCodes('&', noperm);
				
				sender.sendMessage(prefix + " " + noperm);
				
				return true;
				
			}
			
			int length = args.length;
			
			String syntax = getMessagesFile.getPath("Messages.Syntax.KickSyntax", this.main);
			syntax = ChatColor.translateAlternateColorCodes('&', syntax);
			
			if (length == 0) {
				
				sender.sendMessage(prefix + " " + syntax);
			
				return true;
				
			} else if (length == 1) {
				
				Player target = Bukkit.getServer().getPlayer(args[0]);
				
				if (target == null) {
					
					String notfound = getMessagesFile.getPath("Messages.Errors.PlayerNotFound", this.main);
					notfound = ChatColor.translateAlternateColorCodes('&', notfound);
					notfound = notfound.replaceAll("%player%", args[0]);
					
					sender.sendMessage(prefix + " " + notfound);
					
					return true;
					
				} else {
				
					Punish.createFile(target, this.main);
					String result = Punish.KickNoReason(target, (Player) sender, this.main);
					
					if (result.equals("kicked")) {
						
						String kicked = getMessagesFile.getPath("Messages.Punishment.KickWithoutReason", this.main);
						kicked = ChatColor.translateAlternateColorCodes('&', kicked);
						kicked = kicked.replaceAll("%player%", target.getName());
						kicked = kicked.replaceAll("%sender%", sender.getName());
						Bukkit.broadcast(prefix + " " + kicked, "xpunish.notify");
						
					}
					
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
				
					String notfound = getMessagesFile.getPath("Messages.Errors.PlayerNotFound", this.main);
					notfound = ChatColor.translateAlternateColorCodes('&', notfound);
					notfound = notfound.replaceAll("%player%", args[0]);
					
					sender.sendMessage(prefix + " " + notfound);
				
					return true;
				
				} else {
			
					Punish.createFile(target, this.main);
					String result = Punish.KickReason(target, (Player) sender, reason, this.main);
					
					if (result.equals("kicked")) {
						
						String kicked = getMessagesFile.getPath("Messages.Punishment.KickWithReason", this.main);
						kicked = ChatColor.translateAlternateColorCodes('&', kicked);
						kicked = kicked.replaceAll("%player%", target.getName());
						kicked = kicked.replaceAll("%sender%", sender.getName());
						kicked = kicked.replaceAll("%reason%", reason);
						Bukkit.broadcast(prefix + " " + kicked, "xpunish.notify");
						
					}
					
					return true;
				
				}
			}
			
		}
		
		return false;
		
	}
	
}
