package me.akihiro.discordbot.bot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface BotStrategy {

	void execute(MessageReceivedEvent event);

}
