package me.muffin.xpunish.Miscs;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.muffin.xpunish.xPunish;

public class Punish {
	
	public static void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		
		try {
			
			ymlConfig.save(ymlFile);
		
		} catch (IOException e) {
			
		e.printStackTrace();
		
		}
		
	}
	
	public static void createFile(Player target, xPunish main) {
		
		UUID uuid = target.getUniqueId();
		File file = new File(main.getDataFolder() + File.separator + "Database" + File.separator + uuid.toString() + ".yml");
		
		if (!file.exists()) {
			
			try {
				
				file.createNewFile();
				
			} catch (IOException ex) {
				
				xPunish.log("[xPunish] Failed to create a punish file for " + target.getName(), main);
				ex.printStackTrace();
				
			}
			
		}
		
	}
	
	public static void createFileOff(OfflinePlayer target, xPunish main) {
		
		UUID uuid = target.getUniqueId();
		File file = new File(main.getDataFolder() + File.separator + "Database" + File.separator + uuid.toString() + ".yml");
		
		if (!file.exists()) {
			
			try {
				
				file.createNewFile();
				
			} catch (IOException ex) {
				
				xPunish.log("[xPunish] Failed to create a punish file for " + target.getName(), main);
				ex.printStackTrace();
				
			}
			
		}
		
	}
	
	public static String PermaBanNoReasonOnline(Player target, Player sender, xPunish main) {
		
		//TODO
		String banscreen = getMessagesFile.getPath("Messages.PunishmentScreen.PermaBan", main)
		String reason = "Change This!";
		
		//Kick the player
		target.kickPlayer(reason);
		
		//Current time
		long ms = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date(ms);
		String result = sdf.format(date);
		
		UUID uuid = target.getUniqueId();
		String name = target.getName();
		File file = new File(main.getDataFolder() + File.separator + "Database" + File.separator + uuid.toString() + ".yml");
		FileConfiguration database = YamlConfiguration.loadConfiguration(file);
		
		if (!database.getBoolean(uuid + ".Ban.Banned")) {
			
			//Set ban data to database
			database.set(uuid + ".Ban.Name", name);
			database.set(uuid + ".Ban.Banned", true);
			database.set(uuid + ".Ban.Reason", null);
			database.set(uuid + ".Ban.Sender", sender.getName());
			database.set(uuid + ".Ban.Date", result);
			saveCustomYml(database, file);
			
			return "banned";
			
		}
		
		return "noban";
		
	}
	
	public static String PermaBanNoReasonOffline(OfflinePlayer target, Player sender, xPunish main) {
		
		//TODO
		
		//Current time
		long ms = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date(ms);
		String result = sdf.format(date);
		
		UUID uuid = target.getUniqueId();
		String name = target.getName();
		File file = new File(main.getDataFolder() + File.separator + "Database" + File.separator + uuid.toString() + ".yml");
		FileConfiguration database = YamlConfiguration.loadConfiguration(file);
		
		if (!database.getBoolean(uuid + ".Ban.Banned")) {
			
			//Set ban data to database
			database.set(uuid + ".Ban.Name", name);
			database.set(uuid + ".Ban.Banned", true);
			database.set(uuid + ".Ban.Reason", null);
			database.set(uuid + ".Ban.Sender", sender.getName());
			database.set(uuid + ".Ban.Date", result);
			saveCustomYml(database, file);
			
			return "banned";
			
		}
		
		return "noban";
		
	}
	
	public static String PermaBanReasonOnline(Player target, Player sender, String reason, xPunish main) {
		
		//TODO
		
		//Current time
		long ms = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date(ms);
		String result = sdf.format(date);
		
		UUID uuid = target.getUniqueId();
		String name = target.getName();
		File file = new File(main.getDataFolder() + File.separator + "Database" + File.separator + uuid.toString() + ".yml");
		FileConfiguration database = YamlConfiguration.loadConfiguration(file);
		
		if (!database.getBoolean(uuid + ".Ban.Banned")) {
			
			//Set ban data to database
			database.set(uuid + ".Ban.Name", name);
			database.set(uuid + ".Ban.Banned", true);
			database.set(uuid + ".Ban.Reason", reason);
			database.set(uuid + ".Ban.Sender", sender.getName());
			database.set(uuid + ".Ban.Date", result);
			saveCustomYml(database, file);
			
			return "banned";
			
		}
		
		return "noban";
		
	}
	
	public static String PermaBanReasonOffline(OfflinePlayer target, Player sender, String reason, xPunish main) {
		
		//TODO
		
		//Current time
		long ms = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date(ms);
		String result = sdf.format(date);
		
		UUID uuid = target.getUniqueId();
		String name = target.getName();
		File file = new File(main.getDataFolder() + File.separator + "Database" + File.separator + uuid.toString() + ".yml");
		FileConfiguration database = YamlConfiguration.loadConfiguration(file);
		
		if (!database.getBoolean(uuid + ".Ban.Banned")) {
			
			//Set ban data to database
			database.set(uuid + ".Ban.Name", name);
			database.set(uuid + ".Ban.Banned", true);
			database.set(uuid + ".Ban.Reason", reason);
			database.set(uuid + ".Ban.Sender", sender.getName());
			database.set(uuid + ".Ban.Date", result);
			saveCustomYml(database, file);
			
			return "banned";
			
		}
		
		return "noban";
		
	}
	
	public static String KickNoReason(Player target, Player sender, xPunish main) {
		
		//TODO
		
		//Kick the player
		String kickscreen = getMessagesFile.getPath("Messages.PunishmentScreen.KickWithoutReason", main);
		kickscreen = ChatColor.translateAlternateColorCodes('&', kickscreen);
		kickscreen = kickscreen.replaceAll("%sender%", sender.getName());
		target.kickPlayer(kickscreen);
		
		//Current time
		long ms = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date(ms);
		String result = sdf.format(date);
		
		UUID uuid = target.getUniqueId();
		String name = target.getName();
		File file = new File(main.getDataFolder() + File.separator + "Database" + File.separator + uuid.toString() + ".yml");
		FileConfiguration database = YamlConfiguration.loadConfiguration(file);
		
		//Data to UUID database
		database.set(uuid + ".Kick.Name", name);
		database.set(uuid + ".Kick.Reason", null);
		database.set(uuid + ".Kick.Sender", sender.getName());
		database.set(uuid + ".Kick.Date", result);
		saveCustomYml(database, file);
		
		return "kicked";
		
	}
	
	public static String KickReason(Player target, Player sender, String reason, xPunish main) {
		
		//TODO
		
		//Kick the player
		String kickscreen = getMessagesFile.getPath("Messages.PunishmentScreen.KickWithReason", main);
		kickscreen = ChatColor.translateAlternateColorCodes('&', kickscreen);
		kickscreen = kickscreen.replaceAll("%reason%", reason);
		kickscreen = kickscreen.replaceAll("%sender%", sender.getName());
		
		String prefix = getMessagesFile.getPath("Messages.Prefix", main);
		prefix = ChatColor.translateAlternateColorCodes('&', prefix);
		kickscreen = kickscreen.replaceAll("%prefix%", prefix);
		
		target.kickPlayer(kickscreen);
		
		//Current time
		long ms = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date(ms);
		String result = sdf.format(date);
		
		UUID uuid = target.getUniqueId();
		String name = target.getName();
		File file = new File(main.getDataFolder() + File.separator + "Database" + File.separator + uuid.toString() + ".yml");
		FileConfiguration database = YamlConfiguration.loadConfiguration(file);
		
		//Data to UUID database
		database.set(uuid + ".Kick.Name", name);
		database.set(uuid + ".Kick.Reason", reason);
		database.set(uuid + ".Kick.Sender", sender.getName());
		database.set(uuid + ".Kick.Date", result);
		saveCustomYml(database, file);
		
		return "kicked";
		
	}
	
}
