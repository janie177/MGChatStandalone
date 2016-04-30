package com.minegusta.mgchatstandalone.util;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.concurrent.ConcurrentMap;

public class MuteHandler {

	static File file;
	static FileConfiguration conf;

	public static void createOrLoadMuteFile(Plugin p) {
		try {
			file = new File(p.getDataFolder(), "mutes.yml");

			if (!file.exists()) {
				p.saveResource("mutes.yml", false);
				Bukkit.getLogger().info("Successfully created " + file.getName() + ".");
			}
			conf = YamlConfiguration.loadConfiguration(file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void save() {
		try {
			conf.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static ConcurrentMap<String, Mute> muteMap = Maps.newConcurrentMap();

	public static boolean isMuted(String name)
	{
		if(muteMap.containsKey(name.toLowerCase()))
		{
			Mute mute = muteMap.get(name.toLowerCase());

			if(mute.isExpired())
			{
				unMute(name.toLowerCase());
				return false;
			}
			return true;
		}
		return false;
	}

	public static Mute getMute(String name)
	{
		return muteMap.get(name.toLowerCase());
	}

	public static void mute(String name, long duration)
	{
		muteMap.put(name.toLowerCase(), Mute.create(name.toLowerCase(), duration));
	}

	public static void mute(String name, long duration, long start)
	{
		muteMap.put(name.toLowerCase(), Mute.create(name.toLowerCase(), duration, start));
	}

	public static void unMute(String name)
	{
		if(muteMap.containsKey(name.toLowerCase())) muteMap.remove(name.toLowerCase());
	}

	public static void saveMutes()
	{
		conf.set("mutes", null);

		for(String name : muteMap.keySet())
		{
			if(MuteHandler.isMuted(name))
			{
				Mute mute = MuteHandler.getMute(name);
				conf.set("mutes." + name + ".duration", mute.getDuration());
				conf.set("mutes." + name + ".start", mute.getStart());
			}
		}
		save();
	}

	public static void loadMutes()
	{
		muteMap.clear();

		if(conf.get("mutes") == null)
		{
			return;
		}

		ConfigurationSection s = conf.getConfigurationSection("mutes");
		for(String name : s.getKeys(false))
		{
			long duration = s.getLong(name + ".duration", 3);
			long start = s.getLong(name + ".start", 5);

			MuteHandler.mute(name, duration, start);
		}
	}
}
