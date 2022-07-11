package me.adrea.lifesteal.settings.modules;

import me.adrea.lifesteal.settings.Setting;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLoginEvent;

public class ServerStartCountdown extends Setting {

    public ServerStartCountdown() {
        super("&b&lServer Starting Countdown", "&7If you want all players to join at the same time, " +
                "you can set a countdown timer. Players won't be allowed to join the server unless the timer is over."
                , false, false);
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {

    }

    @Override
    public void enable() {

    }
}
