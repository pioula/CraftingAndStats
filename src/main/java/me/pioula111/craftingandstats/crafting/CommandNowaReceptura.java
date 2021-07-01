package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import me.pioula111.craftingandstats.items.myItems.MyItem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CommandNowaReceptura implements CommandExecutor {
    private CraftingManager craftingManager;

    public CommandNowaReceptura(CraftingManager craftingManager) {
        this.craftingManager = craftingManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Nie jesteś graczem!");
            return true;
        }
        Player player = (Player) sender;

        if (args.length != 2) {
            player.sendMessage(ChatColor.RED + "Zła komenda! Wpisz /nowareceptura <nazwa_craftingu> <nazwa_receptury>");
            return true;
        }

        WorkBench workBench;
        if (!craftingManager.hasCrafting(args[0])) {
            player.sendMessage(ChatColor.RED + "Nie ma takiego craftingu!");
            return true;
        }
        workBench = craftingManager.getCrafting(args[0]);

        if (workBench.hasRecipe(args[1])) {
            player.sendMessage(ChatColor.RED + "Jest już taka receptura!");
            return true;
        }

        if (player.getInventory().getItemInOffHand().getAmount() == 0) {
            player.sendMessage(ChatColor.RED + "Nie masz przedmiotu wynikowego w drugiej dłoni!");
            return true;
        }

        if (!player.getInventory().getItemInOffHand().getItemMeta().getPersistentDataContainer().has(NameSpacedKeys.KEY_AUTHOR, PersistentDataType.STRING)) {
            player.sendMessage(ChatColor.RED + "Przedmiot wynikowy musi być stworzony w przy pomocy komendy /stworzprzedmiot <nazwa_przedmiotu>!");
            return true;
        }

        boolean isEmpty = true;
        for (int i = 0; i < 9; i++) {
            if (player.getInventory().getItem(i) != null) {
                isEmpty = false;
                break;
            }
        }

        if (isEmpty) {
            player.sendMessage(ChatColor.RED + "Masz pusty ekwipunek!");
            return true;
        }

        for (int i = 0; i < 9; i++) {
            if (player.getInventory().getItem(i) != null &&
                    (!player.getInventory().getItem(i).hasItemMeta() ||
                    !player.getInventory().getItem(i).getItemMeta().getPersistentDataContainer().has(NameSpacedKeys.KEY_AUTHOR, PersistentDataType.STRING))) {
                player.sendMessage(ChatColor.RED + "Materiały muszą byś stworzone przy pomocy /stworzprzedmiot <nazwa_przedmiotu>!");
                return true;
            }
        }


        ArrayList<Material> materials = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (player.getInventory().getItem(i) != null) {
                materials.add(new Material(new MyItem(player.getInventory().getItem(i)), player.getInventory().getItem(i).getAmount()));
            }
        }

        workBench.addRecipe(new Recipe(args[1], materials, new Material(new MyItem(player.getInventory().getItemInOffHand()), player.getInventory().getItemInOffHand().getAmount())));
        sender.sendMessage(ChatColor.GREEN + "Receptura została pomyślnie stworzona!");
        return true;
    }
}
