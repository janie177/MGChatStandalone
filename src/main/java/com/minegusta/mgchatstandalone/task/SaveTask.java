package com.minegusta.mgchatstandalone.task;

import com.minegusta.mgchatstandalone.config.ConfigHandler;
import com.minegusta.mgchatstandalone.main.Main;
import org.bukkit.Bukkit;

public class SaveTask {
	private static int ID = -1;

	public static void start()
	{
		ID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
			@Override
			public void run() {
				ConfigHandler.saveMutes(Main.getPlugin());
			}
		}, 20 * 60 * 5, 20 * 60 * 5);
	}

	public static void stop()
	{
		if(ID != -1)
		{
			Bukkit.getScheduler().cancelTask(ID);
		}
	}


}
