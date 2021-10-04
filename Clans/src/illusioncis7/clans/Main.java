package illusioncis7.clans;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import illusioncis7.clans.listener.CommandListener;
import illusioncis7.clans.listener.EventListener;
import illusioncis7.clans.listener.TabCompletion;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(new EventListener(this), this);
		this.getCommand("groups").setExecutor(new CommandListener(this));
		this.getCommand("groups").setTabCompleter(new TabCompletion(this));
		this.saveDefaultConfig();
	}
	
	@Override
	public void onDisable()
	{
		
	}
	
	public WorldEditPlugin getWorldEdit()
	{
		Plugin p = Bukkit.getPluginManager().getPlugin("WorldEdit");
		if (p instanceof WorldEditPlugin) return (WorldEditPlugin) p;
		else return null;
	}
}
