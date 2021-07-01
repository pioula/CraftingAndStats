package me.pioula111.craftingandstats.items.commands.weaponsCommands;

import me.pioula111.craftingandstats.gui.MenuHelper;
import me.pioula111.craftingandstats.items.ItemManager;
import me.pioula111.craftingandstats.items.commands.CommandsHelper;
import me.pioula111.craftingandstats.items.myItems.MyWeapon;
import me.pioula111.craftingandstats.items.properites.weapons.LongDistance;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandLongDistance implements CommandExecutor {
    private ItemManager itemManager;

    public CommandLongDistance(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = CommandsHelper.conditions(sender, args.length, 0, itemManager);

        if (player == null)
            return true;

        MyWeapon item = (MyWeapon) itemManager.getItem(player);
        item.setWeaponType(new LongDistance());

        itemManager.updateMaker(player, item);

        player.sendMessage(Component.text().content("Podaj obrażenia broni /dmg <obrażenia>").style(Style.style(MenuHelper.MAIN_NAME)));
        return true;
    }
}
