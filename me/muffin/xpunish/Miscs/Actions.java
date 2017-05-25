package me.muffin.xpunish.Miscs;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.muffin.xpunish.xPunish;
import me.muffin.xpunish.Commands.Tempban;

public class Actions {

	private static xPunish main;
	public Actions(xPunish main) {
		Actions.main = main;
	}
	
	public static void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		
		try {
			
			ymlConfig.save(ymlFile);
		
		} catch (IOException e) {
			
		e.printStackTrace();
		
		}
		
	}
	
	public static void HackSev1RO(Player p, String r, Player s, int bc, boolean ip) {
		
		String prefix = main.getConfig().getString("prefix");
		FileConfiguration database = YamlConfiguration.loadConfiguration(new File("Database.yml"));
		
		if (p == null) {
			
			s.sendMessage(prefix + " §cError: §fCouldn't find the target player! Please try again.");
			
		} else {
			
			if (bc == 1) {
				
				Bukkit.broadcastMessage(prefix + "\n§6Player §c" + p.getName() + " §6has been §4§lTemporarily Banned §6due to §c" + r + " §6by §c" + s.getName());
				
			} else if (bc == 2) {
				
				for (Player staff : Bukkit.getOnlinePlayers()) {
					
					if (p.hasPermission("xpunish.staff")) {
						
						staff.sendMessage(prefix + "\n§6Player §c" + p.getName() + " §6has been §4§lTemporarily Banned §6due to §c" + r + " §6by §c" + s.getName());
						
					}
					
				}
				
			}
			
			long banEnd = System.currentTimeMillis() + TimeUnits.getTicks("day", 1);
			
			database.set("Database." + p.getName() + ".Ban.Banned", true);
			database.set("Database." + p.getName() + ".Ban.Type", 1);
			database.set("Database." + p.getName() + ".Ban.End", banEnd);
			database.set("Database." + p.getName() + ".Ban.Reason", r);
			database.set("Database." + p.getName() + ".Ban.Sender", s.getName());
			saveCustomYml(database, new File("Database.yml"));
			
			p.kickPlayer("§4§lYou have been Temporarily Banned!\n\n§6§lEnd: §c" + Tempban.getMessage(banEnd) + "\n§6§lReason: §c" + r + "\n§6§lBy: §c" + s.getName() + "\n\n§8[§cxPunish§8]");
			
		}
		
	}
	
}
