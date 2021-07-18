package me.pioula111.craftingandstats.pvpAndPve;

import me.pioula111.craftingandstats.CraftingAndStats;
import me.pioula111.craftingandstats.ItemHelper;
import me.pioula111.craftingandstats.NameSpacedKeys;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitScheduler;

public class TwoHandedWeapons {
    private CraftingAndStats plugin;
    public TwoHandedWeapons(CraftingAndStats plugin) {
        this.plugin = plugin;
    }

    public void shedule() {
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    ItemStack item = onlinePlayer.getInventory().getItemInMainHand();
                    if (item.getAmount() > 0 && item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(NameSpacedKeys.KEY_WEAPON_TYPE, PersistentDataType.STRING) &&
                            !item.getItemMeta().getPersistentDataContainer().get(NameSpacedKeys.KEY_WEAPON_TYPE, PersistentDataType.STRING).equals("one_handed")) {
                        ItemStack itemInOffHand = onlinePlayer.getInventory().getItemInOffHand();
                        if (itemInOffHand.getAmount() > 0) {
                            onlinePlayer.sendMessage(ChatColor.RED + "Musisz mieć dwie wolne ręce by obsługiwać tę broń!");
                            ItemHelper.dropItem(onlinePlayer,itemInOffHand.clone());
                            onlinePlayer.getInventory().setItemInOffHand(null);
                        }
                    }
                }
            }
        }, 20L, 200L);
    }
}
