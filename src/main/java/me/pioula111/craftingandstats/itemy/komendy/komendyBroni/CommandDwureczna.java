package me.pioula111.craftingandstats.itemy.komendy.komendyBroni;

import me.pioula111.craftingandstats.itemy.ItemManager;
import me.pioula111.craftingandstats.itemy.MyItem;
import me.pioula111.craftingandstats.itemy.bronie.Dwureczna;
import me.pioula111.craftingandstats.itemy.bronie.Jednoreczna;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandDwureczna implements CommandExecutor {
    private ItemManager itemManager;
    private final static TextColor nazwaK = TextColor.color(0x8088FF);

    public CommandDwureczna(ItemManager itemManager) {
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
        item.setTypBroni(new Dwureczna());

        itemManager.updateMaker(player, item);

        player.sendMessage(Component.text().content("Podaj obrażenia broni /dmg <obrażenia>").style(Style.style(nazwaK)));
        return true;
    }
}