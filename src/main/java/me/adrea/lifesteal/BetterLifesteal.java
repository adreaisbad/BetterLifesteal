package me.adrea.lifesteal;

import me.adrea.lifesteal.config.PlayersConfig;
import me.adrea.lifesteal.settings.ModuleManager;
import me.adrea.lifesteal.settings.Setting;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterLifesteal extends JavaPlugin {

    public static String PREFIX = "&cLS &7> &f";

    private ModuleManager moduleManager;

    @Override
    public void onEnable() {
        //

        PlayersConfig.setup();

        moduleManager = new ModuleManager();

        moduleManager.initialize();

        for (Setting module : moduleManager.classes) {
            getServer().getPluginManager().registerEvents(module, this);
        }

        writeConsoleMessage("&aDone.");

    }

    @Override
    public void onDisable() {
        //
        writeConsoleMessage("&cDisabled.");
    }

    public String textWithColor(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public void writeConsoleMessage(String message) {
        getServer().getConsoleSender().sendMessage(textWithColor(message));
    }
}
