package cz.lipop.mcsigns;

import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class McSigns extends JavaPlugin implements Listener {
	@Override
	public void onDisable() {
		// Don't log disabling, Spigot does that for you automatically!
	}

	@Override
	public void onEnable() {
		// Don't log enabling, Spigot does that for you automatically!
		getServer().getPluginManager().registerEvents(this, this);

		// Commands enabled with following method must have entries in plugin.yml
		getCommand("warp").setExecutor(new WarpCommand(this));
		getCommand("warps").setExecutor(new WarpsCommand(this));
		getCommand("setwarp").setExecutor(new SetWarpCommand(this));
		getCommand("loc").setExecutor(new SetWarpCommand(this));
		getCommand("repair").setExecutor(new RepairCommand(this));
	}

	@EventHandler
	public void onSignChange(SignChangeEvent e) throws IOException {
		Player p = e.getPlayer();
		System.out.println("Sign adds: " + e.getLine(0));
		String command = e.getLine(0);

		if (command.equalsIgnoreCase("[warp]")) {
			List<String> warps = ListWarps.list(this);
			String warpName = e.getLine(1);
			if (warps.indexOf(warpName) >= 0) {
				e.setLine(0, "§9[warp]");
			}
			else {
				e.setLine(0, "§c[warp]");
			}
		}
		
		if (command.equalsIgnoreCase("[loc]")) {
			e.setLine(0, "§9[loc]");
			String warpName = e.getLine(1).toLowerCase();
			Bukkit.dispatchCommand(p, "setwarp " + warpName);
		}

		if (command.equalsIgnoreCase("[setwarp]")) {
			e.setLine(0, "§9[setwarp]");
			String warpName = e.getLine(1).toLowerCase();
			Bukkit.dispatchCommand(p, "setwarp " + warpName);
		}
		
		if (command.equalsIgnoreCase("[repair]")) {
			e.setLine(0, "§9[repair]");
		}
	}

	@EventHandler
	public void onSignClick(PlayerInteractEvent e) {
		Block b = e.getClickedBlock();
		
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}

		Player p = e.getPlayer();
		if (b.getState() instanceof Sign) {
			Sign sign = (Sign) b.getState();
			String command = ChatColor.stripColor(sign.getLine(0));
			if (command.equalsIgnoreCase("[repair]")) {
				Bukkit.dispatchCommand(p, "repair");
			}
			if (command.equalsIgnoreCase("[warp]")) {
				String warp = ChatColor.stripColor(sign.getLine(1)).toLowerCase();
				Bukkit.dispatchCommand(p, "warp " + warp);
			}
		}
	}

}
