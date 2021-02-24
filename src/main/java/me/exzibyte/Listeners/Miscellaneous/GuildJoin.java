package me.exzibyte.Listeners.Miscellaneous;

import me.exzibyte.Utilities.Colors;
import me.exzibyte.Utilities.Config;
import me.exzibyte.Utilities.Utilities;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.Instant;

public class GuildJoin extends ListenerAdapter {
    public void onGuildJoin(GuildJoinEvent event) {

        Utilities utils = new Utilities();
        EmbedBuilder eb = new EmbedBuilder();
        if (!utils.guildExists(event.getGuild())) {
            utils.createNewGuild(event.getGuild());
            eb.setDescription(String.format("I've been added to a new guild. \n\n```\nGuildID: %s\nGuild Name: %s\nMember Count: %s\nDefault Locale: %s\n```", event.getGuild().getId(), event.getGuild().getName(), event.getGuild().getMemberCount(), utils.getDefaultLocale(event.getGuild())));
            eb.setColor(Colors.correctGreen);

        } else {
            eb.setDescription(String.format("Either Discord fired a false guild join event or I got readded to a guild that is already in the database.\n\n```\nGuild Name: %s\nGuildID: %s\n```", event.getGuild().getName(), event.getGuild().getId()));
            eb.setColor(Colors.warningYellow);

        }
        eb.setTimestamp(Instant.now());
        eb.setFooter("GuildJoinEvent Log");
        utils.getGlobalLogChannel(event).sendMessage(eb.build()).queue((message) -> {
            eb.clear();
        });

    }

}
