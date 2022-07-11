package me.adrea.lifesteal.settings.modules.eliminations;

import me.adrea.lifesteal.config.PlayersConfig;
import me.adrea.lifesteal.settings.Setting;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class Elimination extends Setting {

    // protected HashMap<UUID, PlayerBase> smpPlayers = new HashMap<>();

    private HeartsUtils heartsUtils;

    public Elimination() {
        super("Elimination", "When a player got killed, they lose a heart, " +
                "and the killer gets an extra heart.", true, false);

        heartsUtils = new HeartsUtils();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        /*
         * smpPlayers.put(p.getUniqueId(), p.hasPlayedBefore() ? new PlayerBase(PlayersConfig.getFile()
         *       .getInt(p.getUniqueId() + ".currentHearts"), PlayersConfig.getFile()
         *       .getInt(p.getUniqueId() + ".lostHearts"), PlayersConfig.getFile()
         *       .getInt(p.getUniqueId() + ".gainedHearts"))
         *           : new PlayerBase(20, 0, 0 ));
        */

        if (!p.hasPlayedBefore()) {
            setHealth(p.getUniqueId(), 20, 0, 0);
        }

        heartsUtils.setMaxHealth(p, getCurrentHearts(p.getUniqueId()));
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        Player p = (Player) e.getEntity();
        Player pk = e.getEntity().getKiller();

        setHealth(p.getUniqueId(),
                getCurrentHearts(p.getUniqueId()) - 1 ,
                getLostHearts(p.getUniqueId()) + 1,
                getGainedHearts(p.getUniqueId()));

        if (pk != null) {
            setHealth(pk.getUniqueId(),
                    getCurrentHearts(pk.getUniqueId()) + 1 ,
                    getLostHearts(pk.getUniqueId()),
                    getGainedHearts(pk.getUniqueId()) + 1);

            heartsUtils.setMaxHealth(pk, getCurrentHearts(pk.getUniqueId()) + 1);
        }

        heartsUtils.setMaxHealth(p, getCurrentHearts(p.getUniqueId()) - 1);

    }

    private void setHealth(UUID uuid, int current, int lost, int gained) {
        PlayersConfig.getFile().set(uuid + ".currentHearts", current);
        PlayersConfig.getFile().set(uuid + ".lostHearts", lost);
        PlayersConfig.getFile().set(uuid + ".gainedHearts", gained);

        PlayersConfig.saveConfiguration();
    }

    private int getCurrentHearts(UUID uuid) {
        return PlayersConfig.getFile().getInt(uuid + ".currentHearts");
    }

    private int getLostHearts(UUID uuid) {
        return PlayersConfig.getFile().getInt(uuid + ".lostHearts");
    }

    private int getGainedHearts(UUID uuid) {
        return PlayersConfig.getFile().getInt(uuid + ".gainedHearts");
    }
}
