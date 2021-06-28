package me.pioula111.craftingandstats.itemy.komendy.komendyInne;

import me.pioula111.craftingandstats.MenuColors;
import me.pioula111.craftingandstats.itemy.rodzaje.*;
import me.pioula111.craftingandstats.itemy.ItemManager;
import me.pioula111.craftingandstats.itemy.MyItem;
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

public class CommandStworzItem implements CommandExecutor {
    private ItemManager itemManager;

    public CommandStworzItem(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Zła Komenda! Wpisz /stworzitem <nazwa_itemu>");
            return true;
        }

        Player player = (Player) sender;
        if (player.getInventory().getItemInMainHand().getAmount() != 1) {
            player.sendMessage(ChatColor.RED + "Musisz mieć dokładnie jeden item w głównej ręce, który chcesz podmienić!");
            return true;
        }

        MyItem item = new MyItem();
        item.setPodmienionyItem(player.getInventory().getItemInMainHand().getType());
        item.setNazwa(args[0]);
        itemManager.updateMaker(player, item);

        player.sendMessage(createMenu());
        return true;
    }

    private TextComponent createMenu() {
        TextComponent menu = Component.text().content("ᚾᛁᚷᚺᛏ ").style(Style.style(MenuColors.DECORATIONS))
                .append(Component.text().content("Wybierz Rodzaj").style(Style.style(MenuColors.MAIN_NAME, TextDecoration.BOLD)))
                .append(Component.text().content(" ᚾᛁᚷᚺᛏ\n").style(Style.style(MenuColors.DECORATIONS))).build();
        int enumerator = 0;
        menu = menu.append(new Rzemieslniczy().menuComponent(++enumerator));
        menu = menu.append(new Zywnosc().menuComponent(++enumerator));
        menu = menu.append(new Inne().menuComponent(++enumerator));
        menu = menu.append(new Bron().menuComponent(++enumerator));
        menu = menu.append(new Pancerz().menuComponent(++enumerator));
        menu = menu.append(new Narzedzia().menuComponent(++enumerator));
        menu = menu.append(new Napoj().menuComponent(++enumerator));

        return menu;
    }
}
