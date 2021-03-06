package me.exzibyte.Utilities;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import me.exzibyte.Quiver;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;

import static com.mongodb.client.model.Filters.eq;

public class Utilities {

    public static HashMap<String, HashMap<String, String>> guilds = new HashMap<String, HashMap<String, String>>();
    public static HashMap<String, HashMap<String, String>> members = new HashMap<String, HashMap<String, String>>();
    Logging logging = new Logging();
    Database db = new Database();
    Integer guildCount = 0;
    Integer memberCount = 0;

    public void load() {
        guilds.clear();
        db.connect();
        MongoCollection<Document> guildsCollection = db.getCollection("guilds");

        FindIterable<Document> iterable = guildsCollection.find();
        MongoCursor<Document> cursor = iterable.iterator();

        try {
            while (cursor.hasNext()) {
                JSONParser parser = new JSONParser();
                JSONObject obj = (JSONObject) parser.parse(cursor.next().toJson());
                HashMap<String, String> data = new HashMap<String, String>();
                data.put("locale", obj.get("locale").toString());
                data.put("prefix", obj.get("prefix").toString());
                data.put("logChannel", obj.get("logChannelID").toString());
                data.put("joinLog", obj.get("joinLogID").toString());
                data.put("ownerRole", obj.get("ownerRoleID").toString());
                data.put("administratorRole", obj.get("administratorRoleID").toString());
                data.put("moderatorRole", obj.get("moderatorRoleID").toString());
                data.put("helperRole", obj.get("helperRoleID").toString());
                data.put("isPremium", obj.get("isPremium").toString());
                data.put("isBlacklisted", obj.get("isBlacklisted").toString());
                data.put("adminSet", obj.get("adminSetEnabled").toString());

                guilds.put(obj.get("guildID").toString(), data);
            }
        } catch (ParseException ex) {
            logging.error(this.getClass(), ex.toString());
        } finally {
            db.close();
        }

    }

    public void loadMembers() {
        members.clear();
        db.connect();
        MongoCollection<Document> membersCollection = db.getCollection("members");

        FindIterable<Document> iterable = membersCollection.find();
        MongoCursor<Document> cursor = iterable.iterator();

        try {
            while (cursor.hasNext()) {
                JSONParser parser = new JSONParser();
                JSONObject obj = (JSONObject) parser.parse(cursor.next().toJson());
                HashMap<String, String> data = new HashMap<String, String>();
                data.put("isBlacklisted", obj.get("isBlacklisted").toString());

                members.put(obj.get("userID").toString(), data);
            }
        } catch (ParseException ex) {
            logging.error(this.getClass(), ex.toString());
        } finally {
            db.close();
        }
    }

    public boolean guildExists(Guild guild) {
        db.connect();
        MongoCollection<Document> guilds = db.getCollection("guilds");
        if (guilds.find(eq("guildID", guild.getId())).first() != null) {
            db.close();
            return true;
        } else{
            db.close();
            return false;
        }
    }

    public boolean memberExists(Member member) {
        db.connect();
        MongoCollection<Document> members = db.getCollection("members");
        if (members.find(eq("userID", member.getId())).first() != null) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

    public void createNewGuild(Guild guild) {
        guildCount++;
        System.out.println("Guilds:" + guildCount);
        db.connect();

        MongoCollection<Document> guildsCollection = db.getCollection("guilds");
        Document newGuild = new Document("guildID", guild.getId())
                .append("guildName", guild.getName())
                .append("prefix", "Q!")
                .append("locale", getDefaultLocale(guild))
                .append("logChannelID", "")
                .append("joinLogID", "")
                .append("ownerRoleID", "")
                .append("administratorRoleID", "")
                .append("moderatorRoleID", "")
                .append("helperRoleID", "")
                .append("isPremium", false)
                .append("isBlacklisted", false)
                .append("adminSetEnabled", false);

        guildsCollection.insertOne(newGuild);
        db.close();
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("locale", getDefaultLocale(guild));
        data.put("prefix", "Q!");
        data.put("logChannel", "");
        data.put("joinLog", "");
        data.put("ownerRole", "");
        data.put("administratorRole", "");
        data.put("moderatorRole", "");
        data.put("helperRole", "");
        data.put("isPremium", "false");
        data.put("isBlacklisted", "false");
        data.put("adminSet", "false");

        guilds.put(guild.getId(), data);
    }

    public void createNewMember(Member member) {

        memberCount++;
        System.out.println("Members:" + memberCount);

        if (!member.getUser().isBot()) {
            db.connect();

            MongoCollection<Document> membersCollection = db.getCollection("members");
            Document newMember = new Document("userID", member.getUser().getId())
                    .append("userName", member.getUser().getAsTag())
                    .append("isBlacklisted", false);

            membersCollection.insertOne(newMember);
            db.close();
            HashMap<String, String> data = new HashMap<String, String>();
            data.put("isBlacklisted", "false");

            members.put(member.getId(), data);
        }
    }

    public String getDefaultLocale(Guild guild) {
        switch (guild.getRegionRaw()) {
            case "brazil":
                return "pt_BR";
            case "europe":
                return "en_GB";
            case "india":
                return "hi_IN";
            case "hongkong":
                return "en_HK";
            case "japan":
                return "ja_JP";
            case "russia":
                return "ru_RU";
            case "singapore":
                return "en_SG";
            case "southafrica":
                return "en_ZA";
            case "sydney":
                return "en_AU";
            default:
                return "en_US";
        }
    }

    public String getLocale(Guild guild) {
        HashMap<String, String> map = guilds.get(guild.getId());
        return map.get("locale");
    }

    public void setLocale(Guild guild, String locale) {
        db.connect();
        MongoCollection<Document> guildsCollection = db.getCollection("guilds");
        Document guildDoc = guildsCollection.find(eq("guildID", guild.getId())).first();
        Bson newGuildDoc = new Document("locale", locale);
        Bson updateDoc = new Document("$set", newGuildDoc);
        guildsCollection.findOneAndUpdate(guildDoc, updateDoc);
        db.close();

        HashMap<String, String> map = guilds.get(guild.getId());
        map.replace("locale", locale);
        guilds.put(guild.getId(), map);
    }

    public String getPrefix(Guild guild) {
        HashMap<String, String> map = guilds.get(guild.getId());
        return map.get("prefix");
    }

    public void setPrefix(Guild guild, String prefix) {
        db.connect();
        MongoCollection<Document> guildsCollection = db.getCollection("guilds");
        Document guildDoc = guildsCollection.find(eq("guildID", guild.getId())).first();
        Bson newGuildDoc = new Document("prefix", prefix);
        Bson updateDoc = new Document("$set", newGuildDoc);
        guildsCollection.findOneAndUpdate(guildDoc, updateDoc);
        db.close();

        HashMap<String, String> map = guilds.get(guild.getId());
        map.replace("prefix", prefix);
        guilds.put(guild.getId(), map);
    }

    public TextChannel getGlobalLogChannel(GuildMessageReceivedEvent event) {
        return event.getJDA().getShardManager().getGuildById("488137783127572491").getTextChannelById("517756124846358529");
    }

    public TextChannel getGlobalLogChannel(GuildJoinEvent event) {
        return event.getJDA().getShardManager().getGuildById("488137783127572491").getTextChannelById("517756124846358529");
    }

    public TextChannel getGuildLogChannel(Guild guild) {
        HashMap<String, String> map = guilds.get(guild.getId());

        if (map.get("logChannel").equals("")) return null;

        return guild.getTextChannelById(map.get("logChannel"));
    }

    public void setGuildLogChannel(Guild guild, String channelID) {
        db.connect();
        MongoCollection<Document> guildsCollection = db.getCollection("guilds");
        Document guildDoc = guildsCollection.find(eq("guildID", guild.getId())).first();
        Bson newGuildDoc = new Document("logChannelID", channelID);
        Bson updateDoc = new Document("$set", newGuildDoc);
        guildsCollection.findOneAndUpdate(guildDoc, updateDoc);
        db.close();

        HashMap<String, String> map = guilds.get(guild.getId());
        map.replace("logChannel", channelID);
        guilds.put(guild.getId(), map);
    }

    public TextChannel getJoinLog(Guild guild) {
        HashMap<String, String> map = guilds.get(guild.getId());

        if (map.get("joinLog").equals("")) return null;

        return guild.getTextChannelById(map.get("joinLog"));
    }

    public void setJoinLog(Guild guild, String channelID) {
        db.connect();
        MongoCollection<Document> guildsCollection = db.getCollection("guilds");
        Document guildDoc = guildsCollection.find(eq("guildID", guild.getId())).first();
        Bson newGuildDoc = new Document("joinLogID", channelID);
        Bson updateDoc = new Document("$set", newGuildDoc);
        guildsCollection.findOneAndUpdate(guildDoc, updateDoc);
        db.close();

        HashMap<String, String> map = guilds.get(guild.getId());
        map.replace("joinLog", channelID);
        guilds.put(guild.getId(), map);
    }

    public Role getOwnerRole(Guild guild) {
        HashMap<String, String> map = guilds.get(guild.getId());

        if (map.get("ownerRole").equals("")) return null;

        return guild.getRoleById(map.get("ownerRole"));
    }

    public Role getAdministratorRole(Guild guild) {
        HashMap<String, String> map = guilds.get(guild.getId());

        if (map.get("administratorRole").equals("")) return null;

        return guild.getRoleById(map.get("administratorRole"));
    }

    public Role getModeratorRole(Guild guild) {
        HashMap<String, String> map = guilds.get(guild.getId());

        if (map.get("moderatorRole").equals("")) return null;

        return guild.getRoleById(map.get("moderatorRole"));
    }

    public Role getHelperRole(Guild guild) {
        HashMap<String, String> map = guilds.get(guild.getId());

        if (map.get("helperRole").equals("")) return null;

        return guild.getRoleById(map.get("helperRole"));
    }

    public void setOwnerRole(Guild guild, String roleID) {
        db.connect();
        MongoCollection<Document> guildsCollection = db.getCollection("guilds");
        Document guildDoc = guildsCollection.find(eq("guildID", guild.getId())).first();
        Bson newGuildDoc = new Document("ownerRoleID", roleID);
        Bson updateDoc = new Document("$set", newGuildDoc);
        guildsCollection.findOneAndUpdate(guildDoc, updateDoc);
        db.close();

        HashMap<String, String> map = guilds.get(guild.getId());
        map.replace("ownerRole", getOwnerRole(guild).getId(), roleID);
        guilds.put(guild.getId(), map);
    }

    public void setAdministratorRole(Guild guild, String roleID) {
        db.connect();
        MongoCollection<Document> guildsCollection = db.getCollection("guilds");
        Document guildDoc = guildsCollection.find(eq("guildID", guild.getId())).first();
        Bson newGuildDoc = new Document("administratorRoleID", roleID);
        Bson updateDoc = new Document("$set", newGuildDoc);
        guildsCollection.findOneAndUpdate(guildDoc, updateDoc);
        db.close();

        HashMap<String, String> map = guilds.get(guild.getId());
        map.replace("administratorRole", getAdministratorRole(guild).getId(), roleID);
        guilds.put(guild.getId(), map);
    }

    public void setModeratorRole(Guild guild, String roleID) {
        db.connect();
        MongoCollection<Document> guildsCollection = db.getCollection("guilds");
        Document guildDoc = guildsCollection.find(eq("guildID", guild.getId())).first();
        Bson newGuildDoc = new Document("moderatorRoleID", roleID);
        Bson updateDoc = new Document("$set", newGuildDoc);
        guildsCollection.findOneAndUpdate(guildDoc, updateDoc);
        db.close();

        HashMap<String, String> map = guilds.get(guild.getId());
        map.replace("moderatorRole", getModeratorRole(guild).getId(), roleID);
        guilds.put(guild.getId(), map);
    }

    public void setHelperRole(Guild guild, String roleID) {
        db.connect();
        MongoCollection<Document> guildsCollection = db.getCollection("guilds");
        Document guildDoc = guildsCollection.find(eq("guildID", guild.getId())).first();
        Bson newGuildDoc = new Document("helperRoleID", roleID);
        Bson updateDoc = new Document("$set", newGuildDoc);
        guildsCollection.findOneAndUpdate(guildDoc, updateDoc);
        db.close();

        HashMap<String, String> map = guilds.get(guild.getId());
        map.replace("helperRole", getHelperRole(guild).getId(), roleID);
        guilds.put(guild.getId(), map);
    }

    public String isPremium(Guild guild) {
        HashMap<String, String> map = guilds.get(guild.getId());
        return (map.get("isPremium"));
    }

    public void setPremium(Guild guild, String enabled) {
        db.connect();
        MongoCollection<Document> guildsCollection = db.getCollection("guilds");
        Document guildDoc = guildsCollection.find(eq("guildID", guild.getId())).first();
        Bson newGuildDoc = new Document("isPremium", Boolean.parseBoolean(enabled));
        Bson updateDoc = new Document("$set", newGuildDoc);
        guildsCollection.findOneAndUpdate(guildDoc, updateDoc);
        db.close();

        HashMap<String, String> map = guilds.get(guild.getId());
        map.replace("isPremium", isPremium(guild), enabled);
        guilds.put(guild.getId(), map);
    }

    public boolean isServerBlacklisted(Guild guild) {
        HashMap<String, String> map = guilds.get(guild.getId());
        return Boolean.parseBoolean(map.get("isBlacklisted"));
    }

    public boolean isMemberBlacklisted(Member member) {
        HashMap<String, String> map = members.get(member.getUser().getId());
        return Boolean.parseBoolean(map.get("isBlacklisted"));
    }

    public boolean isAdminSetEnabled(Guild guild) {
        HashMap<String, String> map = guilds.get(guild.getId());
        return Boolean.parseBoolean(map.get("adminSet"));
    }

}
