package com.minegusta.mgchatstandalone.main;

import com.minegusta.mgchatstandalone.config.ConfigHandler;
import com.minegusta.mgchatstandalone.listeners.MessageListener;
import com.minegusta.mgchatstandalone.task.SaveTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	private static Plugin PLUGIN;
	public static boolean PEX_ENABLED;

	@Override
	public void onEnable() {
		PLUGIN = this;
		saveDefaultConfig();

		ConfigHandler.readConfig(getPlugin().getConfig());

		for (Command c : Command.values()) {
			Bukkit.getPluginCommand(c.name().toLowerCase()).setExecutor(c.getCommandClass());
		}

		for (Listener l : Listener.values()) {
			Bukkit.getPluginManager().registerEvents(l.getListener(), this);
		}


		//Pex
		PEX_ENABLED = Bukkit.getPluginManager().isPluginEnabled("PermissionsEx");


		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new MessageListener());

		//Load the mutes
		ConfigHandler.loadMutes(getPlugin());

		//Start the savetask
		SaveTask.start();
	}

	@Override
	public void onDisable()
	{
		ConfigHandler.saveMutes(getPlugin());
		SaveTask.stop();
	}

	public static Plugin getPlugin()
	{
		return PLUGIN;
	}
}
