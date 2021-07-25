package me.pioula111.craftingandstats.lockedChests;

import me.pioula111.craftingandstats.lockedChests.json.LockedManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.HashMap;
import java.util.HashSet;

public class AddingOwnerManager implements Listener {
    private HashMap<String, String> players;
    private LockedManager lockedManager;

    public AddingOwnerManager(LockedManager lockedManager) {
        players = new HashMap<>();
        this.lockedManager = lockedManager;
    }

    public void addPlayer(Player player, String owner) {
        players.put(player.getName(), owner);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (players.containsKey(event.getPlayer().getName()) && event.getHand() == EquipmentSlot.HAND &&
                event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock() != null &&
                lockedManager.isOpenable(event.getClickedBlock())) {
            Block clickedBlock = event.getClickedBlock();

            if (isDoor(clickedBlock.getType())) {
                Location doorsLocation = clickedBlock.getLocation();
                doorsLocation.setY(doorsLocation.getY() - 1);
                if (isDoor(event.getPlayer().getWorld().getBlockAt(doorsLocation).getType())) {
                    clickedBlock = event.getPlayer().getWorld().getBlockAt(doorsLocation);
                }
            }

            String blockKey = String.valueOf(clickedBlock.getBlockKey());

            Player player = event.getPlayer();
            lockedManager.addOwner(players.get(player.getName()), blockKey);
            player.sendMessage(ChatColor.GREEN + "Pomyślnie przydzielono właściciela. PAMIETAJ, ŻE ABY WYŁĄCZY TRYB DODAWANIA, MUSISZ KLIKNĄĆ LPM!");
            event.setCancelled(true);
        }
        else if (players.containsKey(event.getPlayer().getName()) &&
                (event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR)) {
            players.remove(event.getPlayer().getName());
            event.getPlayer().sendMessage(ChatColor.GREEN + "Przerwano dodawanie właściciela!");
            event.setCancelled(true);
        }
    }

    private boolean isDoor(Material block) {
        switch (block) {
            case DARK_OAK_DOOR:
            case ACACIA_DOOR:
            case BIRCH_DOOR:
            case CRIMSON_DOOR:
            case JUNGLE_DOOR:
            case OAK_DOOR:
            case SPRUCE_DOOR:
            case WARPED_DOOR:
                return true;
        }

        return false;
    }
}
