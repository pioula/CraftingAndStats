package me.pioula111.craftingandstats.stats;

import de.themoep.inventorygui.GuiElement;
import de.themoep.inventorygui.GuiStateElement;
import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import me.pioula111.craftingandstats.CraftingAndStats;
import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class CommandStaty implements CommandExecutor {
    private final String nameFlags = "&f&B&L";
    private final String valueNameFlags = "&f&A";
    private final String actualValueFlags = "&f&6&L";
    private final String titleFlags = "&L";
    private CraftingAndStats plugin;

    public CommandStaty(CraftingAndStats plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;
        Player player = (Player) sender;

        String[] guiSetup = {
                ".ż.j.m...",
                ".s.d.g...",
                ".z.ł....."
        };
        StaticGuiElement empty_field = new StaticGuiElement('.', new ItemStack(Material.SLIME_BALL),
                ChatColor.translateAlternateColorCodes('&', nameFlags + "."));
        StaticGuiElement health_stat = new StaticGuiElement('ż', new ItemStack(Material.SLIME_BALL),
                ChatColor.translateAlternateColorCodes('&', nameFlags + "Życie"),
                ChatColor.translateAlternateColorCodes('&', valueNameFlags + "Wartość: " + actualValueFlags + "69"));
        StaticGuiElement strength_stat = new StaticGuiElement('s', new ItemStack(Material.SLIME_BALL),
                ChatColor.translateAlternateColorCodes('&', nameFlags + "Siła"),
                ChatColor.translateAlternateColorCodes('&', valueNameFlags + "Wartość: " + actualValueFlags + "69"));
        StaticGuiElement dexterity_stat = new StaticGuiElement('z', new ItemStack(Material.SLIME_BALL),
                ChatColor.translateAlternateColorCodes('&', nameFlags + "Zręczność"),
                ChatColor.translateAlternateColorCodes('&', valueNameFlags + "Wartość: " + actualValueFlags + "69"));
        StaticGuiElement one_handed_stat = new StaticGuiElement('j', new ItemStack(Material.SLIME_BALL),
                ChatColor.translateAlternateColorCodes('&', nameFlags + "Broń Jednoręczna"),
                ChatColor.translateAlternateColorCodes('&', valueNameFlags + "Wartość: " + actualValueFlags + "69"));
        StaticGuiElement two_handed_stat = new StaticGuiElement('d', new ItemStack(Material.SLIME_BALL),
                ChatColor.translateAlternateColorCodes('&', nameFlags + "Broń Dwuręczna"),
                ChatColor.translateAlternateColorCodes('&', valueNameFlags + "Wartość: " + actualValueFlags + "69"));
        StaticGuiElement archery_stat = new StaticGuiElement('ł', new ItemStack(Material.SLIME_BALL),
                ChatColor.translateAlternateColorCodes('&', nameFlags + "Łucznictwo"),
                ChatColor.translateAlternateColorCodes('&', valueNameFlags + "Wartość: " + actualValueFlags + "69"));
        StaticGuiElement hunting_stat = new StaticGuiElement('m', new ItemStack(Material.SLIME_BALL),
                ChatColor.translateAlternateColorCodes('&', nameFlags + "Myślistwo"),
                ChatColor.translateAlternateColorCodes('&', valueNameFlags + "Wartość: " + actualValueFlags + "69"));
        StaticGuiElement mining_stat = new StaticGuiElement('g', new ItemStack(Material.SLIME_BALL),
                ChatColor.translateAlternateColorCodes('&', nameFlags + "Górnictwo"),
                ChatColor.translateAlternateColorCodes('&', valueNameFlags + "Wartość: " + actualValueFlags + "69"));

        InventoryGui gui = new InventoryGui(plugin, ChatColor.translateAlternateColorCodes('&',titleFlags + " Statystyki"), guiSetup);
        gui.addElement(empty_field);
        gui.addElement(health_stat);
        gui.addElement(strength_stat);
        gui.addElement(dexterity_stat);
        gui.addElement(one_handed_stat);
        gui.addElement(two_handed_stat);
        gui.addElement(archery_stat);
        gui.addElement(hunting_stat);
        gui.addElement(mining_stat);
        gui.show(player);
        return true;
    }
}
