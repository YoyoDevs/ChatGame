package me.iiyoyo.purplestudioschatgames;

import org.bukkit.plugin.java.JavaPlugin;

public final class PurpleStudiosChatGames extends JavaPlugin{
    @Override
    public void onEnable() {

        broadcast broadcasty = new broadcast();

        broadcasty.broadcast(this);

        getServer().getPluginManager().registerEvents(broadcasty, this);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
