package me.pioula111.craftingandstats.items.commands.drinkCommands;

import me.pioula111.craftingandstats.gui.MenuHelper;
import me.pioula111.craftingandstats.items.ItemManager;
import me.pioula111.craftingandstats.items.commands.CommandsHelper;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandDrink implements CommandExecutor {
    private ItemManager itemManager;

    public CommandDrink(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = CommandsHelper.conditions(sender, args.length, 0, itemManager);

        if (player == null)
            return true;

        player.sendMessage(Component.text().content("Podaj pierwszy efekt napoju /efekty <nazwa_efektu> <czas_efektu_w_sekundach> <poziom_efektu_liczone_od_1>").style(Style.style(MenuHelper.MAIN_NAME)));

        return true;
    }
}
