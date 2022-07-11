package me.adrea.lifesteal.settings;

import org.bukkit.event.Listener;

public class Setting implements Listener {

    public Setting(String settingName, String settingDescription, boolean settingState, boolean settingDisabled) {
        this.settingName = settingName;
        this.settingDescription = settingDescription;
        this.settingState = settingState;
        this.settingDisabled = settingDisabled;
    }

    private String settingName;
    private String settingDescription;
    private boolean settingState;
    private boolean settingDisabled;

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
}
