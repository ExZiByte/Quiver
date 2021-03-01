package me.exzibyte;

import me.exzibyte.Listeners.Commands.HarvestExistingServers;
import me.exzibyte.Listeners.Commands.Settings;
import me.exzibyte.Listeners.Miscellaneous.GuildJoin;
import me.exzibyte.Listeners.Miscellaneous.NewMember;
import me.exzibyte.Listeners.Miscellaneous.Ready;
import me.exzibyte.Utilities.Config;
import me.exzibyte.Utilities.Utilities;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;

public class Quiver {
    static Utilities utils = new Utilities();


    private Quiver() throws LoginException {
        Config config = new Config();
        DefaultShardManagerBuilder quiver = DefaultShardManagerBuilder.createDefault(config.get("token"));

        quiver.setActivity(Activity.watching("shards come online"));
        quiver.setStatus(OnlineStatus.DO_NOT_DISTURB);
        quiver.setMemberCachePolicy(MemberCachePolicy.ALL);
        quiver.enableIntents(GatewayIntent.GUILD_MEMBERS);

        quiver.addEventListeners(

                //Command Listeners
                new Settings(),

                //Guild Listeners
                new GuildJoin(),
                new NewMember(),
                new HarvestExistingServers(),

                //Member Listeners

                //Miscellaneous Listeners
                new Ready()
        );

        quiver.setShardsTotal(2);


        quiver.build();
    }

    public static void main(String[] args) throws LoginException {
        utils.load();
        utils.loadMembers();
        new Quiver();
    }

}
