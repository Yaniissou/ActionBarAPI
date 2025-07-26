package fr.yanissou.actionbarapi.tasks;

import fr.yanissou.actionbarapi.ActionBarAPI;
import fr.yanissou.actionbarapi.managers.ActionBarManager;
import fr.yanissou.actionbarapi.model.ActionBarEntry;
import fr.yanissou.actionbarapi.utils.ActionBarUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Task that sends the action bar to all players with an action bar
 *
 * @see ActionBarUtils
 */
public class ActionBarTask extends BukkitRunnable {


    private final ActionBarManager actionBarManager;

    public ActionBarTask(Plugin plugin) {
        this.actionBarManager = Objects.requireNonNull(
            plugin.getServer().getServicesManager().load(ActionBarManager.class));
        runTaskTimer(plugin, 0, 20L);
    }

    @Override
    public void run() {
        this.actionBarManager.getActionBarPlayers().forEach(actionBarPlayer -> {
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

            // Decrease remaining time
            actionBarEntries.forEach(ActionBarEntry::decreaseTimeLeft);
        });
    }
}
