package me.adrea.lifesteal.settings;

import me.adrea.lifesteal.BetterLifesteal;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class Setting implements Listener {

    protected Plugin plugin = BetterLifesteal.getPlugin(BetterLifesteal.class);

    public Setting(String settingName, String settingDescription, boolean settingState, boolean settingDisabled, Material settingItem) {
        this.settingName = settingName;
        this.settingDescription = settingDescription;
        this.settingState = settingState;
        this.settingDisabled = settingDisabled;
        this.settingItem = settingItem;
    }

    private String settingName;
    private String settingDescription;
    private boolean settingState;
    private boolean settingDisabled;
    private Material settingItem;

    public void enable() {}

    public void disable() {}

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getSettingDescription() {
        return settingDescription;
    }

    public void setSettingDescription(String settingDescription) {
        this.settingDescription = settingDescription;
    }

    public boolean isSettingState() {
        return settingState;
    }

    public void setSettingState(boolean settingState) {
        this.settingState = settingState;
    }

    public boolean isSettingDisabled() {
        return settingDisabled;
    }

    public void setSettingDisabled(boolean settingDisabled) {
        this.settingDisabled = settingDisabled;
    }

    public Material getSettingItem() { return settingItem; }

    public void setSettingItem(Material settingItem) { this.settingItem = settingItem; }
}
