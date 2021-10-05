package illusioncis7.groups.main;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final String uuid;
    private Player player;
    private String chatColor;
    private Group group;
    private Boolean clanChatActive;
    private List<Invitation> invitations;

    private ConfigManager ucm;

    public User(String playerByUUID, boolean isNew)
    {
        uuid = playerByUUID;
        if (isNew)
        {
            ConfigManager cfg = new ConfigManager("config.yml");
            ucm = new ConfigManager("user.yml");
            clanChatActive = false;
            chatColor = cfg.getFileConfiguration().getString(CFG.ConfigDefaultUserChatColor());
            save();
        }
    }

    public User(String playerByUUID)
    {
        ucm = new ConfigManager("user.yml");
        this.uuid = playerByUUID;
    }

    private void load()
    {
        chatColor = ucm.getFileConfiguration().getString(CFG.UserChatColor(uuid));
        if (ucm.getFileConfiguration().getString(CFG.UserGroup(uuid), "null").equals("null")) {
            group = null;
        } else {
            group = new Group(ucm.getFileConfiguration().getString(CFG.UserGroup(uuid)));
        }
        if (ucm.getFileConfiguration().getStringList(CFG.UserInvitations(uuid)).isEmpty()) {
            invitations = null;
        } else {
            for (String s : ucm.getFileConfiguration().getStringList(CFG.UserInvitations(uuid)))
            {
                invitations.add(new Invitation(new Group(s), this));
            }
        }
    }

    private void save()
    {
        ucm.set(CFG.UserClanChatActive(uuid), clanChatActive);
        ucm.set(CFG.UserChatColor(uuid), chatColor);
        if (!invitations.isEmpty())
        {
            List<String> invs = new ArrayList<>();
            for (Invitation i : invitations)
            {
                invs.add(i.group.getGroupName());
            }
            ucm.set(CFG.UserInvitations(uuid), invs);
        }
        if (group != null)
        {
            ucm.set(CFG.UserGroup(uuid), group.getGroupName());
        }
    }

    public boolean switchClanChat()
    {
        clanChatActive = !clanChatActive;
        save();
        return this.clanChatActive;
    }
    public boolean getClanChatActive()
    {
        return clanChatActive;
    }

    public void setInvitation(Invitation newInvitation)
    {
        invitations.add(newInvitation);
        save();
    }
    public List<Invitation> getInvitations()
    {
        return this.invitations;
    }

    public void setGroup(Group newGroup)
    {
        this.group = newGroup;
        save();
    }
    public Group getGroup()
    {
        return this.group;
    }

    public String getUuid()
    {
        return this.uuid;
    }



    private boolean userExists()
    {
        return !ucm.getFileConfiguration().getString(CFG.UserChatColor(uuid), "null").equals("null");
    }
}
