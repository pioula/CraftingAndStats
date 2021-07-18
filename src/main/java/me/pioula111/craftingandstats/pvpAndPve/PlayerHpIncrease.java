package me.pioula111.craftingandstats.pvpAndPve;

import me.pioula111.craftingandstats.pvpAndPve.death.DeathManager;
import me.pioula111.craftingandstats.stats.json.StatManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerHpIncrease implements Listener {
    private StatManager statManager;
    private DeathManager deathManager;

    public PlayerHpIncrease(StatManager statManager, DeathManager deathManager) {
        this.deathManager = deathManager;
        this.statManager = statManager;
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.CONTACT)
                return;
            if (deathManager.playerIsDead(player)) {
                event.setCancelled(true);
            }
            else {
                statManager.getPlayerStats(player).increaseStatExp(player, "health", 5);
            }
        }
    }
}
