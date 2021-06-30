package me.pioula111.craftingandstats.items.commands.toolsCommands;

import me.pioula111.craftingandstats.MenuHelper;
import me.pioula111.craftingandstats.items.ItemManager;
import me.pioula111.craftingandstats.items.commands.CommandsHelper;
import me.pioula111.craftingandstats.items.myItems.MyItem;
import me.pioula111.craftingandstats.items.properites.tools.*;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandTool implements CommandExecutor {
    private ItemManager itemManager;

    public CommandTool(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = CommandsHelper.conditions(sender, args.length, 0, itemManager);

        if (player == null)
            return true;

        player.sendMessage(createMenu());

        return true;
    }

    private TextComponent createMenu() {
        TextComponent menu = MenuHelper.createMenu("Wybierz Narzędzie");
        int enumerator = 0;
        menu = menu.append(new Pickaxe().menuComponent(++enumerator));
        menu = menu.append(new Scythe().menuComponent(++enumerator));
        menu = menu.append(new Sickle().menuComponent(++enumerator));
        menu = menu.append(new Axe().menuComponent(++enumerator));
        menu = menu.append(new FishingRod().menuComponent(++enumerator));

        return menu;
    }
}
