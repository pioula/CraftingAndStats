package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.stats.json.StatManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class CommandAcceptJob implements CommandExecutor {
    private StatManager statManager;
    private TeachJobManager manager;

    public CommandAcceptJob(StatManager statManager, TeachJobManager manager) {
        this.statManager = statManager;
        this.manager = manager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;

        Player player = (Player) sender;

        if (args.length != 0) {
            player.sendMessage(ChatColor.RED + "Zła komenda! spróbuj /akceptujnauke");
            return true;
        }

        Player teacher = manager.pullTeacher(player);
        if (teacher == null) {
            player.sendMessage(ChatColor.RED + "Nikt nie chce ciebie niczego nauczyć w tym momencie!");
            return true;
        }

        if (!teacher.isOnline()) {
            player.sendMessage(ChatColor.RED + "Twój nauczyciel wyszedł z gry!");
            return true;
        }

        int numberOfCoins = 0;
        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (isGoldCoind(itemStack))
                numberOfCoins += itemStack.getAmount();
        }

        if (numberOfCoins < 2) {
            player.sendMessage(ChatColor.RED + "Nie masz wystarczającej liczby monet. Musisz mieć dwie złote monety przy sobie!");
            return true;
        }

        numberOfCoins = 2;

        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (isGoldCoind(itemStack)) {
                int removedItems = Math.min(numberOfCoins, itemStack.getAmount());
                itemStack.setAmount(itemStack.getAmount() - removedItems);
                if (itemStack.getAmount() <= 0) {
                    player.getInventory().removeItem(itemStack);
                }
                numberOfCoins -= removedItems;
            }
            if (numberOfCoins <= 0)
                break;
        }

        statManager.getPlayerStats(player).setJob(statManager.getPlayerStats(teacher).getJob());
        player.sendMessage(ChatColor.GREEN + "Zostałeś nauczony!");
        teacher.sendMessage(ChatColor.GREEN + "Nauka przebiegła pomyślnie!");
        return true;
    }

    private boolean isGoldCoind(ItemStack itemStack) {
        return itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().getPersistentDataContainer().has(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING) &&
                itemStack.getItemMeta().getPersistentDataContainer().get(NameSpacedKeys.KEY_NAME, PersistentDataType.STRING).equals("Złota Moneta");
    }
}
