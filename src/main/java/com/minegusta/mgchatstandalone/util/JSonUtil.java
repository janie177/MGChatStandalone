package com.minegusta.mgchatstandalone.util;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;

public class JSonUtil {

	public static String componentToString(BaseComponent component)
	{
		return ComponentSerializer.toString(component);
	}

	public static TextComponent jsonToComponent(String json)
	{
		return (TextComponent) ComponentSerializer.parse(json)[0];
	}

}
