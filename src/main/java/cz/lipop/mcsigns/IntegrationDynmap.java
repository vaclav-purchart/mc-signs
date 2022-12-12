package cz.lipop.mcsigns;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.dynmap.DynmapAPI;
import org.dynmap.markers.MarkerIcon;
import org.dynmap.markers.MarkerSet;

// Inspired from https://github.com/Sytm/waypoints/blob/v3/master/waypoints/src/main/kotlin/de/md5lukas/waypoints/integrations/DynMapIntegration.kt
public class IntegrationDynmap implements Listener {
	McSigns plugin;
	MarkerSet markerSet;
	MarkerIcon defaultMarkerIcon;
	boolean enabled = false;

	public IntegrationDynmap(McSigns plugin) {
		this.plugin = plugin;
		
		Plugin dynmapPluginInstance = this.plugin.getServer().getPluginManager().getPlugin("dynmap");
		if (dynmapPluginInstance == null) {
			this.enabled = false;
			return;
		}
		
		this.plugin.getLogger().log(Level.INFO, "Found DynMap plugin");
        this.enabled = true;
		
		var dynmap = (DynmapAPI)dynmapPluginInstance;
		// https://github.com/webbukkit/dynmap/blob/v3.0/DynmapCoreAPI/src/main/java/org/dynmap/markers/MarkerAPI.java
        var markerApi = dynmap.getMarkerAPI();
        // https://github.com/webbukkit/dynmap/wiki/Using-Markers#marker-icons
        this.defaultMarkerIcon = markerApi.getMarkerIcon("pin");

        this.markerSet = markerApi.createMarkerSet(
            "mcsigns_public",
            "McSigns",
            null,
            false
        );
        
        List<String> warps = ListWarps.list(this.plugin);
    	warps.forEach(name -> {
			try {
				this.createMarker(Warp.readFromFile(name, this.plugin));
			} catch (IOException e) {
				this.plugin.getLogger().log(Level.WARNING, "Warp not found! " + e.getMessage());
			}
		});
	}

	public void createMarker(Warp warp) {
		if (!this.enabled) {
			this.plugin.getLogger().log(Level.WARNING, "Dynmap integration is not enabled, cannot add warp");
			return;
		}
		
		this.plugin.getLogger().log(Level.INFO, "Adding Dynmap warp " + warp.name);
		markerSet.createMarker(warp.name, // ID
			warp.name, // Label
			warp.world, // World ID
			warp.x, // X
			warp.y, // Y
			warp.z, // z
			this.defaultMarkerIcon, // Marker icon
			false // is persistent - lost during restarts  
		);
	}
}
