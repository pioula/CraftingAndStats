package me.pioula111.craftingandstats.itemy.komendy.komendyBroni;

import me.pioula111.craftingandstats.MenuColors;
import me.pioula111.craftingandstats.itemy.*;
import me.pioula111.craftingandstats.itemy.wlasciwosci.bronie.Dlugodystansowa;
import me.pioula111.craftingandstats.itemy.wlasciwosci.bronie.Dwureczna;
import me.pioula111.craftingandstats.itemy.wlasciwosci.bronie.Jednoreczna;
import me.pioula111.craftingandstats.itemy.wlasciwosci.rodzaje.Bron;
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

public class CommandBron implements CommandExecutor {
    private ItemManager itemManager;

    public CommandBron(ItemManager itemManager) {
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
        item.setRodzaj(new Bron());

        itemManager.updateMaker(player, item);
        player.sendMessage(createMenu());

        return true;
    }

    private TextComponent createMenu() {
        TextComponent menu = MenuColors.createMenu("Wybierz Typ Broni");
        int enumerator = 0;
        menu = menu.append(new Jednoreczna().menuComponent(++enumerator));
        menu = menu.append(new Dwureczna().menuComponent(++enumerator));
        menu = menu.append(new Dlugodystansowa().menuComponent(++enumerator));

        return menu;
    }
}
