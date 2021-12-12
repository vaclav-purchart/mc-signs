package cz.lipop.mcsigns;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand implements CommandExecutor {
	McSigns plugin;

	public SetWarpCommand(McSigns plugin) {
		this.plugin = plugin;
	}

	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String cmdName = cmd.getName().toLowerCase();

        if (!(cmdName.equals("setwarp") || cmdName.equals("loc"))) {
            return false;
        }
        
        if (args.length != 1) {
			sender.sendMessage("§cError: usage warp <warp_name>");
			return true;
        }
        
        if (sender instanceof Player) {
			String warpName = args[0].toLowerCase();
			Player p = (Player) sender;
			
			File folder = this.plugin.getDataFolder();
			File f = new File(folder, warpName + ".yml");
			f.getParentFile().mkdirs();
			
			try {
				boolean isNew = f.createNewFile();
				PrintWriter w = new PrintWriter(f);
				Location pos = p.getLocation();
				w.println("# world"); w.println(pos.getWorld().getName());
				w.println("# x"); w.println(pos.getX());
				w.println("# y"); w.println(pos.getY());
				w.println("# z"); w.println(pos.getZ());
				w.println("# pitch"); w.println(pos.getPitch());
				w.println("# yaw"); w.println(pos.getYaw());
				w.close();
		        
				if (!isNew) {
					p.sendMessage("§9Warp " + warpName + " already exists -> overwritten!"); 
				}
				else {
					p.sendMessage("§9Warp " + warpName + " set!"); 
				}
		        return true;

			} catch (IOException e) {
				sender.sendMessage("§cError: usage warp <warp_name>");
		        return true;

			}
        }
        return true;
    }
}
