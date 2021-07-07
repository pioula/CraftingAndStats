package me.pioula111.craftingandstats.stats.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import me.pioula111.craftingandstats.stats.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class StatJsonOnJoin implements Listener {
    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson;
    private File file;
    private JsonReader reader;
    private StatManager statManager;

    public StatJsonOnJoin(StatManager statManager) {
        this.statManager = statManager;
    }

    public void readJson(File file, Player player) {
        gsonBuilder.setPrettyPrinting();

        gson = gsonBuilder.create();
        this.file = file;
        try {
            reader = new JsonReader(new FileReader(file.getPath()));
        }
        catch(FileNotFoundException ex) {
            Bukkit.getLogger().info("Nieznaleziono Pliku Json!");
        }

        reload(player);
    }

    private void reload(Player player) {
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.exists()) {
                  statManager.actualisePlayer(player, gson.fromJson(reader, PlayerStats.class));
            }
            else {
                if (!file.createNewFile())
                    Bukkit.getLogger().info("[CraftingAndStats] NIE UDAŁO SIĘ STWORZYĆ PLIKU JSON");
                statManager.actualisePlayer(player, new PlayerStats());
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        File jsonFile = new File("plugins/CraftingAndStats/stats/" + event.getPlayer().getName() + "-" + event.getPlayer().getUniqueId() + ".json");

        readJson(jsonFile, event.getPlayer());
    }
}
