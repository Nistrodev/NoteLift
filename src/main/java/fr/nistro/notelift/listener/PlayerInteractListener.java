package fr.nistro.notelift.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction().toString().contains("RIGHT")) {
			if (event.getClickedBlock() != null) {				
				if (event.getClickedBlock().getType() == Material.NOTE_BLOCK) {
					// Annule l'interaction
					event.setCancelled(true);
				}
			}
		}
	}


	// Anulle l'interaction des crafts
	@EventHandler
	public void onPlayerCraft(CraftItemEvent event) {
		if (event.getRecipe().getResult().getType() == Material.NOTE_BLOCK && event.getRecipe().getResult().getItemMeta().getDisplayName() == null) {
			// Annule le craft
			event.setCancelled(true);
		}

	}
}
