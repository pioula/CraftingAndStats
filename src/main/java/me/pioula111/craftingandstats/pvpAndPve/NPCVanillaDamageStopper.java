package me.pioula111.craftingandstats.pvpAndPve;

import net.citizensnpcs.api.event.NPCDamageByEntityEvent;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCVanillaDamageStopper implements Listener {

    @EventHandler
    public void onNPCDAmageByEntity(NPCDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player || event.getDamager() instanceof Arrow)
            event.setDamage(0);
    }
}
