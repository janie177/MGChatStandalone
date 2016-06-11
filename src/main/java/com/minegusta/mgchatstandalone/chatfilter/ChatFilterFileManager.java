package com.minegusta.mgchatstandalone.chatfilter;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class ChatFilterFileManager {

	static File file;
	static FileConfiguration conf;

	public static void createOrLoadMuteFile(Plugin p) {
		try {
			file = new File(p.getDataFolder(), "filter.yml");

			if (!file.exists()) {
				p.saveResource("filter.yml", false);
				Bukkit.getLogger().info("Successfully created " + file.getName() + ".");
			}
			conf = YamlConfiguration.loadConfiguration(file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<String> getBlockedWords()
	{
		List<String> blocked = Lists.newArrayList();

		blocked.addAll(conf.getStringList("filtered-words").stream().map(String::toLowerCase).collect(Collectors.toList()));

		return blocked;
	}

	public static List<String> getReplacementWords()
	{
		return conf.getStringList("replacement-words");
	}
}
