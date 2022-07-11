package me.adrea.lifesteal.gui.types;

import me.adrea.lifesteal.BetterLifesteal;
import me.adrea.lifesteal.settings.Setting;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class GlobalModules {

    // Menu
    private int inventorySize = 54;
    private String inventoryName = "BetterLifesteal | Settings";
    private Inventory inventory = Bukkit.createInventory(null, inventorySize, inventoryName);
    private BetterLifesteal plugin = BetterLifesteal.getPlugin(BetterLifesteal.class);

    // Menu Design
    private int CREDIT_SLOT = 15;
    private ItemStack CREDIT_ITEM;

    public void display(Player p) {

        // Credit Item

        CREDIT_ITEM = new ItemStack(Material.PLAYER_HEAD, 1 , (short) 3);

        SkullMeta creditSkull = (SkullMeta) CREDIT_ITEM.getItemMeta();

        creditSkull.setOwner("IPlayLegit");

        CREDIT_ITEM.getItemMeta().setDisplayName(plugin.textWithColor("&f&lBetter&c&lLifesteal"));
        CREDIT_ITEM.getItemMeta().setLore(Arrays.asList(plugin.textWithColor("&r")
                , plugin.textWithColor("&eVersion&f: &f0.0.1")
                , plugin.textWithColor("&eAuthor&f: &fadrea")
                , plugin.textWithColor("&eGitHub&f: &fWIP")
                , plugin.textWithColor("&eDiscord&f: &fWIP")
                , plugin.textWithColor("&r")
                , plugin.textWithColor("&aYou are running the latest version.")));

        CREDIT_ITEM.setItemMeta(creditSkull);

        inventory.setItem(CREDIT_SLOT, CREDIT_ITEM);

        // All Modules

        for (int i = 0; i < 45; i++) {
            for (Setting s : plugin.getModuleManager().classes) {

                ItemStack SETTING = new ItemStack(s.getSettingItem(), 1 , (short) 3);

                SETTING.getItemMeta().setDisplayName(s.getSettingName());

                
            }
        }

    }

}
