package illusion.cis7.configmanager;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import illusioncis7.clans.Main;

public class ConfigManager {
	
	private Main instance;
	private File customConfigFile;
	private FileConfiguration customConfig;
	
	public ConfigManager(Main main, String configfile)
	{
		instance = main;
		customConfigFile = null;
		customConfig = null;
		createCustomConfig(configfile);
	}
	public FileConfiguration getConfig()
	{
		return customConfig;
	}
	
	public void save()
	{
		try {
			customConfig.save(customConfigFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean createCustomConfig(String configfile)
	{
		customConfigFile = new File(instance.getDataFolder(), configfile);
		if (!customConfigFile.exists())
		{
			customConfigFile.getParentFile().mkdirs();
			instance.saveResource(configfile, false);
		}
		
		customConfig = new YamlConfiguration();
		try
		{
			customConfig.load(customConfigFile);
			return true;
		}
		catch (IOException | InvalidConfigurationException e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
