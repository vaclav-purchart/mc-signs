package cz.lipop.mcsigns.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.PlayerInventory;

import cz.lipop.mcsigns.McSigns;

public class RepairCommand implements CommandExecutor {
	McSigns plugin;

	public RepairCommand(McSigns plugin) {
		this.plugin = plugin;
	}

	private static ItemStack fixItem(ItemStack item) {
		if (item != null && item.hasItemMeta()) {
			ItemMeta mainHandItemMeta = item.getItemMeta();
			((Damageable) mainHandItemMeta).setDamage(0);
			item.setItemMeta(mainHandItemMeta);
		}
		return item;
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
			PlayerInventory inventory = p.getInventory();
			ItemStack mainHandItem = inventory.getItemInMainHand();
			inventory.setItemInMainHand(RepairCommand.fixItem(mainHandItem));

			ItemStack offHandItem = inventory.getItemInOffHand();
			inventory.setItemInOffHand(RepairCommand.fixItem(offHandItem));
			p.sendMessage("§9Item(s) repaired!");
		}
 
		return true;
	}
}
