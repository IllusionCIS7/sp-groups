package illusioncis7.groups.main;

import illusioncis7.groups.Listener.CLgroups;
import illusioncis7.groups.Listener.ChatListener;
import illusioncis7.groups.Listener.EventListener;
import illusioncis7.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private ConfigManager cm;
    private ConfigManager mcm;
    private ConfigManager ucm;
    private ConfigManager gcm;

    @Override
    public void onEnable()
    {
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        Bukkit.getPluginCommand("groups").setExecutor(new CLgroups());
        Bukkit.getLogger().info(ChatColor.GREEN + "Groups ist aktiv!");
        cm = new ConfigManager(CFG.ConfigFile());
        mcm = new ConfigManager(CFG.MessagesFile());
        ucm = new ConfigManager(CFG.UserFile());
        gcm = new ConfigManager(CFG.GroupsFile());
    }

    @Override
    public void onDisable()
    {

    }
}
