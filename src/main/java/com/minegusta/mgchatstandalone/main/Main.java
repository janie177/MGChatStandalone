package com.minegusta.mgchatstandalone.main;

import com.google.common.collect.Lists;
import com.minegusta.mgchatstandalone.chatfilter.ChatFilter;
import com.minegusta.mgchatstandalone.chatfilter.ChatFilterFileManager;
import com.minegusta.mgchatstandalone.commands.FactionsChatCommand;
import com.minegusta.mgchatstandalone.config.ConfigHandler;
import com.minegusta.mgchatstandalone.listeners.FactionsChatListener;
import com.minegusta.mgchatstandalone.listeners.MessageListener;
import com.minegusta.mgchatstandalone.task.SaveTask;
import com.minegusta.mgchatstandalone.util.MuteHandler;
import com.minegusta.mgchatstandalone.util.PlayersUtil;
import net.minegusta.mglib.saving.mgplayer.PlayerSaveManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	private static Plugin PLUGIN;
	public static boolean PEX_ENABLED, FACTIONS_ENABLED, RACES_ENABLED, HEROPVP_ENABLED;
	private static PlayerSaveManager<MGPlayer> saveManager;

	@Override
	public void onEnable() {
		PLUGIN = this;
		saveDefaultConfig();

		ConfigHandler.readConfig(getPlugin().getConfig());

		MuteHandler.createOrLoadMuteFile(getPlugin());

		for (Command c : Command.values()) {
			Bukkit.getPluginCommand(c.name().toLowerCase()).setExecutor(c.getCommandClass());
		}

		for (Listener l : Listener.values()) {
			Bukkit.getPluginManager().registerEvents(l.getListener(), this);
		}

		//Init save manager
		saveManager = new PlayerSaveManager<>(this, MGPlayer.class, 180);


		//Pex
		PEX_ENABLED = Bukkit.getPluginManager().isPluginEnabled("PermissionsEx");
		FACTIONS_ENABLED = Bukkit.getPluginManager().isPluginEnabled("Factions");
		RACES_ENABLED = Bukkit.getPluginManager().isPluginEnabled("MGRacesRedone");
		HEROPVP_ENABLED = Bukkit.getPluginManager().isPluginEnabled("HeroPvP");

		if(FACTIONS_ENABLED)
		{
			Bukkit.getPluginCommand("fc").setExecutor(new FactionsChatCommand());
			Bukkit.getPluginManager().registerEvents(new FactionsChatListener(), this);
		}

		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new MessageListener());
		this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new PlayersUtil());

		//Load the mutes
		MuteHandler.loadMutes();

		//Load the chatfilter files
		ChatFilterFileManager.createOrLoadMuteFile(this);

		//Init the chat filter.
		ChatFilter.init();

		//Start the savetask
		SaveTask.start();
	}

	@Override
	public void onDisable()
	{
		MuteHandler.saveMutes();
		SaveTask.stop();
	}

	public static PlayerSaveManager getSaveManager()
	{
		return saveManager;
	}

	public static Plugin getPlugin()
	{
		return PLUGIN;
	}
}
