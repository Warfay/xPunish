package me.muffin.xpunish.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.muffin.xpunish.GUI.GUI;

public class GUICommand implements CommandExecutor {

	private String reason = null;
	
	@SuppressWarnings("unused")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		
		if ((cmd.getName().equalsIgnoreCase("punish")) || (cmd.getName().equalsIgnoreCase("p")) || (cmd.getName().equalsIgnoreCase("punishgui")) || (cmd.getName().equalsIgnoreCase("pu"))) {
			
			if (!sender.hasPermission("xpunish.gui")) {
				
				sender.sendMessage("§8[§cxPunish§8] §cYou are not permitted to this command!");
				
				return true;
				
			} else {
				
				int length = args.length;
				
				if (length == 0) {
					
					sender.sendMessage("§8[§cxPunish§8] §cInvalid syntax!\n§8[§cxPunish§8] §7/punish <player> [reason]");
					
					return true;
					
				} else if (length == 1) {
					
					Player target = Bukkit.getPlayer(args[0]);
					Player sender2 = (Player) sender;
					String target2 = args[0];
					
					if (target == null) {
						
						
						
						return true;
						
					} else {
						
						
						
						return true;
						
					}
					
				} else if (length > 1) {
					
					Player target = Bukkit.getPlayer(args[0]);
					Player sender2 = (Player) sender;
					String target2 = args[0];
					reason = args[1];
					
					int i = 0;
					for (i=2;i<length;i++) {
						
						reason = reason + " " + args[i];
						
					}
					
					if (target == null) {
						
						
						
						return true;
						
					} else {
					
						GUI.PunishOnlineR(target, reason, sender2);
						
						
						return true;
						
					}
					
				}
				
			}
			
		}
		
		return false;
		
	}
	
}
