package me.pioula111.craftingandstats.items.commands.drinkCommands;

import me.pioula111.craftingandstats.items.ItemManager;
import me.pioula111.craftingandstats.items.commands.CommandsHelper;
import me.pioula111.craftingandstats.items.myItems.MyDrink;
import me.pioula111.craftingandstats.items.myItems.MyItem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandDrinkColor implements CommandExecutor {
    private ItemManager itemManager;

    public CommandDrinkColor(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = CommandsHelper.conditions(sender, args.length, 1, itemManager);

        if (player == null)
            return false;

        MyDrink item = (MyDrink) itemManager.getItem(player);
        try {
            item.setDrinkColor(Integer.decode(args[0]));
        }
        catch(NumberFormatException ex) {
            sender.sendMessage(ChatColor.RED + "Zły format koloru! Spróbuj wpisać jeszcze raz /kolornapoju <kolor_napoju> np. /kolornapoju 0xFFFFFF");
            return true;
        }

        itemManager.removeMaker(player);
        player.getInventory().addItem(item.makeItem(1));

        player.sendMessage(ChatColor.GREEN + "Stworzono napój!");
        return true;
    }
}
