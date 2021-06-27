package me.pioula111.craftingandstats.stats;

import de.themoep.inventorygui.GuiElement;
import de.themoep.inventorygui.GuiStateElement;
import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import me.pioula111.craftingandstats.CraftingAndStats;
import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class CommandTest implements CommandExecutor {
    private CraftingAndStats plugin;

    public CommandTest(CraftingAndStats plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;
        Player player = (Player) sender;

        String[] guiSetup = {
                "  s      ",
                "         ",
                "         "
        };
        StaticGuiElement element = new StaticGuiElement('s', new ItemStack(Material.REDSTONE),"test");
        InventoryGui gui = new InventoryGui(plugin, "Test", guiSetup);
        gui.setFiller(new ItemStack(Material.GRAY_STAINED_GLASS, 1)); // fill the empty slots with this
        gui.addElement(element);
        gui.show(player);
        return true;
    }
}
