package com.minegusta.mgchatstandalone.util;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.minegusta.mgchatstandalone.config.ConfigHandler;
import com.minegusta.mgchatstandalone.main.Main;
import com.minegusta.mgracesredone.main.Races;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Formatter {

	public static String[] formatMessage(Player p)
	{
		String[] result = new String[2];

		String displayName = p.getDisplayName();
		String server = ConfigHandler.SERVER_NAME_IN_CHAT;
		String rank = "";
		if(Main.PEX_ENABLED){
			PermissionUser user = PermissionsEx.getUser(p);
			List<String> groups = user.getParentIdentifiers();
			for(String s : groups)
			{
				//Getting the rank, defaults to empty string if it doesn't exist in the file.
				String rankDisplay = ConfigHandler.getDisplay(s);

				//If the player has a donor rank, it gets the number and inserts it in the rank here. No need to make a donor display for every possible amount.
				if(s.toLowerCase().startsWith("donor"))
				{
					String digit = s.replaceAll("[\\D]", "");

					rankDisplay = ConfigHandler.getDisplay("donor").replace("%amount%", digit);
				}
				rank = rank + rankDisplay;
			}
		}

		result[0] = ChatColor.translateAlternateColorCodes('&', ConfigHandler.LOCAL_FORMAT.replace("$player$", displayName).replace("$server$", server).replace("$rank$", rank).replace("$time$", LocalTime.now().getHour() + ":" + LocalTime.now().getMinute()));
		result[1] = ChatColor.translateAlternateColorCodes('&', ConfigHandler.GLOBAL_FORMAT.replace("$player$", displayName).replace("$server$", server).replace("$rank$", rank).replace("$time$", LocalTime.now().getHour() + ":" + LocalTime.now().getMinute()));

		if(Main.RACES_ENABLED)
		{
			result[0] = result[0].replace("$race$", Races.getRace(p).getTag());
			result[1] = result[1].replace("$race$", Races.getRace(p).getTag());
		}
		if(Main.FACTIONS_ENABLED)
		{
			MPlayer uplayer = MPlayer.get(p);
			Faction faction = uplayer.getFaction();
			String factionName = faction.getName();

			result[0] = result[0].replace("$faction$", factionName);
			result[1] = result[1].replace("$faction$", factionName);
		}

		return result;
	}
}
