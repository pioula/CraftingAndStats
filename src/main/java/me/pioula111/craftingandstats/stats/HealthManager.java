package me.pioula111.craftingandstats.stats;

import me.pioula111.craftingandstats.stats.json.StatManager;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class HealthManager implements Listener {
    private StatManager statManager;

    public HealthManager(StatManager statManager) {
        this.statManager = statManager;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() != getHealth(event.getPlayer())) {
            event.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(getHealth(event.getPlayer()));
        }
    }

    private double getHealth(Player player) {
        return (double)(statManager.getPlayerStats(player).getStat("health"));
    }


}
