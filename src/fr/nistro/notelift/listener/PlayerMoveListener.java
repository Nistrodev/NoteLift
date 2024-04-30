package fr.nistro.notelift.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.nistro.notelift.Main; // Importer la classe Main pour accéder au préfixe
import fr.nistro.notelift.util.NoteBlockUtils; // Importer la classe NoteBlockUtils pour les vérifications des blocs de note
import fr.nistro.notelift.util.TeleportUtils; // Importer la classe TeleportUtils pour les vérifications de l'espace libre

public class PlayerMoveListener implements Listener {

    private final double JUMP_THRESHOLD = 0.2; // Seuil de saut du joueur

    private double lastY = 0; // Dernière position Y du joueur
    
    // Gestion de l'événement PlayerMove
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        // Ignorer les mouvements annulés
        if (event.isCancelled()) return;
        
        // Récupérer le joueur
        Player player = event.getPlayer();
        
        // Vérifier si le joueur est en mode "fly"
        if (!player.isFlying()) {
            double currentY = event.getTo().getY(); // Position Y actuelle du joueur
            double deltaY = currentY - lastY; // Calcul de la variation de la position Y
            
            // Si la variation de la position Y dépasse le seuil de saut
            if (deltaY > JUMP_THRESHOLD) {
                // Vérifier si un bloc de note se trouve en dessous du joueur
                if (NoteBlockUtils.isNoteBlockBelow(player)) {
                    Integer[] noteBlockCoords = NoteBlockUtils.isNoteBlockAboveBlock(player);
                    if (noteBlockCoords != null) {
                        // Vérifier si l'espace au-dessus du bloc de note est libre
                        if (TeleportUtils.isFreeSpaceAbove(noteBlockCoords[0], noteBlockCoords[1], noteBlockCoords[2])) {
                        	// Téléporter le joueur au-dessus du bloc de note au milieu du bloc
                        	// Sauvegarder l'orientation du joueur
                        	float yaw = player.getLocation().getYaw();
                        	float pitch = player.getLocation().getPitch();
                        	// Téléporter le joueur
                        	TeleportUtils.TeleportPlayer(noteBlockCoords[0], noteBlockCoords[1], noteBlockCoords[2], yaw, pitch, player);
                            player.sendMessage(Main.getPrefix() + "§aTéléportation réussie !");
                        } else {
                            player.sendMessage(Main.getPrefix() + "§cIl n'y a pas assez de place pour se téléporter !");
                        }
                    }
                }
            }
        }

        // Mettre à jour la dernière position Y du joueur
        lastY = event.getTo().getY();
    }
}
