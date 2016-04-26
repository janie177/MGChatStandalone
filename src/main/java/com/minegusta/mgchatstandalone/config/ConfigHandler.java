package com.minegusta.mgchatstandalone.config;

import com.google.common.collect.Maps;
import com.minegusta.mgchatstandalone.util.Rank;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.concurrent.ConcurrentMap;

public class ConfigHandler {

	public static String SERVER_NAME, SEND_TO, SERVER_NAME_IN_CHAT;
	private static ConcurrentMap<String, Rank> rankMap = Maps.newConcurrentMap();


	public static void readConfig(FileConfiguration conf)
	{
		SERVER_NAME = conf.getString("server-name", "server");
		SEND_TO = conf.getString("send-to", "");
		SERVER_NAME_IN_CHAT = conf.getString("server-name-in-chat", "");

		ConfigurationSection s = conf.getConfigurationSection("ranks");
		for(String rank : s.getKeys(false))
		{
			String display = s.getString(rank + "." + "display", "");
			int weight = s.getInt(rank + "." + "weight", 0);
			rankMap.put(conf.getString("ranks." + rank).toLowerCase(), new Rank(rank, display, weight));
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
}
