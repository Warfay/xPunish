package me.muffin.xpunish.Miscs;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import me.muffin.xpunish.xPunish;
import me.muffin.xpunish.Commands.Tempban;

public class BanListener implements Listener {

	@SuppressWarnings("unused")
	private xPunish main;
	public BanListener(xPunish main) {
		this.main = main;
	}
	
	public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		
		try {
			
			ymlConfig.save(ymlFile);
		
		} catch (IOException e) {
			
		e.printStackTrace();
		
		}
		
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		
		Player p = e.getPlayer();
		
		FileConfiguration database = YamlConfiguration.loadConfiguration(new File("Database.yml"));
		
		boolean banned = database.getBoolean("Database." + p.getName() + ".Ban.Banned");
		String reason = database.getString("Database." + p.getName() + ".Ban.Reason");
		String sender = database.getString("Database." + p.getName() + ".Ban.Sender");
		int type = database.getInt("Database." + p.getName() + ".Ban.Type");
		long banEnd = database.getLong("Database." + p.getName() + ".Ban.End");
		
		if (reason == null) {
			
			if (banned == true) {
				
				if (type == 0) {
				
					e.setKickMessage("§4§lYou have been Permanently Banned!\n\n§6§lBy: §c" + sender + "\n\n§8[§cxPunish§8]");
					e.setResult(PlayerLoginEvent.Result.KICK_BANNED);
				
				} else if (type == 1) {
					
					long now = System.currentTimeMillis();
					
					if (banEnd - now <= 0) {
						
						database.set("Database." + p.getName() + ".Ban.Banned", false);
						database.set("Database." + p.getName() + ".Ban.Sender", null);
						database.set("Database." + p.getName() + ".Ban.Type", null);
						database.set("Database." + p.getName() + ".Ban.End", null);
						saveCustomYml(database, new File("Database.yml"));
						
					} else {
					
						e.setKickMessage("§4§lYou have been Temporarily Banned!\n\n§6§lEnd: §c" + Tempban.getMessage(banEnd) + "\n§6§lBy: §c" + sender + "\n\n§8[§cxPunish§8]");
						e.setResult(PlayerLoginEvent.Result.KICK_BANNED);
					
					}
						
				} else {
					
					e.setKickMessage("§cError: §fPunishment database misconfiguration!\n\n§fCaused by \"Database.yml\"");
					e.setResult(PlayerLoginEvent.Result.KICK_OTHER);
					
				}
				
			}
			
		} else {
			
			if (banned == true) {
				
				if (type == 0) {
					
					e.setKickMessage("§4§lYou have been Permanently Banned!\n\n§6§lReason: §c" + reason + "\n§6§lBy: §c" + sender + "\n\n§8[§cxPunish§8]");
					e.setResult(PlayerLoginEvent.Result.KICK_BANNED);
			
				} else if (type == 1) {
					
					long now = System.currentTimeMillis();
					
					if (banEnd - now <= 0) {
						
						database.set("Database." + p.getName() + ".Ban.Banned", false);
						database.set("Database." + p.getName() + ".Ban.Reason", null);
						database.set("Database." + p.getName() + ".Ban.Sender", null);
						database.set("Database." + p.getName() + ".Ban.Type", null);
						database.set("Database." + p.getName() + ".Ban.End", null);
						saveCustomYml(database, new File("Database.yml"));
						
					} else {
						
						e.setKickMessage("§4§lYou have been Temporarily Banned!\n\n§6§lEnd: §c" + Tempban.getMessage(banEnd) + "\n§6§lReason: §c" + reason + "\n§6§lBy: §c" + sender + "\n\n§8[§cxPunish§8]");
						e.setResult(PlayerLoginEvent.Result.KICK_BANNED);
					
					}
						
				} else {
					
					e.setKickMessage("§cError: §fPunishment database misconfiguration!\n\n§fCaused by \"Database.yml\"!");
					e.setResult(PlayerLoginEvent.Result.KICK_OTHER);
					
				}
				
			}
			
		}
		
	}
	
}
