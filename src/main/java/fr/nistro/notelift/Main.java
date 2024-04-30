package fr.nistro.notelift;

import org.bukkit.plugin.java.JavaPlugin;

import fr.nistro.notelift.util.ConfigUtil;
import fr.nistro.notelift.listener.PlayerInteractListener;
import fr.nistro.notelift.listener.PlayerMoveListener;
import fr.nistro.notelift.listener.PlayerToggleSneakListener;
import fr.nistro.notelift.util.NoteBlockUtils;

public class Main extends JavaPlugin {
    
    public static String prefix;
    
    public void onEnable() {
    	// Enregistrement des gestionnaires d'événements
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerToggleSneakListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
   
        saveDefaultConfig();
        
        // Référencement du préfixe
        prefix = getConfig().getString("prefix"); 
        
        ConfigUtil config = new ConfigUtil(this, "config.yml");
        config.save();
        
        // Création de la recette du bloc de note
        getServer().addRecipe(NoteBlockUtils.getNoteBlockRecipe());
      
        getLogger().info("Plugin NoteLift activé !");
    }
    
    @Override
    public void onDisable() {
        getLogger().info("Plugin NoteLift désactivé !");
    }
    
	public static String getPrefix() {
		return prefix;
	}

}
