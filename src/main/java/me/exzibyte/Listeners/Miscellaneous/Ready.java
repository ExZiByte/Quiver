package me.exzibyte.Listeners.Miscellaneous;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Ready extends ListenerAdapter {

    public void onReady(ReadyEvent event){
        event.getJDA().getShardManager().setActivityProvider((id) -> Activity.watching(String.format("Shard ID: %s", id)));
        event.getJDA().getPresence().setStatus(OnlineStatus.ONLINE);
    }

}
