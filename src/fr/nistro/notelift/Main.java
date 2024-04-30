package fr.nistro.notelift;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import fr.nistro.notelift.listener.PlayerInteractListener;
import fr.nistro.notelift.listener.PlayerMoveListener;
import fr.nistro.notelift.listener.PlayerToggleSneakListener;

public class Main extends JavaPlugin {
    
    public static String prefix;
    
    public void onEnable() {
    	// Enregistrement des gestionnaires d'événements
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerToggleSneakListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
   
        // Référencement du préfixe
		PluginDescriptionFile pdfFile = this.getDescription();
		        
        prefix = pdfFile.getPrefix();
      
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
