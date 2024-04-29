package fr.nistro.notelift.util;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class NoteBlockUtils implements Listener {
    
    // Méthode statique pour vérifier si un bloc de note se trouve en dessous du joueur
    public static boolean isNoteBlockBelow(Player player) {
        // Récupérer la position du joueur
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY() - 1; // Décaler d'un bloc vers le bas
        int z = player.getLocation().getBlockZ();

        // Vérifier si le bloc en dessous du joueur est un bloc de note
        return player.getWorld().getBlockAt(x, y, z).getType().name().contains("NOTE_BLOCK");
    }

    // Méthode statique pour trouver les coordonnées du bloc de note en dessous du joueur
    public static Integer[] isNoteBlockBelowBlock(Player player) {
        // Récupérer la position du joueur
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY() - 2; // Décaler de deux blocs vers le bas
        int z = player.getLocation().getBlockZ();

        // Limite inférieure de la recherche
        int minY = 0;

        // Parcourir les blocs vers le bas à partir de la position actuelle du joueur
        for (int i = y; i >= minY; i--) {
            // Vérifier si le bloc est un bloc de note
            if (player.getWorld().getBlockAt(x, i, z).getType().name().contains("NOTE_BLOCK")) {
                // Retourner les coordonnées du bloc de note trouvé
                return new Integer[] {x, i, z};
            }
        }

        return null; // Aucun bloc de note trouvé en dessous du joueur
    }
    
    // Méthode statique pour trouver les coordonnées du bloc de note au-dessus du joueur
    public static Integer[] isNoteBlockAboveBlock(Player player) {
        // Récupérer la position du joueur
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY() + 1; // Décaler d'un bloc vers le haut
        int z = player.getLocation().getBlockZ();

        // Limite supérieure de la recherche
        int maxY = player.getWorld().getMaxHeight();

        // Parcourir les blocs vers le haut à partir de la position actuelle du joueur
        for (int i = y; i <= maxY; i++) {
            // Vérifier si le bloc est un bloc de note
            if (player.getWorld().getBlockAt(x, i, z).getType().name().contains("NOTE_BLOCK")) {
                // Retourner les coordonnées du bloc de note trouvé
                return new Integer[] {x, i, z};
            }
        }

        return null; // Aucun bloc de note trouvé au-dessus du joueur
    }
}
