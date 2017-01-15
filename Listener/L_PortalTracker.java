package Listener;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import me.mindyyt.PortalRun.PortalRun;
import net.md_5.bungee.api.ChatColor;

public class L_PortalTracker implements Listener{

	// Instanz setzen für die Maineinbindung
	private PortalRun plugin;
	public L_PortalTracker(PortalRun pr){
		pr = plugin;
	}
	
	@EventHandler
	public void onCompassInteract(PlayerInteractEvent e){
		
		// Define Variables
		Player p = e.getPlayer();
		Action action = e.getAction();
		
		// Check if the item in hand of player equals compass && the slot == hand
		if(p.getItemInHand().getType() == Material.COMPASS && e.getHand().equals(EquipmentSlot.HAND)){
			
			// Check if the player made a rightclick
			if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK){
				
				// Get the location of the player
				Location loc = p.getLocation();
				
				
				// Set the min range around the player
				int minX = p.getLocation().getBlockX() - 50;
				int minY = p.getLocation().getBlockY() - 25;
				int minZ = p.getLocation().getBlockZ() - 50;
				
				// Set the max range around the player
				int maxX = p.getLocation().getBlockX() + 50;
				int maxY = p.getLocation().getBlockY() + 25;
				int maxZ = p.getLocation().getBlockZ() + 50;
				
				// For loops for "scanning" the blocks
				for(int x = minX; x <= maxX; x++){
					for(int y = minY; y <= maxY; y++){
						for(int z = minZ; z <= maxZ; z++){
							
							// Check if there is an beacon in this area
							Location locBlock = new Location(p.getWorld(),x,y,z);
							Block beacon = p.getWorld().getBlockAt(locBlock);
							
							// Output for the player
							if(beacon.getType() == Material.BEACON){
								
								// Check Player positon
								int pLocX = p.getLocation().getBlockX();
								int pLocZ = p.getLocation().getBlockZ();
								int pLocY = p.getLocation().getBlockY();
								
								// Check Block location
								int bLocX = beacon.getLocation().getBlockX();
								int bLocZ = beacon.getLocation().getBlockZ();
								int bLocY = beacon.getLocation().getBlockY();
								
								// Get the difference between player and beacon
								
								// X Axis
								if(pLocX - bLocX > 50 || -(pLocX) - (-bLocX) > 50){
									p.sendMessage(ChatColor.RED + "There is a portal nearby");
								}else if(pLocX - bLocX <= 50 && pLocX - bLocX > 25 || (-pLocX) - (-bLocX) <= 50 && (-pLocX) - (-bLocX) > 25 ){
									p.sendMessage(ChatColor.YELLOW + "There is a portal nearby");
								}else{
									p.sendMessage(ChatColor.GREEN + "There is a portal nearby");
								}
								
								// Z Axis
								if(pLocZ - bLocZ > 50 || -(pLocZ) - (-bLocZ) > 50){
									p.sendMessage(ChatColor.RED + "There is a portal nearby");
								}else if(pLocZ - bLocZ <= 50 && pLocZ - bLocZ > 25 || (-pLocZ) - (-bLocZ) <= 50 && (-pLocZ) - (-bLocZ) > 25 ){
									p.sendMessage(ChatColor.YELLOW + "There is a portal nearby");
								}else{
									p.sendMessage(ChatColor.GREEN + "There is a portal nearby");
								}
							}
						}
					}
				}
			}
		}
	}	
}
