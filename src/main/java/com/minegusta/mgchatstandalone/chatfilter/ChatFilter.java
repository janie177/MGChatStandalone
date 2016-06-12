package com.minegusta.mgchatstandalone.chatfilter;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.minegusta.mgchatstandalone.util.RandomUtil;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class ChatFilter {

	private static ConcurrentMap<String, Boolean> filter = Maps.newConcurrentMap();


	public static boolean hasFilter(Player p)
	{
		return !filter.containsKey(p.getUniqueId().toString()) || filter.get(p.getUniqueId().toString());
	}

	public static void setFilter(Player p, boolean b)
	{
		filter.put(p.getUniqueId().toString(), b);
	}

	public static boolean toggleFilter(Player p)
	{
		if(hasFilter(p))
		{
			filter.put(p.getUniqueId().toString(), false);
			return false;
		}
		else
		{
			filter.put(p.getUniqueId().toString(), true);
			return true;
		}
	}


	/**
	 * The lists containing the words
	 */
	private static List<String> blocked = Lists.newArrayList();
	private static List<String> replacements = Lists.newArrayList();

	/**
	 * Load all the cursewords and replacements.
	 */
	public static void init()
	{
		blocked = ChatFilterFileManager.getBlockedWords();
		replacements = ChatFilterFileManager.getReplacementWords();
	}

	public static boolean isBlocked(String word)
	{
		return blocked.contains(word.toLowerCase());
	}

	public static String getReplacement()
	{
		return replacements.get(RandomUtil.randomNumber(replacements.size()) - 1);
	}



}
