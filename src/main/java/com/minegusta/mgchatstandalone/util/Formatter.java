package com.minegusta.mgchatstandalone.util;

import com.minegusta.mgchatstandalone.config.ConfigHandler;
import com.minegusta.mgchatstandalone.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.List;

public class Formatter {

	public static String formatMessage(Player p, String message)
	{
		String result = "";
		message = ChatColor.translateAlternateColorCodes('&', message);

		if(!p.hasPermission("minegusta.chatcolor"))
		{
			message = ChatColor.stripColor(message);
		}

		String displayName = p.getDisplayName();
		String server = ConfigHandler.SERVER_NAME_IN_CHAT;
		String rank = "";
		if(Main.PEX_ENABLED){
			PermissionUser user = PermissionsEx.getUser(p);
			List<String> groups = user.getParentIdentifiers();
			for(String s : groups)
			{
				rank = rank + ConfigHandler.getDisplay(s);
			}
		}

		result = server + rank + displayName + "&7: " + message;


		return ChatColor.translateAlternateColorCodes('&', result);
	}
}
