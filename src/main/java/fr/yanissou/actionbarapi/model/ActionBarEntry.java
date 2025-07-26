package fr.yanissou.actionbarapi.model;

import java.util.function.Supplier;
import org.bukkit.entity.Player;

/**
 * Represents an entry in the action bar.
 *
 * @see ActionBarValue
 * @see ActionBarPlayer
 */
public class ActionBarEntry {

    private final String key;

    /**
     * The value of the action bar entry.
     */
    private final ActionBarValue actionBarValue;
    private final int duration;
    private int timeleft;

    /**
     * Creates an action bar entry with a message and a duration.
     *
     * @param actionBarValue the value of the action bar entry
     * @param duration       the duration of the action bar entry
     */
    public ActionBarEntry(final String key, final ActionBarValue actionBarValue, final int duration) {
        this.key = key;
        this.actionBarValue = actionBarValue;
        this.duration = duration;
        this.timeleft = duration;
    }

    // Backward compatibility
    public ActionBarEntry(final String key, final Supplier<String> actionBarValue, final int duration) {
        this(key, player -> actionBarValue.get(), duration);
    }

    /**
     * Creates an action bar entry with a message and no duration.
     *
     * @param message the value of the action bar entry
     */
    public ActionBarEntry(final String key, final ActionBarValue message) {
        this(key, message, -1);
    }

    // Backward compatibility
    public ActionBarEntry(final String key, final Supplier<String> actionBarValue) {
        this(key, player -> actionBarValue.get());
    }

    public String getKey() {
        return key;
    }

    /**
     * Gets the value object of the action bar entry.
     *
     * @return the value object of the action bar entry
     */
    public ActionBarValue getActionBarValue() {
        return actionBarValue;
    }

    /**
     * Gets the String value of the action bar entry.
     *
     * @return the String value of the action bar entry
     */
    public String getValue(Player player) {
        return actionBarValue.getValue(player);
    }

    /**
     * Gets the duration of the action bar entry.
     *
     * @return the duration of the action bar entry, or -1 if the entry has no duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Decreases the time left for the action bar entry.
     * Does nothing if the entry has no duration.
     * Does nothing if the time left is already 0.
     */
    public void decreaseTimeLeft() {
        if (duration == -1) return;
        if (this.timeleft <= 0) return;
        this.timeleft--;
    }

    /**
     * Checks if the action bar entry is expired.
     *
     * @return true if the action bar entry is expired, false otherwise
     */
    public boolean isExpired() {
        return this.timeleft == 0;
    }
}

