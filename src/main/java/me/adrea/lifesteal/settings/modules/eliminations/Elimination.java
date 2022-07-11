package me.adrea.lifesteal.settings.modules.eliminations;

import me.adrea.lifesteal.config.PluginConfig;
import me.adrea.lifesteal.settings.Setting;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class Elimination extends Setting {

    // protected HashMap<UUID, PlayerBase> smpPlayers = new HashMap<>();

    private HeartsUtils heartsUtils;

    public Elimination() {
        super("Elimination", "When a player got killed, they lose a heart, " +
                "and the killer gets an extra heart.", true, false, Material.APPLE);

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

        EntityDamageEvent ede = p.getLastDamageCause();

        EntityDamageEvent.DamageCause dc = ede.getCause();

        if (p instanceof Player && pk instanceof Player
                && dc == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {

            // Casual 1v1, I'm the best ww so I got full hearts.

            setHealth(p.getUniqueId(),
                    getCurrentHearts(p.getUniqueId()) - 1 ,
                    getLostHearts(p.getUniqueId()) + 1,
                    getGainedHearts(p.getUniqueId()));

            if (pk != null) {
                setHealth(pk.getUniqueId(),
                        getCurrentHearts(pk.getUniqueId()) + 1 ,
                        getLostHearts(pk.getUniqueId()),
                        getGainedHearts(pk.getUniqueId()) + 1);

                heartsUtils.setMaxHealth(pk, pk.getHealth() + 1);
            }

            heartsUtils.setMaxHealth(p, p.getHealth() - 1);

        } else {

            if (!plugin.getConfig().getBoolean("Settings.AllowEnvironmentDeath")) return;

            setHealth(p.getUniqueId(),
                    getCurrentHearts(p.getUniqueId()) - 1 ,
                    getLostHearts(p.getUniqueId()) + 1,
                    getGainedHearts(p.getUniqueId()));

            if (pk != null) {
                setHealth(pk.getUniqueId(),
                        getCurrentHearts(pk.getUniqueId()) + 1 ,
                        getLostHearts(pk.getUniqueId()),
                        getGainedHearts(pk.getUniqueId()) + 1);

                heartsUtils.setMaxHealth(pk, pk.getHealth() + 1);
            }

            heartsUtils.setMaxHealth(p, p.getHealth()  - 1);
        }



    }

    private void setHealth(UUID uuid, int current, int lost, int gained) {
        PluginConfig.getFile().set(uuid + ".currentHearts", current);
        PluginConfig.getFile().set(uuid + ".lostHearts", lost);
        PluginConfig.getFile().set(uuid + ".gainedHearts", gained);

        PluginConfig.saveConfiguration();
    }

    private int getCurrentHearts(UUID uuid) {
        return PluginConfig.getFile().getInt(uuid + ".currentHearts");
    }

    private int getLostHearts(UUID uuid) {
        return PluginConfig.getFile().getInt(uuid + ".lostHearts");
    }

    private int getGainedHearts(UUID uuid) {
        return PluginConfig.getFile().getInt(uuid + ".gainedHearts");
    }
}
