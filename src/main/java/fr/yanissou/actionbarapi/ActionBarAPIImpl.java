package fr.yanissou.actionbarapi;

import fr.yanissou.actionbarapi.managers.ActionBarManager;
import fr.yanissou.actionbarapi.model.ActionBarEntry;
import fr.yanissou.actionbarapi.model.ActionBarPlayer;
import fr.yanissou.actionbarapi.utils.ActionBarUtils;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicesManager;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ActionBarAPIImpl implements ActionBarAPI {

    private final Plugin plugin;

    public ActionBarAPIImpl(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void addEntry(UUID playerUuid, ActionBarEntry entry) {
        final ServicesManager servicesManager = this.plugin.getServer().getServicesManager();
        final ActionBarManager actionBarManager = Objects.requireNonNull(servicesManager.load(ActionBarManager.class));

        ActionBarPlayer actionBarPlayer = actionBarManager.getActionBarPlayer(playerUuid);
        if (actionBarPlayer == null) {
            return;
        }
        actionBarPlayer.addActionBarEntry(entry);
    }

    @Override
    public void removeEntry(UUID playerUuid, String key) {
        final ServicesManager servicesManager = this.plugin.getServer().getServicesManager();
        final ActionBarManager actionBarManager = Objects.requireNonNull(servicesManager.load(ActionBarManager.class));

        ActionBarPlayer actionBarPlayer = actionBarManager.getActionBarPlayer(playerUuid);
        if (actionBarPlayer == null) {
            return;
        }
        actionBarPlayer.removeActionBarEntry(key);
    }

    @Override
    public List<ActionBarEntry> getEntries(UUID playerUuid) {
        final ServicesManager servicesManager = this.plugin.getServer().getServicesManager();
        final ActionBarManager actionBarManager = Objects.requireNonNull(servicesManager.load(ActionBarManager.class));

        ActionBarPlayer actionBarPlayer = actionBarManager.getActionBarPlayer(playerUuid);
        if (actionBarPlayer == null) {
            return null;
        }
        return actionBarPlayer.getActionBarEntries();
    }

    @Override
    public void update(final UUID playerUuid) {
        final ServicesManager servicesManager = this.plugin.getServer().getServicesManager();
        final ActionBarManager actionBarManager = Objects.requireNonNull(servicesManager.load(ActionBarManager.class));
        final ActionBarPlayer actionBarPlayer = actionBarManager.getActionBarPlayer(playerUuid);

        Player player = Bukkit.getPlayer(actionBarPlayer.getUniqueId());
        if (player == null) {
            return;
        }

        final List<ActionBarEntry> actionBarEntries = actionBarPlayer.getActionBarEntries();
        if (actionBarEntries.isEmpty()) {
            return;
        }
        final String formattedActionBar = ActionBarUtils.formatActionBar(actionBarEntries, player);
        ActionBarUtils.sendActionBar(player, formattedActionBar);

        // Remove expired entries
        actionBarEntries.removeIf(ActionBarEntry::isExpired);
    }
}
