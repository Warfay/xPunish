package me.muffin.xpunish.Miscs;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import me.muffin.xpunish.xPunish;

public class JoinListener implements Listener {

	@SuppressWarnings("unused")
	private xPunish main;
	public JoinListener(xPunish main) {
		this.main = main;
	}
	
	@EventHandler
	public void onJoin(PlayerLoginEvent e) {
		
		FileConfiguration database = YamlConfiguration.loadConfiguration(new File("Database.yml"));
		FileConfiguration players = YamlConfiguration.loadConfiguration(new File("Players.yml"));
		
		String uuid = database.getString("Database." + e.getPlayer().getName() + ".UUID");
		players.set("Players." + e.getPlayer().getName(), e.getPlayer().getUniqueId());
		String uuid2 = players.getString("Database." + e.getPlayer().getName());
		
		if (uuid != uuid2) {
			
			database.set("Database." + e.getPlayer().getName() + ".UUID", uuid2);
			
		}
		
	}
	
}
