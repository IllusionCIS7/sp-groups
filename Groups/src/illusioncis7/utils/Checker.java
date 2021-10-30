package illusioncis7.utils;

import illusioncis7.groups.main.CFG;
import illusioncis7.groups.main.Group;
import illusioncis7.groups.main.User;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.Set;

public final class Checker {

    private static final FileConfiguration cm = new ConfigManager(CFG.ConfigFile()).getFileConfiguration();

    /*
    public static boolean HasIllegalColorCodes(String s)
    {
        List<String> forbiddenColors = cm.getStringList("forbiddenColors");
        return checkStrings(forbiddenColors, s);
    } */

    public static boolean HasIllegalGroupName(String s)
    {
        List<String> forbiddenNames = cm.getStringList("forbiddenGroupNames");
        return checkStrings(forbiddenNames, s);
    }

    public static Group checkGroupNameAvailability(String groupName)
    {
        FileConfiguration cm = new ConfigManager(CFG.GroupsFile()).getFileConfiguration();
        Set<String> guids = null;
        guids = cm.getConfigurationSection("groups").getKeys(false);
        for (String guid : guids)
        {
            String gn = cm.getString(CFG.GroupName(guid));
            if (gn.equalsIgnoreCase(groupName))
            {
                return new Group(guid);
            }
        }
        return new Group(null);
    }

    public static User checkUserAvailability(String tcUUID)
    {
        FileConfiguration cm = new ConfigManager(CFG.UserFile()).getFileConfiguration();
        Set<String> uuids = null;
        uuids = cm.getConfigurationSection("user").getKeys(false);
        for (String uuid : uuids)
        {
            if (uuid.equals(tcUUID))
            {
                return new User(uuid);
            }
        }
        return null;
    }

    public static boolean isValidColorCode(String colorCode)
    {
        if (colorCode.length()%2==0)
        {
            int numCodes = (colorCode.length()%2);
            List<String> codes = null;
            int i = 0;
            StringBuilder buffer = "";
            for (char c : colorCode.toCharArray())
            {
                buffer.append(c);
                if (i==1)
                {
                    codes.add(buffer.toString());
                    i = 0;
                    buffer = "";
                }
                else
                {
                    i++;
                }
            }
            List<String> listCodes = cm.getStringList(CFG.ColorCodes());
            int valid = 0;
            for (String s : listCodes)
            {
                for (String ss : codes)
                {
                    if (ss.equalsIgnoreCase(s))
                    {
                        valid++;
                    }
                }
            }
            return (valid==codes.size());
        }
        else
        {
            return false;
        }
    }

    private static boolean checkStrings(List<String> illegalStrings, String stc)
    {
        String lc = stc.toLowerCase();
        String uc = stc.toUpperCase();

        for (String fs : illegalStrings)
        {
            if ((stc.contains(fs)) || (lc.contains(fs)) || (uc.contains(fs)))
            {
                return true;
            }
        }
        return false;
    }
}
