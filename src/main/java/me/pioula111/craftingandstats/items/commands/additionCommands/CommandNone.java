package me.pioula111.craftingandstats.items.commands.additionCommands;

import me.pioula111.craftingandstats.items.ItemManager;
import me.pioula111.craftingandstats.items.commands.CommandsHelper;
import me.pioula111.craftingandstats.items.myItems.MyArmor;
import me.pioula111.craftingandstats.items.myItems.MyItem;
import me.pioula111.craftingandstats.items.myItems.MyWeapon;
import me.pioula111.craftingandstats.items.properites.additions.None;
import me.pioula111.craftingandstats.items.properites.additions.Upgrade;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandNone implements CommandExecutor {
    private ItemManager itemManager;

    public CommandNone(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = CommandsHelper.conditions(sender, args.length, 0, itemManager);

        if (player == null)
            return true;

        MyItem item = itemManager.getItem(player);

        if (item instanceof MyWeapon)
            ((MyWeapon) item).setAddition(new None());
        else
            ((MyArmor) item).setAddition(new None());

        itemManager.removeMaker(player);
        player.getInventory().addItem(item.makeItem(1));
        player.sendMessage(ChatColor.GREEN + "Pomyślnie stworzono przedmiot!");

        return true;
    }
}
