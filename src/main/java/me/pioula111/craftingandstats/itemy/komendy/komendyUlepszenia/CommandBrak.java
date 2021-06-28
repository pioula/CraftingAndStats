package me.pioula111.craftingandstats.itemy.komendy.komendyUlepszenia;

import me.pioula111.craftingandstats.itemy.ItemManager;
import me.pioula111.craftingandstats.itemy.MyItem;
import me.pioula111.craftingandstats.itemy.bronie.Dlugodystansowa;
import me.pioula111.craftingandstats.itemy.bronie.Dwureczna;
import me.pioula111.craftingandstats.itemy.bronie.Jednoreczna;
import me.pioula111.craftingandstats.itemy.rodzaje.Bron;
import me.pioula111.craftingandstats.itemy.ulepszenia.Brak;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandBrak implements CommandExecutor {
    private ItemManager itemManager;

    public CommandBrak(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;

        if (args.length != 0) {
            sender.sendMessage(ChatColor.RED + "Użyj /stworzitem żeby stworzyć nowy item!");
            return true;
        }

        Player player = (Player) sender;

        if (!itemManager.isMakingItem(player)) {
            sender.sendMessage(ChatColor.RED + "Użyj /stworzitem żeby stworzyć nowy item!");
            return true;
        }

        MyItem item = itemManager.getItem(player);
        item.setUlepszenie(new Brak());

        itemManager.removeMaker(player);
        player.getInventory().addItem(item.makeItem());
        player.sendMessage(ChatColor.GREEN + "Pomyślnie stworzono przedmiot!");

        return true;
    }
}
