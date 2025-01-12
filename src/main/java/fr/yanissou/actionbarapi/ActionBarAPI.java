package fr.yanissou.actionbarapi;

import fr.yanissou.actionbarapi.listeners.PlayerListeners;
import fr.yanissou.actionbarapi.managers.ActionBarManager;
import fr.yanissou.actionbarapi.model.ActionBarEntry;
import fr.yanissou.actionbarapi.tasks.ActionBarTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;

import java.util.List;
import java.util.UUID;

public interface ActionBarAPI {

    /**
     * Get the instance of the ActionBarAPI
     * @return the instance of the ActionBarAPI
     */
    static ActionBarAPI get() {
        return Bukkit.getServicesManager().load(ActionBarAPI.class);
    }

    /**
     * Register and set up the ActionBarAPI
     * @param plugin the plugin to register the ActionBarAPI for
     */
    static void register(final Plugin plugin) {
        final ServicesManager servicesManager = plugin.getServer().getServicesManager();
        servicesManager.register(ActionBarAPI.class, new ActionBarAPIImpl(plugin), plugin, ServicePriority.Normal);
        servicesManager.register(ActionBarManager.class, new ActionBarManager(), plugin, ServicePriority.Normal);

        final PluginManager pluginManager = plugin.getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerListeners(plugin), plugin);
        new ActionBarTask(plugin);
    }

    /**
     * Add an entry to the action bar of a player
     * @param playerUuid the UUID of the player
     * @param entry the entry to add
     */
    void addEntry(final UUID playerUuid, final ActionBarEntry entry);

    /**
     * Remove an entry from the action bar of a player
     * @param playerUuid the UUID of the player
     * @param key the key of the entry to remove
     */
    void removeEntry(final UUID playerUuid, final String key);

    /**
     * Get the entries of the action bar of a player
     * @param playerUuid
     * @return
     */
    List<ActionBarEntry> getEntries(final UUID playerUuid);
}
