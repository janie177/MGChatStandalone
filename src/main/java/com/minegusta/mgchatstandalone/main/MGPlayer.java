package com.minegusta.mgchatstandalone.main;

import com.minegusta.mgchatstandalone.chatfilter.ChatFilter;
import net.minegusta.mglib.saving.mgplayer.MGPlayerModel;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class MGPlayer extends MGPlayerModel {

	@Override
	public void onLoad(FileConfiguration fileConfiguration) {
		ChatFilter.setFilter(Bukkit.getPlayer(getUuid()), fileConfiguration.getBoolean("filter"));
	}

	@Override
	public void updateConf(FileConfiguration fileConfiguration) {
		fileConfiguration.set("filter", ChatFilter.hasFilter(Bukkit.getPlayer(getUuid())));
	}
}
