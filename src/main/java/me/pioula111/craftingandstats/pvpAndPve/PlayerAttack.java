package me.pioula111.craftingandstats.pvpAndPve;

import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.RandomHelper;
import me.pioula111.craftingandstats.pvpAndPve.death.DeathManager;
import me.pioula111.craftingandstats.stats.PlayerStats;
import me.pioula111.craftingandstats.stats.json.StatManager;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PlayerAttack implements Listener {
    private StatManager statManager;
    private DeathManager deathManager;

    public PlayerAttack(StatManager statManager, DeathManager deathManager) {
        this.deathManager = deathManager;
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
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && statManager.hasPlayer(((Player) event.getDamager())) && event.getEntity() instanceof LivingEntity) {

            Player damager = (Player) event.getDamager();
            LivingEntity attacked = (LivingEntity) event.getEntity();
            event.setDamage(0);
            double damage = 0;
            if (damager.getInventory().getItemInMainHand().getAmount() > 0 && damager.getInventory().getItemInMainHand().hasItemMeta())
                damage = getDamage(attacked, damager, damager.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer());
            else
                damage = getDamage(attacked, damager, null);

            attacked.damage(damage);
        }
        else if (event.getDamager() instanceof Arrow && event.getEntity() instanceof LivingEntity) {
            Arrow arrow = (Arrow) event.getDamager();
            if (arrow.getShooter() instanceof Player) {
                event.setDamage(0);
                ((LivingEntity) event.getEntity()).damage(getDamage((LivingEntity) event.getEntity(), (Player) arrow.getShooter(), arrow.getPersistentDataContainer()));
            }
        }
    }


    private int getDamage(LivingEntity attacked, Player damager, PersistentDataContainer weapon) {
        if (deathManager.playerIsDead(damager))
            return 0;
        if (weapon == null)
            return 1;
        int armor = 0;

        if (attacked.getEquipment() != null && attacked.getEquipment().getChestplate() != null && attacked.getEquipment().getChestplate().hasItemMeta()) {
            PersistentDataContainer attackedArmorPdc = attacked.getEquipment().getChestplate().getItemMeta().getPersistentDataContainer();
            if (attackedArmorPdc != null && attackedArmorPdc.has(NameSpacedKeys.KEY_ARMOR, PersistentDataType.INTEGER)) {
                armor = attackedArmorPdc.get(NameSpacedKeys.KEY_ARMOR, PersistentDataType.INTEGER);
            }
        }

        int dmg = 0;
        PlayerStats damagerStats = statManager.getPlayerStats(damager);
        if (weapon.has(NameSpacedKeys.KEY_DMG, PersistentDataType.DOUBLE)) {

            String requiredStat = weapon.get(NameSpacedKeys.KEY_STATISTIC, PersistentDataType.STRING);

            if (damagerStats.getStat(requiredStat) < weapon.get(NameSpacedKeys.KEY_STATISTIC_LEVEL, PersistentDataType.INTEGER)) {
                damager.sendMessage(ChatColor.RED + "Nie potrafisz posługiwać się tą bronią!");
            }
            else {
                if (!(weapon.has(NameSpacedKeys.KEY_AUTHOR, PersistentDataType.STRING) && weapon.get(NameSpacedKeys.KEY_WEAPON_TYPE, PersistentDataType.STRING).equals("long_distance"))) {
                    long stat = damagerStats.getStat(weapon.get(NameSpacedKeys.KEY_WEAPON_TYPE, PersistentDataType.STRING));
                    statManager.getPlayerStats(damager).increaseStatExp(damager, weapon.get(NameSpacedKeys.KEY_WEAPON_TYPE, PersistentDataType.STRING), 5);
                    statManager.getPlayerStats(damager).increaseStatExp(damager, weapon.get(NameSpacedKeys.KEY_STATISTIC, PersistentDataType.STRING), 5);

                    if (RandomHelper.hasHappened(stat)) {
                        dmg = (int) (damagerStats.getStat(requiredStat) / 10 +(int)((double)weapon.get(NameSpacedKeys.KEY_DMG, PersistentDataType.DOUBLE)));
                    }
                }
            }
        }

        return Math.max(dmg - armor, 1);
    }

}
