package me.muffin.xpunish.GUI;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ReportGUI implements Listener {

	private static Player sender2 = null;
	private static Player target2 = null;
	private static String reason2 = null;
	
	public static void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		
		try {
			
			ymlConfig.save(ymlFile);
		
		} catch (IOException e) {
			
		e.printStackTrace();
		
		}
		
	}
	
	public static void rGUIon(Player sender, Player target, String reason) {
		
		sender2 = sender;
		target2 = target;
		reason2 = reason;
		
		Inventory menu = Bukkit.createInventory(null, 54, "Report");
		
		//Cancel button
		ItemStack c = new ItemStack(Material.BED, 1);
		ItemMeta cm = c.getItemMeta();
		cm.setDisplayName("§c§lCancel");
		c.setItemMeta(cm);
		menu.setItem(0, new ItemStack(c));
		
		//Info button
		ItemStack b1 = new ItemStack(Material.BOOK, 1);
		ItemMeta b1m = b1.getItemMeta();
		b1m.setDisplayName("§c§lReport");
		List<String> b1l = new ArrayList<>();
		b1l.add("");
		b1l.add("§6§lPlayer: §c" + target.getName());
		b1l.add("§6§lReason: §c" + reason);
		b1m.setLore(b1l);
		b1.setItemMeta(b1m);
		menu.setItem(4, new ItemStack(b1));
		
		//Earlier reports
		ItemStack r = new ItemStack(Material.PAPER, 1);
		ItemMeta rm = r.getItemMeta();
		rm.setDisplayName("§6§lYour reports");
		List<String> rl = new ArrayList<>();
		rl.add("");
		rl.add("§7Check your earlier");
		rl.add("§7reports' status here");
		rm.setLore(rl);
		r.setItemMeta(rm);
		menu.setItem(8, new ItemStack(r));
		
		//Glass pane
		ItemStack gp = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta gpm = gp.getItemMeta();
		gpm.setDisplayName(" ");
		gp.setItemMeta(gpm);

		//Set glass panes
		menu.setItem(9, new ItemStack(gp));
		menu.setItem(10, new ItemStack(gp));
		menu.setItem(11, new ItemStack(gp));
		menu.setItem(12, new ItemStack(gp));
		menu.setItem(13, new ItemStack(gp));
		menu.setItem(14, new ItemStack(gp));
		menu.setItem(15, new ItemStack(gp));
		menu.setItem(16, new ItemStack(gp));
		menu.setItem(17, new ItemStack(gp));
		menu.setItem(45, new ItemStack(gp));
		menu.setItem(46, new ItemStack(gp));
		menu.setItem(47, new ItemStack(gp));
		menu.setItem(48, new ItemStack(gp));
		menu.setItem(49, new ItemStack(gp));
		menu.setItem(50, new ItemStack(gp));
		menu.setItem(51, new ItemStack(gp));
		menu.setItem(52, new ItemStack(gp));
		menu.setItem(53, new ItemStack(gp));
		
		//Hack report
		ItemStack hack = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta hackm = hack.getItemMeta();
		hackm.setDisplayName("§2§lHacked Client");
		List<String> hackl = new ArrayList<>();
		hackl.add("");
		hackl.add("§7Report " + target.getName() + " for");
		hackl.add("§7using a hacked client");
		hackm.setLore(hackl);
		hack.setItemMeta(hackm);
		menu.setItem(29, new ItemStack(hack));
		
		//Chat report
		ItemStack chat = new ItemStack(Material.BOOK_AND_QUILL, 1);
		ItemMeta chatm = chat.getItemMeta();
		chatm.setDisplayName("§2§lChat Offense");
		List<String> chatl = new ArrayList<>();
		chatl.add("");
		chatl.add("§7Report " + target.getName() + " for");
		chatl.add("§7using inappropriate language");
		chatl.add("§7or advertising other content");
		chatm.setLore(chatl);
		chat.setItemMeta(chatm);
		menu.setItem(31, new ItemStack(chat));
		
		//Griefing etc. report
		ItemStack grief = new ItemStack(Material.TNT, 1);
		ItemMeta griefm = grief.getItemMeta();
		griefm.setDisplayName("§2§lGameplay Offense");
		List<String> griefl = new ArrayList<>();
		griefl.add("");
		griefl.add("§7Report " + target.getName() + " for");
		griefl.add("§7griefing, abusing bugs etc.");
		griefm.setLore(griefl);
		grief.setItemMeta(griefm);
		menu.setItem(33, new ItemStack(grief));
			
		sender.openInventory(menu);
		
	}
	
	public static void rGUIhack(Player target, Player sender, String reason) {
		
		FileConfiguration database = YamlConfiguration.loadConfiguration(new File("Database.yml"));
		
		int reports = database.getInt("Database.Reports." + sender.getName() + ".Count");
		reports = reports + 1;
		
		if (reports == 1) {
			
			database.set("Database.Reports." + sender.getName() + ".1.Count", reports);
			database.set("Database.Reports." + sender.getName() + ".1.Target", target.getName());
			database.set("Database.Reports." + sender.getName() + ".1.Sender", sender.getName());
			database.set("Database.Reports." + sender.getName() + ".1.Reason", reason);
			saveCustomYml(database, new File("Database.yml"));
			
		} else if (reports == 2) {
			
			database.set("Database.Reports." + sender.getName() + ".2.Count", reports);
			database.set("Database.Reports." + sender.getName() + ".2.Target", target.getName());
			database.set("Database.Reports." + sender.getName() + ".2.Sender", sender.getName());
			database.set("Database.Reports." + sender.getName() + ".2.Reason", reason);
			saveCustomYml(database, new File("Database.yml"));
			
		} else if (reports == 3) {
			
			database.set("Database.Reports." + sender.getName() + ".3.Count", reports);
			database.set("Database.Reports." + sender.getName() + ".3.Target", target.getName());
			database.set("Database.Reports." + sender.getName() + ".3.Sender", sender.getName());
			database.set("Database.Reports." + sender.getName() + ".3.Reason", reason);
			saveCustomYml(database, new File("Database.yml"));
			
		} else if (reports == 4) {
			
			database.set("Database.Reports." + sender.getName() + ".4.Count", reports);
			database.set("Database.Reports." + sender.getName() + ".4.Target", target.getName());
			database.set("Database.Reports." + sender.getName() + ".4.Sender", sender.getName());
			database.set("Database.Reports." + sender.getName() + ".4.Reason", reason);
			saveCustomYml(database, new File("Database.yml"));
			
		} else if (reports == 5) {
			
			database.set("Database.Reports." + sender.getName() + ".5.Count", reports);
			database.set("Database.Reports." + sender.getName() + ".5.Target", target.getName());
			database.set("Database.Reports." + sender.getName() + ".5.Sender", sender.getName());
			database.set("Database.Reports." + sender.getName() + ".5.Reason", reason);
			saveCustomYml(database, new File("Database.yml"));
			
		} else if (reports == 6) {
			
			database.set("Database.Reports." + sender.getName() + ".6.Count", reports);
			database.set("Database.Reports." + sender.getName() + ".6.Target", target.getName());
			database.set("Database.Reports." + sender.getName() + ".6.Sender", sender.getName());
			database.set("Database.Reports." + sender.getName() + ".6.Reason", reason);
			saveCustomYml(database, new File("Database.yml"));
			
		} else if (reports == 7) {
			
			database.set("Database.Reports." + sender.getName() + ".7.Count", reports);
			database.set("Database.Reports." + sender.getName() + ".7.Target", target.getName());
			database.set("Database.Reports." + sender.getName() + ".7.Sender", sender.getName());
			database.set("Database.Reports." + sender.getName() + ".7.Reason", reason);
			saveCustomYml(database, new File("Database.yml"));
			
		} else if (reports == 8) {
			
			database.set("Database.Reports." + sender.getName() + ".8.Count", reports);
			database.set("Database.Reports." + sender.getName() + ".8.Target", target.getName());
			database.set("Database.Reports." + sender.getName() + ".8.Sender", sender.getName());
			database.set("Database.Reports." + sender.getName() + ".8.Reason", reason);
			saveCustomYml(database, new File("Database.yml"));
			
		} else if (reports == 9) {
			
			database.set("Database.Reports." + sender.getName() + ".9.Count", reports);
			database.set("Database.Reports." + sender.getName() + ".9.Target", target.getName());
			database.set("Database.Reports." + sender.getName() + ".9.Sender", sender.getName());
			database.set("Database.Reports." + sender.getName() + ".9.Reason", reason);
			saveCustomYml(database, new File("Database.yml"));
			
		} else if (reports == 10) {
			
			database.set("Database.Reports." + sender.getName() + ".10.Count", reports);
			database.set("Database.Reports." + sender.getName() + ".10.Target", target.getName());
			database.set("Database.Reports." + sender.getName() + ".10.Sender", sender.getName());
			database.set("Database.Reports." + sender.getName() + ".10.Reason", reason);
			saveCustomYml(database, new File("Database.yml"));
			
		}
		
		if (reports > 10) {
			
			sender.sendMessage("§8[§cxPunish§8] §cYou have already sent 10 reports!\n§8[§cxPunish§8] §cPlease wait a week from your first report!");
			
			
			
		} else {
		
			Inventory menu = Bukkit.createInventory(null, 54, "Report (Hacking)");
			
			//Info button
			ItemStack b1 = new ItemStack(Material.BOOK, 1);
			ItemMeta b1m = b1.getItemMeta();
			b1m.setDisplayName("§c§lReport");
			List<String> b1l = new ArrayList<>();
			b1l.add("");
			b1l.add("§6§lPlayer: §c" + target.getName());
			b1l.add("§6§lReason: §c" + reason);
			b1m.setLore(b1l);
			b1.setItemMeta(b1m);
			menu.setItem(4, new ItemStack(b1));
			
			//Glass pane
			ItemStack gp = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
			ItemMeta gpm = gp.getItemMeta();
			gpm.setDisplayName(" ");
			gp.setItemMeta(gpm);
			
			//Set glass pane
			menu.setItem(9, new ItemStack(gp));
			menu.setItem(10, new ItemStack(gp));
			menu.setItem(11, new ItemStack(gp));
			menu.setItem(12, new ItemStack(gp));
			menu.setItem(13, new ItemStack(gp));
			menu.setItem(14, new ItemStack(gp));
			menu.setItem(15, new ItemStack(gp));
			menu.setItem(16, new ItemStack(gp));
			menu.setItem(17, new ItemStack(gp));
			menu.setItem(45, new ItemStack(gp));
			menu.setItem(46, new ItemStack(gp));
			menu.setItem(47, new ItemStack(gp));
			menu.setItem(48, new ItemStack(gp));
			menu.setItem(49, new ItemStack(gp));
			menu.setItem(50, new ItemStack(gp));
			menu.setItem(51, new ItemStack(gp));
			menu.setItem(52, new ItemStack(gp));
			menu.setItem(53, new ItemStack(gp));
			
			//Info display
			ItemStack d = new ItemStack(Material.IRON_SWORD, 1);
			ItemMeta dm = d.getItemMeta();
			dm.setDisplayName("§a§lReport sent!");
			List<String> dl = new ArrayList<>();
			dl.add("");
			dl.add("§7Your report has been successfully sent");
			dl.add("§7for a staff member to investigate");
			dl.add("");
			dl.add("§7Player: §e" + target.getName());
			dl.add("§7Reason: §e" + reason);
			dl.add("");
			dl.add("§7Status: §6Waiting...");
			dm.setLore(dl);
			d.setItemMeta(dm);
			menu.setItem(31, new ItemStack(d));
			
			//Close
			ItemStack close = new ItemStack(Material.BED, 1);
			ItemMeta closem = close.getItemMeta();
			closem.setDisplayName("§c§lClose");
			close.setItemMeta(closem);
			menu.setItem(0, new ItemStack(close));
		
			sender.openInventory(menu);
		
		}
		
	}
	
	public static void ownReports(Player sender) {
		
		Inventory menu = Bukkit.createInventory(null, 54, "Your reports");
		
		//Glass pane
		ItemStack gp = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta gpm = gp.getItemMeta();
		gpm.setDisplayName(" ");
		gp.setItemMeta(gpm);
		
		//Set glass pane
		menu.setItem(9, new ItemStack(gp));
		menu.setItem(10, new ItemStack(gp));
		menu.setItem(11, new ItemStack(gp));
		menu.setItem(12, new ItemStack(gp));
		menu.setItem(13, new ItemStack(gp));
		menu.setItem(14, new ItemStack(gp));
		menu.setItem(15, new ItemStack(gp));
		menu.setItem(16, new ItemStack(gp));
		menu.setItem(17, new ItemStack(gp));
		menu.setItem(45, new ItemStack(gp));
		menu.setItem(46, new ItemStack(gp));
		menu.setItem(47, new ItemStack(gp));
		menu.setItem(48, new ItemStack(gp));
		menu.setItem(49, new ItemStack(gp));
		menu.setItem(50, new ItemStack(gp));
		menu.setItem(51, new ItemStack(gp));
		menu.setItem(52, new ItemStack(gp));
		menu.setItem(53, new ItemStack(gp));
		
		//Info
		ItemStack inf = new ItemStack(Material.BOOK, 1);
		ItemMeta infm = inf.getItemMeta();
		infm.setDisplayName("§6§lYour reports");
		List<String> infl = new ArrayList<>();
		infl.add("");
		infl.add("§7Check your past reports' status");
		infm.setLore(infl);
		inf.setItemMeta(infm);
		menu.setItem(4, new ItemStack(inf));
		
		//Cancel button
		ItemStack canc = new ItemStack(Material.BED, 1);
		ItemMeta cancm = canc.getItemMeta();
		cancm.setDisplayName("§c§lCancel");
		canc.setItemMeta(cancm);
		menu.setItem(0, new ItemStack(canc));
		
		//Coming Soon!
		ItemStack cs = new ItemStack(Material.BARRIER, 1);
		ItemMeta csm = cs.getItemMeta();
		csm.setDisplayName("§c§lComing Soon!");
		List<String> csl = new ArrayList<>();
		csl.add("");
		csl.add("§6This feature is not");
		csl.add("§6implemented, yet!");
		csm.setLore(csl);
		cs.setItemMeta(csm);
		menu.setItem(31, new ItemStack(cs));
		
		sender.openInventory(menu);
		
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		ItemStack i = e.getCurrentItem();
		String m = e.getInventory().getName();
		
		if (m.contains("Report")) {
			
			if (i.getType() == Material.STAINED_GLASS_PANE) {
			
				e.setCancelled(true);
			
			} else if (i.getType() == Material.BOOK) {
		
				e.setCancelled(true);
			
			} else if (i.getType() == Material.PAPER) {
			
				e.setCancelled(true);
				ownReports(p);
				
			} else if (i.getType() == Material.BED) {
			
				e.setCancelled(true);
				p.closeInventory();
			
			} else if (i.getType() == Material.IRON_SWORD) {
			
				e.setCancelled(true);
				rGUIhack(target2, sender2, reason2);
			
			} else if (i.getType() == Material.BOOK_AND_QUILL) {
			
				e.setCancelled(true);
			
			} else if (i.getType() == Material.TNT) {
			
				e.setCancelled(true);
			
			}
		
		} else if (m.contains("Your reports")) {
			
			if (i.getType() == Material.BED) {
				
				e.setCancelled(true);
				p.closeInventory();
				
			}
			
		} else if (m.contains("Report (Hacking)")) {
			
			if (i.getType() == Material.BED) {
				
				e.setCancelled(true);
				p.closeInventory();
				
			} else if (i.getType() == Material.BOOK) {
				
				e.setCancelled(true);
				
			} else if (i.getType() == Material.IRON_SWORD) {
				
				e.setCancelled(true);
				
			}
				
		}
		
	}
	
}
