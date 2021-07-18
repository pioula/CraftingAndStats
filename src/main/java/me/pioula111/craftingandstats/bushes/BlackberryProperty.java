package me.pioula111.craftingandstats.bushes;

import me.pioula111.craftingandstats.NameSpacedKeys;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BlackberryProperty implements Listener {
    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();
        if (item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING) &&
                item.getItemMeta().getPersistentDataContainer().get(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING).equals(BerriesE.BLACKBERRY.toString())) {
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 300, 1));
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 300, 2));
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 0));
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 300, 1));
        }

    }
}
