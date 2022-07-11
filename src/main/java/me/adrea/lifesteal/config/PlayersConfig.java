package me.adrea.lifesteal.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PlayersConfig {

    private static File file;
    private static FileConfiguration playersFile;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("BetterLifesteal").getDataFolder(), "players.yml");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        playersFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getFile() {
        return playersFile;
    }

    public static void saveConfiguration() {
        try {
            playersFile.save(file);
        } catch (IOException e) {
            // nigger stuffs
        }
    }

    public static void reloadConfiguration() {
        playersFile = YamlConfiguration.loadConfiguration(file);
    }
}
