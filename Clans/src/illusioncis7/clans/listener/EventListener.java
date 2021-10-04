package illusioncis7.clans.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import illusion.cis7.configmanager.ConfigManager;
import illusioncis7.clans.Main;
import illusioncis7.clans.Messages;
import illusioncis7.clans.Spieler;

public class EventListener implements Listener {

	private Main instance;
	
	public EventListener(Main main)
	{
		instance = main;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e)
	{
		ConfigManager playerCM = new ConfigManager(instance, "spieler.yml");
		Bukkit.getConsoleSender().sendMessage("1");
		if (playerCM.getConfig().getString("spieler." + e.getPlayer().getUniqueId().toString(), null) == null)
		{
			Bukkit.getConsoleSender().sendMessage("2");
			new Spieler(instance, e.getPlayer().getUniqueId().toString(), true);
		}
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e)
	{
		ConfigManager msgCM = new ConfigManager(instance, "messages.yml");
		Messages msg = new Messages(instance);
		Player p = e.getPlayer();
		Spieler s = new Spieler(instance, p.getUniqueId().toString());
		e.setFormat(msg.formatMessage(p, s, e.getMessage(), s.clanChatActive()));
		String lastMessageFrom = msg.LastMessageFrom();
		
		if (s.clanChatActive())
		{
			for (Spieler sp : s.getClan().getMembers())
			{
				for (Player pl : Bukkit.getOnlinePlayers())
				{
					if (sp.getUniqueUserID().equals(pl.getUniqueId().toString()))
					{
						String format = msg.formatMessage(p, s, e.getMessage(), s.clanChatActive());
						format = format.replace("%1$s", p.getName());
						format = format.replace("%2$s", e.getMessage());
						pl.sendMessage(format);
					}
				}
			}
			e.setCancelled(true);
		}
		else
		{
			if (p.getUniqueId().toString().equals(lastMessageFrom))
			{
				e.setFormat(ChatColor.translateAlternateColorCodes('&', "&7%2$s"));
			}
			msgCM.getConfig().set("lastMessageFrom", p.getUniqueId().toString());
			msgCM.save();
		}
	}
}
