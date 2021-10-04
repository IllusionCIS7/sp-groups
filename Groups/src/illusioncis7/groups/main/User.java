package illusioncis7.groups.main;

import org.bukkit.entity.Player;

import java.util.List;

public class User {

    Main instance;
    String uuid;
    Player player;
    String chatColor;
    Group group;
    Boolean clanChatActive;
    List<Invitation> invitations;

    public User(Main main)
    {
        this.instance = main;
    }
    public User(Main main, String playerByUUID)
    {
        this.instance = main;
        this.uuid = playerByUUID;
    }

    public boolean switchClanChat()
    {
        if (clanChatActive)
        {
            clanChatActive = false;
        }
        else {
            clanChatActive = true;
        }
        Update();
        return this.clanChatActive;
    }
    public boolean getClanChatActive()
    {
        return clanChatActive;
    }

    public void setInvitation(Invitation newInvitation)
    {
        invitations.add(newInvitation);
        Update();
    }
    public List<Invitation> getInvitations()
    {
        return this.invitations;
    }

    public void setGroup(Group newGroup)
    {
        this.group = newGroup;
    }
    public Group getGroup()
    {
        return this.group;
    }

    private void setUuid(String newUUID)
    {
        this.uuid = newUUID;
    }
    public String getUuid()
    {
        return this.uuid;
    }

    private void Update()
    {

    }
    private void GetData()
    {

    }
}
