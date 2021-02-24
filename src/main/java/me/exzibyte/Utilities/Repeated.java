package me.exzibyte.Utilities;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class Repeated {

    EmbedBuilder eb = new EmbedBuilder();
    Locale locale = new Locale();

    public void warnOfBlacklistedServer(GuildMessageReceivedEvent event){
        eb.setDescription(locale.getMessage(event.getGuild(), "blacklistErrors", "blacklistedServer").replace("[serverowner]", event.getGuild().getOwner().getAsMention()).replace("[botdev]", event.getJDA().retrieveUserById("79693184417931264").complete().getAsTag()).replace("[supportserver]", "https://exacu.be/quiver/discord"));
        eb.setColor(0x000000);
        eb.setTimestamp(Instant.now());
        eb.setFooter(locale.getMessage(event.getGuild(), "embed", "footerServerBlacklistNotification"));

        event.getChannel().sendMessage(eb.build()).queue((message) -> {
            message.delete().queueAfter(20, TimeUnit.SECONDS);
            eb.clear();
        });
    }

    public void warnOfBlacklistedMember(GuildMessageReceivedEvent event){
        eb.setDescription(locale.getMessage(event.getGuild(), "blacklistErrors", "blacklistedMember").replace("[botdev]", event.getJDA().retrieveUserById("79693184417931264").complete().getAsTag()).replace("[supportserver]", "https://exacu.be/quiver/discord"));
        eb.setColor(0x000000);
        eb.setTimestamp(Instant.now());
        eb.setFooter(locale.getMessage(event.getGuild(), "embed", "footerMemberBlacklistNotification"));

        event.getChannel().sendMessage(eb.build()).queue((message) -> {
            message.delete().queueAfter(20, TimeUnit.SECONDS);
            eb.clear();
        });
    }

}
