package me.pioula111.craftingandstats.itemy.komendy.komendyNarzedzia;

import me.pioula111.craftingandstats.itemy.ItemManager;
import me.pioula111.craftingandstats.itemy.MyItem;
import me.pioula111.craftingandstats.itemy.narzedzia.Kilof;
import me.pioula111.craftingandstats.itemy.narzedzia.Kosa;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandKosa implements CommandExecutor {
    private ItemManager itemManager;

    public CommandKosa(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;

        if (args.length != 0) {
            sender.sendMessage(ChatColor.RED + "Użyj /stworzitem żeby stworzyć nowy item!");
            return true;
        }

        Player player = (Player) sender;

        if (!itemManager.isMakingItem(player)) {
            sender.sendMessage(ChatColor.RED + "Użyj /stworzitem żeby stworzyć nowy item!");
            return true;
        }

        MyItem item = itemManager.getItem(player);
        itemManager.removeMaker(player);
        item.setTypNarzedzia(new Kosa());

        player.getInventory().addItem(item.makeItem(1));
        player.sendMessage(ChatColor.GREEN + "Stworzono kosę!");
        return true;
    }
}
