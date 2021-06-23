package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import java.util.EventListener;

public class UsingAndRemovingWorkBench implements EventListener {
    private CraftingManager craftingManager;

    public UsingAndRemovingWorkBench(CraftingManager craftingManager) {
        this.craftingManager = craftingManager;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractAtEntityEvent event) {
        if (craftingManager.hasCrafting(event.getRightClicked().getCustomName())) {
            if (event.getPlayer().getMainHand().name().equals("Patyk")) {
                event.getRightClicked().remove();
            }
            else {
                craftingManager.getCrafting(event.getRightClicked().getCustomName()).openMenu(event.getPlayer());
            }
        }
    }
}
