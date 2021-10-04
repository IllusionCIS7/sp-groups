package illusioncis7.clans;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import illusion.cis7.configmanager.ConfigManager;

public class Clan {
	
	private Main instance;
	private String clanName;
	private String clanOwner;
	private String prefixColor;
	private String baseWorld;
	private double baseX;
	private double baseY;
	private double baseZ;
	private List<String> members;
	private String configPrefix;
	private ConfigManager cm;
	private ConfigManager confMan;
	
	public Clan(Main main, String clanname) {
		instance = main;
		cm = new ConfigManager(instance, "clans.yml");
		confMan = new ConfigManager(instance, "config.yml");
		configPrefix = "clans." + clanname + ".";
		clanName = clanname;
		clanOwner = cm.getConfig().getString(configPrefix + "owner");
		prefixColor = cm.getConfig().getString(configPrefix + "prefix-color");
		baseWorld = cm.getConfig().getString(configPrefix + "base.world");
		baseX = cm.getConfig().getDouble(configPrefix + "base.x");
		baseY = cm.getConfig().getDouble(configPrefix + "base.y");
		baseZ = cm.getConfig().getDouble(configPrefix + "base.z");
		members = cm.getConfig().getStringList(configPrefix + "members");
		
	}
	
	public String getPrefixColor()
	{
		return prefixColor;
	}
	public boolean setPrefixColor(String color)
	{
		for (String s : confMan.getConfig().getStringList("colorCodes"))
		{
			if (color.equals(s))
			{
				cm.getConfig().set(configPrefix + "prefix-color", color);
				cm.save();
				return true;
			}
		}
		return false;
	}
	public void delete()
	{
		cm.getConfig().set("clans." + getClanName(), null);
		cm.save();
	}
	public Location getBase()
	{
		return new Location(Bukkit.getWorld(baseWorld), baseX, baseY, baseZ);
	}
	public void setBase(Location loc)
	{
		ConfigManager clanCM = new ConfigManager(instance, "clans.yml");
		
		String world = loc.getWorld().getName();
		double posx = loc.getX();
		double posy = loc.getY();
		double posz = loc.getZ();
		
		clanCM.getConfig().set("clans." + getClanName() + ".base.world", world);
		clanCM.getConfig().set("clans." + getClanName() + ".base.x", posx);
		clanCM.getConfig().set("clans." + getClanName() + ".base.y", posy);
		clanCM.getConfig().set("clans." + getClanName() + ".base.z", posz);
		clanCM.save();
	}
	public Spieler getOwner()
	{
		return new Spieler(instance, clanOwner);
	}
	public String getClanName()
	{
		return clanName;
	}
	public List<Spieler> getMembers()
	{
		List<Spieler> spieler = new ArrayList<>();
		for (String s : members)
		{
			if (s != "")
			{
				spieler.add(new Spieler(instance, s));
			}
		}
		return spieler;
	}
	
	public void addMember(Spieler spieler)
	{
		members.add(spieler.getUniqueUserID());
		spieler.setClan(this);
		cm.getConfig().set(configPrefix + "members", members);
		cm.save();
	}
	public void removeMember(Spieler spieler)
	{
		int count = 0;
		for (String s : members)
		{
			if (spieler.getUniqueUserID().equals(s))
			{
				members.remove(count);
				cm.getConfig().set(configPrefix + "members", members);
				cm.save();
				break;
			}
			count++;
		}
	}
	
	public Invitation getInvitation(Spieler toAdd)
	{
		return new Invitation(this, toAdd);
	}
	
	public ConfigManager getClanConfigManager()
	{
		return cm;
	}
}
