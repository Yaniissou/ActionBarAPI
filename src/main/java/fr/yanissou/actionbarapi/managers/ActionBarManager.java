package fr.yanissou.actionbarapi.managers;

import fr.yanissou.actionbarapi.model.ActionBarPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Manages all the ActionBarPlayers
 *
 * @see ActionBarPlayer
 */
public class ActionBarManager {

    private final List<ActionBarPlayer> actionBarPlayers;

    public ActionBarManager() {
        this.actionBarPlayers = new ArrayList<>();
    }

    /**
     * Creates an ActionBarPlayer for the given player
     *
     * @param player
     */
    public void createActionBarPlayer(final Player player) {
        this.actionBarPlayers.add(new ActionBarPlayer(player));
    }

    /**
     * Get all the ActionBarPlayers
     *
     * @return a list of all the ActionBarPlayers
     */
    public List<ActionBarPlayer> getActionBarPlayers() {
        return actionBarPlayers;
    }

    /**
     * Get an ActionBarPlayer by their UUID
     *
     * @param playerUUID the UUID of the player
     * @return the ActionBarPlayer with the given UUID, or null if not found
     */
    public ActionBarPlayer getActionBarPlayer(final UUID playerUUID) {
        return this.actionBarPlayers.stream()
                .filter(actionBarPlayer -> actionBarPlayer.getUniqueId().equals(playerUUID))
                .findFirst()
                .orElse(null);
    }
}
