package me.pioula111.craftingandstats.stats;

import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import me.pioula111.craftingandstats.CraftingAndStats;
import me.pioula111.craftingandstats.gui.GuiHelper;
import me.pioula111.craftingandstats.stats.json.StatManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CommandStaty implements CommandExecutor {

    private CraftingAndStats plugin;
    private StatManager statManager;

    public CommandStaty(CraftingAndStats plugin) {
        this.plugin = plugin;
        statManager = plugin.getStatManager();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;
        Player player = (Player) sender;

        String[] guiSetup = {
                " ż j g f ",
                " s d     ",
                " z ł     "
        };

        PlayerStats playerStats = statManager.getPlayerStats(player);

        StaticGuiElement healthStat = GuiHelper.createStat('ż', "Życie", playerStats.getStat("health"));
        StaticGuiElement strengthStat = GuiHelper.createStat('s', "Siła", playerStats.getStat("strength"));
        StaticGuiElement dexterityStat = GuiHelper.createStat('z', "Zręczność", playerStats.getStat("dexterity"));
        StaticGuiElement oneHandedStat = GuiHelper.createStat('j', "Broń Jednoręczna", playerStats.getStat("one_handed"));
        StaticGuiElement twoHandedStat = GuiHelper.createStat('d', "Broń dwuręczna", playerStats.getStat("two_handed"));
        StaticGuiElement archeryStat = GuiHelper.createStat('ł', "Łucznictwo", playerStats.getStat("long_distance"));
        StaticGuiElement miningStat = GuiHelper.createStat('g', "Górnictwo", playerStats.getStat("mining"));
        StaticGuiElement jobStat = new StaticGuiElement('f', new ItemStack(Material.SLIME_BALL),
                ChatColor.translateAlternateColorCodes('&', GuiHelper.NAME_FLAGS + playerStats.getJob()));


        InventoryGui gui = new InventoryGui(plugin, GuiHelper.createGuiTitle("Statystyki"), guiSetup);
        gui.setFiller(GuiHelper.getFiller());
        gui.addElement(healthStat);
        gui.addElement(strengthStat);
        gui.addElement(dexterityStat);
        gui.addElement(oneHandedStat);
        gui.addElement(twoHandedStat);
        gui.addElement(archeryStat);
        gui.addElement(miningStat);
        gui.addElement(jobStat);
        gui.show(player);
        return true;
    }
}
