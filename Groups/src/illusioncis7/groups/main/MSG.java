package illusioncis7.groups.main;

import illusioncis7.utils.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.Objects;

public final class MSG {

    private static final FileConfiguration cm = new ConfigManager("messages.yml").getFileConfiguration();


    public static String ChatToggleActive() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cm.getString("chatToggleActive")));
    }

    public static String IllegalColorCode() {
        String msg = cm.getString("illegalColorCode");
        FileConfiguration fc = new ConfigManager("config.yml").getFileConfiguration();
        List<String> illegalCodes = fc.getStringList("forbiddenColors");
        StringBuilder illColors = new StringBuilder();
        for (String s : illegalCodes)
        {
            illColors.append(s).append(", ");
        }
        msg = msg.replaceAll("%illegalColorCodes", illColors.toString());
        return ChatColor.translateAlternateColorCodes('$', msg);
    }

    public static String IllegalGroupName()
    {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cm.getString("illegalGroupName")));
    }

    public static String ChatToggleInactive() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cm.getString("chatToggleInactive")));
    }

    public static String Failed() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cm.getString("failed")));
    }

    public static String GroupNotExists(String falseGroupName) {
        String msg = cm.getString("groupNotExists");
        msg = msg.replaceAll("%groupName%", falseGroupName);
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String PlayerNotExists() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cm.getString("playerNotExists")));
    }

    public static String PlayerNotOnline() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cm.getString("playerNotOnline")));
    }

    public static String MemberRemoveSuccess() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cm.getString("memberRemoveSuccess")));
    }

    public static String MemberAddSuccess() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cm.getString("memberAddSuccess")));
    }

    public static String GotInvite(Group group) {
        String msg = cm.getString("gotInvite");
        msg = msg.replaceAll("%groupName%", group.getGroupName());
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String AlreadyInGroup() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cm.getString("alreadyInGroup")));
    }

    public static String LeavedGroup() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cm.getString("leavedGroup")));
    }

    public static String CreateGroupAlreadyExists(Group group) {
        String msg = cm.getString("createGroup.alreadyExists");
        msg = msg.replaceAll("%groupName%", group.getGroupName());
        msg = msg.replaceAll("%owner%", Objects.requireNonNull(group.getOwner().getOfflinePlayer().getName()));
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String CreateGroupSuccess(Group group) {
        String msg = cm.getString("createGroup.success");
        msg = msg.replaceAll("%groupName%", group.getGroupName());
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String HasNoGroup() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cm.getString("hasNoGroup")));
    }

    public static String DeleteGroupDoesntExists() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cm.getString("deleteGroup.doesntExists")));
    }

    public static String DeleteGroupSuccess(String groupName) {
        String msg = cm.getString("deleteGroup.success");
        msg = msg.replaceAll("%groupName%", groupName);
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String DeleteGroupNotify(String groupName) {
        String msg = cm.getString("deleteGroup.notify");
        msg = msg.replaceAll("%groupName%", groupName);
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String NotOwner() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cm.getString("notOwner")));
    }

    public static String InviteIncoming(Group group) {
        String msg = cm.getString("invite.incoming");
        msg = msg.replaceAll("%groupName%", group.getGroupName());
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String NotOnline() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cm.getString("notOnline")));
    }

    public static String InviteAccepted() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cm.getString("invite.accepted")));
    }

    public static String InviteAlreadyInGroup() {
        return ChatColor.translateAlternateColorCodes('&', cm.getString("invite.alreadyInGroup"));
    }

    public static String InviteSuccessfull(User newUser) {
        String msg = cm.getString("invite.success");
        msg = msg.replaceAll("%newUser%", newUser.getOfflinePlayer().getName());
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String InviteDeclined() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cm.getString("invite.declined")));
    }

    public static String InviteList(Group group) {
        String msg = cm.getString("invite.list");
        msg = msg.replaceAll("%groupName%", group.getGroupName());
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String InviteNotExists() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cm.getString("invite.notExists")));
    }

    public static String BaseSpawnCreated() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cm.getString("baseSpawn.created")));
    }

    public static String BaseSpawnTeleported() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cm.getString("baseSpawn.teleported")));
    }

    public static String BaseSpawnNotExists() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cm.getString("baseSpawn.notExists")));
    }

    public static String ColorsNameColorChanged() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cm.getString("colors.nameColorChanged")));
    }

    public static String ColorsGroupColorChanged() {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cm.getString("colors.groupColorChanged")));
    }

    public static List<String> Stats(Group group) {
        List<String> list = cm.getStringList("stats");
        StringBuilder members = null;
        for (User u : group.getMembers())
        {
            String username = u.getOfflinePlayer().getName();
            members.append(username).append(", ");
        }
        int i = 0;
        for (String s : list)
        {
            list.set(i, list.get(i).replaceAll("%groupName%", group.getGroupName()));
            list.set(i, list.get(i).replaceAll("%owner%", Objects.requireNonNull(group.getOwner().getOfflinePlayer().getName())));
            list.set(i, list.get(i).replaceAll("%members%", members.toString()));
            i++;
        }
        return makeColorsList(list);
    }

    public static List<String> Commands() {
        return makeColorsList(cm.getStringList("commands"));
    }

    private static List<String> makeColorsList(List<String> list)
    {
        List<String> anotherList = null;
        for (String s : list)
        {
            anotherList.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        return anotherList;
    }
}
