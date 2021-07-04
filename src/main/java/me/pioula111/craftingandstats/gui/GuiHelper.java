package me.pioula111.craftingandstats.gui;

import de.themoep.inventorygui.GuiPageElement;
import de.themoep.inventorygui.StaticGuiElement;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GuiHelper {
    public static final String NAME_FLAGS = "&f&B&L";
    public static final String VALUE_NAME_FLAGS = "&f&A";
    public static final String ACTUAL_VALUE_FLAGS = "&f&6&L";
    public static final String TITLE_FLAGS = "&L";

    public static String createGuiTitle(String title) {
        return ChatColor.translateAlternateColorCodes('&',GuiHelper.TITLE_FLAGS + " " + title);
    }

    public static StaticGuiElement createStat(char replacement, String name, long value) {
        return new StaticGuiElement(replacement, new ItemStack(Material.SLIME_BALL),
                ChatColor.translateAlternateColorCodes('&', GuiHelper.NAME_FLAGS + name),
                ChatColor.translateAlternateColorCodes('&', GuiHelper.VALUE_NAME_FLAGS + "Wartość: " + GuiHelper.ACTUAL_VALUE_FLAGS + value));
    }

    public static GuiPageElement leftArrow(char replacement) {
       return new GuiPageElement(replacement, new ItemStack(Material.SLIME_BALL), GuiPageElement.PageAction.PREVIOUS, "Kliknij, aby przejść w lewo");
    }

    public static GuiPageElement rightArrow(char replacement) {
        return new GuiPageElement(replacement, new ItemStack(Material.SLIME_BALL), GuiPageElement.PageAction.NEXT, "Kliknij, aby przejść w prawo");
    }

    public static ItemStack getFiller() {
        return new ItemStack(Material.SLIME_BALL);
    }
}
