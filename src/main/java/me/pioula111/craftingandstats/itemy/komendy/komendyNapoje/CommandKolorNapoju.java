package me.pioula111.craftingandstats.itemy.komendy.komendyNapoje;

import me.pioula111.craftingandstats.itemy.ItemManager;
import me.pioula111.craftingandstats.itemy.MyItem;
import me.pioula111.craftingandstats.itemy.statystyki.Sila;
import me.pioula111.craftingandstats.itemy.statystyki.Zrecznosc;
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

public class CommandKolorNapoju implements CommandExecutor {
    private ItemManager itemManager;

    public CommandKolorNapoju(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Użyj /stworzitem żeby stworzyć nowy item!");
            return true;
        }

        Player player = (Player) sender;

        if (!itemManager.isMakingItem(player)) {
            sender.sendMessage(ChatColor.RED + "Użyj /stworzitem żeby stworzyć nowy item!");
            return true;
        }

        MyItem item = itemManager.getItem(player);
        try {
            item.setKolorNapoju(Integer.decode(args[0]));
        }
        catch(NumberFormatException ex) {
            sender.sendMessage(ChatColor.RED + "Zły format koloru! Spróbuj wpisać jeszcze raz /kolornapoju <kolor_napoju> np. /kolornapoju 0xFFFFFF");
            return true;
        }

        itemManager.removeMaker(player);
        player.getInventory().addItem(item.makeItem());

        return true;
    }
}
