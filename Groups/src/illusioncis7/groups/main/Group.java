package illusioncis7.groups.main;

import illusioncis7.utils.Checker;
import illusioncis7.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/*

Diese Klasse beinhaltet alle für eine Gruppe wichtige Eigenschaften und Methoden

 */

public class Group {

    private final String guid;
    private String groupName;
    private String chatColor;
    private User owner = null;
    private List<User> member;
    private World baseWorld;
    private Location baseLoc;


    private ConfigManager gcm;

    // Konstruktor zum Erstellen einer neuen Gruppe
    public Group(String newGroupName, User newOwner)
    {
        /* Erzeugt eine möglichst zufällige ID für die Gruppe.
           Die ID ist wichtig, da ich es möglich machen möchte, den Gruppennamen wechsel zu können.
           Das wird so erleichtert
        */
        this.guid = UUID.fromString(newOwner.getUuid()+ new Random().nextInt(100)).toString();
        this.owner = newOwner;
        this.groupName = newGroupName;
        this.member.add(newOwner);
        getConfigs();
        save();
    }

    // Wenn nur ein String dem Konstruktor übergeben wird, wird das Gruppe-Objekt mit den Eigenschaften gefüllt
    public Group(String guid)
    {
        this.guid = guid;
        getConfigs();
        load();
    }

    // Speichert die Eigenschaften ab. Je nachdem, ob die Gruppe existiert oder nicht, geschieht das anders
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
            /*
            (baseWorld==null)?"null":baseWorld.getName() im Befehl unter diesem Kommentar ist ein Short-If-Else
            Ausgeschrieben sähe das so aus:

            if (baseWorld==null) {
                gcm.set(CFG.GroupBaseWorld(guid), "null");
            } else {
                gcm.set(CFG.GroupBaseWorld(guid), baseWorld.getName();
            }
             */
            gcm.set(CFG.GroupBaseWorld(guid), (baseWorld==null)?"null":baseWorld.getName());
            gcm.set(CFG.GroupBaseCoordinatesX(guid), (baseLoc==null)?"null":baseLoc.getX());
            gcm.set(CFG.GroupBaseCoordinatesY(guid), (baseLoc==null)?"null":baseLoc.getY());
            gcm.set(CFG.GroupBaseCoordinatesZ(guid), (baseLoc==null)?"null":baseLoc.getZ());
        }
    }

    // Gibt eine Liste der UUIDs aller Member zurück
    private List<String> getMembersUUID()
    {
        // Erstelle eine neue String-Liste
        List<String> memberes = new ArrayList<>();

        /*
        Für jedes Objekt in der Liste member Erstelle ein User-Objekt mit dem Namen u
         */
        for (User u : member)
        {
            // Füge der String-Liste memberes die UUID des aktuellen Users der member Liste hinzu
            memberes.add(u.getUuid());
        }
        // Gebe die String-Liste memberes zurück
        return memberes;
    }

    // Fügt einen Spieler der member Liste hinzu und speichert dies sofort
    public void setMember(User newUser)
    {
        member.add(newUser);
        save();
    }

    // Gibt eine Liste von User Objekten zurück die die Gruppenmitglieder beinhaltet
    public List<User> getMembers()
    {
        return member;
    }

    // Lade alle Parameter der Gruppe in der Config in das Objekt
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

    // Definiert das Objekt gcm welches nun die Config für die groups.yml enthält
    private void getConfigs()
    {
        gcm = new ConfigManager(Main.getPlugin(Main.class), "groups.yml");
    }

    // Selbsterklärend
    public boolean setGroupName(String newGroupName)
    {
        if (Checker.HasIllegalGroupName(groupName))
        {
            return false;
        }
        else
        {
            this.groupName = newGroupName;
            return true;
        }
    }

    // Selbsterklärend
    public String getGroupName()
    {
        return this.groupName;
    }

    // Selbsterklärend
    public boolean setChatColor(String newChatColor)
    {
        if (Checker.HasIllegalColorCodes(newChatColor))
        {
            return false;
        }
        else
        {
            this.chatColor = newChatColor;
            return true;
        }
    }

    // Selbsterklärend
    public String getChatColor()
    {
        return this.chatColor;
    }

    // Selbsterklärend
    public User getOwner()
    {
        return this.owner;
    }

    // Selbsterklärend
    public void setOwner(User newOwner)
    {
        this.owner = newOwner;
    }

    // Gibt einen boolschen Wert zurück, ob die Gruppe bzw dessen UniqueID schon in der Config existiert
    private boolean groupExists()
    {
        return (!gcm.getFileConfiguration().getString(CFG.GroupName(guid), "null").equals("null"));
    }
}
