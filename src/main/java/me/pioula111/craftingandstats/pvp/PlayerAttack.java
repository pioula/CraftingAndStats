package me.pioula111.craftingandstats.pvp;

import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.RandomHelper;
import me.pioula111.craftingandstats.stats.PlayerStats;
import me.pioula111.craftingandstats.stats.json.StatManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PlayerAttack implements Listener {
    public StatManager statManager;

    public PlayerAttack(StatManager statManager) {
        this.statManager = statManager;
    }

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event) {
        if (event.getBow().getItemMeta().getPersistentDataContainer().has(NameSpacedKeys.KEY_DMG, PersistentDataType.DOUBLE)) {
            PersistentDataContainer bowPdc = event.getBow().getItemMeta().getPersistentDataContainer();
            PersistentDataContainer arrowPdc = event.getProjectile().getPersistentDataContainer();
            arrowPdc.set(NameSpacedKeys.KEY_DMG, PersistentDataType.DOUBLE,
                    bowPdc.get(NameSpacedKeys.KEY_DMG, PersistentDataType.DOUBLE) * event.getForce());
            arrowPdc.set(NameSpacedKeys.KEY_STATISTIC, PersistentDataType.STRING, bowPdc.get(NameSpacedKeys.KEY_STATISTIC, PersistentDataType.STRING));
            arrowPdc.set(NameSpacedKeys.KEY_STATISTIC_LEVEL, PersistentDataType.INTEGER, bowPdc.get(NameSpacedKeys.KEY_STATISTIC_LEVEL, PersistentDataType.INTEGER));
            arrowPdc.set(NameSpacedKeys.KEY_WEAPON_TYPE, PersistentDataType.STRING, bowPdc.get(NameSpacedKeys.KEY_WEAPON_TYPE, PersistentDataType.STRING));
        }
    }

    @EventHandler
    public void onPlayerTakeDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player damager = (Player) event.getDamager();
            Player attacked = (Player) event.getEntity();
            event.setCancelled(true);
            double damage = 0;
            if (damager.getInventory().getItemInMainHand().getAmount() > 0 && damager.getInventory().getItemInMainHand().hasItemMeta())
                damage = getDamage(attacked, damager, damager.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer());
            else
                damage = getDamage(attacked, damager, null);

            attacked.damage(damage);
        }
        else if (event.getDamager() instanceof Arrow && event.getEntity() instanceof Player) {
            Arrow arrow = (Arrow) event.getDamager();
            if (arrow.getShooter() instanceof Player) {
                event.setCancelled(true);
                ((Player) event.getEntity()).damage(getDamage((Player) event.getEntity(), (Player) arrow.getShooter(), arrow.getPersistentDataContainer()));
            }
        }
    }


    private double getDamage(Player attacked, Player damager, PersistentDataContainer weapon) {
        if (weapon == null)
            return 1.;
        int armor = 0;
        if (attacked.getInventory().getChestplate() != null) {
            PersistentDataContainer attackedArmorPdc = attacked.getInventory().getChestplate().getItemMeta().getPersistentDataContainer();
            if (attackedArmorPdc.has(NameSpacedKeys.KEY_ARMOR, PersistentDataType.INTEGER)) {
                armor = attackedArmorPdc.get(NameSpacedKeys.KEY_ARMOR, PersistentDataType.INTEGER);
            }
        }

        double dmg = 0;
        PlayerStats damagerStats = statManager.getPlayerStats(damager);
        if (weapon.has(NameSpacedKeys.KEY_DMG, PersistentDataType.DOUBLE)) {

            String requiredStat = weapon.get(NameSpacedKeys.KEY_STATISTIC, PersistentDataType.STRING);

            if (damagerStats.getStat(requiredStat) < weapon.get(NameSpacedKeys.KEY_STATISTIC_LEVEL, PersistentDataType.INTEGER)) {
                damager.sendMessage(ChatColor.RED + "Nie potrafisz posługiwać się tą bronią!");
            }
            else {
                if (!(weapon.has(NameSpacedKeys.KEY_AUTHOR, PersistentDataType.STRING) && weapon.get(NameSpacedKeys.KEY_WEAPON_TYPE, PersistentDataType.STRING).equals("long_distance"))) {
                    long stat = damagerStats.getStat(weapon.get(NameSpacedKeys.KEY_WEAPON_TYPE, PersistentDataType.STRING));
                    if (RandomHelper.hasHappened(stat)) {
                        dmg = weapon.get(NameSpacedKeys.KEY_DMG, PersistentDataType.DOUBLE) + (double)damagerStats.getStat(requiredStat) / 10.;
                    }
                }
            }
        }

        return Math.max(dmg - armor, 1);
    }

}
