package cz.lipop.mcsigns;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {
	McSigns plugin;

	public WarpCommand(McSigns plugin) {
		this.plugin = plugin;
	}

	private World findWorld(String string) {
		Iterator<World> worlds = this.plugin.getServer().getWorlds().iterator();
		while (worlds.hasNext()) {
			World world = worlds.next();
			if (world.getName().equalsIgnoreCase(string)) {
				return world;
			}
		}
		return null;
	}

	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String cmdName = cmd.getName().toLowerCase();
        
        if (args.length != 1) {
			sender.sendMessage("§cError: usage warp <warp_name>");
			return true;
        }
        
        String warpName = args[0];

        if (!cmdName.equals("warp")) {
            return false;
        }
        
		File folder = this.plugin.getDataFolder();
		List<String> warps = ListWarps.list(this.plugin);
		
		if (warps.indexOf(warpName) == -1) {
			sender.sendMessage("§cWarp " + warpName + " is not defined!");
			return true;
		}
		
		if (sender instanceof Player) {
			sender.sendMessage("§9Warping to " + warpName);
			Player player = (Player) sender;
			File ff = new File(folder, warpName + ".yml");
			try {
				BufferedReader f = new BufferedReader(new FileReader(ff));
				// world
				f.readLine(); String world = f.readLine();
				// x
				f.readLine(); double x = Double.parseDouble(f.readLine());
				// y
				f.readLine(); double y = Double.parseDouble(f.readLine());
				// z
				f.readLine(); double z = Double.parseDouble(f.readLine());
				// pitch
				f.readLine(); float pitch = Float.parseFloat(f.readLine());
				// yaw
				f.readLine(); float yaw = Float.parseFloat(f.readLine());
				f.close();
				
				World worldRef = this.findWorld(world);
				if (worldRef == null) {
					sender.sendMessage("§cCannot find world " + world);
					return true;
				}
				Location pos = new Location(worldRef, x, y, z, yaw, pitch);
				player.teleport(pos);
			} 
			catch (FileNotFoundException e) {
				sender.sendMessage("§cWarp " + warpName + " is not defined!");
				return true;
			}
			catch (IOException e) {
				sender.sendMessage("§cWarp " + warpName + " is not defined!");
				return true;
			}
		}

        return true;
    }
}
