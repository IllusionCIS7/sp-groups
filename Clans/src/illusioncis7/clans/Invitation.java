package illusioncis7.clans;

import java.util.List;

public class Invitation {

	private Clan clan;
	private Spieler spieler;
	
	public Invitation(Clan clan, Spieler s)
	{
		this.clan = clan;
		spieler = s;
	}
	
	public Clan getClan()
	{
		return clan;
	}
	
	public Spieler getPlayer()
	{
		return spieler;
	}
	
	public boolean acceptInvitation()
	{
		if (!getPlayer().hasClan())
		{
			getClan().addMember(getPlayer());
			removeInvitation();
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean removeInvitation()
	{
		List<String> invitations = getPlayer().getInvitations();
		int count = 0;
		for (String s : invitations)
		{
			if (getClan().getClanName().equals(s))
			{
				invitations.remove(count);
				getPlayer().getPlayerConfigManager().getConfig().set("spieler." + getPlayer().getUniqueUserID() + ".invitations", invitations);
				getPlayer().getPlayerConfigManager().save();
				return true;
			}
			count++;
		}
		return false;
	}
}
