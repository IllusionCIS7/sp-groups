package illusioncis7.clans;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import illusion.cis7.configmanager.ConfigManager;

public class Spieler {

	private Main instance;
	private String uuid;
	private String nameColor;
	private List<String> invitations;
	private String clanName;
	private boolean isInClan;
	private boolean clanChatToggle;
	private ConfigManager pcm;
	private ConfigManager cm;
	
	// Neuen Spieler hinzufügen
	public Spieler(Main main, String uuid, boolean isNew)
	{
		instance = main;
		if (isNew)
		{
			pcm = new ConfigManager(instance, "spieler.yml");
			cm = new ConfigManager(instance, "config.yml");
			pcm.getConfig().set("spieler." + uuid + ".namecolor", cm.getConfig().getString("defaultNameColor"));
			pcm.getConfig().set("spieler." + uuid + ".clanChatToggle", false);
			
			pcm.save();
			cm.save();
		}
	}
	
	// Vorhandenen Spieler abrufen
	public Spieler(Main main, String uuid)
	{
		instance = main;
		pcm = new ConfigManager(instance, "spieler.yml");
		cm = new ConfigManager(instance, "config.yml");
		this.uuid = uuid;
		nameColor = pcm.getConfig().getString("spieler." + uuid + ".namecolor");
		invitations = pcm.getConfig().getStringList("spieler." + uuid + ".invitations");
		clanName = "";
		clanName = pcm.getConfig().getString("spieler." + uuid + ".clan");
		isInClan = ((clanName != null)? true : false);
		clanChatToggle = pcm.getConfig().getBoolean("spieler." + uuid + ".clanChatToggle");
	}
	
	public boolean hasClan()
	{
		return isInClan;
	}
	
	public boolean clanChatActive()
	{
		return clanChatToggle;
	}
	
	public void toggleClanChat()
	{
		clanChatToggle = (clanChatToggle ? false : true);
		pcm.getConfig().set("spieler." + getUniqueUserID() + ".clanChatToggle", clanChatToggle);
		pcm.save();
	}
	
	public Clan getClan()
	{
		if (isInClan)
		{
			return new Clan(instance, clanName);
		}
		else
		{
			return null;
		}
	}
	
	public void setClan(Clan clan)
	{
		this.clanName = clan.getClanName();
		pcm.getConfig().set("spieler." + this.getUniqueUserID() + ".clan", this.clanName);
		pcm.save();
	}
	
	public void setInvitation(Invitation inv)
	{
		boolean is = false;
		for (String s : getInvitations())
		{
			if (inv.getClan().getClanName().equals(s))
			{
				is = true;
			}
		}
		if (!is)
		{
			invitations.add(inv.getClan().getClanName());
			pcm.getConfig().set("spieler." + this.getUniqueUserID() + ".invitations", invitations);
			pcm.save();
		}
	}
	
	public boolean isClanOwner()
	{
		if (this.hasClan())
		{
			if (getClan().getOwner().getUniqueUserID().equals(getUniqueUserID()))
			{
				return true;
			}
		}
		return false;
	}
	
	public void leaveClan()
	{
		if (getClan().getOwner().getUniqueUserID().equals(getUniqueUserID()))
		{
			ConfigManager ccm = getClan().getClanConfigManager();
			List<Spieler> spieler = getClan().getMembers();
			String uuid = spieler.get(0).getUniqueUserID();
			if (!getUniqueUserID().equals(uuid))
			{
				ccm.getConfig().set("clans." + getClan().getClanName() + ".owner", uuid);
			}
			else
			{
				if (spieler.size() == 1)
				{
					ccm.getConfig().set("clans." + getClan().getClanName(), null);
				}
				else
				{
					ccm.getConfig().set("clans." + getClan().getClanName(), spieler.get(1));
				}
			}
			pcm.getConfig().set("spieler." + getUniqueUserID() + ".clan", null);
			ccm.save();
			pcm.save();
		}
		else
		{
			getClan().removeMember(this);
			pcm.getConfig().set("spieler." + getUniqueUserID() + ".clan", null);
			pcm.save();
		}
	}
	
	public List<String> getInvitations()
	{
		return invitations;
	}
	
	public String getUniqueUserID()
	{
		return uuid;
	}
	
	public String getNameColor()
	{
		return nameColor;
	}
	public boolean setNameColor(String color)
	{
		for (String s : cm.getConfig().getStringList("colorCodes"))
		{
			if (color.equals(s))
			{
				pcm.getConfig().set("spieler." + getUniqueUserID() + ".namecolor", color);
				pcm.save();
				return true;
			}
		}
		return false;
	}
	
	public Player getPlayer()
	{
		return Bukkit.getPlayer(UUID.fromString(getUniqueUserID()));
	}
	public OfflinePlayer getOPlayer()
	{
		return Bukkit.getOfflinePlayer(UUID.fromString(getUniqueUserID()));
	}
	
	public ConfigManager getPlayerConfigManager()
	{
		return pcm;
	}
}
