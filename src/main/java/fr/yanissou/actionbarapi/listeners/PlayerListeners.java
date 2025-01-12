package fr.yanissou.actionbarapi.listeners;

import fr.yanissou.actionbarapi.managers.ActionBarManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicesManager;

import java.util.Objects;

/**
 * Listens for player join events to create an action bar for the player.
 */
public class PlayerListeners implements Listener {

    private final ActionBarManager actionBarManager;

    public PlayerListeners(Plugin plugin) {
        final ServicesManager servicesManager = plugin.getServer().getServicesManager();
        this.actionBarManager = Objects.requireNonNull(servicesManager.load(ActionBarManager.class));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        this.actionBarManager.createActionBarPlayer(event.getPlayer());
    }
}
