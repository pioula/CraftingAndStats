package me.pioula111.craftingandstats.lockedChests;

import me.pioula111.craftingandstats.ItemHelper;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CommandDuplicateKey implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;

        if (args.length != 0) {
            sender.sendMessage(ChatColor.RED + "Użyj /dorobklucze");
            return true;
        }

        Player player = (Player) sender;

        if (!(KeyManager.isKey(player.getInventory().getItemInMainHand()) && KeyManager.isKey(player.getInventory().getItemInOffHand()))) {
            player.sendMessage(ChatColor.RED + "W lewej ręce musisz trzymać klucz który chcesz nadpisać, a w prawej ten który chcesz dorobić!");
            return true;
        }

        ItemStack keyToReplace = player.getInventory().getItemInOffHand();
        ItemStack properKey = player.getInventory().getItemInMainHand();
        player.getInventory().setItemInOffHand(null);
        ItemStack newKeyes = properKey.clone();
        newKeyes.setAmount(keyToReplace.getAmount());
        ItemHelper.dropItem(player, newKeyes);

        player.sendMessage(ChatColor.GREEN + "Pomyślnie dorobiono klucze!");
        return true;
    }

}
