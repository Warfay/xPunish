package me.muffin.xpunish.Commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.muffin.xpunish.xPunish;
import me.muffin.xpunish.Miscs.Punish;
import me.muffin.xpunish.Miscs.getMessagesFile;
import net.md_5.bungee.api.ChatColor;

public class Ban implements CommandExecutor {

	private String reason = "";
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
			
			String prefix = getMessagesFile.getPath("Messages.Prefix", this.main);
			prefix = ChatColor.translateAlternateColorCodes('&', prefix);
			
			if (!sender.hasPermission("xpunish.ban")) {
				
				String noperm = getMessagesFile.getPath("Messages.NoPerm", this.main);
				noperm = ChatColor.translateAlternateColorCodes('&', noperm);
				
				sender.sendMessage(prefix + " " + noperm);
				
				return true;
				
			}
			
			int length = args.length;
			
			if (length == 0) {
				
				String syntax = getMessagesFile.getPath("Messages.Syntax.PermaBanSyntax", this.main);
				syntax = ChatColor.translateAlternateColorCodes('&', syntax);
				sender.sendMessage(prefix + " " + syntax);
				
				return true;
				
			} else if (length == 1) {
				
				Player target = Bukkit.getServer().getPlayer(args[0]);
				@SuppressWarnings("deprecation")
				OfflinePlayer target2 = Bukkit.getOfflinePlayer(args[0]);
				
				if (target == null) {
					
					Punish.createFileOff(target2, this.main);
					String banned = Punish.PermaBanNoReasonOffline(target2, (Player) sender, this.main);
					
					if (banned.equals("banned")) {

						String banmsg = getMessagesFile.getPath("Messages.Punishment.PermaBanWithoutReason", this.main);
						banmsg = ChatColor.translateAlternateColorCodes('&', banmsg);
						banmsg = banmsg.replaceAll("%player%", target2.getName());
						banmsg = banmsg.replaceAll("%sender%", sender.getName());
						
						Bukkit.broadcast(prefix + " " + banmsg, "xpunish.notify");
						
						return true;
						
					} else {
						
						String alreadybanned = getMessagesFile.getPath("Messages.Errors.AlreadyBanned", this.main);
						alreadybanned = ChatColor.translateAlternateColorCodes('&', alreadybanned);
						sender.sendMessage(prefix + " " + alreadybanned);
						
						return true;
						
					}
					
				} else {
					
					Punish.createFile(target, this.main);
					String banned = Punish.PermaBanNoReasonOnline(target, (Player) sender, this.main);
					
					if (banned.equals("banned")) {
						
						String banmsg = getMessagesFile.getPath("Messages.Punishment.PermaBanWithoutReason", this.main);
						banmsg = ChatColor.translateAlternateColorCodes('&', banmsg);
						banmsg = banmsg.replaceAll("%player%", target2.getName());
						banmsg = banmsg.replaceAll("%sender%", sender.getName());
						
						Bukkit.broadcast(prefix + " " + banmsg, "xpunish.notify");
						
						return true;
						
					} else {
						
						String alreadybanned = getMessagesFile.getPath("Messages.Errors.AlreadyBanned", this.main);
						alreadybanned = ChatColor.translateAlternateColorCodes('&', alreadybanned);
						sender.sendMessage(prefix + " " + alreadybanned);
						
						return true;
						
					}
					
				}
				
			} else if (length > 1) {
				
				reason = args[1];
				
				int i = 0;
				
				for (i=2;i<length;i++) {
					
					reason = reason + " " + args[i];
					
				}
				
				Player target = Bukkit.getServer().getPlayer(args[0]);
				@SuppressWarnings("deprecation")
				OfflinePlayer target2 = Bukkit.getOfflinePlayer(args[0]);	
				
				if (target == null) {
					
					Punish.createFileOff(target2, this.main);
					String banned = Punish.PermaBanReasonOffline(target2, (Player) sender, reason, this.main);
					
					if (banned.equals("banned")) {
						
						String banmsg = getMessagesFile.getPath("Messages.Punishment.PermaBanWithReason", this.main);
						banmsg = ChatColor.translateAlternateColorCodes('&', banmsg);
						banmsg = banmsg.replaceAll("%player%", target2.getName());
						banmsg = banmsg.replaceAll("%sender%", sender.getName());
						banmsg = banmsg.replaceAll("%reason%", reason);
						
						Bukkit.broadcast(prefix + " " + banmsg, "xpunish.notify");
						
						return true;
						
					} else {
						
						String alreadybanned = getMessagesFile.getPath("Messages.Errors.AlreadyBanned", this.main);
						alreadybanned = ChatColor.translateAlternateColorCodes('&', alreadybanned);
						sender.sendMessage(prefix + " " + alreadybanned);
						
						return true;
						
					}
					
				} else {
					
					Punish.createFile(target, this.main);
					String banned = Punish.PermaBanReasonOnline(target, (Player) sender, reason, this.main);
					
					if (banned.equals("banned")) {
						
						String banmsg = getMessagesFile.getPath("Messages.Punishment.PermaBanWithReason", this.main);
						banmsg = ChatColor.translateAlternateColorCodes('&', banmsg);
						banmsg = banmsg.replaceAll("%player%", target2.getName());
						banmsg = banmsg.replaceAll("%sender%", sender.getName());
						banmsg = banmsg.replaceAll("%reason%", reason);
						
						Bukkit.broadcast(prefix + " " + banmsg, "xpunish.notify");
						
						return true;
						
					} else {
						
						String alreadybanned = getMessagesFile.getPath("Messages.Errors.AlreadyBanned", this.main);
						alreadybanned = ChatColor.translateAlternateColorCodes('&', alreadybanned);
						sender.sendMessage(prefix + " " + alreadybanned);
						
						return true;
						
					}
					
				}
				
			}
				
		}
		
		return false;
		
	}
		
}
