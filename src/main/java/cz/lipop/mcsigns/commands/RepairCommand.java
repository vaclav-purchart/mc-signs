package cz.lipop.mcsigns.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import cz.lipop.mcsigns.McSigns;

public class RepairCommand implements CommandExecutor {
	McSigns plugin;

	public RepairCommand(McSigns plugin) {
		this.plugin = plugin;
	}

	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String cmdName = cmd.getName().toLowerCase();
        
        if (args.length != 0) {
			sender.sendMessage("§cError: usage repair");
			return true;
        }
        
        if (!cmdName.equals("repair")) {
            return false;
        }
        
        if (sender instanceof Player) {
        	Player p = (Player) sender;
            final ItemStack stack = p.getItemInHand();
    		stack.setDurability((short) 0);
    		p.setItemInHand(stack);
    		p.sendMessage("§9Item repaired!");
        }       
 
        return true;
    }
}
