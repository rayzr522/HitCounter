package me.rayzr522.hitcounter;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerListener implements Listener {
    private final Map<UUID, Integer> hitsPerSecond = new HashMap<>();

    public PlayerListener(HitCounter plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                hitsPerSecond.clear();
            }
        }.runTaskTimer(plugin, 20L, 20L);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();

        int newHits = hitsPerSecond.getOrDefault(event.getEntity().getUniqueId(), 0) + 1;
        hitsPerSecond.put(event.getEntity().getUniqueId(), newHits);

        damager.sendMessage("Hits this second: " + newHits + " - " + event.getClass().getSimpleName());
    }
}
