package illusioncis7.groups.main;

import illusioncis7.utils.Checker;
import illusioncis7.utils.ConfigManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/*

Diese Klasse stellt einen Spieler dar

 */

public class User {

    // Eigenschaften eines Spielers
    private final String uuid;
    private Player player;
    private String chatColor;
    private Group group;
    private Boolean clanChatActive;
    private List<Invitation> invitations;

    private ConfigManager ucm;

    /* Wird als zweiter Parameter true übergeben, wird der Spieler in der User-Config mit Default-Werten
       angelegt
    */
    public User(String playerByUUID, boolean isNew)
    {
        uuid = playerByUUID;
        if (isNew)
        {
            ConfigManager cfg = new ConfigManager(Main.getPlugin(Main.class), "config.yml");
            ucm = new ConfigManager(Main.getPlugin(Main.class), "user.yml");
            clanChatActive = false;
            chatColor = cfg.getFileConfiguration().getString(CFG.ConfigDefaultUserChatColor());
            save();
        }
    }

    /* Wird nur die UUID eines Spielers an den Konstruktor übergeben, wird das Spieler-Objekt mit den dazugehörigen
       Eigenschaften gefüllt
    */
    public User(String playerByUUID)
    {
        ucm = new ConfigManager(Main.getPlugin(Main.class), "user.yml");
        this.uuid = playerByUUID;
        load();
    }

    public User(Player playerByObject)
    {
        ucm = new ConfigManager(Main.getPlugin(Main.class), "user.yml");
        this.uuid = playerByObject.getUniqueId().toString();
        load();
    }

    // Lädt die Eigenschaften eines Spielers anhand der UUID
    private void load()
    {
        chatColor = ucm.getFileConfiguration().getString(CFG.UserChatColor(uuid));
        clanChatActive = ucm.getFileConfiguration().getBoolean(CFG.UserClanChatActive(uuid));

        // Überprüft, ob eine Gruppe in der Spieler-Config vorhanden ist
        if (ucm.getFileConfiguration().getString(CFG.UserGroup(uuid), "null").equals("null")) {
            group = null;
        } else {
            group = new Group(ucm.getFileConfiguration().getString(CFG.UserGroup(uuid)));
        }

        // Überprüft ob Einladungen in der Spieler-Config vorhanden sind
        if (ucm.getFileConfiguration().getStringList(CFG.UserInvitations(uuid)).isEmpty()) {
            invitations = null;
        } else {
            for (String s : ucm.getFileConfiguration().getStringList(CFG.UserInvitations(uuid)))
            {
                /*
                Fügt vorhandene Einladungen hinzu, indem eine neue Einladung mit den Übergabeparametern der Gruppe
                und des Spielers (diese Klasse, deshalb 'this') erstellt wird
                 */
                invitations.add(new Invitation(new Group(s), this));
            }
        }
    }

    // Speichert alle Eigenschaften dieses Spieler-Objekts in der Spieler-Config
    private void save()
    {
        ucm.set(CFG.UserClanChatActive(uuid), clanChatActive);
        ucm.set(CFG.UserChatColor(uuid), chatColor);

        // Einladungen sollen nur gespeichert werden, wenn auch welche vorhanden sind
        if (!invitations.isEmpty())
        {
            List<String> invs = new ArrayList<>();
            for (Invitation i : invitations)
            {
                invs.add(i.group.getGroupName());
            }
            ucm.set(CFG.UserInvitations(uuid), invs);
        }

        // Die Gruppe soll nur gespeichert werden, wenn auch eine vorhanden ist
        if (group != null)
        {
            ucm.set(CFG.UserGroup(uuid), group.getGroupName());
        }
    }

    // Schaltet den Boolean Wert um und speichert dies auch direkt in der User-Config
    public boolean switchClanChat()
    {
        clanChatActive = !clanChatActive;
        save();
        return this.clanChatActive;
    }

    // Gibt einen Booleschen Wert zurück, ob der clanChat aktiv ist
    public boolean getClanChatActive()
    {
        return clanChatActive;
    }

    // Fügt eine Einladung der Liste hinzu und speichert diese sofort
    public void setInvitation(Invitation newInvitation)
    {
        invitations.add(newInvitation);
        save();
    }

    // Gibt eine Liste der Einladungen zurück
    public List<Invitation> getInvitations()
    {
        return this.invitations;
    }

    // Setzt die Gruppe des Spielers und speichert diese sofort
    public void setGroup(Group newGroup)
    {
        this.group = newGroup;
        save();
    }

    // Gibt die Gruppe des Spielers zurück
    public Group getGroup()
    {
        return this.group;
    }

    // Gibt die UUID des Spielers zurück
    public String getUuid()
    {
        return this.uuid;
    }

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

    public String getChatColor()
    {
        return this.chatColor;
    }

    // Überprüft ob der Spieler bzw die UUID im Spieler-Objekt schon in der User-Config existiert
    private boolean userExists()
    {
        return !ucm.getFileConfiguration().getString(CFG.UserChatColor(uuid), "null").equals("null");
    }
    public boolean hasClan()
    {
        return (group!=null);
    }
}
