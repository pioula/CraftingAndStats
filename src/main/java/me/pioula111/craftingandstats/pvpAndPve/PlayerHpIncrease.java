package me.pioula111.craftingandstats.pvpAndPve;

import me.pioula111.craftingandstats.stats.json.StatManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerHpIncrease implements Listener {
    public StatManager statManager;
    
    public PlayerHpIncrease(StatManager statManager) {
        this.statManager = statManager;
    }
    
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            statManager.getPlayerStats(((Player) event.getEntity())).increaseStatExp(((Player) event.getEntity()), "health", 5);
        }
    }
}
