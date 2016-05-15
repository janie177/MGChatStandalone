package com.minegusta.mgchatstandalone.listeners;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MConf;
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

	@EventHandler(priority = EventPriority.LOWEST)
	public void onChat(AsyncPlayerChatEvent e)
	{
		if(e.isCancelled()) return;
		Player p = e.getPlayer();
		MPlayer uplayer = MPlayer.get(p);
		Faction faction = uplayer.getFaction();

		if(FactionsChatCommand.getFChatType(p) == 1)
		{
			e.setCancelled(true);
			String message = e.getMessage();

			faction.getOnlinePlayers().forEach(pl -> pl.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "FC" + ChatColor.DARK_GREEN + "] " + ChatColor.GRAY + uplayer.getRole().getPrefix() + ChatColor.YELLOW + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.GREEN + message));
			Bukkit.getLogger().info(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "FC" + ChatColor.DARK_GREEN + "] " + ChatColor.YELLOW + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.GREEN + message);
		}
		else if(FactionsChatCommand.getFChatType(p) > 1)
		{
			e.setCancelled(true);
			String message = e.getMessage();
			for(Faction ally : FactionColl.get().getAll())
			{
				if(ally.getOnlinePlayers().isEmpty()) continue;
				Rel wish = faction.getRelationTo(ally);
				if(wish != null && wish == Rel.ALLY)
				{
					ally.getOnlinePlayers().forEach(pl -> pl.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.LIGHT_PURPLE + "AC" + ChatColor.DARK_PURPLE + "] " + ChatColor.DARK_PURPLE + "[" + ChatColor.LIGHT_PURPLE + faction.getName() + ChatColor.DARK_PURPLE + "]" + ChatColor.YELLOW + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.LIGHT_PURPLE + message));
				}
			}
			faction.getOnlinePlayers().forEach(pl -> pl.sendMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.LIGHT_PURPLE + "AC" + ChatColor.DARK_PURPLE + "]" + ChatColor.DARK_PURPLE + "[" + ChatColor.GREEN + faction.getName() + ChatColor.DARK_PURPLE + "]" + ChatColor.YELLOW + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.LIGHT_PURPLE + message));
			Bukkit.getLogger().info(ChatColor.DARK_PURPLE + "[" + ChatColor.LIGHT_PURPLE + "AC" + ChatColor.DARK_PURPLE + "] " + ChatColor.DARK_PURPLE + "[" + ChatColor.GREEN + faction.getName() + ChatColor.DARK_PURPLE + "]" + ChatColor.YELLOW + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.LIGHT_PURPLE + message);
		}
	}

}
