package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.gui.MenuHelper;
import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public class CommandJobs implements CommandExecutor {
    private CraftingManager craftingManager;

    public CommandJobs(CraftingManager craftingManager) {
        this.craftingManager = craftingManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;

        if (args.length != 0) {
            sender.sendMessage(ChatColor.RED + "Zła komenda! Użyj /fachy");
            return true;
        }

        HashSet<Job> jobs =  craftingManager.getJobs();

        sender.sendMessage(createMenu(jobs));
        return true;
    }

    private TextComponent createMenu(HashSet<Job> jobs) {
        TextComponent menu = Component.text().content("ᚾᛁᚷᚺᛏ ").style(Style.style(MenuHelper.DECORATIONS))
                .append(Component.text().content("Fachy").style(Style.style(MenuHelper.MAIN_NAME, TextDecoration.BOLD)))
                .append(Component.text().content(" ᚾᛁᚷᚺᛏ\n").style(Style.style(MenuHelper.DECORATIONS))).build();
        int enumerator = 0;
        for (Job job : jobs) {
            menu = menu.append(job.menuComponent(++enumerator));
        }

        return menu;
    }
}
