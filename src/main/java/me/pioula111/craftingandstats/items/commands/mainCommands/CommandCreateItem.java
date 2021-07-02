package me.pioula111.craftingandstats.items.commands.mainCommands;

import me.pioula111.craftingandstats.gui.MenuHelper;
import me.pioula111.craftingandstats.items.myItems.*;
import me.pioula111.craftingandstats.items.myItems.MyTool;
import me.pioula111.craftingandstats.items.ItemManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandCreateItem implements CommandExecutor {
    private ItemManager itemManager;
    private MyItem[] itemTypes;

    public CommandCreateItem(ItemManager itemManager) {
        this.itemManager = itemManager;
        itemTypes = new MyItem[] {new MyOthers(), new MyArmor(), new MyWeapon(), new MyDrink(),
                new MyFood(), new MyHandCraft(), new MyTool()};
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Zła Komenda! Wpisz /stworzitem <nazwa_itemu> <rodzaj_itemu>");
            return true;
        }


        Player player = (Player) sender;
        if (player.getInventory().getItemInMainHand().getAmount() != 1) {
            player.sendMessage(ChatColor.RED + "Musisz mieć dokładnie jeden item w głównej ręce, który chcesz podmienić!");
            return true;
        }

        MyItem item = deserialize(args[1]);

        if (item == null) {
            player.sendMessage(ChatColor.RED + "Nie ma takiego typu! Typy to:");
            for (MyItem itemType : itemTypes) {
                player.sendMessage(itemType.toString());
            }

            return true;
        }

        item.setSwappedItem(player.getInventory().getItemInMainHand().getType());
        item.setName(args[0].replace("_"," "));
        itemManager.updateMaker(player, item);

        player.sendMessage(nextStep(item));
        return true;
    }

    private Component nextStep(MyItem item) {
        ClickEvent clickEvent = ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/" + item.getType());

        return Component.text().content("Kliknij tu, aby przejść dalej!").style(Style.style(TextColor.color(MenuHelper.DECORATIONS))).clickEvent(clickEvent).build();
    }

    private MyItem deserialize(String arg) {
        if (itemTypes[0].toString().equals(arg))
            return new MyOthers();

        if (itemTypes[1].toString().equals(arg))
            return new MyArmor();

        if (itemTypes[2].toString().equals(arg))
            return new MyWeapon();

        if (itemTypes[3].toString().equals(arg))
            return new MyDrink();

        if (itemTypes[4].toString().equals(arg))
            return new MyFood();

        if (itemTypes[5].toString().equals(arg))
            return new MyHandCraft();

        if (itemTypes[6].toString().equals(arg))
            return new MyTool();

        return null;
    }
}
