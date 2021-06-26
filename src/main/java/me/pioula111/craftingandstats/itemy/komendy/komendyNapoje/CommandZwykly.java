package me.pioula111.craftingandstats.itemy.komendy.komendyNapoje;

import me.pioula111.craftingandstats.itemy.ItemManager;
import me.pioula111.craftingandstats.itemy.MyItem;
import me.pioula111.craftingandstats.itemy.napoje.Lingering;
import me.pioula111.craftingandstats.itemy.napoje.Zwykly;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandZwykly implements CommandExecutor {
    private ItemManager itemManager;
    private final static TextColor nazwaK = TextColor.color(0x8088FF);

    public CommandZwykly(ItemManager itemManager) {
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
        item.setTypNapoju(new Zwykly());

        itemManager.updateMaker(player, item);

        player.sendMessage(Component.text().content("Podaj pierwszy efekt napoju /efekty <nazwa_efektu> <czas_efektu_w_sekundach> <poziom_efektu_liczone_od_1>").style(Style.style(nazwaK)));
        return true;
    }
}
