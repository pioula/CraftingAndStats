package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CommandWytworzPrzedmiot implements CommandExecutor {
    public static final String PASSWORD = "s=D3A{8E>GV~L}k3";
    private CraftingManager craftingManager;

    public CommandWytworzPrzedmiot(CraftingManager craftingManager) {
        this.craftingManager = craftingManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //x y z nazwa_craftingu nazwa_receptury s=D3A{8E>GV~L}k3
        if (!(sender instanceof Player))
            return false;

        Player player = (Player) sender;

        if (args.length != 6) {
            player.sendMessage(ChatColor.RED + "Nie masz uprawnień by użyć tej komendy!");
            return true;
        }

        if (!args[5].equals(PASSWORD)) {
            player.sendMessage(ChatColor.RED + "Nie masz uprawnień by użyć tej komendy!");
            return true;
        }

        Location craftingLocation = new Location(player.getWorld(),Double.parseDouble(args[0]),Double.parseDouble(args[1]),Double.parseDouble(args[2]));
        if (craftingLocation.distance(player.getLocation()) > 3) {
            player.sendMessage(ChatColor.RED + "Jesteś za daleko od miejsca wytwarzania przedmiotu!");
            return true;
        }

        WorkBench crafting = craftingManager.getCrafting(args[3]);
        Recipe recipe = crafting.getRecipe(args[4]);
        for (Material material : recipe.getMaterials()) {
            ItemStack itemStack = material.getMaterial();
            int numberOfItems = itemStack.getAmount();
            for (ItemStack content : player.getInventory().getContents()) {
                if (content != null && content.asOne().equals(itemStack.asOne())) {
                    numberOfItems -= content.getAmount();
                }
                if (numberOfItems <= 0)
                    break;
            }

            if (numberOfItems > 0) {
                player.sendMessage(ChatColor.RED + "Nie masz wystarczającej liczby składników by wytworzyć ten przedmiot!");
                return true;
            }
        }

        for (Material material : recipe.getMaterials()) {
            ItemStack itemStack = material.getMaterial();
            int numberOfItems = itemStack.getAmount();
            for (ItemStack content : player.getInventory().getContents()) {
                if (content != null && content.asOne().equals(itemStack.asOne())) {
                    int removedItems = Math.min(numberOfItems, content.getAmount());
                    content.setAmount(content.getAmount() - removedItems);
                    if (content.getAmount() <= 0) {
                        player.getInventory().removeItem(content);
                    }
                    numberOfItems -= removedItems;
                }
                if (numberOfItems <= 0)
                    break;
            }
        }

        player.getInventory().addItem(recipe.getResult());
        player.sendMessage(ChatColor.GREEN + "Wytworzono przedmiot!");

        return true;
    }
}
