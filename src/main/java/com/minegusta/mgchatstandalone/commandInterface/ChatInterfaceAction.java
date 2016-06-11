package com.minegusta.mgchatstandalone.commandInterface;

import com.google.common.collect.Lists;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.List;

public enum ChatInterfaceAction {
	PROMOTE(19, "pex promote $name$ staff", ChatColor.GREEN + "" + ChatColor.BOLD + "Promote", Lists.newArrayList(ChatColor.DARK_GREEN + "Promote this player."), Material.EMERALD_BLOCK, 1, 0),
	DEMOTE(37, "pex demote $name$ staff", ChatColor.DARK_RED + "" + ChatColor.BOLD + "Demote", Lists.newArrayList(ChatColor.RED + "Demote this player."), Material.REDSTONE_BLOCK, 1, 0),
	LISTRANKS(28, "", ChatColor.DARK_PURPLE + "$name$'s" + ChatColor.YELLOW + " Ranks:", Lists.newArrayList("$groups$"), Material.PAPER, 1, 0),
	SLAP(25, "slap $name$", ChatColor.BLUE + "Slap! " + ChatColor.GREEN + "(With a brick :D)", Lists.newArrayList(), Material.CLAY_BRICK, 1, 0),
	ROCKET(34, "rocket $name$", ChatColor.DARK_PURPLE + "Rocket " + ChatColor.RED + "$name$" + ChatColor.DARK_PURPLE + "!", Lists.newArrayList(), Material.FIREWORK, 1, 0),
	WARN1(30, "warn $name$ " + ChatColor.RED + "Stop spamming!", ChatColor.LIGHT_PURPLE + "Warn " + ChatColor.GOLD + "(Spam)", Lists.newArrayList(), Material.BOOK, 1, 0),
	WARN2(31, "warn $name$ " + ChatColor.RED + "No Vulgarity!", ChatColor.LIGHT_PURPLE + "Warn " + ChatColor.GOLD + "(Vulgarity)", Lists.newArrayList(), Material.BOOK, 1, 0),
	WARN3(32, "warn $name$ " + ChatColor.RED + "Do not break the rules!", ChatColor.LIGHT_PURPLE + "Warn " + ChatColor.GOLD + "(Rulebreaking)", Lists.newArrayList(), Material.BOOK, 1, 0),
	KICK1(39, "kick $name$ Stop spamming!", ChatColor.RED + "Kick" + ChatColor.YELLOW + " (Spam)", Lists.newArrayList(), Material.POISONOUS_POTATO, 1, 0),
	KICK2(40, "kick $name$ Watch your language!", ChatColor.RED + "Kick" + ChatColor.YELLOW + " (Language)", Lists.newArrayList(), Material.POISONOUS_POTATO, 1, 0),
	KICK3(41, "kick $name$ Start behaving!", ChatColor.RED + "Kick" + ChatColor.YELLOW + " (General)", Lists.newArrayList(), Material.POISONOUS_POTATO, 1, 0),
	MUTE5(12, "mute $name$ 5", ChatColor.YELLOW + "Mute " + ChatColor.RED + "$name$" + ChatColor.YELLOW + " for 5 minutes.", Lists.newArrayList(), Material.WATCH, 5, 0),
	MUTE30(13, "mute $name$ 30", ChatColor.YELLOW + "Mute " + ChatColor.RED + "$name$" + ChatColor.YELLOW + " for 30 minutes.", Lists.newArrayList(), Material.WATCH, 30, 0),
	MUTE560(14, "mute $name$ 60", ChatColor.YELLOW + "Mute " + ChatColor.RED + "$name$" + ChatColor.YELLOW + " for 60 minutes.", Lists.newArrayList(), Material.WATCH, 60, 0);

	private int index;
	private String command;
	private String name;
	private List<String> lore;
	private Material material;
	private int amount;
	private int data;

	ChatInterfaceAction(int index, String command, String name, List<String> lore, Material material, int amount, int data)
	{
		this.index = index;
		this.command = command;
		this.name = name;
		this.lore = lore;
		this.material = material;
		this.amount = amount;
		this.data = data;
	}

	public static String getCommandForIndex(int index, String target)
	{
		for(ChatInterfaceAction a : ChatInterfaceAction.values())
		{
			if(a.getIndex() == index)
			{
				return a.getCommand().replace("$name$", target);
			}
		}
		return "";
	}


	public int getIndex() {
		return index;
	}

	public String getCommand() {
		return command;
	}

	public String getName() {
		return name;
	}

	public List<String> getLore() {
		return lore;
	}

	public Material getMaterial() {
		return material;
	}

	public int getData() {
		return data;
	}

	public int getAmount() {
		return amount;
	}
}
