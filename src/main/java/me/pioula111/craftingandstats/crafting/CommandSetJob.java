package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.stats.json.StatManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandSetJob implements CommandExecutor {
    private StatManager statManager;

    public CommandSetJob(StatManager statManager) {
        this.statManager = statManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;

        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Zła komenda! Użyj /ustawfach <nick_gracza> <kowal/luczarz/alchemik/brak>");
            return true;
        }

        Player player = Bukkit.getServer().getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(ChatColor.RED + "Nie ma takiego gracza!");
            return false;
        }

        switch(args[1]) {
            case "kowal":
                statManager.getPlayerStats(player).setJobSmith();
                break;
            case "luczarz":
                statManager.getPlayerStats(player).setJobArcher();
                break;
            case "alchemik":
                statManager.getPlayerStats(player).setJobAlchemist();
                break;
            default:
                statManager.getPlayerStats(player).setJobNone();
        }

        sender.sendMessage(ChatColor.GREEN + "Pomyślnie ustawiono fach!");
        return true;
    }
}
