package me.iiyoyo.purplestudioschatgames;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class broadcast implements Listener {
    private boolean minecraft;
    private String scrambledWord;

    private static final HashMap<String, String> ANSWERS = new HashMap<>();
    static {
        ANSWERS.put("EFIMCRTNA", "MINECRAFT");
        ANSWERS.put("ANOGJM", "MOJANG");
        ANSWERS.put("DDNAMIO", "DIAMOND");
        ANSWERS.put("MEEDRASL", "EMERALDS");
        ANSWERS.put("LVGAILE", "VILLAGE");
        ANSWERS.put("HNERTE", "NETHER");
        ANSWERS.put("BMZOIE", "ZOMBIE");
        ANSWERS.put("ETVSE", "STEVE");
        ANSWERS.put("LYRETA", "ELYTRA");
        ANSWERS.put("CKEPIXA", "PICKAXE");
        ANSWERS.put("OALPTR", "PORTAL");
    }

    private static final Random RANDOM = new Random();

    public void broadcast(Plugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                minecraft = false;
                scrambledWord = new ArrayList<>(ANSWERS.keySet()).get(RANDOM.nextInt(ANSWERS.size()));
                Bukkit.broadcastMessage(ChatColor.YELLOW + "Unscramble " + scrambledWord + " for 1 diamond ore");
            }
        }.runTaskTimer(plugin, 0, 10 * 20);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        if (!minecraft && message.equalsIgnoreCase(ANSWERS.get(scrambledWord))) {
            player.getInventory().addItem(new ItemStack(Material.DIAMOND));
            player.sendMessage(ChatColor.GREEN + "Hooray! You've unscrambled the word, earning yourself a precious diamond as a reward!");
            minecraft = true;
        }
    }
}
