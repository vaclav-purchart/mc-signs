package cz.lipop.mcsigns;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WarpsCommand implements CommandExecutor {
	McSigns plugin;

	public WarpsCommand(McSigns plugin) {
		this.plugin = plugin;
	}

	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String cmdName = cmd.getName().toLowerCase();

        if (!cmdName.equals("warps")) {
            return false;
        }
        
		List<String> warps = ListWarps.list(this.plugin);
		sender.sendMessage("§9Warps: " + warps.toString());
        return true;
    }
}
