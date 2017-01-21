package Listener;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


import me.mindyyt.PortalRun.PortalRun;
import net.md_5.bungee.api.ChatColor;

public class L_PortalAction implements Listener{
	// Instance of the main class
	private PortalRun plugin;
	public L_PortalAction(PortalRun pr){
		pr = plugin;
	}
	
	// PlayerClick Event Block
	@EventHandler
	static void onPortalClick(PlayerInteractEvent e){
		/*
		 * Check if:
		 * 1) action == Right Click Block AND EquipmentSlot == Hand
		 * 2) Object in Hand == WATCH
		 * 3) Clicked Block = STAINED_GLASS
		 */
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getHand().equals(EquipmentSlot.HAND)){
			if(e.getPlayer().getItemInHand().getType() == Material.WATCH){
				if(e.getClickedBlock().getType() == Material.STAINED_GLASS && e.getClickedBlock().getData() == (byte)11 || e.getClickedBlock().getData() == (byte)13){
					/*
					 * Create an inventory with 5 items for portal actions
					 */
					
					
					// Hack Item
					ItemStack hack = new ItemStack(Material.GOLD_BLOCK);
					ItemMeta hackMeta = hack.getItemMeta();
					hackMeta.setDisplayName(ChatColor.AQUA + "Hack");
					hack.setItemMeta(hackMeta);
					
					// Deploy Item
					ItemStack deploy = new ItemStack(Material.DIAMOND_BLOCK);
					ItemMeta deployMeta = deploy.getItemMeta();
					deployMeta.setDisplayName(ChatColor.YELLOW + "Deploy");
					deploy.setItemMeta(deployMeta);
					
					// Link Portal Item
					ItemStack createLink = new ItemStack(Material.EMERALD_BLOCK);
					ItemMeta createLinkMeta = createLink.getItemMeta();
					createLinkMeta.setDisplayName(ChatColor.GRAY + "Link");
					createLink.setItemMeta(createLinkMeta);
					
					// Mod Item
					ItemStack setMod = new ItemStack(Material.IRON_BLOCK);
					ItemMeta setModMeta = setMod.getItemMeta();
					setModMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Mod");
					setMod.setItemMeta(setModMeta);
					
					// Recharge Item
					ItemStack recharge = new ItemStack(Material.REDSTONE_BLOCK);
					ItemMeta rechargeMeta = recharge.getItemMeta();
					rechargeMeta.setDisplayName(ChatColor.WHITE + "Recharge");
					recharge.setItemMeta(rechargeMeta);
								
					// Create a new inventory
					Inventory inv = Bukkit.createInventory(null, 27, "Portal Menu");
					
					// Creating Clickable Items
					inv.setItem(4, hack);
					inv.setItem(12, deploy);
					inv.setItem(13, createLink);
					inv.setItem(14, setMod);
					inv.setItem(22, recharge);
					
					
					// Display the inventory to the player
					e.getPlayer().openInventory(inv);
					
					// Inventory if the clicked block == glas
				}else if(e.getClickedBlock().getType() == Material.STAINED_GLASS && e.getClickedBlock().getData() == (byte)0){
					
					// Hack Item
					ItemStack hack = new ItemStack(Material.GOLD_BLOCK);
					ItemMeta hackMeta = hack.getItemMeta();
					hackMeta.setDisplayName(ChatColor.AQUA + "Hack");
					hack.setItemMeta(hackMeta);
					
					// Deploy Item
					ItemStack deploy = new ItemStack(Material.DIAMOND_BLOCK);
					ItemMeta deployMeta = deploy.getItemMeta();
					deployMeta.setDisplayName(ChatColor.YELLOW + "Deploy");
					deploy.setItemMeta(deployMeta);
					
					Inventory inv = Bukkit.createInventory(null, 27, "Portal Menu");
					
					// Creating Clickable Items
					inv.setItem(4, hack);
					inv.setItem(22, deploy);
					
					// Display the inventory to the player
					e.getPlayer().openInventory(inv);
				}
			}
		}	
	}
	
	// Inventory Click Event
	@EventHandler
	// Check which inventory is used
	public void onInventoryClick(InventoryClickEvent e){
		
		// Get the player
		Player player = (Player) e.getWhoClicked();
		Material b = e.getCurrentItem().getType();
		boolean click = e.getClick().isRightClick() || e.getClick().isLeftClick();
		
		// Inventar "dicht" machen dass der Block nicht verrückbar ist
		if(e.getInventory().getName().equals("Portal Menu") || e.getInventory().getName().equals("Deploy") || e.getInventory().getName().equals("Mods")){
			e.setCancelled(true);
			
			// Action on Itemclick
			
			// Hack
			if(b == Material.GOLD_BLOCK && click == true){
				
				// Items for the ArrayList
				
				//Resonator
				ItemStack reso = new ItemStack(Material.COAL);
				ItemMeta resoMeta = reso.getItemMeta();
				resoMeta.setDisplayName("Resonator");
				reso.setItemMeta(resoMeta);
				
				// Mod
				ItemStack shield = new ItemStack(Material.DIAMOND);
				ItemMeta shieldMeta = shield.getItemMeta();
				shieldMeta.setDisplayName("Shield");
				shield.setItemMeta(shieldMeta);
				
				// Key
				ItemStack key = new ItemStack(Material.EMERALD);
				ItemMeta keyMeta = key.getItemMeta();
				keyMeta.setDisplayName("Key");
				key.setItemMeta(keyMeta);
				
				// Burster
				ItemStack burster = new ItemStack(Material.ARROW);
				ItemMeta bursterMeta = key.getItemMeta();
				bursterMeta.setDisplayName("Burster");
				burster.setItemMeta(bursterMeta);
				
				// ArrayList for the loot
				ArrayList<ItemStack> items = new ArrayList();
				items.add(reso);
				items.add(shield);
				items.add(key);
				items.add(burster);
				
				//Random Generator
				for(int x = 0; x < items.size(); x++){
					Random rand = new Random();
					int randNum = rand.nextInt(5);
					items.get(x).setAmount(randNum);
					
				}
				
				// Player gets the items
				player.getInventory().addItem(reso);
				player.getInventory().addItem(shield);
				player.getInventory().addItem(key);
				player.getInventory().addItem(burster);
				
				
				// Placeholder for action
				player.sendMessage(ChatColor.GREEN + "You successfully hacked the portal.");
				player.sendMessage(ChatColor.GRAY + "The hack requires the following items: ");
				player.sendMessage(ChatColor.GRAY + " " + items.get(0).getAmount() + " " + items.get(0).getItemMeta().getDisplayName());
				player.sendMessage(ChatColor.GRAY + " " + items.get(1).getAmount() + " " + items.get(1).getItemMeta().getDisplayName());
				player.sendMessage(ChatColor.GRAY + " " + items.get(2).getAmount() + " " + items.get(2).getItemMeta().getDisplayName());
				player.sendMessage(ChatColor.GRAY + " " + items.get(3).getAmount() + " " + items.get(3).getItemMeta().getDisplayName());
				player.sendMessage(ChatColor.GRAY + " " + items.get(4).getAmount() + " " + items.get(4).getItemMeta().getDisplayName());
				
			}
			
			
			// Deploy
			if(b == Material.DIAMOND_BLOCK && click == true){
				
				
				Inventory deploy = Bukkit.createInventory(null, 9, "Deploy");
				
				//Testitem
				ItemStack deployReso = new ItemStack(Material.APPLE);
				ItemMeta deployResoMeta = deployReso.getItemMeta();
				deployResoMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Eat an apple :D");
				deployReso.setItemMeta(deployResoMeta);
				
				deploy.setItem(0, deployReso);
				
				
				player.openInventory(deploy);
				
				
			}
			
			// Mods
			if(b == Material.IRON_BLOCK && click == true){
				Inventory mods = Bukkit.createInventory(null, 9, "Mods");
				
				// Testitem
				ItemStack deployMod = new ItemStack(Material.APPLE);
				ItemMeta deployModMeta = deployMod.getItemMeta();
				deployModMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Eat an apple :D");
				deployMod.setItemMeta(deployModMeta);
				
				mods.setItem(0, deployMod);
				
				player.openInventory(mods);
			}
			
			// Recharge
			if(b == Material.REDSTONE_BLOCK && click == true){
							
				// Placeholder
				player.sendMessage(ChatColor.GRAY + "You recharged the portal.");;
			}
			
			// Recharge
			if(b == Material.EMERALD_BLOCK && click == true){
										
				// Placeholder
				player.sendMessage(ChatColor.GRAY + "Do you have any key of any portals?.");;
			}
		}
	}
}
