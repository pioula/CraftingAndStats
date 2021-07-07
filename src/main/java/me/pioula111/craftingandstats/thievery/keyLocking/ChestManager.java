package me.pioula111.craftingandstats.thievery.keyLocking;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;

public class ChestManager {
    private long oneHour = 3600000;//60 min * 60s * 1000mils
    private HashMap<Location, Long> openedChests;
    private HashMap<Location, ArrayList<Boolean>> codes;

    public ChestManager() {
        this.openedChests = new HashMap<>();
        this.codes = new HashMap<>();
    }

    public boolean isOpened(Location loc) {
        if (openedChests.containsKey(loc)) {
            if (openedChests.get(loc) + oneHour > System.currentTimeMillis())
                return true;
            openedChests.remove(loc);
        }
        return false;
    }

    public void openChest(Location loc) {
        codes.remove(loc);
        openedChests.put(loc, System.currentTimeMillis());
    }

    public void setCode(Location loc, ArrayList<Boolean> code) {
        if (!codes.containsKey(loc))
            codes.put(loc, code);
    }

    public ArrayList<Boolean> getCode(Location loc) {
        return codes.get(loc);
    }
}
