package me.pioula111.craftingandstats.items.commands.drinkCommands;

import me.pioula111.craftingandstats.items.ItemManager;
import me.pioula111.craftingandstats.items.commands.CommandsHelper;
import me.pioula111.craftingandstats.items.myItems.MyDrink;
import me.pioula111.craftingandstats.items.myItems.MyItem;
import me.pioula111.craftingandstats.items.properites.drinks.Effect;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffectTypeWrapper;
import org.jetbrains.annotations.NotNull;

public class CommandEffects implements CommandExecutor {
    private ItemManager itemManager;

    public CommandEffects(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = CommandsHelper.conditions(sender, args.length, 3, itemManager);

        if (player == null)
            return true;

        MyDrink item = (MyDrink) itemManager.getItem(player);
        int time;
        int level;
        try {
            time = Integer.parseInt(args[1]);
        }
        catch(NumberFormatException ex) {
            sender.sendMessage(ChatColor.RED + "Zły format czasu trwania! Spróbuj wpisać jeszcze raz np. /efekty <nazwa_efektu> <czas_trwania_w_sekundach> <poziom_efektu_liczone_od_1>");
            return true;
        }

        try {
            level = Integer.parseInt(args[2]);
        }
        catch(NumberFormatException ex) {
            sender.sendMessage(ChatColor.RED + "Zły format poziomu! Spróbuj wpisać jeszcze raz np. /efekty <nazwa_efektu> <czas_trwania_w_sekundach> <poziom_efektu_liczone_od_1>");
            return true;
        }

        PotionEffectType effectType = PotionEffectTypeWrapper.getByName(args[0]);
        if (effectType == null) {
            sender.sendMessage(ChatColor.RED + "Zły format efektu! Spróbuj wpisać jeszcze raz np. /efekty <nazwa_efektu> <czas_trwania_w_sekundach> <poziom_efektu_liczone_od_1>");
            return true;
        }

        Effect effect = new Effect(effectType, time * 20, level - 1);
        item.addEffect(effect);

        itemManager.updateMaker(player, item);
        player.sendMessage(ChatColor.GOLD + "Jeżeli chcesz skończyć dodawanie efektów wpisz /kolornapoju <kolor_w_systemie_szesnastkowym> np. /kolornapoju 0xFFFFFF, jeżeli chcesz dalej dodawać efekty wpisz /efekty <nazwa_efektu> <czas_trwania_w_sekundach> <poziom_efektu_liczone_od_1>");

        return true;
    }
}
