package me.akihiro.discordbot;

import me.akihiro.discordbot.bot.Bot;
import me.akihiro.discordbot.command.administration.Ban;
import me.akihiro.discordbot.command.administration.ClearMessage;
import me.akihiro.discordbot.command.moderation.MessageCleanModeration;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.List;

/*
*   @author: Akihiro Flushenov;
 */
public class Main {

	public static String TOKEN_BOT = "";

	public static void main(String[] args) {
		Bot bot = new Bot();
		MessageCleanModeration command1 = new MessageCleanModeration();
		ClearMessage command2 = new ClearMessage();
		Ban command3 = new Ban();
		bot.setCommand(command1);
		bot.setCommand(command2);
		bot.setCommand(command3);
		JDABuilder.createLight(TOKEN_BOT)
				.addEventListeners(bot)
				.enableIntents(
						List.of(
								GatewayIntent.GUILD_MESSAGES,
								GatewayIntent.MESSAGE_CONTENT
						)
				).setStatus(OnlineStatus.ONLINE)
				.setActivity(Activity.playing("Java bot"))
				.build();
	}

}