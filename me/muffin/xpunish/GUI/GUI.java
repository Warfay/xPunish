package me.muffin.xpunish.GUI;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.muffin.xpunish.Miscs.Actions;

public class GUI implements Listener {

	private static Player pl2 = null;
	private static String re2 = null;
	private static Player se2 = null;
	
	private int bcs = 0;
	private boolean ips = false;
	
	public static void PunishOnlineR(Player p, String r, Player s) {
		
		pl2 = p;
		re2 = r;
		se2 = s;
		
		Inventory menu = Bukkit.createInventory(null, 54, "Punishment (Online) 1/2");
		
		//Broadcast button
		ItemStack bc = new ItemStack(Material.BOOK, 1);
		ItemMeta bcm = bc.getItemMeta();
		bcm.setDisplayName("§6§lBroadcast");
		List<String> bcl = new ArrayList<>();
		bcl.add("");
		bcl.add("§fBroadcast a message");
		bcl.add("§fabout the punishment");
		bcl.add("");
		bcl.add("§7Off §aAll §5Staff");
		bcm.setLore(bcl);
		bc.setItemMeta(bcm);
		menu.setItem(15, new ItemStack(bc));
		
		//Network ban button
		ItemStack ip = new ItemStack(Material.ENDER_PEARL, 1);
		ItemMeta ipm = ip.getItemMeta();
		ipm.setDisplayName("§6§lNetwork Punishment");
		List<String> ipl = new ArrayList<>();
		ipl.add("");
		ipl.add("§fPunish all the players");
		ipl.add("§fwith the same IP-Address");
		ipl.add("");
		ipl.add("§7Off §aOn");
		ipm.setLore(ipl);
		ip.setItemMeta(ipm);
		menu.setItem(16, new ItemStack(ip));
		
		//Broadcast indicator
		ItemStack bci = new ItemStack(Material.INK_SACK, 1, (byte) 8);
		ItemMeta bcim = bci.getItemMeta();
		bcim.setDisplayName("§7Off");
		List<String> bcil = new ArrayList<>();
		bcil.add("");
		bcil.add("§fNo broadcast");
		bcim.setLore(bcil);
		bci.setItemMeta(bcim);
		menu.setItem(24, new ItemStack(bci));
		
		//Network ban indicator
		ItemStack ipi = new ItemStack(Material.INK_SACK, 1, (byte) 8);
		ItemMeta ipim = bci.getItemMeta();
		ipim.setDisplayName("§7Off");
		List<String> ipil = new ArrayList<>();
		ipil.add("");
		ipil.add("§fNo network punishment");
		ipim.setLore(ipil);
		ipi.setItemMeta(ipim);
		menu.setItem(25, new ItemStack(ipi));
		
		//Next page
		ItemStack np = new ItemStack(Material.ARROW, 1);
		ItemMeta npm = np.getItemMeta();
		npm.setDisplayName("§2§lNext page");
		np.setItemMeta(npm);
		menu.setItem(53, new ItemStack(np));
		
		//Hack info
		ItemStack hi = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta him = hi.getItemMeta();
		him.setDisplayName("§6§lClient Offense");
		List<String> hil = new ArrayList<>();
		hil.add("");
		hil.add("§7Type: §eBan");
		hil.add("");
		hil.add("§fHacked §7/ §fModified Client");
		hil.add("§fKillAura§7, §fFlight§7, §fX-Ray");
		him.setLore(hil);
		hi.setItemMeta(him);
		menu.setItem(9, new ItemStack(hi));
		
		//Hack Severity 1
		ItemStack h1 = new ItemStack(Material.INK_SACK, 1, (byte) 2);
		ItemMeta h1m = h1.getItemMeta();
		h1m.setDisplayName("§2§lSeverity 1");
		List<String> h1l = new ArrayList<>();
		h1l.add("§f--------------------------");
		h1l.add("§7Type: §eBan");
		h1l.add("§7Duration: §e1 Day");
		h1l.add("§7Reason: §e" + r);
		h1l.add("§f--------------------------");
		h1l.add("§7Examples:");
		h1l.add("    §eMinimap, Nametags,");
		h1l.add("    §eDamage Indicator...");
		h1l.add("§f--------------------------");
		h1m.setLore(h1l);
		h1.setItemMeta(h1m);
		menu.setItem(10, new ItemStack(h1));
		
		//Hack Severity 2
		ItemStack h2 = new ItemStack(Material.INK_SACK, 1, (byte) 11);
		ItemMeta h2m = h2.getItemMeta();
		h2m.setDisplayName("§2§lSeverity 2");
		List<String> h2l = new ArrayList<>();
		h2l.add("§f--------------------------");
		h2l.add("§7Type: §eBan");
		h2l.add("§7Duration: §e1 Week");
		h2l.add("§7Reason: §e" + r);
		h2l.add("§f--------------------------");
		h2l.add("§7Examples:");
		h2l.add("    §eTracers, X-Ray,");
		h2l.add("    §eCriticals...");
		h2l.add("§f--------------------------");
		h2m.setLore(h2l);
		h2.setItemMeta(h2m);
		menu.setItem(11, new ItemStack(h2));
		
		//Hack Severity 3
		ItemStack h3 = new ItemStack(Material.INK_SACK, 1, (byte) 14);
		ItemMeta h3m = h3.getItemMeta();
		h3m.setDisplayName("§2§lSeverity 3");
		List<String> h3l = new ArrayList<>();
		h3l.add("§f--------------------------");
		h3l.add("§7Type: §eBan");
		h3l.add("§7Duration: §e1 Month");
		h3l.add("§7Reason: §e" + r);
		h3l.add("§f--------------------------");
		h3l.add("§7Examples:");
		h3l.add("    §eSpeed, Flight, Regen,");
		h3l.add("    §eKillAura, AutoClicker...");
		h3l.add("§f--------------------------");
		h3m.setLore(h3l);
		h3.setItemMeta(h3m);
		menu.setItem(12, new ItemStack(h3));
		
		//Hack Severity 4
		ItemStack h4 = new ItemStack(Material.INK_SACK, 1, (byte) 1);
		ItemMeta h4m = h4.getItemMeta();
		h4m.setDisplayName("§2§lSeverity 4");
		List<String> h4l = new ArrayList<>();
		h4l.add("§f--------------------------");
		h4l.add("§7Type: §eBan");
		h4l.add("§7Duration: §e3 Months");
		h4l.add("§7Reason: §e" + r);
		h4l.add("§f--------------------------");
		h4l.add("§7Examples:");
		h4l.add("    §eKillAura, Flight, Speed,");
		h4l.add("    §eTimer, ForceOP...");
		h4l.add("§f--------------------------");
		h4m.setLore(h4l);
		h4.setItemMeta(h4m);
		menu.setItem(13, new ItemStack(h4));
		
		//Chat Offense
		ItemStack ci = new ItemStack(Material.BOOK_AND_QUILL, 1);
		ItemMeta cim = ci.getItemMeta();
		cim.setDisplayName("§6§lChat Offense");
		List<String> cil = new ArrayList<>();
		cil.add("");
		cil.add("§7Type: §eMute");
		cil.add("");
		cil.add("§fUsing inappropriate language§7,");
		cil.add("§fAdvertising§7, §fSpamming");
		cim.setLore(cil);
		ci.setItemMeta(cim);
		menu.setItem(18, new ItemStack(ci));
		
		//Chat Severity 1
		ItemStack c1 = new ItemStack(Material.INK_SACK, 1, (byte) 2);
		ItemMeta c1m = c1.getItemMeta();
		c1m.setDisplayName("§2§lSeverity 1");
		List<String> c1l = new ArrayList<>();
		c1l.add("§f--------------------------");
		c1l.add("§7Type: §eMute");
		c1l.add("§7Duration: §e1 Day");
		c1l.add("§7Reason: §e" + r);
		c1l.add("§f--------------------------");
		c1l.add("§7Examples:");
		c1l.add("    §eLight Swearing, Light");
		c1l.add("    §eAdvertisement");
		c1l.add("§f--------------------------");
		c1m.setLore(c1l);
		c1.setItemMeta(c1m);
		menu.setItem(19, new ItemStack(c1));
		
		//Chat Severity 2
		ItemStack c2 = new ItemStack(Material.INK_SACK, 1, (byte) 11);
		ItemMeta c2m = c2.getItemMeta();
		c2m.setDisplayName("§2§lSeverity 2");
		List<String> c2l = new ArrayList<>();
		c2l.add("§f--------------------------");
		c2l.add("§7Type: §eMute");
		c2l.add("§7Duration: §e1 Week");
		c2l.add("§7Reason: §e" + r);
		c2l.add("§f--------------------------");
		c2l.add("§7Examples:");
		c2l.add("    §eDecent Swearing, Decent");
		c2l.add("    §eAdvertisement, Spamming");
		c2l.add("§f--------------------------");
		c2m.setLore(c2l);
		c2.setItemMeta(c2m);
		menu.setItem(20, new ItemStack(c2));
		
		//Chat Severity 3
		ItemStack c3 = new ItemStack(Material.INK_SACK, 1, (byte) 14);
		ItemMeta c3m = c3.getItemMeta();
		c3m.setDisplayName("§2§lSeverity 3");
		List<String> c3l = new ArrayList<>();
		c3l.add("§f--------------------------");
		c3l.add("§7Type: §eMute");
		c3l.add("§7Duration: §e1 Month");
		c3l.add("§7Reason: §e" + r);
		c3l.add("§f--------------------------");
		c3l.add("§7Examples:");
		c3l.add("    §eMajor Swearing, Major");
		c3l.add("    §eAdvertisement, Spamming");
		c3l.add("§f--------------------------");
		c3m.setLore(c3l);
		c3.setItemMeta(c3m);
		menu.setItem(21, new ItemStack(c3));
		
		//Chat Severity 4
		ItemStack c4 = new ItemStack(Material.INK_SACK, 1, (byte) 1);
		ItemMeta c4m = c4.getItemMeta();
		c4m.setDisplayName("§2§lSeverity 4");
		List<String> c4l = new ArrayList<>();
		c4l.add("§f--------------------------");
		c4l.add("§7Type: §eMute");
		c4l.add("§7Duration: §e3 Months");
		c4l.add("§7Reason: §e" + r);
		c4l.add("§f--------------------------");
		c4l.add("§7Examples:");
		c4l.add("    §eMajor Swearing, Major");
		c4l.add("    §eAdvertisement, Spamming");
		c4l.add("§f--------------------------");
		c4m.setLore(c4l);
		c4.setItemMeta(c4m);
		menu.setItem(22, new ItemStack(c4));
		
		//Abusement
		ItemStack ai = new ItemStack(Material.LAVA_BUCKET, 1);
		ItemMeta aim = ai.getItemMeta();
		aim.setDisplayName("§6§lAbusement");
		List<String> ail = new ArrayList<>();
		ail.add("");
		ail.add("§7Type: §eBan");
		ail.add("");
		ail.add("§fAbusing bugs §7/ §fglitches");
		aim.setLore(ail);
		ai.setItemMeta(aim);
		menu.setItem(27, new ItemStack(ai));
		
		//Abuse Severity 1
		ItemStack a1 = new ItemStack(Material.INK_SACK, 1, (byte) 2);
		ItemMeta a1m = a1.getItemMeta();
		a1m.setDisplayName("§2§lSeverity 1");
		List<String> a1l = new ArrayList<>();
		a1l.add("§f--------------------------");
		a1l.add("§7Type: §eBan");
		a1l.add("§7Duration: §e1 Day");
		a1l.add("§7Reason: §e" + r);
		a1l.add("§f--------------------------");
		a1l.add("§7Examples:");
		a1l.add("    §eAbusing minor bugs / glitches");
		a1l.add("§f--------------------------");
		a1m.setLore(a1l);
		a1.setItemMeta(a1m);
		menu.setItem(28, new ItemStack(a1));
		
		//Abuse Severity 2
		ItemStack a2 = new ItemStack(Material.INK_SACK, 1, (byte) 11);
		ItemMeta a2m = a2.getItemMeta();
		a2m.setDisplayName("§2§lSeverity 2");
		List<String> a2l = new ArrayList<>();
		a2l.add("§f--------------------------");
		a2l.add("§7Type: §eBan");
		a2l.add("§7Duration: §e1 Week");
		a2l.add("§7Reason: §e" + r);
		a2l.add("§f--------------------------");
		a2l.add("§7Examples:");
		a2l.add("    §eAbusing map glitches");
		a2l.add("    §efor average advantage");
		a2m.setLore(a2l);
		a2.setItemMeta(a2m);
		menu.setItem(29, new ItemStack(a2));
		
		//Abuse Severity 3
		ItemStack a3 = new ItemStack(Material.INK_SACK, 1, (byte) 14);
		ItemMeta a3m = a3.getItemMeta();
		a3m.setDisplayName("§2§lSeverity 3");
		List<String> a3l = new ArrayList<>();
		a3l.add("§f--------------------------");
		a3l.add("§7Type: §eBan");
		a3l.add("§7Duration: §e1 Month");
		a3l.add("§7Reason: §e" + r);
		a3l.add("§f--------------------------");
		a3l.add("§7Examples:");
		a3l.add("    §eUsing bugs / glitches");
		a3l.add("    §efor significant advantage");
		a3l.add("§f--------------------------");
		a3m.setLore(a3l);
		a3.setItemMeta(a3m);
		menu.setItem(30, new ItemStack(a3));
		
		//Abuse Severity 4
		ItemStack a4 = new ItemStack(Material.INK_SACK, 1, (byte) 1);
		ItemMeta a4m = a4.getItemMeta();
		a4m.setDisplayName("§2§lSeverity 4");
		List<String> a4l = new ArrayList<>();
		a4l.add("§f--------------------------");
		a4l.add("§7Type: §eBan");
		a4l.add("§7Duration: §e3 Months");
		a4l.add("§7Reason: §e" + r);
		a4l.add("§f--------------------------");
		a4l.add("§7Examples:");
		a4l.add("    §eUsing bugs / glitches");
		a4l.add("    §efor significant advantage");
		a4l.add("§f--------------------------");
		a4m.setLore(a4l);
		a4.setItemMeta(a4m);
		menu.setItem(31, new ItemStack(a4));
		
		s.openInventory(menu);
		
	}
	
	@SuppressWarnings({ "deprecation" })
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		
		ItemStack i = e.getCurrentItem();
		String m = e.getInventory().getName();
		
		if (m.contains("Punishment (Offline) 1/2")) {
			
			if (i.getType() == Material.BOOK) {
				
				e.setCancelled(true);
				
			} else if (i.getType() == Material.LEATHER_BOOTS) {
				
				e.setCancelled(true);
				
			} else if (i.getType() == Material.REDSTONE_BLOCK) {
				
				e.setCancelled(true);
				
			} else if (i.getType() == Material.BOOK_AND_QUILL) {
				
				e.setCancelled(true);
				
			} else if (i.getType() == Material.PAPER) {
				
				e.setCancelled(true);
				
			}
			
		} else if (m.contains("Punishment (Online) 1/2")) {
			
			if (i.getType() == Material.BOOK_AND_QUILL) {
				
				e.setCancelled(true);
				
			//Check severity
			} else if (i.getType() == Material.INK_SACK) {
				
				if (i.getData().getData() == (byte) 2) {
					
					//HackSev1
					if (i.getItemMeta().getLore().get(6).contains("Minimap")) {
						
						e.setCancelled(true);
						Actions.HackSev1RO(pl2, re2, se2, bcs, ips);
						
					//ChatSev1
					} else if (i.getItemMeta().getLore().get(6).contains("Light Swearing")) {
						
						e.setCancelled(true);
						
					}
					
				} else if (i.getItemMeta().getDisplayName().contains("Off")) {
					
					if (i.getItemMeta().getLore().get(1).contains("No broadcast")) {
						
						e.setCancelled(true);
						ItemStack bc2 = new ItemStack(Material.INK_SACK, 1, (byte) 10);
						ItemMeta bc2m = bc2.getItemMeta();
						bc2m.setDisplayName("§aAll");
						List<String> bc2l = new ArrayList<>();
						bc2l.add("");
						bc2l.add("§fBroadcast");
						bc2l.add("§fto everyone");
						bc2m.setLore(bc2l);
						bc2.setItemMeta(bc2m);
						e.getInventory().setItem(24, new ItemStack(bc2));
						
						bcs = 1;
						
						
					} else if (i.getItemMeta().getLore().get(1).contains("No network punishment")) {
						
						e.setCancelled(true);
						ItemStack ip2 = new ItemStack(Material.INK_SACK, 1, (byte) 10);
						ItemMeta ip2m = ip2.getItemMeta();
						ip2m.setDisplayName("§aOn");
						List<String> ip2l = new ArrayList<>();
						ip2l.add("");
						ip2l.add("§fPunish all players");
						ip2l.add("§fwith the same IP-Address");
						ip2m.setLore(ip2l);
						ip2.setItemMeta(ip2m);
						e.getInventory().setItem(25, new ItemStack(ip2));
						
					}
				
				} else if (i.getItemMeta().getDisplayName().contains("All")) {
					
					if (i.getItemMeta().getLore().get(1).contains("Broadcast")) {
						
						e.setCancelled(true);
						ItemStack bc3 = new ItemStack(Material.INK_SACK, 1, (byte) 5);
						ItemMeta bc3m = bc3.getItemMeta();
						bc3m.setDisplayName("§5Staff");
						List<String> bc3l = new ArrayList<>();
						bc3l.add("");
						bc3l.add("§fBroadcast to");
						bc3l.add("§fstaff members");
						bc3m.setLore(bc3l);
						bc3.setItemMeta(bc3m);
						e.getInventory().setItem(24, new ItemStack(bc3));
						
						bcs = 2;
						
					}
					
				} else if (i.getItemMeta().getDisplayName().contains("Staff")) {
					
					e.setCancelled(true);
					ItemStack bc4 = new ItemStack(Material.INK_SACK, 1, (byte) 8);
					ItemMeta bc4m = bc4.getItemMeta();
					bc4m.setDisplayName("§7Off");
					List<String> bc4l = new ArrayList<>();
					bc4l.add("");
					bc4l.add("§fNo broadcast");
					bc4m.setLore(bc4l);
					bc4.setItemMeta(bc4m);
					e.getInventory().setItem(24, new ItemStack(bc4));
					
					bcs = 0;
					
				} else if (i.getItemMeta().getDisplayName().contains("On")) {
					
					e.setCancelled(true);
					ItemStack ip3 = new ItemStack(Material.INK_SACK, 1, (byte) 8);
					ItemMeta ip3m = ip3.getItemMeta();
					ip3m.setDisplayName("§7Off");
					List<String> ip3l = new ArrayList<>();
					ip3l.add("");
					ip3l.add("§fNo network punishment");
					ip3m.setLore(ip3l);
					ip3.setItemMeta(ip3m);
					e.getInventory().setItem(25, new ItemStack(ip3));
					
				}
				
			}
			
		}
		
	}
	
}
