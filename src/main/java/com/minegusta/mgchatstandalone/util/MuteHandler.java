package com.minegusta.mgchatstandalone.util;

import com.google.common.collect.Maps;

import java.util.concurrent.ConcurrentMap;

public class MuteHandler {

	private static ConcurrentMap<String, Mute> muteMap = Maps.newConcurrentMap();

	public static boolean isMuted(String name)
	{
		if(muteMap.containsKey(name.toLowerCase()))
		{
			Mute mute = muteMap.get(name.toLowerCase());

			if(mute.isExpired())
			{
				unMute(name.toLowerCase());
				return false;
			}
			return true;
		}
		return false;
	}

	public static Mute getMute(String name)
	{
		return muteMap.get(name.toLowerCase());
	}

	public static void mute(String name, long duration)
	{
		muteMap.put(name.toLowerCase(), Mute.create(name.toLowerCase(), duration));
	}

	public static void mute(String name, long duration, long start)
	{
		muteMap.put(name.toLowerCase(), Mute.create(name.toLowerCase(), duration, start));
	}

	public static void unMute(String name)
	{
		if(muteMap.containsKey(name.toLowerCase())) muteMap.remove(name.toLowerCase());
	}

	public static ConcurrentMap<String, Mute> getMutes()
	{
		return muteMap;
	}
}
