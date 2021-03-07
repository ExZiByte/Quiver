package me.exzibyte.Listeners.Commands;

import me.exzibyte.Utilities.Utilities;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HarvestExistingServers extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        Utilities utils = new Utilities();
        if(args[0].equalsIgnoreCase(utils.getPrefix(event.getGuild()) + "harvest")){
            System.out.println("This is getting fucking retarded");
            for(Guild guild: event.getJDA().getGuilds()){
                if(!utils.guildExists(guild)){
                    utils.createNewGuild(guild);

                }
            }
            for(Guild guild: event.getJDA().getShardManager().getGuilds()){
                for(Member member: guild.getMembers()){
                    if(!utils.memberExists(member)){
                        utils.createNewMember(member);
                    }
                }
            }

            utils.getGlobalLogChannel(event).sendMessage("Done putting arrows in my quiver").queue();
        }
    }

}
