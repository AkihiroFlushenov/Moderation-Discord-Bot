package me.akihiro.discordbot.command.administration;

import me.akihiro.discordbot.bot.BotStrategy;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.concurrent.TimeUnit;

public class Ban implements BotStrategy {

	/*
	*   Банит пользователя. Пример: /ban @test123
	 */
	@Override
	public void execute(MessageReceivedEvent event) {
		Member member = event.getMember();
		String messageContent = event.getMessage().getContentRaw();
		if (member != null && member.getRoles().stream().anyMatch(role -> role.getId().equals("1218227701970632866"))) {
			if (messageContent.startsWith("/ban")){
				String[] parts = messageContent.split("\\s+");
				if (parts.length > 1) {
					String userArgument = parts[1];
					if (userArgument.startsWith("@")) {
						String userName = userArgument.substring(1);
						Member targetMember = event.getMessage().getMentions().getMembers().stream()
								.filter(m -> m.getEffectiveName().equalsIgnoreCase(userName))
								.findFirst()
								.orElse(null);
						if (targetMember != null) {
							event.getGuild().ban(targetMember, 255, TimeUnit.DAYS).queue(
									success -> event.getChannel().sendMessage("Пользователь " + targetMember.getAsMention() + " был забанен.").queue(),
									error -> event.getChannel().sendMessage("Не удалось забанить пользователя " + targetMember.getAsMention() + ".").queue()
							);
						} else {
							event.getChannel().sendMessage("Пользователь " + userArgument + " не найден.").queue();
						}
					} else {
						event.getChannel().sendMessage("Пожалуйста, укажите пользователя для бана в формате @username.").queue();
					}
				} else {
					event.getChannel().sendMessage("Пожалуйста, укажите пользователя для бана.").queue();
				}
			}
		}
	}
}