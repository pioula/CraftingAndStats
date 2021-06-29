package me.pioula111.craftingandstats.itemy.komendy.komendyNapoje;

import me.pioula111.craftingandstats.MenuColors;
import me.pioula111.craftingandstats.itemy.ItemManager;
import me.pioula111.craftingandstats.itemy.MyItem;
import me.pioula111.craftingandstats.itemy.wlasciwosci.napoje.Lingering;
import me.pioula111.craftingandstats.itemy.wlasciwosci.napoje.Splash;
import me.pioula111.craftingandstats.itemy.wlasciwosci.napoje.Zwykly;
import me.pioula111.craftingandstats.itemy.wlasciwosci.rodzaje.Napoj;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandNapoj implements CommandExecutor {
    private ItemManager itemManager;

    public CommandNapoj(ItemManager itemManager) {
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
        item.setPodmienionyItem(Material.POTION);
        item.setRodzaj(new Napoj());

        itemManager.updateMaker(player, item);
        player.sendMessage(createMenu());

        return true;
    }

    private TextComponent createMenu() {
        TextComponent menu = MenuColors.createMenu("Wybierz Rodzaj Napoju");
        int enumerator = 0;
        menu = menu.append(new Lingering().menuComponent(++enumerator));
        menu = menu.append(new Splash().menuComponent(++enumerator));
        menu = menu.append(new Zwykly().menuComponent(++enumerator));

        return menu;
    }
}
