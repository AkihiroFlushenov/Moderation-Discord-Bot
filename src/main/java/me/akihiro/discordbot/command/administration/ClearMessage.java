package me.akihiro.discordbot.command.administration;

import me.akihiro.discordbot.bot.BotStrategy;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class ClearMessage implements BotStrategy {

	/*
	*   Удаляет сообщения в чате. Пример: /messageclear 200
	 */
	@Override
	public void execute(MessageReceivedEvent event) {
		String messageContent = event.getMessage().getContentRaw();
		if (messageContent.startsWith("/messageclear")) {
			Member member = event.getMember();
			if (member != null && member.getRoles().stream().anyMatch(role -> role.getId().equals("1218227701970632866"))) {
				String[] parts = messageContent.split("\\s+");
				if (parts.length >= 2) {
					int messagesToDelete = Integer.parseInt(parts[1]);
					if (messagesToDelete >= 1 && messagesToDelete <= 500) {
						List<Message> messages = event.getChannel().getHistory().retrievePast(messagesToDelete).complete();
						event.getChannel().delete().queue();
					} else {
						event.getChannel().sendMessage("Количество сообщений для удаления должно быть от 1 до 500.").queue();
					}
				} else {
					event.getChannel().sendMessage("Использование: /messageclear <количество сообщений>").queue();
				}
			} else {
				event.getChannel().sendMessage("У вас нет прав для выполнения этой команды.").queue();
			}
		}
	}
}
