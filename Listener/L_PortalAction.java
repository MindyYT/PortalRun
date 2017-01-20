package Listener;

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
				if(e.getClickedBlock().getType() == Material.STAINED_GLASS){
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
		boolean click = e.getClick().isRightClick();
		
		// Inventar "dicht" machen das der Block nicht verrückbar ist
		if(e.getInventory().getName().equals("Portal Menu")){
			e.setCancelled(true);
			
			// Action at Itemclick
			// Deploy
			if(b == Material.DIAMOND_BLOCK && click == true){
				Inventory deploy = Bukkit.createInventory(null, 9, "Deploy");
				
				player.sendMessage("Deployed a resonator in this portal");
				
			}
			// Mods
			if(b == Material.IRON_BLOCK && click == true){
				Inventory mods = Bukkit.createInventory(null, 9, "MOds");
				
				player.sendMessage("Deployed a mod in this portal");
				
			}
		}
	}
}
