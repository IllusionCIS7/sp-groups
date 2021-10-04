package illusioncis7.clans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import illusion.cis7.configmanager.ConfigManager;

public class ClanManager {
	
	 private Main instance;
	 private ConfigManager playerCM;
	 private ConfigManager clanCM;
	 private ConfigManager cm;
	 private String defaultClanColor;
	 private Messages msg;
	
	public ClanManager(Main main)
	{
		instance = main;
		
		playerCM = new ConfigManager(instance, "spieler.yml");
		clanCM = new ConfigManager(instance, "clans.yml");
		cm = new ConfigManager(instance, "config.yml");
		defaultClanColor = cm.getConfig().getString("defaultClanColor");
		msg = new Messages(instance);
	}
	
	public boolean playerExist(String uuid)
	{
		if (playerCM.getConfig().getString("spieler." + uuid, null) != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean clanExist(String clanName)
	{
		if (clanCM.getConfig().getString("clans." + clanName, null) != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void createClan(String clanName, Spieler spieler)
	{
		clanCM.getConfig().set("clans." + clanName + ".owner", spieler.getUniqueUserID());
		List<String> members = new ArrayList<String>();
		members.add(spieler.getUniqueUserID());
		clanCM.getConfig().set("clans." + clanName + ".members", members);
		clanCM.getConfig().set("clans." + clanName + ".prefix-color", defaultClanColor);
		playerCM.getConfig().set("spieler." + spieler.getUniqueUserID() + ".clan", clanName);
		clanCM.save();
		playerCM.save();
	}
	
	public void deleteClan(String clanName)
	{
		List<String> mem = clanCM.getConfig().getStringList("clans." + clanName + ".members");
		Collection<? extends Player> players = Bukkit.getOnlinePlayers();
		
		for (Player p : players)
		{
			for (String uuid : mem)
			{
				if (p.getUniqueId().toString().equals(uuid))
				{
					p.sendMessage(msg.ClanDeleteNotify(clanName));
				}
			}
		}
	}
}
