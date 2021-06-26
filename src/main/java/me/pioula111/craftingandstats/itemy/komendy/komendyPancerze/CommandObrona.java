package me.pioula111.craftingandstats.itemy.komendy.komendyPancerze;

import me.pioula111.craftingandstats.itemy.ItemManager;
import me.pioula111.craftingandstats.itemy.MyItem;
import me.pioula111.craftingandstats.itemy.rodzaje.Inne;
import me.pioula111.craftingandstats.itemy.statystyki.Sila;
import me.pioula111.craftingandstats.itemy.statystyki.Zrecznosc;
import me.pioula111.craftingandstats.itemy.ulepszenia.Brak;
import me.pioula111.craftingandstats.itemy.ulepszenia.Wzmocnienie;
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

public class CommandObrona implements CommandExecutor {
    private ItemManager itemManager;
    private final static TextColor ozdobyK = TextColor.color(0x2C3394);
    private final static TextColor nazwaK = TextColor.color(0x8088FF);
    private final static TextColor rodzajK = TextColor.color(0x947B1E);
    private final static TextColor LPMK = TextColor.color(0xDECA1B);

    public CommandObrona(ItemManager itemManager) {
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

        int obrona = 0;
        try {
            obrona = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException ex) {
            sender.sendMessage(ChatColor.RED + "Zły format liczby! Spróbuj wpisać /obrona <wielkość_obrony> np. /obrona 10");
        }
        MyItem item = itemManager.getItem(player);
        item.setObrona(obrona);

        if (obrona == 0) {
            itemManager.removeMaker(player);
            player.getInventory().addItem(item.makeItem());
            player.sendMessage(ChatColor.GREEN + "Stworzono item!");
            return true;
        }

        itemManager.updateMaker(player, item);
        player.sendMessage(createMenu());

        return true;
    }

    private TextComponent createMenu() {
        TextComponent menu = Component.text().content("ᚾᛁᚷᚺᛏ ").style(Style.style(ozdobyK))
                .append(Component.text().content("Wybierz Ulepszenie").style(Style.style(nazwaK, TextDecoration.BOLD)))
                .append(Component.text().content(" ᚾᛁᚷᚺᛏ\n").style(Style.style(ozdobyK))).build();
        int enumerator = 0;
        menu = menu.append(new Brak().menuComponent(++enumerator));
        menu = menu.append(new Wzmocnienie().menuComponent(++enumerator));

        return menu;
    }
}
