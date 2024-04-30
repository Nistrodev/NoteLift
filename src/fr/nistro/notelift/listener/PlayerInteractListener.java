package fr.nistro.notelift.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		// Si un joueur interagit avec un bloc de note
		if (event.getClickedBlock().getType() == Material.NOTE_BLOCK) {
			// Si il fait un clic droit
			if (event.getAction().toString().contains("RIGHT")) {				
				// Annule l'interaction
				event.setCancelled(true);
			}
		}
	}
}
