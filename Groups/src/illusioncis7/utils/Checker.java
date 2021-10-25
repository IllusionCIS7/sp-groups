package illusioncis7.utils;

import illusioncis7.groups.main.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public final class Checker {

    private static final FileConfiguration cm = new ConfigManager("config.yml").getFileConfiguration();

    public static boolean HasIllegalColorCodes(String s)
    {
        List<String> forbiddenColors = cm.getStringList("forbiddenColors");
        return checkStrings(forbiddenColors, s);
    }

    public static boolean HasIllegalGroupName(String s)
    {
        List<String> forbiddenNames = cm.getStringList("forbiddenGroupNames");
        return checkStrings(forbiddenNames, s);
    }

    private static boolean checkStrings(List<String> illegalStrings, String stc)
    {
        String lc = stc.toLowerCase();
        String uc = stc.toUpperCase();

        for (String fs : illegalStrings)
        {
            if (stc.contains(lc) || stc.contains(uc))
            {
                return true;
            }
        }
        return false;
    }
}
