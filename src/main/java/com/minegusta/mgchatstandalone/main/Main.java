package com.minegusta.mgchatstandalone.main;

import com.minegusta.mgchatstandalone.commands.FactionsChatCommand;
import com.minegusta.mgchatstandalone.config.ConfigHandler;
import com.minegusta.mgchatstandalone.listeners.FactionsChatListener;
import com.minegusta.mgchatstandalone.listeners.MessageListener;
import com.minegusta.mgchatstandalone.task.SaveTask;
import com.minegusta.mgchatstandalone.util.MuteHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	private static Plugin PLUGIN;
	public static boolean PEX_ENABLED, FACTIONS_ENABLED, RACES_ENABLED;

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


		//Pex
		PEX_ENABLED = Bukkit.getPluginManager().isPluginEnabled("PermissionsEx");
		FACTIONS_ENABLED = Bukkit.getPluginManager().isPluginEnabled("Factions");
		RACES_ENABLED = Bukkit.getPluginManager().isPluginEnabled("MGRaces");

		if(FACTIONS_ENABLED)
		{
			Bukkit.getPluginCommand("fc").setExecutor(new FactionsChatCommand());
			Bukkit.getPluginManager().registerEvents(new FactionsChatListener(), this);
		}

		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new MessageListener());

		//Load the mutes
		MuteHandler.loadMutes();

		//Start the savetask
		SaveTask.start();
	}

	@Override
	public void onDisable()
	{
		MuteHandler.saveMutes();
		SaveTask.stop();
	}

	public static Plugin getPlugin()
	{
		return PLUGIN;
	}
}
