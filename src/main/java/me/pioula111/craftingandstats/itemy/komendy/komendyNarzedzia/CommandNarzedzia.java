package me.pioula111.craftingandstats.itemy.komendy.komendyNarzedzia;

import me.pioula111.craftingandstats.MenuColors;
import me.pioula111.craftingandstats.itemy.ItemManager;
import me.pioula111.craftingandstats.itemy.MyItem;
import me.pioula111.craftingandstats.itemy.wlasciwosci.narzedzia.*;
import me.pioula111.craftingandstats.itemy.wlasciwosci.rodzaje.Narzedzia;
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

public class CommandNarzedzia implements CommandExecutor {
    private ItemManager itemManager;

    public CommandNarzedzia(ItemManager itemManager) {
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
        item.setRodzaj(new Narzedzia());

        itemManager.updateMaker(player, item);
        player.sendMessage(createMenu());

        return true;
    }

    private TextComponent createMenu() {
        TextComponent menu = MenuColors.createMenu("Wybierz Narzędzie");
        int enumerator = 0;
        menu = menu.append(new Kilof().menuComponent(++enumerator));
        menu = menu.append(new Kosa().menuComponent(++enumerator));
        menu = menu.append(new Sierp().menuComponent(++enumerator));
        menu = menu.append(new Topor().menuComponent(++enumerator));
        menu = menu.append(new Wedka().menuComponent(++enumerator));

        return menu;
    }
}
