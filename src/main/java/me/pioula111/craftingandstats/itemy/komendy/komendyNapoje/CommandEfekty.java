package me.pioula111.craftingandstats.itemy.komendy.komendyNapoje;

import me.pioula111.craftingandstats.itemy.ItemManager;
import me.pioula111.craftingandstats.itemy.MyItem;
import me.pioula111.craftingandstats.itemy.napoje.Efekt;
import me.pioula111.craftingandstats.itemy.statystyki.Sila;
import me.pioula111.craftingandstats.itemy.statystyki.Zrecznosc;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffectTypeWrapper;
import org.jetbrains.annotations.NotNull;

public class CommandEfekty implements CommandExecutor {
    private ItemManager itemManager;
    private final static TextColor ozdobyK = TextColor.color(0x2C3394);
    private final static TextColor nazwaK = TextColor.color(0x8088FF);
    private final static TextColor rodzajK = TextColor.color(0x947B1E);
    private final static TextColor LPMK = TextColor.color(0xDECA1B);

    public CommandEfekty(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;

        if (args.length != 3) {
            sender.sendMessage(ChatColor.RED + "Zła komenda, spróbuj jeszcze raz :)");
            return true;
        }

        Player player = (Player) sender;

        if (!itemManager.isMakingItem(player)) {
            sender.sendMessage(ChatColor.RED + "Użyj /stworzitem żeby stworzyć nowy item!");
            return true;
        }

        MyItem item = itemManager.getItem(player);
        int czasTrwania = 0;
        int poziom = 0;
        try {
            czasTrwania = Integer.parseInt(args[1]);
        }
        catch(NumberFormatException ex) {
            sender.sendMessage(ChatColor.RED + "Zły format czasu trwania! Spróbuj wpisać jeszcze raz np. /efekty <nazwa_efektu> <czas_trwania_w_sekundach> <poziom_efektu_liczone_od_1>");
            return true;
        }

        try {
            poziom = Integer.parseInt(args[2]);
        }
        catch(NumberFormatException ex) {
            sender.sendMessage(ChatColor.RED + "Zły format poziomu! Spróbuj wpisać jeszcze raz np. /efekty <nazwa_efektu> <czas_trwania_w_sekundach> <poziom_efektu_liczone_od_1>");
            return true;
        }

        PotionEffectType typEfektu = PotionEffectTypeWrapper.getByName(args[0]);
        if (typEfektu == null) {
            sender.sendMessage(ChatColor.RED + "Zły format efektu! Spróbuj wpisać jeszcze raz np. /efekty <nazwa_efektu> <czas_trwania_w_sekundach> <poziom_efektu_liczone_od_1>");
            return true;
        }

        Efekt efekt = new Efekt(typEfektu, czasTrwania * 20, poziom);
        item.addEfekt(efekt);

        itemManager.updateMaker(player, item);
        player.sendMessage(ChatColor.GOLD + "Jeżeli chcesz skończyć dodawanie efektów wpisz /kolornapoju <kolor_w_systemie_szesnastkowym> np. /kolor 0xFFFFFF, jeżeli chcesz dalej dodawać efekty wpisz /efekty <nazwa_efektu> <czas_trwania_w_sekundach> <poziom_efektu_liczone_od_1>");

        return true;
    }
}