package me.pioula111.craftingandstats.itemy.komendy.komendyInne;

import me.pioula111.craftingandstats.MenuColors;
import me.pioula111.craftingandstats.itemy.ItemManager;
import me.pioula111.craftingandstats.itemy.MyItem;
import me.pioula111.craftingandstats.itemy.bronie.Dlugodystansowa;
import me.pioula111.craftingandstats.itemy.rodzaje.*;
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

public class CommandPrzerwijRobienieItemu implements CommandExecutor {
    private ItemManager itemManager;

    public CommandPrzerwijRobienieItemu(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;

        if (args.length != 0) {
            sender.sendMessage(ChatColor.RED + "Niepoprawna komenda! użyj /przerwijrobienieitemu");
            return true;
        }

        Player player = (Player) sender;

        if (!itemManager.isMakingItem(player)) {
            sender.sendMessage(ChatColor.RED + "Nie robisz aktualnie żadnego itemu!");
            return true;
        }

        itemManager.removeMaker(player);
        player.sendMessage(Component.text().content("Pomyślnie przerwano robienie itemu!").style(Style.style(MenuColors.MAIN_NAME)));
        return true;
    }
}
