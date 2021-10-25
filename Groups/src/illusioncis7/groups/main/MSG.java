package illusioncis7.groups.main;

import illusioncis7.utils.ConfigManager;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public final class MSG {

    private static final FileConfiguration cm = new ConfigManager("messages.yml").getFileConfiguration();


    public static String ChatToggleActive() { return cm.getString("chatToggleActive"); }

    public static String ChatToggleInactive() { return cm.getString("chatToggleInactive"); }

    public static String Failed() { return cm.getString("failed"); }

    public static String GroupNotExists() {return cm.getString("groupNotExists"); }

    public static String PlayerNotExists() { return cm.getString("playerNotExists"); }

    public static String PlayerNotOnline() { return cm.getString("playerNotOnline"); }

    public static String MemberRemoveSuccess() { return cm.getString("memberRemoveSuccess"); }

    public static String MemberAddSuccess() { return cm.getString("memberAddSuccess"); }

    public static String GotInvite() { return cm.getString("gotInvite"); }

    public static String AlreadyInGroup() { return cm.getString("alreadyInGroup"); }

    public static String LeavedGroup() { return cm.getString("leavedGroup"); }

    public static String CreateGroupAlreadyExists() { return cm.getString("createGroup.alreadyExists"); }

    public static String CreateGroupSuccess() { return cm.getString("createGroup.success"); }

    public static String DeleteGroupDoesntExists() { return cm.getString("deleteGroup.doesntExists"); }

    public static String DeleteGroupSuccess() { return cm.getString("deleteGroup.success"); }

    public static String DeleteGroupNotify() { return cm.getString("deleteGroup.notify"); }

    public static String NotOwner() { return cm.getString("notOwner"); }

    public static String InviteIncoming() { return cm.getString("invite.incoming"); }

    public static String InviteAccepted() { return cm.getString("invite.accepted"); }

    public static String InviteDeclined() { return cm.getString("invite.declined"); }

    public static String InviteList() { return cm.getString("invite.list"); }

    public static String InviteNotExists() { return cm.getString("invite.notExists"); }

    public static String BaseSpawnCreated() { return cm.getString("baseSpawn.created"); }

    public static String BaseSpawnTeleported() { return cm.getString("baseSpawn.teleported"); }

    public static String ColorsNameColorChanged() { return cm.getString("colors.nameColorChanged"); }

    public static String ColorsGroupColorChanged() { return cm.getString("colors.groupColorChanged"); }

    public static List<String> Stats() { return cm.getStringList("stats"); }

    public static List<String> Commands() { return cm.getStringList("commands"); }

}
