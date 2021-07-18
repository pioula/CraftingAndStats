package me.pioula111.craftingandstats.shoping;

import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.shoping.json.ShopManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class CommandAddGood implements CommandExecutor {
    private ShopManager shopManager;

    public CommandAddGood(ShopManager shopManager) {
        this.shopManager = shopManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;

        if (args.length != 3) {
            sender.sendMessage(ChatColor.RED + "Użyj /dodajtowar <liczba_złotych_monet> <liczba_srebrnych_monet> <liczba_miedzianych_monet>");
            return true;
        }

        int gold, silver, bronze;
        try {
            gold = Integer.parseInt(args[0]);
        } catch (Exception ex) {
            sender.sendMessage(ChatColor.RED + "Zły format liczby złotych monet!");
            return true;
        }

        try {
            silver = Integer.parseInt(args[1]);
        } catch (Exception ex) {
            sender.sendMessage(ChatColor.RED + "Zły format liczby srebrnych monet!");
            return true;
        }

        try {
            bronze = Integer.parseInt(args[2]);
        } catch (Exception ex) {
            sender.sendMessage(ChatColor.RED + "Zły format liczby miedzianych monet!");
            return true;
        }

        if ((gold < 0 || gold > 64) || (silver < 0 || silver > 64) || (bronze < 0 || bronze > 64)) {
            sender.sendMessage(ChatColor.RED + "Monety muszą być z przedziału [0, 64]");
            return true;
        }

        if (gold == 0 && silver == 0 && bronze == 0) {
            sender.sendMessage(ChatColor.RED + "Nie możesz sprzedawać czegoś za darmo!");
            return true;
        }

        Player player = (Player) sender;

        if (!shopManager.playerHasShop(player)) {
            sender.sendMessage(ChatColor.RED + "Nie masz sklepu!");
            return true;
        }

        if (!(player.getInventory().getItemInMainHand().getAmount() > 0 && player.getInventory().getItemInMainHand().hasItemMeta() &&
                player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(NameSpacedKeys.KEY_AUTHOR, PersistentDataType.STRING))) {
            sender.sendMessage(ChatColor.RED + "Musisz trzymać przedmiot, który chcesz sprzedać w swojej dłoni!");
            return true;
        }

        shopManager.addGood(player, player.getInventory().getItemInMainHand(), new Price(gold, silver, bronze));
        player.sendMessage(ChatColor.GREEN + "Pomyślnie dodano towar!");
        return true;
    }
}
