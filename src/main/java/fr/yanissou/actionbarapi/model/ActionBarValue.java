package fr.yanissou.actionbarapi.model;

import org.bukkit.entity.Player;

/**
 * Represents a value for an action bar entry.
 */
@FunctionalInterface
public interface ActionBarValue {
    /**
     * Gets the value of the action bar entry.
     * @return the value of the action bar entry
     */
    String getValue(Player player);
}
