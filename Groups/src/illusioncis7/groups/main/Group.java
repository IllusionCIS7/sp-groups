package illusioncis7.groups.main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Group {

    private final String guid;
    private String groupName;
    private String chatColor;
    private User owner = null;
    private List<User> member;
    private World baseWorld;
    private Location baseLoc;

    private ConfigManager gcm;

    public Group(String newGroupName, User newOwner)
    {
        this.guid = UUID.fromString(newOwner.getUuid()+ new Random().nextInt(100)).toString();
        this.owner = newOwner;
        this.groupName = newGroupName;
        this.member.add(newOwner);
        getConfigs();
    }

    public Group(String guid)
    {
        this.guid = guid;
        getConfigs();
        load();
    }

    private void save()
    {
        if (!groupExists())
        {
            gcm.set(CFG.GroupOwner(guid), owner.getUuid());
            gcm.set(CFG.GroupMember(guid), getMembersUUID());
        }
        else
        {
            gcm.set(CFG.GroupOwner(guid), owner.getUuid());
            gcm.set(CFG.GroupMember(guid), getMembersUUID());
            gcm.set(CFG.GroupName(guid), groupName);
            gcm.set(CFG.GroupChatColor(guid), chatColor);
            gcm.set(CFG.GroupBaseWorld(guid), (baseWorld==null)?"null":baseWorld.getName());
            gcm.set(CFG.GroupBaseCoordinatesX(guid), (baseLoc==null)?"null":baseLoc.getX());
            gcm.set(CFG.GroupBaseCoordinatesY(guid), (baseLoc==null)?"null":baseLoc.getY());
            gcm.set(CFG.GroupBaseCoordinatesZ(guid), (baseLoc==null)?"null":baseLoc.getZ());
        }
    }

    private List<String> getMembersUUID()
    {
        List<String> memberes = new ArrayList<>();
        for (User u : member)
        {
            memberes.add(u.getUuid());
        }
        return memberes;
    }

    public void setMember(User newUser)
    {
        member.add(newUser);
        save();
    }
    public List<User> getMembers()
    {
        return member;
    }

    private void load()
    {
        if (!gcm.getFileConfiguration().getString(this.guid, "null").equals("null"))
        {
            this.groupName = gcm.getFileConfiguration().getString(CFG.GroupName(guid));
            this.chatColor = gcm.getFileConfiguration().getString(CFG.GroupChatColor(guid));
            this.owner = new User(gcm.getFileConfiguration().getString(CFG.GroupOwner(guid)));
            if (!gcm.getFileConfiguration().getString(CFG.GroupBaseWorld(guid), "null").equals("null"))
            {
                this.baseWorld = Bukkit.getWorld(gcm.getFileConfiguration().getString(CFG.GroupBaseWorld(guid), ""));
            } else {
                this.baseWorld = null;
            }
            if (this.baseWorld != null)
            {
                double x = gcm.getFileConfiguration().getDouble(CFG.GroupBaseCoordinatesX(guid));
                double y = gcm.getFileConfiguration().getDouble(CFG.GroupBaseCoordinatesY(guid));
                double z = gcm.getFileConfiguration().getDouble(CFG.GroupBaseCoordinatesZ(guid));
                this.baseLoc = new Location(this.baseWorld, x, y, z);
            } else {
                this.baseLoc = null;
            }
            List<User> members = new ArrayList<>();
            for (String uuid : gcm.getFileConfiguration().getStringList(CFG.GroupMember(guid)))
            {
                members.add(new User(uuid));
            }
            this.member = members;
        }
    }

    private void getConfigs()
    {
        gcm = new ConfigManager("groups.yml");
    }

    public void setGroupName(String newGroupName)
    {
        this.groupName = newGroupName;
    }
    public String getGroupName()
    {
        return this.groupName;
    }

    public void setChatColor(String newChatColor)
    {
        this.chatColor = newChatColor;
    }
    public String getChatColor()
    {
        return this.chatColor;
    }

    public User getOwner()
    {
        return this.owner;
    }
    public void setOwner(User newOwner)
    {
        this.owner = newOwner;
    }

    private boolean groupExists()
    {
        return (!gcm.getFileConfiguration().getString(CFG.GroupName(guid), "null").equals("null"));
    }
}
