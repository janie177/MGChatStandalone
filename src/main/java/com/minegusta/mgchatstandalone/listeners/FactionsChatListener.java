package com.minegusta.mgchatstandalone.listeners;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.minegusta.mgchatstandalone.commands.FactionsChatCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class FactionsChatListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent e)
	{
		if(FactionsChatCommand.hasFChatEnabled(e.getPlayer()))
		{
			Player p = e.getPlayer();

			e.setCancelled(true);
			String message = e.getMessage();

			MPlayer uplayer = MPlayer.get(p);
			Faction faction = uplayer.getFaction();

			faction.getOnlinePlayers().forEach(pl -> pl.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "FC" + ChatColor.DARK_GREEN + "] " + ChatColor.DARK_PURPLE + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.LIGHT_PURPLE + message));
			Bukkit.getLogger().info(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "FC" + ChatColor.DARK_GREEN + "] " + ChatColor.DARK_PURPLE + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.LIGHT_PURPLE + message);
		}
	}

}
