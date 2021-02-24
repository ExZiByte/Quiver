package me.exzibyte.Listeners.Commands;

import me.exzibyte.Utilities.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class Settings extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        Locale locale = new Locale();
        Repeated repeated = new Repeated();
        Utilities utils = new Utilities();
        if (args[0].equalsIgnoreCase(utils.getPrefix(event.getGuild()) + "settings")) {
            EmbedBuilder eb = new EmbedBuilder();
            EmbedBuilder log = new EmbedBuilder();
            if (!utils.isServerBlacklisted(event.getGuild())) {
                if (!utils.isMemberBlacklisted(event.getMember())) {
                    if (utils.isAdminSetEnabled(event.getGuild())) {
                        if (event.getMember().isOwner()) {
                            if (args.length == 1) {
                                eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "insufficientArguments"));
                                eb.setColor(Colors.errorRed);
                                eb.setTimestamp(Instant.now());
                                eb.setFooter(locale.getMessage(event.getGuild(), "embed", "insufficientArguments"));

                                event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                    message.delete().queueAfter(20, TimeUnit.SECONDS);
                                    eb.clear();
                                });
                            } else if (args.length == 2) {
                                switch (args[1].toLowerCase()) {
                                    case "prefix": {
                                        eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "insufficientPrefixArguments"));
                                        eb.setColor(Colors.warningYellow);
                                        eb.setTimestamp(Instant.now());
                                        eb.setFooter(locale.getMessage(event.getGuild(), "embed", "insufficientArguments"));
                                        event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                            message.delete().queueAfter(20, TimeUnit.SECONDS);
                                            eb.clear();
                                        });
                                        break;
                                    }
                                    case "locale":
                                    case "language":
                                    case "lang": {
                                        eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "insufficientLocaleArguments"));
                                        eb.setColor(Colors.warningYellow);
                                        eb.setTimestamp(Instant.now());
                                        eb.setFooter(locale.getMessage(event.getGuild(), "embed", "insufficientArguments"));
                                        event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                            message.delete().queueAfter(20, TimeUnit.SECONDS);
                                            eb.clear();
                                        });
                                        break;
                                    }
                                    case "logchannel":
                                    case "log": {
                                        eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "insufficientLogChannelArguments"));
                                        eb.setColor(Colors.warningYellow);
                                        eb.setTimestamp(Instant.now());
                                        eb.setFooter(locale.getMessage(event.getGuild(), "embed", "insufficientArguments"));
                                        event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                            message.delete().queueAfter(20, TimeUnit.SECONDS);
                                            eb.clear();
                                        });
                                        break;
                                    }
                                    case "joinlog":
                                    case "leavelog":
                                    case "joinleavelog": {
                                        eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "insufficientJoinLeaveLogArguments"));
                                        eb.setColor(Colors.warningYellow);
                                        eb.setTimestamp(Instant.now());
                                        eb.setFooter(locale.getMessage(event.getGuild(), "embed", "insufficientArguments"));
                                        event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                            message.delete().queueAfter(20, TimeUnit.SECONDS);
                                            eb.clear();
                                        });
                                        break;
                                    }
                                    case "ownerrole":
                                    case "owner": {
                                        eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "insufficientOwnerRoleArguments"));
                                        eb.setColor(Colors.warningYellow);
                                        eb.setTimestamp(Instant.now());
                                        eb.setFooter(locale.getMessage(event.getGuild(), "embed", "insufficientArguments"));
                                        event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                            message.delete().queueAfter(20, TimeUnit.SECONDS);
                                            eb.clear();
                                        });
                                        break;
                                    }
                                    case "administratorrole":
                                    case "administrator":
                                    case "adminrole":
                                    case "admin": {
                                        eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "insufficientAdminRoleArguments"));
                                        eb.setColor(Colors.warningYellow);
                                        eb.setTimestamp(Instant.now());
                                        eb.setFooter(locale.getMessage(event.getGuild(), "embed", "insufficientArguments"));
                                        event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                            message.delete().queueAfter(20, TimeUnit.SECONDS);
                                            eb.clear();
                                        });
                                        break;
                                    }
                                    case "moderatorrole":
                                    case "moderator":
                                    case "modrole":
                                    case "mod": {
                                        eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "insufficientModRoleArguments"));
                                        eb.setColor(Colors.warningYellow);
                                        eb.setTimestamp(Instant.now());
                                        eb.setFooter(locale.getMessage(event.getGuild(), "embed", "insufficientArguments"));
                                        event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                            message.delete().queueAfter(20, TimeUnit.SECONDS);
                                            eb.clear();
                                        });
                                        break;
                                    }
                                    case "helperrole":
                                    case "helper": {
                                        eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "insufficientHelperRoleArguments"));
                                        eb.setColor(Colors.warningYellow);
                                        eb.setTimestamp(Instant.now());
                                        eb.setFooter(locale.getMessage(event.getGuild(), "embed", "insufficientArguments"));
                                        event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                            message.delete().queueAfter(20, TimeUnit.SECONDS);
                                            eb.clear();
                                        });
                                        break;
                                    }
                                }
                            } else if (args.length == 3) {
                                switch (args[1].toLowerCase()) {
                                    case "prefix": {
                                        utils.setPrefix(event.getGuild(), args[2]);
                                        eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "setPrefixSuccess").replace("[prefix]", args[2]));
                                        eb.setColor(Colors.correctGreen);
                                        eb.setTimestamp(Instant.now());
                                        eb.setFooter(locale.getMessage(event.getGuild(), "embed", "footerPrefixChangeLog"));

                                        log.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "setPrefixSuccessLog").replace("[member]", event.getMember().getAsMention()).replace("[prefix]", args[2]));
                                        log.setColor(Colors.correctGreen);
                                        log.setTimestamp(Instant.now());
                                        log.setFooter(locale.getMessage(event.getGuild(), "embed", "footerPrefixChangeLog"));

                                        if (utils.getGuildLogChannel(event.getGuild()) != null) {
                                            utils.getGuildLogChannel(event.getGuild()).sendMessage(log.build()).queue((logger) -> {
                                                event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                                    message.delete().queueAfter(20, TimeUnit.SECONDS);
                                                    eb.clear();
                                                    log.clear();
                                                });
                                            });
                                        }
                                        break;
                                    }
                                    case "locale":
                                    case "language":
                                    case "lang": {
                                        utils.setLocale(event.getGuild(), args[2]);
                                        eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "setLocaleSuccess").replace("[locale]", args[2]));
                                        eb.setColor(Colors.correctGreen);
                                        eb.setTimestamp(Instant.now());
                                        eb.setFooter(locale.getMessage(event.getGuild(), "embed", "footerLocaleChangeLog"));

                                        log.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "setLocaleSuccessLog").replace("[member]", event.getMember().getAsMention()).replace("[locale]", args[2]));
                                        log.setColor(Colors.correctGreen);
                                        log.setTimestamp(Instant.now());
                                        log.setFooter(locale.getMessage(event.getGuild(), "embed", "footerLocaleChangeLog"));

                                        if (utils.getGuildLogChannel(event.getGuild()) != null) {
                                            utils.getGuildLogChannel(event.getGuild()).sendMessage(log.build()).queue((logger) -> {
                                                event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                                    message.delete().queueAfter(20, TimeUnit.SECONDS);
                                                    eb.clear();
                                                    log.clear();
                                                });
                                            });
                                        }
                                        break;
                                    }
                                    case "logchannel":
                                    case "log": {
                                        utils.setGuildLogChannel(event.getGuild(), event.getMessage().getMentionedChannels().get(0).getId());
                                        eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "setLogChannelSuccess").replace("[channel]", event.getMessage().getMentionedChannels().get(0).getAsMention()));
                                        eb.setColor(Colors.correctGreen);
                                        eb.setTimestamp(Instant.now());
                                        eb.setFooter(locale.getMessage(event.getGuild(), "embed", "footerLogChannelChangeLog"));

                                        log.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "setLogChannelSuccessLog").replace("[member]", event.getMember().getAsMention()).replace("[channel]", event.getMessage().getMentionedChannels().get(0).getAsMention()));
                                        log.setColor(Colors.correctGreen);
                                        log.setTimestamp(Instant.now());
                                        log.setFooter(locale.getMessage(event.getGuild(), "embed", "footerLogChannelChangeLog"));

                                        if (utils.getGuildLogChannel(event.getGuild()) != null) {
                                            utils.getGuildLogChannel(event.getGuild()).sendMessage(log.build()).queue((logger) -> {
                                                event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                                    message.delete().queueAfter(20, TimeUnit.SECONDS);
                                                    eb.clear();
                                                    log.clear();
                                                });
                                            });
                                        }
                                        break;
                                    }
                                    case "joinlog":
                                    case "leavelog":
                                    case "joinleavelog": {
                                        utils.setJoinLog(event.getGuild(), event.getMessage().getMentionedChannels().get(0).getId());
                                        eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "setJoinLeaveLogSuccess").replace("[channel]", event.getMessage().getMentionedChannels().get(0).getAsMention()));
                                        eb.setColor(Colors.correctGreen);
                                        eb.setTimestamp(Instant.now());
                                        eb.setFooter(locale.getMessage(event.getGuild(), "embed", "footerJoinLogChannelChangeLog"));

                                        log.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "setJoinLeaveLogSuccessLog").replace("[member]", event.getMember().getAsMention()).replace("[channel]", event.getMessage().getMentionedChannels().get(0).getAsMention()));
                                        log.setColor(Colors.correctGreen);
                                        log.setTimestamp(Instant.now());
                                        log.setFooter(locale.getMessage(event.getGuild(), "embed", "footerJoinLogChannelChangeLog"));

                                        if (utils.getGuildLogChannel(event.getGuild()) != null) {
                                            utils.getGuildLogChannel(event.getGuild()).sendMessage(log.build()).queue((logger) -> {
                                                event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                                    message.delete().queueAfter(20, TimeUnit.SECONDS);
                                                    eb.clear();
                                                    log.clear();
                                                });
                                            });
                                        }
                                        break;
                                    }
                                    case "ownerrole":
                                    case "owner": {
                                        utils.setOwnerRole(event.getGuild(), event.getMessage().getMentionedRoles().get(0).getId());
                                        eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "setOwnerRoleSuccess").replace("[role]", event.getMessage().getMentionedRoles().get(0).getAsMention()));
                                        eb.setColor(Colors.correctGreen);
                                        eb.setTimestamp(Instant.now());
                                        eb.setFooter(locale.getMessage(event.getGuild(), "embed", "footerOwnerRoleChangeLog"));

                                        log.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "setOwnerRoleSuccessLog").replace("[member]", event.getMember().getAsMention()).replace("[role]", event.getMessage().getMentionedRoles().get(0).getAsMention()));
                                        log.setColor(Colors.correctGreen);
                                        log.setTimestamp(Instant.now());
                                        log.setFooter(locale.getMessage(event.getGuild(), "embed", "footerOwnerRoleChangeLog"));

                                        if (utils.getGuildLogChannel(event.getGuild()) != null) {
                                            utils.getGuildLogChannel(event.getGuild()).sendMessage(log.build()).queue((logger) -> {
                                                event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                                    message.delete().queueAfter(20, TimeUnit.SECONDS);
                                                    eb.clear();
                                                    log.clear();
                                                });
                                            });
                                        }
                                        break;
                                    }
                                    case "administratorrole":
                                    case "administrator":
                                    case "adminrole":
                                    case "admin": {
                                        utils.setAdministratorRole(event.getGuild(), event.getMessage().getMentionedRoles().get(0).getId());
                                        eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "setAdminRoleSuccess").replace("[role]", event.getMessage().getMentionedRoles().get(0).getAsMention()));
                                        eb.setColor(Colors.correctGreen);
                                        eb.setTimestamp(Instant.now());
                                        eb.setFooter(locale.getMessage(event.getGuild(), "embed", "footerAdminRoleChangeLog"));

                                        log.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "setAdminRoleSuccessLog").replace("[member]", event.getMember().getAsMention()).replace("[role]", event.getMessage().getMentionedRoles().get(0).getAsMention()));
                                        log.setColor(Colors.correctGreen);
                                        log.setTimestamp(Instant.now());
                                        log.setFooter(locale.getMessage(event.getGuild(), "embed", "footerAdminRoleChangeLog"));

                                        if (utils.getGuildLogChannel(event.getGuild()) != null) {
                                            utils.getGuildLogChannel(event.getGuild()).sendMessage(log.build()).queue((logger) -> {
                                                event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                                    message.delete().queueAfter(20, TimeUnit.SECONDS);
                                                    eb.clear();
                                                    log.clear();
                                                });
                                            });
                                        }
                                        break;
                                    }
                                    case "moderatorrole":
                                    case "moderator":
                                    case "modrole":
                                    case "mod": {
                                        utils.setModeratorRole(event.getGuild(), event.getMessage().getMentionedRoles().get(0).getId());
                                        eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "setModRoleSuccess").replace("[role]", event.getMessage().getMentionedRoles().get(0).getAsMention()));
                                        eb.setColor(Colors.correctGreen);
                                        eb.setTimestamp(Instant.now());
                                        eb.setFooter(locale.getMessage(event.getGuild(), "embed", "footerModRoleChangeLog"));

                                        log.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "setModRoleSuccessLog").replace("[member]", event.getMember().getAsMention()).replace("[role]", event.getMessage().getMentionedRoles().get(0).getAsMention()));
                                        log.setColor(Colors.correctGreen);
                                        log.setTimestamp(Instant.now());
                                        log.setFooter(locale.getMessage(event.getGuild(), "embed", "footerModRoleChangeLog"));

                                        if (utils.getGuildLogChannel(event.getGuild()) != null) {
                                            utils.getGuildLogChannel(event.getGuild()).sendMessage(log.build()).queue((logger) -> {
                                                event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                                    message.delete().queueAfter(20, TimeUnit.SECONDS);
                                                    eb.clear();
                                                    log.clear();
                                                });
                                            });
                                        }
                                        break;
                                    }
                                    case "helperrole":
                                    case "helper": {
                                        utils.setHelperRole(event.getGuild(), event.getMessage().getMentionedRoles().get(0).getId());
                                        eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "setHelperRoleSuccess").replace("[role]", event.getMessage().getMentionedRoles().get(0).getAsMention()));
                                        eb.setColor(Colors.correctGreen);
                                        eb.setTimestamp(Instant.now());
                                        eb.setFooter(locale.getMessage(event.getGuild(), "embed", "footerHelperRoleChangeLog"));

                                        log.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "setHelperRoleSuccessLog").replace("[member]", event.getMember().getAsMention()).replace("[role]", event.getMessage().getMentionedRoles().get(0).getAsMention()));
                                        log.setColor(Colors.correctGreen);
                                        log.setTimestamp(Instant.now());
                                        log.setFooter(locale.getMessage(event.getGuild(), "embed", "footerHelperRoleChangeLog"));

                                        if (utils.getGuildLogChannel(event.getGuild()) != null) {
                                            utils.getGuildLogChannel(event.getGuild()).sendMessage(log.build()).queue((logger) -> {
                                                event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                                    message.delete().queueAfter(20, TimeUnit.SECONDS);
                                                    eb.clear();
                                                    log.clear();
                                                });
                                            });
                                        }
                                        break;
                                    }
                                    case "remove":
                                    case "rem":
                                    case "reset": {
                                        switch (args[2]) {
                                            case "prefix": {
                                                utils.setPrefix(event.getGuild(), "Q!");
                                                eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "setDefaultPrefixSuccess"));
                                                eb.setColor(Colors.correctGreen);
                                                eb.setTimestamp(Instant.now());
                                                eb.setFooter(locale.getMessage(event.getGuild(), "embed", "footerPrefixChangeLog"));

                                                log.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "setDefaultPrefixSuccessLog").replace("[member]", event.getMember().getAsMention()));
                                                log.setColor(Colors.correctGreen);
                                                log.setTimestamp(Instant.now());
                                                log.setFooter(locale.getMessage(event.getGuild(), "embed", "footerPrefixChangeLog"));

                                                if (utils.getGuildLogChannel(event.getGuild()) != null) {
                                                    utils.getGuildLogChannel(event.getGuild()).sendMessage(log.build()).queue((logger) -> {
                                                        event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                                            message.delete().queueAfter(20, TimeUnit.SECONDS);
                                                            eb.clear();
                                                            log.clear();
                                                        });
                                                    });
                                                }
                                                break;
                                            }
                                            case "locale":
                                            case "language":
                                            case "lang": {
                                                utils.setLocale(event.getGuild(), utils.getDefaultLocale(event.getGuild()));
                                                eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "setDefaultLocaleSuccess").replace("[locale]", utils.getDefaultLocale(event.getGuild())));
                                                eb.setColor(Colors.correctGreen);
                                                eb.setTimestamp(Instant.now());
                                                eb.setFooter(locale.getMessage(event.getGuild(), "embed", "footerLocaleChangeLog"));

                                                log.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "setDefaultLocaleSuccessLog").replace("[member]", event.getMember().getAsMention()).replace("[locale]", utils.getDefaultLocale(event.getGuild())));
                                                log.setColor(Colors.correctGreen);
                                                log.setTimestamp(Instant.now());
                                                log.setFooter(locale.getMessage(event.getGuild(), "embed", "footerLocaleChangeLog"));

                                                if (utils.getGuildLogChannel(event.getGuild()) != null) {
                                                    utils.getGuildLogChannel(event.getGuild()).sendMessage(log.build()).queue((logger) -> {
                                                        event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                                            message.delete().queueAfter(20, TimeUnit.SECONDS);
                                                            eb.clear();
                                                            log.clear();
                                                        });
                                                    });
                                                }
                                                break;
                                            }
                                            case "logchannel":
                                            case "log": {
                                                utils.setGuildLogChannel(event.getGuild(), "");
                                                eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "removeLogChannelSuccess"));
                                                eb.setColor(Colors.correctGreen);
                                                eb.setTimestamp(Instant.now());
                                                eb.setFooter(locale.getMessage(event.getGuild(), "embed", "footerLogChannelChangeLog"));

                                                log.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "removeLogChannelSuccessLog").replace("[member]", event.getMember().getAsMention()));
                                                log.setColor(Colors.correctGreen);
                                                log.setTimestamp(Instant.now());
                                                log.setFooter(locale.getMessage(event.getGuild(), "embed", "footerLogChannelChangeLog"));

                                                if (utils.getGuildLogChannel(event.getGuild()) != null) {
                                                    utils.getGuildLogChannel(event.getGuild()).sendMessage(log.build()).queue((logger) -> {
                                                        event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                                            message.delete().queueAfter(20, TimeUnit.SECONDS);
                                                            eb.clear();
                                                            log.clear();
                                                        });
                                                    });
                                                }
                                                break;
                                            }
                                            case "joinlog":
                                            case "leavelog":
                                            case "joinleavelog": {
                                                utils.setJoinLog(event.getGuild(), "");
                                                eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "removeJoinLeaveLogSuccess"));
                                                eb.setColor(Colors.correctGreen);
                                                eb.setTimestamp(Instant.now());
                                                eb.setFooter(locale.getMessage(event.getGuild(), "embed", "footerJoinLogChannelChangeLog"));

                                                log.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "removeJoinLeaveLogSuccessLog").replace("[member]", event.getMember().getAsMention()));
                                                log.setColor(Colors.correctGreen);
                                                log.setTimestamp(Instant.now());
                                                log.setFooter(locale.getMessage(event.getGuild(), "embed", "footerJoinLogChannelChangeLog"));

                                                if (utils.getGuildLogChannel(event.getGuild()) != null) {
                                                    utils.getGuildLogChannel(event.getGuild()).sendMessage(log.build()).queue((logger) -> {
                                                        event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                                            message.delete().queueAfter(20, TimeUnit.SECONDS);
                                                            eb.clear();
                                                            log.clear();
                                                        });
                                                    });
                                                }
                                                break;
                                            }
                                            case "ownerrole":
                                            case "owner": {
                                                utils.setOwnerRole(event.getGuild(), "");
                                                eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "removeOwnerRoleSuccess"));
                                                eb.setColor(Colors.correctGreen);
                                                eb.setTimestamp(Instant.now());
                                                eb.setFooter(locale.getMessage(event.getGuild(), "embed", "footerOwnerRoleChangeLog"));

                                                log.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "removeOwnerRoleSuccessLog").replace("[member]", event.getMember().getAsMention()));
                                                log.setColor(Colors.correctGreen);
                                                log.setTimestamp(Instant.now());
                                                log.setFooter(locale.getMessage(event.getGuild(), "embed", "footerOwnerRoleChangeLog"));

                                                if (utils.getGuildLogChannel(event.getGuild()) != null) {
                                                    utils.getGuildLogChannel(event.getGuild()).sendMessage(log.build()).queue((logger) -> {
                                                        event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                                            message.delete().queueAfter(20, TimeUnit.SECONDS);
                                                            eb.clear();
                                                            log.clear();
                                                        });
                                                    });
                                                }
                                                break;
                                            }
                                            case "administratorrole":
                                            case "administrator":
                                            case "adminrole":
                                            case "admin": {
                                                utils.setAdministratorRole(event.getGuild(), "");
                                                eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "removeAdminRoleSuccess"));
                                                eb.setColor(Colors.correctGreen);
                                                eb.setTimestamp(Instant.now());
                                                eb.setFooter(locale.getMessage(event.getGuild(), "embed", "footerAdminRoleChangeLog"));

                                                log.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "removeAdminRoleSuccessLog").replace("[member]", event.getMember().getAsMention()));
                                                log.setColor(Colors.correctGreen);
                                                log.setTimestamp(Instant.now());
                                                log.setFooter(locale.getMessage(event.getGuild(), "embed", "footerAdminRoleChangeLog"));

                                                if (utils.getGuildLogChannel(event.getGuild()) != null) {
                                                    utils.getGuildLogChannel(event.getGuild()).sendMessage(log.build()).queue((logger) -> {
                                                        event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                                            message.delete().queueAfter(20, TimeUnit.SECONDS);
                                                            eb.clear();
                                                            log.clear();
                                                        });
                                                    });
                                                }
                                                break;
                                            }
                                            case "moderatorrole":
                                            case "moderator":
                                            case "modrole":
                                            case "mod": {
                                                utils.setModeratorRole(event.getGuild(), "");
                                                eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "removeModRoleSuccess"));
                                                eb.setColor(Colors.correctGreen);
                                                eb.setTimestamp(Instant.now());
                                                eb.setFooter(locale.getMessage(event.getGuild(), "embed", "footerModRoleChangeLog"));

                                                log.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "removeModRoleSuccessLog").replace("[member]", event.getMember().getAsMention()));
                                                log.setColor(Colors.correctGreen);
                                                log.setTimestamp(Instant.now());
                                                log.setFooter(locale.getMessage(event.getGuild(), "embed", "footerModRoleChangeLog"));

                                                if (utils.getGuildLogChannel(event.getGuild()) != null) {
                                                    utils.getGuildLogChannel(event.getGuild()).sendMessage(log.build()).queue((logger) -> {
                                                        event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                                            message.delete().queueAfter(20, TimeUnit.SECONDS);
                                                            eb.clear();
                                                            log.clear();
                                                        });
                                                    });
                                                }
                                                break;
                                            }
                                            case "helperrole":
                                            case "helper": {
                                                utils.setHelperRole(event.getGuild(), "");
                                                eb.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "removeHelperRoleSuccess"));
                                                eb.setColor(Colors.correctGreen);
                                                eb.setTimestamp(Instant.now());
                                                eb.setFooter(locale.getMessage(event.getGuild(), "embed", "footerHelperRoleChangeLog"));

                                                log.setDescription(locale.getMessage(event.getGuild(), "settingsCommand", "removeHelperRoleSuccessLog").replace("[member]", event.getMember().getAsMention()));
                                                log.setColor(Colors.correctGreen);
                                                log.setTimestamp(Instant.now());
                                                log.setFooter(locale.getMessage(event.getGuild(), "embed", "footerHelperRoleChangeLog"));

                                                if (utils.getGuildLogChannel(event.getGuild()) != null) {
                                                    utils.getGuildLogChannel(event.getGuild()).sendMessage(log.build()).queue((logger) -> {
                                                        event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                                            message.delete().queueAfter(20, TimeUnit.SECONDS);
                                                            eb.clear();
                                                            log.clear();
                                                        });
                                                    });
                                                }
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        } else if (event.getMember().getRoles().contains(utils.getAdministratorRole(event.getGuild()))) {
                            eb.setDescription(locale.getMessage(event.getGuild(), "permissions", "insufficientPermissionsNoAdmin"));
                            eb.setColor(Colors.errorRed);
                            eb.setTimestamp(Instant.now());
                            eb.setFooter(locale.getMessage(event.getGuild(), "embed", "insufficientPermissions"));

                            event.getChannel().sendMessage(eb.build()).queue((message) -> {
                                message.delete().queueAfter(20, TimeUnit.SECONDS);
                                eb.clear();
                            });
                        }
                    } else {
                        repeated.warnOfBlacklistedMember(event);
                    }
                } else {
                    repeated.warnOfBlacklistedServer(event);
                }
            }
        }
    }
}
