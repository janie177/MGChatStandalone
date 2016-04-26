package com.minegusta.mgchatstandalone.main;

import com.minegusta.mgchatstandalone.commands.MuteCommand;
import com.minegusta.mgchatstandalone.commands.ReplyCommand;
import com.minegusta.mgchatstandalone.commands.UnMuteCommand;
import com.minegusta.mgchatstandalone.commands.WhisperCommand;
import org.bukkit.command.CommandExecutor;

public enum Command {
	MUTE(new MuteCommand()),
	REPLY(new ReplyCommand()),
	UNMUTEW(new UnMuteCommand()),
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
