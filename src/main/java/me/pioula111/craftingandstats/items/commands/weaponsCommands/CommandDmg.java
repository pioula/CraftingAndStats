package me.pioula111.craftingandstats.items.commands.weaponsCommands;

import me.pioula111.craftingandstats.gui.MenuHelper;
import me.pioula111.craftingandstats.items.ItemManager;
import me.pioula111.craftingandstats.items.commands.CommandsHelper;
import me.pioula111.craftingandstats.items.myItems.MyWeapon;
import me.pioula111.craftingandstats.items.properites.statistics.Strength;
import me.pioula111.craftingandstats.items.properites.statistics.Dexterity;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandDmg implements CommandExecutor {
    private ItemManager itemManager;

    public CommandDmg(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = CommandsHelper.conditions(sender, args.length, 1, itemManager);

        if (player == null)
            return true;

        MyWeapon item = (MyWeapon) itemManager.getItem(player);
        try {
            item.setDmg(Double.parseDouble(args[0]));
        }
        catch(NumberFormatException ex) {
            sender.sendMessage(ChatColor.RED + "Zły format dmg! Spróbuj wpisać jeszcze raz /dmg <obrażenia> np. /dmg 5.3");
            return true;
        }

        itemManager.updateMaker(player, item);
        player.sendMessage(createMenu());

        return true;
    }

    private TextComponent createMenu() {
        TextComponent menu = MenuHelper.createMenu("Wybierz Wymaganą Statystykę");
        int enumerator = 0;
        menu = menu.append(new Strength().menuComponent(++enumerator));
        menu = menu.append(new Dexterity().menuComponent(++enumerator));

        return menu;
    }
}
