package me.pioula111.craftingandstats.items.commands.weaponsCommands;

import me.pioula111.craftingandstats.MenuHelper;
import me.pioula111.craftingandstats.items.ItemManager;
import me.pioula111.craftingandstats.items.commands.CommandsHelper;
import me.pioula111.craftingandstats.items.myItems.MyItem;
import me.pioula111.craftingandstats.items.properites.weapons.LongDistance;
import me.pioula111.craftingandstats.items.properites.weapons.TwoHanded;
import me.pioula111.craftingandstats.items.properites.weapons.OneHanded;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandWeapon implements CommandExecutor {
    private ItemManager itemManager;

    public CommandWeapon(ItemManager itemManager) {
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
        TextComponent menu = MenuHelper.createMenu("Wybierz Typ Broni");
        int enumerator = 0;
        menu = menu.append(new OneHanded().menuComponent(++enumerator));
        menu = menu.append(new TwoHanded().menuComponent(++enumerator));
        menu = menu.append(new LongDistance().menuComponent(++enumerator));

        return menu;
    }
}
