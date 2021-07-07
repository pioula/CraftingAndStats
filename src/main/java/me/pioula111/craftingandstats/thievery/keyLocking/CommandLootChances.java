package me.pioula111.craftingandstats.thievery.keyLocking;

import me.pioula111.craftingandstats.thievery.keyLocking.json.LootManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CommandLootChances implements CommandExecutor {
    private LootManager lootManager;

    public CommandLootChances(LootManager lootManager) {
        this.lootManager = lootManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;

        if (args.length == 0) {
            sender.sendMessage(lootManager.showChances());
            return true;
        }

        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Zła komenda! Użyj /lootszanse <id> <szanse_w_procentach>");
            return true;
        }

        ArrayList<LootItem> items = lootManager.getItems();
        int id;
        double chance;
        try {
            id = Integer.parseInt(args[0]);
            chance = Double.parseDouble(args[1]);
        } catch(Exception ex) {
            sender.sendMessage(ChatColor.RED + "Zły format id lub procentów poprawna to: /lootszanse 1 23.5");
            return true;
        }

        id--;
        if (id < 0 || id >= items.size()) {
            sender.sendMessage(ChatColor.RED + "Niepoprawne id, wpisz /lootszanse, by zobaczyć id");
            return true;
        }

        if (chance < 0 || chance > 100) {
            sender.sendMessage(ChatColor.RED + "Szanse muszą należeć do przedziału [0; 100]");
            return true;
        }

        LootItem item = items.get(id);
        item.setChance(chance);
        items.set(id, item);
        lootManager.setItems(items);
        sender.sendMessage(ChatColor.GREEN + "Pomyślnie zmodyfikowano loot!");
        return true;
    }
}
