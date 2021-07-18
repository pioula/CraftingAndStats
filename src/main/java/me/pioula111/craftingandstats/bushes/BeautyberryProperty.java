package me.pioula111.craftingandstats.bushes;

import me.pioula111.craftingandstats.NameSpacedKeys;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BeautyberryProperty implements Listener {
    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();
        if (item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING) &&
                item.getItemMeta().getPersistentDataContainer().get(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING).equals(BerriesE.BEAUTYBERRY.toString())) {
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 0));
        }
    }
}
