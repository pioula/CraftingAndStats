package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;

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

        if (args.length != 3) {
            player.sendMessage(ChatColor.RED + "Zła komenda! Wpisz /nowareceptura <nazwa_fachu> <nazwa_craftingu> <nazwa_receptury>");
            return true;
        }

        Job job;
        if (!craftingManager.hasJob(args[0])) {
            player.sendMessage(ChatColor.RED + "Nie ma takiego fachu!");
            return true;
        }
        job = craftingManager.getJob(args[0]);

        WorkBench workBench;
        if (!craftingManager.hasCrafting(args[1])) {
            player.sendMessage(ChatColor.RED + "Nie ma takiego craftingu w tym fachu!");
            return true;
        }
        workBench = job.getWorkBench(args[1]);

        if (workBench.hasRecipe(args[2])) {
            player.sendMessage(ChatColor.RED + "Jest już taka receptura!");
            return true;
        }

        if (player.getInventory().getItemInOffHand() == null) {
            player.sendMessage(ChatColor.RED + "Nie masz przedmiotu wynikowego w drugiej dłoni!");
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



        HashSet<Material> materials = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            if (player.getInventory().getItem(i) != null) {
                materials.add(new Material(player.getInventory().getItem(i)));
            }
        }

        workBench.addRecipe(new Recipe(args[2], materials, player.getInventory().getItemInOffHand()));
        sender.sendMessage(ChatColor.GREEN + "Receptura została pomyślnie stworzona!");
        return true;
    }
}
