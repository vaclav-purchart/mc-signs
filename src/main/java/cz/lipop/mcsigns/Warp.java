package cz.lipop.mcsigns;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.World;

public class Warp {
	public String name;

	public String world;
	public double x;
	public double y;
	public double z;
	public float pitch;
	public float yaw;

	public Warp(String name, String world, double x, double y, double z, float pitch, float yaw) {
		this.name = name;
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.pitch = pitch;
		this.yaw = yaw;
	}
	
	public static Warp readFromFile(String warpName, McSigns plugin) throws FileNotFoundException, IOException {
		File folder = plugin.getDataFolder();
		File ff = new File(folder, warpName + ".yml");		
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
		
		return new Warp(warpName, world, x, y, z, pitch, yaw);
	}
}
