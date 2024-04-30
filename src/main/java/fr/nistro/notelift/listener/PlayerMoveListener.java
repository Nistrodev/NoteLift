package fr.nistro.notelift.listener;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.nistro.notelift.Main; // Importer la classe Main pour accéder au préfixe
import fr.nistro.notelift.util.NoteBlockUtils; // Importer la classe NoteBlockUtils pour les vérifications des blocs de note
import fr.nistro.notelift.util.TeleportUtils; // Importer la classe TeleportUtils pour les vérifications de l'espace libre

public class PlayerMoveListener implements Listener {

    
    private Map<Player, Double> playerY = new HashMap<>();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
      // Ignorer les mouvements annulés
      if (event.isCancelled()) return;
      
      // Récupérer le joueur
      Player player = event.getPlayer();
      
      // Vérifier si le joueur est en mode "fly"
		if (player.isFlying()) {
			return;
		}
    	
    	// Si le joueur saute
		if (event.getTo().getY() > event.getFrom().getY() && !playerY.containsKey(event.getPlayer())) {
			if (NoteBlockUtils.isNoteBlockBelow(player)) {
              Integer[] noteBlockCoords = NoteBlockUtils.isNoteBlockAboveBlock(player);
              if (noteBlockCoords != null) {
                  // Vérifier si l'espace au-dessus du bloc de note est libre
                  if (TeleportUtils.isFreeSpaceAbove(noteBlockCoords[0], noteBlockCoords[1], noteBlockCoords[2], player)) {
                  	// Téléporter le joueur au-dessus du bloc de note au milieu du bloc
                  	// Sauvegarder l'orientation du joueur
                  	float yaw = player.getLocation().getYaw();
                  	float pitch = player.getLocation().getPitch();
                  	// Téléporter le joueur
                  	TeleportUtils.TeleportPlayer(noteBlockCoords[0], noteBlockCoords[1], noteBlockCoords[2], yaw, pitch, player);
                  	if (Bukkit.getPluginManager().getPlugin("NoteLift").getConfig().getBoolean("message-when-teleporting")) {
                  		player.sendMessage(Main.getPrefix() + Bukkit.getPluginManager().getPlugin("NoteLift").getConfig().getString("messages.teleport-success"));
                  	}
                  	
                  	} else {
                  		if (Bukkit.getPluginManager().getPlugin("NoteLift").getConfig().getBoolean("error-message")) {
                      		player.sendMessage(Main.getPrefix() + Bukkit.getPluginManager().getPlugin("NoteLift").getConfig().getString("messages.not-enough-space"));
                      	}
                  }
              }
			}
        }
    }
}
