package me.adrea.lifesteal.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PluginConfig {

    private static File plFile;
    private static FileConfiguration playersFile;

    public static void setup() {
        plFile = new File(Bukkit.getServer().getPluginManager().getPlugin("BetterLifesteal").getDataFolder(), "players.yml");
        if (!plFile.exists()) {
            plFile.getParentFile().mkdirs();
            try {
                plFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        playersFile = YamlConfiguration.loadConfiguration(plFile);
    }

    public static FileConfiguration getFile() {
        return playersFile;
    }

    public static void saveConfiguration() {
        try {
            playersFile.save(plFile);
        } catch (IOException e) {
            // nigger stuffs
        }
    }

    public static void reloadConfiguration() {
        playersFile = YamlConfiguration.loadConfiguration(plFile);
    }
}
