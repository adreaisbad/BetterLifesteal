package me.adrea.lifesteal;

import me.adrea.lifesteal.config.PluginConfig;
import me.adrea.lifesteal.settings.ModuleManager;
import me.adrea.lifesteal.settings.Setting;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterLifesteal extends JavaPlugin {

    public static String PREFIX = "&cLS &7> &f";

    private ModuleManager moduleManager;

    @Override
    public void onEnable() {
        // Config

        PluginConfig.setup();

        getConfig().getDefaults().options().copyDefaults(true);
        saveDefaultConfig();

        // Modules

        moduleManager = new ModuleManager();

        moduleManager.initialize();

        for (Setting module : moduleManager.classes) {
            getServer().getPluginManager().registerEvents(module, this);
        }

        // Finalize
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

    public void writeMessage(String message, Player p) {
        p.sendMessage(textWithColor(message));
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }
}
