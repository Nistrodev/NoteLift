package fr.nistro.notelift.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class TeleportUtils implements Listener {
    
    // Méthode statique pour vérifier si l'espace au-dessus du bloc spécifié est libre
    public static boolean isFreeSpaceAbove(int x, int y, int z) {
        int maxYBlock = y + 3; // Limite supérieure de la recherche (3 blocs au-dessus)

        // Parcourir les blocs au-dessus du bloc spécifié jusqu'à la limite supérieure
        for (int i = y + 1; i < maxYBlock; i++) {
            // Vérifier si le bloc au-dessus n'est pas vide
            if (!Bukkit.getWorld("world").getBlockAt(x, i, z).isEmpty()) {
                return false; // L'espace n'est pas libre
            }
        }

        return true; // L'espace est libre
    }
    
    // Méthode pour ajouter des particules de téléportation
    public static void addTeleportParticles(int x, int y, int z) {
        // Ajouter des particules de téléportation
        World world = Bukkit.getWorld("world");
        if (world != null) {
            world.spawnParticle(Particle.PORTAL, x + 0.5, y + 0.5, z + 0.5, 100, 0.8, 0.8, 0.8, 0.1);
        } else {
            System.out.println("Le monde spécifié n'existe pas !");
        }
    }
    
    // Méthode pour téléporter le joueur à la position spécifiée
	public static void TeleportPlayer(int x, int y, int z, float yaw, float pitch, Player player) {
		Location newLocation = player.getWorld().getBlockAt(x, y + 1, z).getLocation().add(0.5, 0, 0.5);
    	// Définir la nouvelle position avec l'ancienne orientation
    	newLocation.setYaw(yaw);
    	newLocation.setPitch(pitch);
    	player.teleport(newLocation);
    	
        player.playSound(newLocation, Sound.ENTITY_ENDERMEN_TELEPORT, 1.0f, 1.0f);
    	
    	// Ajouter des particules de téléportation
    	addTeleportParticles(x, y, z);
	}
}
