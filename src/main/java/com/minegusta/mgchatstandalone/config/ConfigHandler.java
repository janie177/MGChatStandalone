package com.minegusta.mgchatstandalone.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.minegusta.mgchatstandalone.util.Mute;
import com.minegusta.mgchatstandalone.util.MuteHandler;
import com.minegusta.mgchatstandalone.util.Rank;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class ConfigHandler {

	public static String SERVER_NAME, SEND_TO, SERVER_NAME_IN_CHAT, LOCAL_FORMAT, GLOBAL_FORMAT;
	private static ConcurrentMap<String, Rank> rankMap = Maps.newConcurrentMap();
	private static List<String> blockedCMDS = Lists.newArrayList();


	public static void readConfig(FileConfiguration conf)
	{
		SERVER_NAME = conf.getString("server-name", "server");
		SEND_TO = conf.getString("send-to", "");
		SERVER_NAME_IN_CHAT = conf.getString("server-name-in-chat", "");
		blockedCMDS = conf.getStringList("mute-blocked-commands");
		LOCAL_FORMAT = ChatColor.translateAlternateColorCodes('&', conf.getString("local-format", "&f%server%&f%rank%&7%player%&7: &f%message%"));
		GLOBAL_FORMAT = ChatColor.translateAlternateColorCodes('&', conf.getString("global-format", "&f%server%&f%rank%&7%player%&7: &f%message%"));

		ConfigurationSection s = conf.getConfigurationSection("ranks");
		for(String rank : s.getKeys(false))
		{
			String display = s.getString(rank + "." + "prefix", "");
			int weight = s.getInt(rank + "." + "weight", 0);
			rankMap.put(rank.toLowerCase(), new Rank(rank, display, weight));
		}
	}

	public static boolean isRank(String rank)
	{
		return rankMap.containsKey(rank.toLowerCase());
	}

	public static Rank getRank(String rank)
	{
		return rankMap.get(rank.toLowerCase());
	}

	public static String getDisplay(String rank)
	{
		if(isRank(rank)) return getRank(rank).getDisplay();
		return "";
	}

	public static void saveMutes(Plugin plugin)
	{
		FileConfiguration conf = plugin.getConfig();
		conf.set("mutes", null);

		for(String name : MuteHandler.getMutes().keySet())
		{
			if(MuteHandler.isMuted(name))
			{
				Mute mute = MuteHandler.getMute(name);
				conf.set("mutes." + name + ".duration", mute.getDuration());
				conf.set("mutes." + name + ".start", mute.getStart());
			}
		}
		plugin.saveConfig();
	}

	public static void loadMutes(Plugin plugin)
	{
		ConfigurationSection s = plugin.getConfig().getConfigurationSection("mutes");
		for(String name : s.getKeys(false))
		{
			long duration = s.getLong(name + ".duration", 3);
			long start = s.getLong(name + ".start", 5);

			MuteHandler.mute(name, duration, start);
		}
	}

	public static List<String> getBlockedCMDS()
	{
		return blockedCMDS;
	}
}
