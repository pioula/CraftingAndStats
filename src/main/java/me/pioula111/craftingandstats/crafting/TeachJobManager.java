package me.pioula111.craftingandstats.crafting;

import me.pioula111.craftingandstats.CraftingAndStats;
import me.pioula111.craftingandstats.crafting.json.CraftingManager;
import me.pioula111.craftingandstats.stats.PlayerStats;
import me.pioula111.craftingandstats.stats.json.StatManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.HashSet;

public class TeachJobManager implements Listener {
    private HashSet<Player> teachers;
    private HashMap<Player, Player> students;
    private StatManager statManager;
    private CraftingAndStats plugin;
    public TeachJobManager(StatManager statManager, CraftingAndStats plugin) {
        this.teachers = new HashSet<>();
        students = new HashMap<>();
        this.statManager = statManager;
        this.plugin = plugin;
    }

    public boolean hasPlayer(Player player) {
        return teachers.contains(player);
    }

    public Player pullTeacher(Player student) {
        if (students.containsKey(student)) {
            Player teacher = students.get(student);
            students.remove(student);
            return teacher;
        }
        else {
            return null;
        }
    }

    public void removePlayer(Player player) {
        teachers.remove(player);
    }

    public void addPlayer(Player player) {
        teachers.add(player);
    }

    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
        if (!teachers.contains(event.getPlayer()))
            return;

        if (!(event.getRightClicked() instanceof Player))
            return;

        Player rightClicked = (Player) event.getRightClicked();

        if (!statManager.getPlayerStats(rightClicked).getJob().equals(PlayerStats.noJob)) {
            event.getPlayer().sendMessage(ChatColor.RED + "Ta osoba ma już fach!");
            return;
        }

        if (students.containsKey(rightClicked)) {
            event.getPlayer().sendMessage(ChatColor.RED + "Ktoś już próbuje nauczyć tę osobę fachu!");
            return;
        }

        rightClicked.sendMessage(Component.text().content("Gracz ").style(Style.style(TextColor.color(0,255,0)))
                .append(event.getPlayer().displayName()).append(Component.text().content(" chce ciebie nauczyć fachu " + statManager.getPlayerStats(event.getPlayer()).getJob() + ". Jeśli chcesz zaakceptować napisz /akceptujnauke w przeciągu 10 sekund")).style(Style.style(TextColor.color(0, 255, 0))));

        teachers.remove(event.getPlayer());
        students.put(rightClicked, event.getPlayer());

        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                if (students.containsKey(rightClicked)) {
                    students.remove(rightClicked);
                    rightClicked.sendMessage(ChatColor.RED + "Nie zaakceptowano nauki!");
                    event.getPlayer().sendMessage(ChatColor.RED + "Uczeń nie zaakceptował nauki!");
                }
            }
        }.runTaskLater(plugin, 200);
    }

}
