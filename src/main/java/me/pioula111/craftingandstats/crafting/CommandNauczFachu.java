package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.CraftingAndStats;
import me.pioula111.craftingandstats.stats.PlayerStats;
import me.pioula111.craftingandstats.stats.json.StatManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

public class CommandNauczFachu implements CommandExecutor {
    private StatManager statManager;
    private NauczFachuManager manager;
    private CraftingAndStats plugin;

    public CommandNauczFachu(StatManager statManager, NauczFachuManager nauczFachuManager, CraftingAndStats plugin) {
        this.statManager = statManager;
        this.manager = nauczFachuManager;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;

        Player player = (Player) sender;

        if (manager.hasPlayer(player)) {
            player.sendMessage(ChatColor.RED + "Jesteś w trakcie nauki!");
            return true;
        }

        if (args.length != 0) {
            player.sendMessage(ChatColor.RED + "Aby nauczyć kogoś fachu wpisz /nauczfachu i kliknij tę osobę prawym przyciskiem myszy w przeciągu 10 sekund!");
            return true;
        }

        if (!statManager.hasPlayer(player) ||
                (statManager.hasPlayer(player) && statManager.getPlayerStats(player).getJob().equals(PlayerStats.noJob))) {
            player.sendMessage(ChatColor.RED + "Jesteś bezrobotny!");
        }

        manager.addPlayer(player);
        player.sendMessage(ChatColor.GREEN + "Kliknij teraz osobę którą chcesz nauczyć prawym przyciskiem myszy!");
        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                if (manager.hasPlayer(player)) {
                    manager.removePlayer(player);
                    if (player.isOnline()) {
                        player.sendMessage(ChatColor.RED + "Anulowano naukę!");
                    }
                }
            }
        }.runTaskLater(plugin, 200);
        return true;
    }
}
