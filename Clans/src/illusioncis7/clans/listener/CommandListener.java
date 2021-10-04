package illusioncis7.clans.listener;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import illusioncis7.clans.Clan;
import illusioncis7.clans.ClanManager;
import illusioncis7.clans.Invitation;
import illusioncis7.clans.Main;
import illusioncis7.clans.Messages;
import illusioncis7.clans.Spieler;

public class CommandListener implements CommandExecutor {

	private Main instance;
	private Messages msg;
	
	public CommandListener(Main main)
	{
		instance = main;
		msg = new Messages(instance);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player && args.length > 0)
		{
			Player p = (Player)sender;
			Spieler cplayer = new Spieler(instance, p.getUniqueId().toString());
			
			if (args[0].equalsIgnoreCase("create") && args.length == 2)
			{
				commandCreate(cplayer, args);
			}
			else if (args[0].equalsIgnoreCase("delete") && args.length == 1)
			{
				commandDelete(cplayer, args);
			}
			else if (args[0].equalsIgnoreCase("setbase") && args.length == 1)
			{
				commandSetBase(cplayer, args);
			}
			else if (args[0].equalsIgnoreCase("tpbase") && args.length == 1)
			{
				commandTpBase(cplayer, args);
			}
			else if (args[0].equalsIgnoreCase("namecolor") && args.length == 2)
			{
				if (cplayer.setNameColor(args[1]))
				{
					cplayer.getPlayer().sendMessage(msg.ColorsNameChanged());
				}
				else
				{
					cplayer.getPlayer().sendMessage(msg.ColorCodeNotRecognized());
				}
			}
			else if (args[0].equalsIgnoreCase("groupcolor") && args.length == 2)
			{
				commandClanColor(cplayer, args);
			}
			else if (args[0].equalsIgnoreCase("stats") && args.length >= 1 && args.length < 3)
			{
				commandStats(cplayer, args);
			}
			else if (args[0].equalsIgnoreCase("addmember") && args.length == 2)
			{
				commandAddMember(cplayer, args);
			}
			else if (args[0].equalsIgnoreCase("removemember") && args.length == 2)
			{
				commandRemoveMember(cplayer, args);
			}
			else if (args[0].equalsIgnoreCase("accept") && args.length == 2)
			{
				commandAccept(cplayer, args);
			}
			else if (args[0].equalsIgnoreCase("decline") && args.length == 2)
			{
				commandDecline(cplayer, args);
			}
			else if (args[0].equalsIgnoreCase("leave") && args.length == 1)
			{
				commandLeave(cplayer, args);
			}
			else if (args[0].equalsIgnoreCase("invitations") && args.length == 1)
			{
				commandInvitations(cplayer, args);
			}
			else if (args[0].equalsIgnoreCase("chat") && args.length == 1)
			{
				commandGroupChat(cplayer, args);
			}
			return true;
		}
		else if (sender instanceof Player && args.length == 0)
		{
			Player p = (Player)sender;
			List<String> cmds = msg.getCommandInfos();
			for (String s : cmds)
			{
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
			}
			return true;
		}
		
		return false;
	}

	private void commandGroupChat(Spieler p, String[] args)
	{
		if (p.hasClan())
		{
			p.toggleClanChat();
			p.getPlayer().sendMessage(msg.toggleClanChat(p.clanChatActive()));
		}
		else
		{
			p.getPlayer().sendMessage(msg.DeleteClanDoesntExist());
		}
	}
	
	private void commandInvitations(Spieler p, String[] args)
	{
		if (!p.getInvitations().isEmpty())
		{
			for (String s : p.getInvitations())
			{
				p.getPlayer().sendMessage(msg.InviteList(s));
			}
		}
		else
		{
			p.getPlayer().sendMessage(msg.InviteNotExists());
		}
	}
	
	private void commandLeave(Spieler p, String[] args)
	{
		if (p.hasClan())
		{
			p.leaveClan();
			p.getPlayer().sendMessage(msg.clanLeaving());
		}
		else
		{
			p.getPlayer().sendMessage(msg.DeleteClanDoesntExist());
		}
	}
	
	private boolean commandAccept(Spieler p, String[] args)
	{
		if (p.hasClan())
		{
			p.getPlayer().sendMessage(msg.AlreadyInClan());
		}
		else
		{
			for (String s : p.getInvitations())
			{
				if (args[1].equalsIgnoreCase(s))
				{
					Invitation inv = new Clan(instance, args[1]).getInvitation(p);
					inv.acceptInvitation();
					p.getPlayer().sendMessage(msg.InviteAccepted());
					return true;
				}
			}
			p.getPlayer().sendMessage(msg.InviteNotExists());
			return true;
		}
		return false;
	}
	private boolean commandDecline(Spieler p, String[] args)
	{
		for (String s : p.getInvitations())
		{
			if (args[1].equalsIgnoreCase(s))
			{
				Invitation inv = new Clan(instance, args[1]).getInvitation(p);
				inv.removeInvitation();
				p.getPlayer().sendMessage(msg.InviteDeclined());
				return true;
			}
		}
		p.getPlayer().sendMessage(msg.InviteNotExists());
		return false;
	}
	
	private void commandRemoveMember(Spieler p, String[] args)
	{
		if (p.hasClan())
		{
			if (p.getClan().getOwner().getUniqueUserID().equals(p.getUniqueUserID()))
			{
				@SuppressWarnings("deprecation")
				OfflinePlayer op = Bukkit.getOfflinePlayer(args[1]);
				if (op.hasPlayedBefore())
				{
					p.getClan().removeMember(new Spieler(instance, op.getUniqueId().toString()));
					p.getPlayer().sendMessage(msg.MemberRemoveSuccess());
				}
			}
			else
			{
				p.getPlayer().sendMessage(msg.ClanDeleteNotOwner());
			}
		}
		else
		{
			p.getPlayer().sendMessage(msg.DeleteClanDoesntExist());
		}
	}
	
	private void commandAddMember(Spieler p, String[] args)
	{
		if (p.hasClan())
		{
			if (p.getClan().getOwner().getUniqueUserID().equals(p.getUniqueUserID()))
			{
				Player player;
				if ((player = Bukkit.getPlayer(args[1])) != null)
				{
					Spieler s = new Spieler(instance, player.getUniqueId().toString());
					s.setInvitation(p.getClan().getInvitation(s));
					p.getPlayer().sendMessage(msg.MemberAddSuccess());
					player.sendMessage(msg.InviteIncoming(p.getClan().getClanName()));
				}
				else
				{
					p.getPlayer().sendMessage(msg.PlayerNotOnline());
				}
			}
			else
			{
				p.getPlayer().sendMessage(msg.ClanDeleteNotOwner());
			}
		}
		else
		{
			p.getPlayer().sendMessage(msg.DeleteClanDoesntExist());
		}
	}
	
	private void commandStats(Spieler p, String[] args)
	{
		if (args.length == 1)
		{
			if (p.hasClan())
			{
				for (String line : msg.ClanStats(p.getClan()))
				{
					p.getPlayer().sendMessage(line);
				}
			}
			else
			{
				p.getPlayer().sendMessage(msg.DeleteClanDoesntExist());
			}
		}
		else if (args.length == 2)
		{
			if (new ClanManager(instance).clanExist(args[1]))
			{
				for (String line : msg.ClanStats(new Clan(instance, args[1])))
				{
					p.getPlayer().sendMessage(line);
				}
			}
			else
			{
				p.getPlayer().sendMessage(msg.ClanNotExists());
			}
		}
	}
	
	private void commandDelete(Spieler p, String[] args)
	{
		if (p.hasClan())
		{
			Clan clan = p.getClan();
			if (p.getUniqueUserID().equals(clan.getOwner().getUniqueUserID()))
			{
				String clanName = clan.getClanName();
				new ClanManager(instance).deleteClan(clanName);
			}
			else
			{
				p.getPlayer().sendMessage(msg.ClanDeleteNotOwner());
			}
		}
		else
		{
			p.getPlayer().sendMessage(msg.DeleteClanDoesntExist());
		}
	}
	
	private void commandCreate(Spieler p, String[] args)
	{
		if (p.hasClan())
		{
			p.getPlayer().sendMessage(msg.PlayerAlreadyHasClan());
		}
		else
		{
			if (new ClanManager(instance).clanExist(args[1]))
			{
				p.getPlayer().sendMessage(msg.ClanAlreadyExists(args[1]));
			}
			else
			{
				new ClanManager(instance).createClan(args[1], p);
				p.getPlayer().sendMessage(msg.ClanCreateSuccess(args[1]));
			}
		}
	}

	private void commandSetBase(Spieler p, String[] args)
	{
		if (p.hasClan())
		{
			if (p.getUniqueUserID().equals(p.getClan().getOwner().getUniqueUserID()))
			{
				p.getClan().setBase(p.getPlayer().getLocation());
				p.getPlayer().sendMessage(msg.BaseSpawnCreated());
			}
			else
			{
				p.getPlayer().sendMessage(msg.BaseSpawnNotOwner());
			}
		}
		else
		{
			p.getPlayer().sendMessage(msg.DeleteClanDoesntExist());
		}
	}

	private void commandTpBase(Spieler p, String[] args)
	{
		if (p.hasClan())
		{
			p.getPlayer().teleport(p.getClan().getBase());
			p.getPlayer().sendMessage(msg.BaseSpawnTeleported());
		}
		else
		{
			p.getPlayer().sendMessage(msg.DeleteClanDoesntExist());
		}
	}
	
	private void commandClanColor(Spieler p, String[] args)
	{
		if (p.hasClan())
		{
			if (p.getClan().getOwner().getUniqueUserID().equals(p.getUniqueUserID()))
			{
				if (p.getClan().setPrefixColor(args[1]))
				{
					p.getPlayer().sendMessage(msg.ColorsClanChanged());
				}
				else
				{
					p.getPlayer().sendMessage(msg.ColorCodeNotRecognized());
				}
			}
			else
			{
				p.getPlayer().sendMessage(msg.ColorsClanNotOwner());
			}
		}
		else
		{
			p.getPlayer().sendMessage(msg.DeleteClanDoesntExist());
		}	
	}
}
