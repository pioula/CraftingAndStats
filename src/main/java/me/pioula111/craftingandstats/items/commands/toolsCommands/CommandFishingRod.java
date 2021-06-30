package me.pioula111.craftingandstats.items.commands.toolsCommands;

import me.pioula111.craftingandstats.items.ItemManager;
import me.pioula111.craftingandstats.items.commands.CommandsHelper;
import me.pioula111.craftingandstats.items.myItems.MyItem;
import me.pioula111.craftingandstats.items.myItems.MyTool;
import me.pioula111.craftingandstats.items.properites.tools.FishingRod;
import me.pioula111.craftingandstats.items.properites.tools.Pickaxe;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandFishingRod implements CommandExecutor {
    private ItemManager itemManager;

    public CommandFishingRod(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = CommandsHelper.conditions(sender, args.length, 0, itemManager);

        if (player == null)
            return true;

        MyTool item = (MyTool) itemManager.getItem(player);
        itemManager.removeMaker(player);
        item.setToolType(new FishingRod());

        player.getInventory().addItem(item.makeItem(1));
        player.sendMessage(ChatColor.GREEN + "Stworzono wędkę!");
        return true;
    }
}
