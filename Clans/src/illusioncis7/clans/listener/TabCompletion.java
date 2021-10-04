package illusioncis7.clans.listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import illusioncis7.clans.Main;
import illusioncis7.clans.Spieler;

public class TabCompletion implements TabCompleter {

	private List<String> groups = new ArrayList<String>();
	private List<String> colors = new ArrayList<String>();
	private Main instance;
	
	
	public TabCompletion(Main main)
	{
		instance = main;
		groups.add("create");
		groups.add("delete");
		groups.add("setbase");
		groups.add("tpbase");
		groups.add("clancolor");
		groups.add("namecolor");
		groups.add("addmember");
		groups.add("removemember");
		groups.add("invitations");
		groups.add("accept");
		groups.add("decline");
		groups.add("chat");
		groups.add("leave");
		groups.add("stats");
		
		colors.add("&0");
		colors.add("&1");
		colors.add("&2");
		colors.add("&3");
		colors.add("&4");
		colors.add("&5");
		colors.add("&6");
		colors.add("&7");
		colors.add("&8");
		colors.add("&9");
		colors.add("&a");
		colors.add("&b");
		colors.add("&c");
		colors.add("&d");
		colors.add("&e");
		colors.add("&f");
		colors.add("&l");
		colors.add("&o");
		colors.add("&r");
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			Spieler s = new Spieler(instance, p.getUniqueId().toString());
			if (args.length == 1)
			{
				return groups;
			}
			else if (args.length == 2 && (args[0].equalsIgnoreCase("namecolor") || args[0].equalsIgnoreCase("groupcolor")))
			{
				return colors;
			}
			else if (args.length == 2 && args[0].equalsIgnoreCase("addmember"))
			{
				if (s.isClanOwner())
				{
					List<String> suggestion = new ArrayList<String>();
					@SuppressWarnings("unchecked")
					List<Player> plrs = (List<Player>) Bukkit.getOnlinePlayers();
					for (Player player : plrs)
					{
						suggestion.add(player.getName());
					}
					for (Player player : plrs)
					{
						for (Spieler uuid : s.getClan().getMembers())
						{
							if (uuid.getUniqueUserID().equals(player.getUniqueId().toString()))
							{
								suggestion.remove(player.getName());
							}
						}
					}
					return suggestion;
				}
				return null;
			}
			else if (args.length == 2 && args[0].equalsIgnoreCase("removemember"))
			{
				if (s.isClanOwner())
				{
					List<String> response = new ArrayList<String>();
					List<Spieler> spielers = s.getClan().getMembers();
					spielers.remove(s);
					for (Spieler spieler : spielers)
					{
						response.add(spieler.getPlayer().getName());
					}
					return response;
				}
			}
			else if (args.length == 2 && args[0].equalsIgnoreCase("accept"))
			{
				if (!s.getInvitations().isEmpty())
				{
					return s.getInvitations();
				}
			}
		}
		
		return null;
	}

}
