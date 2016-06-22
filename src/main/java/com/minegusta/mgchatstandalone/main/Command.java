package com.minegusta.mgchatstandalone.main;

import com.minegusta.mgchatstandalone.commands.*;
import org.bukkit.command.CommandExecutor;

public enum Command {
	MUTE(new MuteCommand()),
	REPLY(new ReplyCommand()),
	UNMUTE(new UnMuteCommand()),
	CRELOAD(new ReloadCommand()),
	CHATINTERFACE(new InterfaceCommand()),
	CHATFILTER(new FilterCommand()),
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
