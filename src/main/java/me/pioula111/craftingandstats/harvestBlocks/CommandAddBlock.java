package me.pioula111.craftingandstats.harvestBlocks;

import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.harvestBlocks.harvestTools.HTool;
import me.pioula111.craftingandstats.harvestBlocks.json.HarvestManager;
import me.pioula111.craftingandstats.items.myItems.MyItem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class CommandAddBlock implements CommandExecutor {
    private HarvestManager harvestManager;

    public CommandAddBlock(HarvestManager harvestManager) {
        this.harvestManager = harvestManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Zła komenda! Spróbuj /bloki <pickaxe/sickle/scythe/axe>");
            return true;
        }

        Player player = (Player) sender;

        HTool tool = harvestManager.getTool(args[0]);
        if (tool == null) {
            sender.sendMessage(ChatColor.RED + "Nie ma takiego narzędzia! Spróbuj /bloki <pickaxe/sickle/scythe/axe>");
            return true;
        }

        if (!tool.hasSpace()) {
            sender.sendMessage(ChatColor.RED + "To narzędzie ma za dużo bloków!");
            return true;
        }

        ItemStack block = player.getInventory().getItemInOffHand();
        if (block.getAmount() == 0 || !block.getType().isBlock()) {
            player.sendMessage(ChatColor.RED + "W lewej ręce musisz trzymać blok!");
            return true;
        }

        ItemStack drop = player.getInventory().getItemInMainHand();
        if (drop.getAmount() == 0 || !drop.getItemMeta().getPersistentDataContainer().has(NameSpacedKeys.KEY_AUTHOR, PersistentDataType.STRING)) {
            player.sendMessage(ChatColor.RED + "W prawej ręce musisz trzymać item stworzony przy pomocy /stworzitem");
            return true;
        }

        tool.addBlock(new HarvestBlock(block.getType(), MyItem.fromItemStack(drop)));

        player.sendMessage(ChatColor.GREEN + "Pomyślnie dodano blok!");
        return true;
    }
}
