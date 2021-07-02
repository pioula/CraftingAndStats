package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandRemoveRecipe implements CommandExecutor {
    private CraftingManager craftingManager;

    public CommandRemoveRecipe(CraftingManager craftingManager) {
        this.craftingManager = craftingManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;

        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Użyj komendy /usunrecepture <nazwa_craftingu> <nazwa_receptury>");
            return true;
        }

        String craftingName = args[0].replace("_"," ");
        String recipeName = args[1].replace("_"," ");
        if (!craftingManager.hasCrafting(craftingName)) {
            sender.sendMessage(ChatColor.RED + "Nie ma takiego craftingu! Wpisz /fachy, a następnie wybierz fach, który cię interesuje by zobaczyć jakie ma craftingi!");
            return true;
        }

        Crafting crafting = craftingManager.getCrafting(craftingName);

        if (!crafting.hasRecipe(recipeName)) {
            sender.sendMessage(ChatColor.RED + "Nie ma takiej receptury w tym craftingu!");
            return true;
        }

        crafting.removeRecipe(recipeName);
        sender.sendMessage(ChatColor.GREEN + "Receptura została pomyślnie usunięta!");
        return true;
    }
}
