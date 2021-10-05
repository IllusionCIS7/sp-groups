package illusioncis7.groups.main;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/*

Diese Klasse ist zum Holen der Config-Dateien. Mit ihr kann man Configs durch Übergabe des Dateinamens abrufen, Werte
auslesen und in die Config-Datei schreiben mit sofortiger Speicherung.

 */

public class ConfigManager {

    private Main instance = Main.getPlugin(Main.class);
    private FileConfiguration dataConfig;
    private File file;

    // Konstruktor. Als Parameter wird der Dateiname der gewünschten Config gefordert
    public ConfigManager(String fileName)
    {
        setConfig(fileName);
    }

    // Gibt bei Aufruf die Config zurück
    public FileConfiguration getFileConfiguration()
    {
        return this.dataConfig;
    }

    // Methode zum Setzen von Parametern mit sofortiger Speicherung
    public void set(String path, Object obj)
    {
        this.dataConfig.set(path, obj);
        save();
    }

    // Speichern der Config
    private void save()
    {
        try {
            dataConfig.save(file);
        } catch (IOException e) {
            instance.getLogger().warning("Config konnte nicht gespeichert werden!: " + file.getName());
        }
    }

    // Erstellen / Abrufen der Config
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
