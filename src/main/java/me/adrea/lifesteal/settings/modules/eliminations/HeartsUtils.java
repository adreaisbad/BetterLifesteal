package me.adrea.lifesteal.settings.modules.eliminations;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

public class HeartsUtils {

    public void setMaxHealth(Player e, double max) {
        AttributeInstance a = e.getAttribute(Attribute.GENERIC_MAX_HEALTH);

        if (a != null)
            a.setBaseValue(max);
    }

}
