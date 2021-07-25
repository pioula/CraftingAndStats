package me.pioula111.craftingandstats.lockedChests;

import me.pioula111.craftingandstats.ItemHelper;
import me.pioula111.craftingandstats.NameSpacedKeys;
import me.pioula111.craftingandstats.gui.ComponentWrapper;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandNameKey implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Użyj /nazwijklucz <nazwa_klucza>");
            return true;
        }

        Player player = (Player) sender;
        if (!KeyManager.isKey(player.getInventory().getItemInMainHand())) {
            sender.sendMessage(ChatColor.RED + "Musisz trzymać w ręku klucz by użyć tej komendy!");
            return true;
        }
        ItemStack item = player.getInventory().getItemInMainHand();
        player.getInventory().removeItem(item);

        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(NameSpacedKeys.KEY_KEY_NAME, PersistentDataType.STRING, args[0].replace("_"," "));

        meta.displayName(ComponentWrapper.itemName("Klucz: " + args[0].replace("_"," ")));
        item.setItemMeta(meta);
        ItemHelper.dropItem(player, item);
        return true;
    }
}
