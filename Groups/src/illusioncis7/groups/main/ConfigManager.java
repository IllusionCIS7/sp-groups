package illusioncis7.groups.main;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private Main instance = Main.getPlugin(Main.class);
    private FileConfiguration dataConfig;
    private File file;

    public ConfigManager(String fileName)
    {
        setConfig(fileName);
    }

    public FileConfiguration getFileConfiguration()
    {
        return this.dataConfig;
    }

    public void set(String path, Object obj)
    {
        this.dataConfig.set(path, obj);
        save();
    }

    private void save()
    {
        try {
            dataConfig.save(file);
        } catch (IOException e) {
            instance.getLogger().warning("Config konnte nicht gespeichert werden!: " + file.getName());
        }
    }

    private void setConfig(String fn)
    {
        file = new File(instance.getDataFolder(), fn + ".yml");
        if (!file.exists())
        {
            final boolean mkdirs = file.getParentFile().mkdirs();
            instance.saveResource(fn + ".yml", false);
            dataConfig = new YamlConfiguration();
            try
            {
                dataConfig.load(file);

            }
            catch (IOException | InvalidConfigurationException e)
            {
                e.printStackTrace();
            }
        }
    }
}
