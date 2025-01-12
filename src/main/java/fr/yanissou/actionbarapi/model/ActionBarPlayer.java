package fr.yanissou.actionbarapi.model;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ActionBarPlayer {

    /**
     * The unique identifier of the player
     */
    private final UUID uniqueId;

    /**
     * The list of action bar entries for the player
     */
    private final List<ActionBarEntry> actionBarEntries;
    public ActionBarPlayer(final Player player) {
        this.uniqueId = player.getUniqueId();
        this.actionBarEntries = new ArrayList<>();
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public List<ActionBarEntry> getActionBarEntries() {
        return actionBarEntries;
    }

    public void addActionBarEntry(final ActionBarEntry actionBarEntry) {
        this.actionBarEntries.add(actionBarEntry);
    }

    public void removeActionBarEntry(final String key) {
        this.actionBarEntries.removeIf(actionBarEntry -> actionBarEntry.getKey().equals(key));
    }
}
