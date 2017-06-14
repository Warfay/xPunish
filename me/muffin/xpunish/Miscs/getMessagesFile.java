package me.muffin.xpunish.Miscs;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.muffin.xpunish.xPunish;

public class getMessagesFile {
	
	public static String getPath(String path, xPunish main) {
		
		File file = new File(main.getDataFolder() + File.separator + "messages.yml");
		
		if (!file.exists()) {
			
			xPunish.log("[ERROR] File 'messages.yml' could not be found!", main);
			
			return null;
			
		} else {
			
			FileConfiguration msg = YamlConfiguration.loadConfiguration(file);
			
			String result = msg.getString(path);
			
			return result;
			
		}
		
	}
	
}
