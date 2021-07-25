package me.pioula111.craftingandstats.lockedChests.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.lockedChests.OpenerInfo;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class LockedManager {
    private HashMap<String, OpenerInfo> lockedOpeners;//lokacja -> czas resetu drzwi/skrzyni/trapdoora

    public LockedManager() {
        this.lockedOpeners = new HashMap<>();
    }

    public boolean canOpen(ItemStack item, String loc) {
        ItemMeta meta = item.getItemMeta();
        if (!meta.getPersistentDataContainer().has(NameSpacedKeys.KEY_OPENERS, PersistentDataType.STRING)) {
            meta.getPersistentDataContainer().set(NameSpacedKeys.KEY_OPENERS, PersistentDataType.STRING, "{}");
            item.setItemMeta(meta);
            return false;
        }
        HashMap<String, String> keyOpeners = deserializeOpeners(meta.getPersistentDataContainer().get(NameSpacedKeys.KEY_OPENERS, PersistentDataType.STRING));

        if (!keyOpeners.containsKey(loc) || !lockedOpeners.containsKey(loc)) {
            return false;
        }

        return keyOpeners.get(loc).equals(lockedOpeners.get(loc).getUuid());
    }

    private HashMap<String, String> deserializeOpeners(String openers) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(openers, HashMap.class);
    }

    private String serializeOpeners(HashMap<String, String> keyOpeners) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(keyOpeners);
    }

    public void createKey(ItemStack key, String loc) {
        ItemMeta meta = key.getItemMeta();
        if (!meta.getPersistentDataContainer().has(NameSpacedKeys.KEY_OPENERS, PersistentDataType.STRING)) {
            meta.getPersistentDataContainer().set(NameSpacedKeys.KEY_OPENERS, PersistentDataType.STRING, "{}");
        }
        String owner = null;
        if (lockedOpeners.containsKey(loc)) {
            owner = lockedOpeners.get(loc).getOwner();
        }

        HashMap<String, String> keyOpeners = deserializeOpeners(meta.getPersistentDataContainer().get(NameSpacedKeys.KEY_OPENERS, PersistentDataType.STRING));
        String uuid = UUID.randomUUID().toString();

        keyOpeners.put(loc, uuid);
        lockedOpeners.put(loc, new OpenerInfo(uuid, owner));

        ArrayList<String> toDelete = new ArrayList<>();
        keyOpeners.forEach((k, v) -> {
            if (!lockedOpeners.containsKey(k) || !lockedOpeners.get(k).getUuid().equals(v)) {
                toDelete.add(k);
            }
        });

        for (String s : toDelete) {
            keyOpeners.remove(s);
        }
        meta.getPersistentDataContainer().set(NameSpacedKeys.KEY_OPENERS, PersistentDataType.STRING, serializeOpeners(keyOpeners));

        key.setItemMeta(meta);
    }

    public boolean isOwner(Player player, String location) {
        return lockedOpeners.containsKey(location) && lockedOpeners.get(location).getOwner() != null &&
                lockedOpeners.get(location).getOwner().equals(player.getName());
    }


    public boolean isOpenable(Block block) {
        switch (block.getType()) {
            case CHEST:
            case ENDER_CHEST:
            case DARK_OAK_DOOR:
            case ACACIA_DOOR:
            case BIRCH_DOOR:
            case CRIMSON_DOOR:
            case JUNGLE_DOOR:
            case OAK_DOOR:
            case SPRUCE_DOOR:
            case WARPED_DOOR:
            case ACACIA_TRAPDOOR:
            case TRAPPED_CHEST:
            case BIRCH_TRAPDOOR:
            case CRIMSON_TRAPDOOR:
            case DARK_OAK_TRAPDOOR:
            case JUNGLE_TRAPDOOR:
            case OAK_TRAPDOOR:
            case SPRUCE_TRAPDOOR:
            case WARPED_TRAPDOOR:
            case ACACIA_FENCE_GATE:
            case BIRCH_FENCE_GATE:
            case CRIMSON_FENCE_GATE:
            case DARK_OAK_FENCE_GATE:
            case JUNGLE_FENCE_GATE:
            case OAK_FENCE_GATE:
            case SPRUCE_FENCE_GATE:
            case WARPED_FENCE_GATE:
                return true;
            default:
                return false;
        }
    }

    public boolean hasBlock(String loc) {
        return lockedOpeners.containsKey(loc);
    }

    public void removeBlock(String blockLoc) {
        lockedOpeners.remove(blockLoc);
    }

    public void addOwner(String owner, String loc) {
        lockedOpeners.put(loc, new OpenerInfo("", owner));
    }
}