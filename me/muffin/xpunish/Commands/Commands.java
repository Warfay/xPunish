package me.muffin.xpunish.Commands;

import java.io.File;
import java.net.InetAddress;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.muffin.xpunish.xPunish;

public class Commands implements CommandExecutor {

	private String msg = "";
	@SuppressWarnings("unused")
	private xPunish main;
	public Commands(xPunish main) {
		this.main = main;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		
		if (cmd.getName().equalsIgnoreCase("xpunish")) {
			
			int length = args.length;
			
			if (!sender.hasPermission("xpunish.info")) {
				
				sender.sendMessage("§8[§cxPunish§8] §cYou are not permitted to this command!");
				
				return true;
				
			}
			
			if (length == 0) {
				
				sender.sendMessage("§8[§cxPunish§8] §6xPunish §cv1.0 §6by §cKaakaomuffini\n§7@------------------Commands------------------@\n   §c/xpunish §a>> Show list of commands\n   §c/xpunish info <player> §a>> Info collected from player\n   §c/xpunish server §a>> Server related features\n   §c/xpunish database §a>> Manage the database §c§lCOMING SOON!\n   §c/xpunish verbose <on;off> §a>> Toggle verbose mode\n   §c/xpunish punish §a>> A list of punish commands\n§7@------------------Commands------------------@\n");
				
				return true;
				
			}
			
			if (args[0].equalsIgnoreCase("info")) {
				
				if (length == 1) {
					
					sender.sendMessage("\n§8[§cxPunish§8] §cInvalid syntax!\n§8[§cxPunish§8] §7/xpunish info <player>");
					
					return true;
					
				}
				
				if (length == 2) {
					
					Player player = Bukkit.getPlayerExact(args[1]);
					
					if (player == null) {
						
						String player2 = args[1];
						
						sender.sendMessage("\n§7@------------------" + player2 + "------------------@\n   §6§lName: §c" + player2 + "\n   §6§lOnline: §cFalse\n§7@------------------" + player2 + "------------------@\n");
						
						return true;
						
					}
					
					if (player.isOnline()) {
						
						InetAddress ip = player.getAddress().getAddress();
						
						UUID uuid = player.getUniqueId();
						
						GameMode gm = player.getGameMode();
						
						Boolean flying = player.isFlying();
						
						Boolean op = player.isOp();
						
						String flying2 = null;
						
						String op2 = null;
						
						if (flying == true) {	
							flying2 = "§aTrue";
						}
						
						if (flying == false) {
							flying2 = "§cFalse";
						}
						
						if (op == true) {
							op2 = "§aTrue";
						}
						
						if (op == false) {
							op2 = "§cFalse";
						}
						
						sender.sendMessage("\n§7@------------------" + player.getName() + "------------------@\n   §6§lName: §c" + player.getName() + "\n   §6§lOnline: §aTrue\n   §6§lIP: §c" + ip + "\n   §6§lUUID: §c" + uuid + "\n   §6§lGamemode: §c" + gm + " §6(isFlying: " + flying2 + "§6)\n   §6§lOP: " + op2 + "\n§7@------------------" + player.getName() + "------------------@\n");
						
						return true;
						
					}
					
				}
				
			} else if (args[0].equalsIgnoreCase("server")) {
				
				if (length > 1) {
					
					sender.sendMessage("\n§8[§cxPunish§8] §cInvalid syntax!\n§8[§cxPunish§8] §7/xpunish server");
					
					return true;
					
				} else {
					
					int onlinep = Bukkit.getServer().getOnlinePlayers().size();
					
					sender.sendMessage("\n§7@------------------Server------------------@\n   §6§lOnline: §c" + onlinep + "\n   §c/xpunish broadcast <message> §a>> Broadcast a message\n   §c/xpunish kickall §a>> Kick all online players\n§7@------------------Server------------------@");
					
					return true;
					
				}
				
			} else if (args[0].equalsIgnoreCase("punish")) {
				
				if (length > 1) {
					
					sender.sendMessage("\n§8[§cxPunish§8] §cInvalid syntax!\n§8[cxPunish§8]");
					
					return true;
					
				} else {
					
					sender.sendMessage("\n§7@------------------Punish------------------@\n   §c/reports [id] §a>> Check all reports or a specific\n   §c/report <player> <reason> §a>> Report a player\n   §c/punish <player> [reason] §a>> Open a Punishment GUI\n   §c/ban <player> [reason] §a>> Ban a player\n   §c/tempban <player> <timespan> [reason] §a>> Tempban a player\n   §c/kick <player> [reason] §aKick a player\n   §c/warn <player> [reason] §a>> Warn a player\n   §c/history <player> §aView a player's history\n§7@------------------Punish------------------@");
					
					return true;
					
				}
				
			} else if (args[0].equalsIgnoreCase("verbose")) {
				
				if (length > 2) {
					
					sender.sendMessage("\n§8[§cxPunish§8] §cInvalid syntax!\n§8[§cxPunish§8] §7/xpunish verbose <on;off>");
					
					return true;
					
				} else if (length == 1) {
				
					sender.sendMessage("\n§8[§cxPunish§8] §cInvalid syntax!\n§8[§cxPunish§8] §7/xpunish verbose <on;off>");
					
					return true;
					
				} else if (length == 2) {
					
					if (args[1].equalsIgnoreCase("on")) {
						
						sender.sendMessage("§8[§cxPunish§8] §aVerbose mode has been enabled!");
						
						return true;
						
					} else if (args[1].equalsIgnoreCase("off")) {
						
						sender.sendMessage("§8[§cxPunish§8] §cVerbose mode has been disabled!");
						
						return true;
						
					} else if (args[1] != "on") {
						
						if (args[1] != "off") {
							
							sender.sendMessage("\n§8[§cxPunish§8] §cInvalid syntax!\n§8[§cxPunish§8] §7/xpunish verbose <on;off>");
							
							return true;
							
						}
						
					}
					
				}
				
			} else if (args[0].equalsIgnoreCase("broadcast")) {
				
				if (length < 2) {
					
					sender.sendMessage("§8[§cxPunish§8] §cInvalid syntax!\n§8[§cxPunish§8] §7/xpunish broadcast <message>");
					
					return true;
					
				} else {
				
					msg = args[1];
					
					if (msg == null) {
						
						sender.sendMessage("§8[§cxPunish§8] §cInvalid syntax!\n§8[§cxPunish§8] §7/xpunish broadcast <message>");
						
						return true;
						
					}
					
					int i = 0;
					
					for (i=2;i<length;i++) {
						
						msg = msg + " " + args[i];
						
					}
					
					Bukkit.broadcastMessage("§8[§6Broadcast§8] §a" + msg);
					
					return true;
					
				}
				
			} else if (args[0].equalsIgnoreCase("kickall")) {
				
				
				
				for (Player player : Bukkit.getServer().getOnlinePlayers()) {
					
					sender.sendMessage("§8[§cxPunish§8] §6All online players have been Kicked!");
					
					if (player != sender) {
					
						player.kickPlayer("§4§lYou have been Kicked!\n\n§8[§cxPunish§8]");
				
					}
						
				}
				
				return true;
				
			} else if (args[0].equalsIgnoreCase("database")) {
				
				sender.sendMessage("§8[§cxPunish§8] §cThis feature isn't available yet!");
				
				return true;
				
			} else if (args[0].equalsIgnoreCase("test")) {
				
				sender.sendMessage("Helllooooyy");
				
				return true;
				
			} else {
				
				sender.sendMessage("§8[§cxPunish§8] §6Something went wrong... Try §c/xpunish");
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
}
