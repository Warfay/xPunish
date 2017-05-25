package me.muffin.xpunish.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.muffin.xpunish.GUI.ReportGUI;

public class Report implements CommandExecutor {

	private String reason = null;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		
		if (cmd.getName().equalsIgnoreCase("report")) {
			
			if (!sender.hasPermission("xpunish.report")) {
				
				sender.sendMessage("§8[§cxPunish§8] §cYou are not permitted to this command!");
				
				return true;
				
			} else {
				
				int length = args.length;
				
				if (length == 0) {
					
					sender.sendMessage("§8[§cxPunish§8] §cInvalid syntax!\n§8[§cxPunish§8] §7/report <player> <reason>");
					
					return true;
					
				} else if (length == 1) {
					
					sender.sendMessage("§8[§cxPunish§8] §cInvalid syntax!\n§8[§cxPunish§8] §7/report <player> <reason>");
					
					return true;
					
				} else if (length == 2) {
					
					Player target = Bukkit.getServer().getPlayer(args[0]);
					Player sender2 = (Player) sender;
					reason = args[1];
					
					if (target == null) {
						
						sender.sendMessage("§8[§cxPunish§8] §cYou can only report online players!");
						
						return true;
						
					} else {
						
						ReportGUI.rGUIon(sender2, target, reason);
						
						return true;
						
					}
					
				} else if (length > 2) {
					
					Player target = Bukkit.getServer().getPlayer(args[0]);
					Player sender2 = (Player) sender;
					reason = args[1];
					int i = 0;
					
					for (i=2;i<length;i++) {
						
						reason = reason + " " + args[i];
						
					}
					
					if (target == null) {
						
						sender.sendMessage("§8[§cxPunish§8] §cYou can only report online players!");
						
						return true;
						
					} else {
						
						ReportGUI.rGUIon(sender2, target, reason);
						
						return true;
						
					}
					
				}
				
			}
			
		}
		
		return false;
		
	}
	
}
