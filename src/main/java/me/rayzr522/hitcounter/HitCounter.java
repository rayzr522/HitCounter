package me.rayzr522.hitcounter;

import org.bukkit.plugin.java.JavaPlugin;

public class HitCounter extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }
}
