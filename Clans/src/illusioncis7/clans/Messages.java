package illusioncis7.clans;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import illusion.cis7.configmanager.ConfigManager;

public class Messages {

	private Main instance;
	private ConfigManager msgCM;
	private ConfigManager cm;
	
	public Messages(Main main)
	{
		instance = main;
		msgCM = new ConfigManager(instance, "messages.yml");
		cm = new ConfigManager(instance, "config.yml");
	}
	public String formatMessage(Player player, Spieler spieler, String message, boolean clanChat)
	{
		String format = getChatFormat();
		String clanName = ((spieler.hasClan()) ? spieler.getClan().getClanName() : getDefaultClan());
		String clanColor = (spieler.hasClan() ? spieler.getClan().getPrefixColor() : getDefaultClanColor());
		format = format.replace("%c%", (clanChat ? "&6&lGC&r " : ""));
		format = format.replace("%clanName%", clanName);
		format = format.replace("%cc%", clanColor);
		format = format.replace("%pc%", spieler.getNameColor());
		format = ChatColor.translateAlternateColorCodes('&', format);
		return format;
	}
	public String getDefaultClanColor()
	{
		return ChatColor.translateAlternateColorCodes('&', cm.getConfig().getString("defaultClanColor"));
	}
	public String toggleClanChat(boolean on)
	{
		if (on)
		{
			return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("chatToggleActive"));
		}
		else
		{
			return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("chatToggleInactive"));
		}
	}
	public String getDefaultPlayerColor()
	{
		return ChatColor.translateAlternateColorCodes('&', cm.getConfig().getString("defaultNameColor"));
	}
	public String getDefaultClan()
	{
		return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("defaultClan"));
	}
	public String getChatFormat()
	{
		return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("chatFormat"));
	}
	public String PlayerNotExist()
	{
		return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("playerNotExists"));
	}
	public List<String> getCommandInfos()
	{
		return msgCM.getConfig().getStringList("commands");
	}
	public String AlreadyInClan()
	{
		return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("alreadyInClan"));
	}
	public String InviteNotExists()
	{
		return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("invite.notExists"));
	}
	public String clanLeaving()
	{
		return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("clanLeaving"));
	}
	public String ClanAlreadyExists(String clanName)
	{
		String text = msgCM.getConfig().getString("createClan.alreadyExists");
		text = text.replace("%clanName%", clanName);
		return ChatColor.translateAlternateColorCodes('&', text);
	}
	public String InviteList(String clanName)
	{
		return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("invite.list").replace("%clanName%", clanName));
	}
	public String PlayerNotOnline()
	{
		return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("playerNotOnline"));
	}
	public String MemberRemoveSuccess()
	{
		return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("memberRemoveSuccess"));
	}
	public String MemberAddSuccess()
	{
		return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("memberAddSuccess"));
	}
	public String ClanCreateSuccess(String clanName)
	{
		String text = msgCM.getConfig().getString("createClan.success");
		text = text.replace("%clanName%", clanName);
		return ChatColor.translateAlternateColorCodes('&', text);
	}
	public String DeleteClanDoesntExist()
	{
		return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("deleteClan.doesntExists"));
	}
	public String DeleteClanSuccess(String clanName)
	{
		String text = msgCM.getConfig().getString("deleteClan.success");
		text = text.replace("%clanName%", clanName);
		return ChatColor.translateAlternateColorCodes('&', text);
	}
	public String PlayerAlreadyHasClan()
	{
		return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("player.alreadyHasClan"));
	}
	public String ClanDeleteNotify(String clanName)
	{
		String text = msgCM.getConfig().getString("deleteClan.notify");
		text = text.replace("%clanName%", clanName);
		return ChatColor.translateAlternateColorCodes('&', text);
	}
	public String ClanDeleteNotOwner()
	{
		return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("deleteClan.notOwner"));
	}
	public String InviteAccepted()
	{
		return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("invite.accepted"));
	}
	public String InviteIncoming(String clanName)
	{
		String text = msgCM.getConfig().getString("invite.incoming");
		text = text.replace("%clanName%", clanName);
		return ChatColor.translateAlternateColorCodes('&', text);
	}
	public String InviteDeclined()
	{
		return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("invite.declined"));
	}
	public String BaseSpawnCreated()
	{
		return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("baseSpawn.created"));
	}
	public String BaseSpawnTeleported()
	{
		return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("baseSpawn.teleported"));
	}
	public String BaseSpawnNotOwner()
	{
		return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("baseSpawn.notOwner"));
	}
	public String ColorsNameChanged()
	{
		return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("colors.nameColorChanged"));
	}
	public String ColorsClanChanged()
	{
		return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("colors.clanColorChanged"));
	}
	public String ColorsClanNotOwner()
	{
		return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("colors.clanColorNotOwner"));
	}
	public String ClanNotExists()
	{
		return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("notExists"));
	}
	public String LastMessageFrom()
	{
		return msgCM.getConfig().getString("lastMessageFrom");
	}
	public String ColorCodeNotRecognized()
	{
		return ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("colors.codeNotRecognized"));
	}
	public String[] ClanStats(Clan clan)
	{
		String line1 = ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("stats.line1"));
		String line2 = msgCM.getConfig().getString("stats.line2");
		line2 = ChatColor.translateAlternateColorCodes('&', line2.replace("%clanName%", clan.getClanName()));
		String line3 = msgCM.getConfig().getString("stats.line3");
		line3 = ChatColor.translateAlternateColorCodes('&', line3.replace("%owner%", clan.getOwner().getOPlayer().getName()));
		String line4 = msgCM.getConfig().getString("stats.line4");
		String line5 = ChatColor.translateAlternateColorCodes('&', msgCM.getConfig().getString("stats.line5"));
		
		String members = "";
		
		for (Spieler s : clan.getMembers())
		{
			members = members + s.getOPlayer().getName() + ", ";
		}
		
		line4 = ChatColor.translateAlternateColorCodes('&', line4.replace("%members%", members));
		
		String[] stats = {line1, line2, line3, line4, line5};
		return stats;
	}
}