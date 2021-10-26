package illusioncis7.groups.main;

import illusioncis7.groups.Listener.CLgroups;
import illusioncis7.groups.Listener.ChatListener;
import illusioncis7.groups.Listener.EventListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable()
    {
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        Bukkit.getPluginCommand("groups").setExecutor(new CLgroups());
        Bukkit.getLogger().info(ChatColor.GREEN + "Groups ist aktiv!");
    }

    @Override
    public void onDisable()
    {

    }
}
