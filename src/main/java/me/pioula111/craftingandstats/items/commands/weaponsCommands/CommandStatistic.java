package me.pioula111.craftingandstats.items.commands.weaponsCommands;

import me.pioula111.craftingandstats.MenuHelper;
import me.pioula111.craftingandstats.items.ItemManager;
import me.pioula111.craftingandstats.items.commands.CommandsHelper;
import me.pioula111.craftingandstats.items.myItems.MyWeapon;
import me.pioula111.craftingandstats.items.properites.additions.None;
import me.pioula111.craftingandstats.items.properites.additions.Upgrade;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandStatistic implements CommandExecutor {
    private ItemManager itemManager;

    public CommandStatistic(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = CommandsHelper.conditions(sender, args.length, 1, itemManager);

        if (player == null)
            return true;

        MyWeapon item = (MyWeapon) itemManager.getItem(player);
        try {
            item.setStatisticLevel(Integer.parseInt(args[0]));
        }
        catch(NumberFormatException ex) {
            sender.sendMessage(ChatColor.RED + "Zły format liczby! Spróbuj wpisać jeszcze raz /statystyka <wielkość_statystyki> np. /statystyka 10");
            return true;
        }

        itemManager.updateMaker(player, item);
        player.sendMessage(createMenu());

        return true;
    }

    private TextComponent createMenu() {
        TextComponent menu = MenuHelper.createMenu("Wybierz Ulepszenie");
        int enumerator = 0;
        menu = menu.append(new None().menuComponent(++enumerator));
        menu = menu.append(new Upgrade().menuComponent(++enumerator));

        return menu;
    }
}
