package me.exzibyte.Listeners.Miscellaneous;

import me.exzibyte.Utilities.Utilities;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class NewMember extends ListenerAdapter {

    public void onGuildMemberJoin(GuildMemberJoinEvent event){
        Utilities utils = new Utilities();
        if(!utils.memberExists(event.getMember())){
            utils.createNewMember(event.getMember());
        }
    }

}
