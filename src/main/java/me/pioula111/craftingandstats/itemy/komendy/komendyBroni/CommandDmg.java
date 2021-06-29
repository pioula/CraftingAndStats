package me.pioula111.craftingandstats.itemy.komendy.komendyBroni;

import me.pioula111.craftingandstats.MenuColors;
import me.pioula111.craftingandstats.itemy.ItemManager;
import me.pioula111.craftingandstats.itemy.MyItem;
import me.pioula111.craftingandstats.itemy.wlasciwosci.statystyki.Sila;
import me.pioula111.craftingandstats.itemy.wlasciwosci.statystyki.Zrecznosc;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandDmg implements CommandExecutor {
    private ItemManager itemManager;

    public CommandDmg(ItemManager itemManager) {
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
            item.setDmg(Double.parseDouble(args[0]));
        }
        catch(NumberFormatException ex) {
            sender.sendMessage(ChatColor.RED + "Zły format dmg! Spróbuj wpisać jeszcze raz /dmg <obrażenia> np. /dmg 5.3");
            return true;
        }

        itemManager.updateMaker(player, item);
        player.sendMessage(createMenu());

        return true;
    }

    private TextComponent createMenu() {
        TextComponent menu = MenuColors.createMenu("Wybierz Wymaganą Statystykę");
        int enumerator = 0;
        menu = menu.append(new Sila().menuComponent(++enumerator));
        menu = menu.append(new Zrecznosc().menuComponent(++enumerator));

        return menu;
    }
}
