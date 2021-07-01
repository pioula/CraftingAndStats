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
                " ż j m f ",
                " s d g   ",
                " z ł     "
        };

        PlayerStats playerStats = statManager.getPlayerStats(player);

        StaticGuiElement health_stat = GuiHelper.createStat('ż', "Życie", playerStats.getHealth());
        StaticGuiElement strength_stat = GuiHelper.createStat('s', "Siła", playerStats.getStrength());
        StaticGuiElement dexterity_stat = GuiHelper.createStat('z', "Zręczność", playerStats.getDexterity());
        StaticGuiElement one_handed_stat = GuiHelper.createStat('j', "Broń Jednoręczna", playerStats.getOneHanded());
        StaticGuiElement two_handed_stat = GuiHelper.createStat('d', "Broń dwuręczna", playerStats.getTwoHanded());
        StaticGuiElement archery_stat = GuiHelper.createStat('ł', "Łucznictwo", playerStats.getArchery());
        StaticGuiElement hunting_stat = GuiHelper.createStat('m', "Myślistwo", playerStats.getHunting());
        StaticGuiElement mining_stat = GuiHelper.createStat('g', "Górnictwo", playerStats.getMining());
        StaticGuiElement job_stat = new StaticGuiElement('f', new ItemStack(Material.SLIME_BALL),
                ChatColor.translateAlternateColorCodes('&', GuiHelper.NAME_FLAGS + playerStats.getJob()));


        InventoryGui gui = new InventoryGui(plugin, GuiHelper.createGuiTitle("Statystyki"), guiSetup);
        gui.setFiller(GuiHelper.getFiller());
        gui.addElement(health_stat);
        gui.addElement(strength_stat);
        gui.addElement(dexterity_stat);
        gui.addElement(one_handed_stat);
        gui.addElement(two_handed_stat);
        gui.addElement(archery_stat);
        gui.addElement(hunting_stat);
        gui.addElement(mining_stat);
        gui.addElement(job_stat);
        gui.show(player);
        return true;
    }
}
