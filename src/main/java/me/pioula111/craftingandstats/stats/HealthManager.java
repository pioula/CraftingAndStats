package me.pioula111.craftingandstats.stats;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import me.pioula111.craftingandstats.CraftingAndStats;
import me.pioula111.craftingandstats.stats.json.StatManager;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class HealthManager implements Listener {
    private StatManager statManager;

    public HealthManager(StatManager statManager) {
        this.statManager = statManager;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() != getHealth(event.getPlayer())) {
                    event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(getHealth(event.getPlayer()));
                }
            }
        }.runTaskLater(JavaPlugin.getPlugin(CraftingAndStats.class), 2L);

    }

    private double getHealth(Player player) {
        return (double)(statManager.getPlayerStats(player).getStat("health"));
    }
}
