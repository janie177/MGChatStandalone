package com.minegusta.mgchatstandalone.main;

import com.minegusta.mgchatstandalone.commands.WhisperCommand;
import org.bukkit.command.CommandExecutor;

public enum Command {
	WHISPER(new WhisperCommand());

	private CommandExecutor commandExecutor;

	Command(CommandExecutor executor)
	{
		this.commandExecutor = executor;
	}

	public CommandExecutor getCommandClass()
	{
		return commandExecutor;
	}
}
