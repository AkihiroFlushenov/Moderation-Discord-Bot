package me.akihiro.discordbot.bot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Bot extends ListenerAdapter {

	private BotStrategy command;

	public void setCommand(BotStrategy command) {
		this.command = command;
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.getAuthor().isBot()) {
			return;
		}

		if (command != null) {
			command.execute(event);
		}
	}

}
