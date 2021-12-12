package cz.lipop.mcsigns;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.Plugin;

public class ListWarps {
	private static String stripExtension(String str) {
		if (str == null) return null;
		int pos = str.lastIndexOf(".");
		if (pos == -1) return str;
		return str.substring(0, pos);
	}

	public static List<String> list(Plugin plugin) {
		File folder = plugin.getDataFolder();

		ArrayList<String> warps = new ArrayList<String>();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isFile()) {
				warps.add(ListWarps.stripExtension(fileEntry.getName()));
			}
		}
		
		return warps;
	}
}
