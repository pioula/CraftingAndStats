package me.pioula111.craftingandstats.harvestBlocks.harvestTools;

import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.harvestBlocks.HarvestBlock;
import me.pioula111.craftingandstats.harvestBlocks.json.HarvestManager;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class InteractionEventCatcher implements Listener {
    private HarvestManager harvestManager;

    public InteractionEventCatcher(HarvestManager harvestManager) {
        this.harvestManager = harvestManager;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock() != null) {
            Player player = event.getPlayer();
            ItemStack tool = player.getInventory().getItemInMainHand();
            if (tool.getAmount() == 1 &&
                    tool.getItemMeta().getPersistentDataContainer().has(NameSpacedKeys.KEY_AUTHOR, PersistentDataType.STRING)) {
                HTool hTool = harvestManager.getTool(Objects.requireNonNull(tool.getItemMeta().getPersistentDataContainer().get(NameSpacedKeys.KEY_TOOL_TYPE, PersistentDataType.STRING)));
                if (hTool == null)
                    return;

                hTool.onPlayerInteract(player, event.getClickedBlock());
            }
        }
    }
}
