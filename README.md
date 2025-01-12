<div align="center">

# ActionBarAPI

A system for dynamically displaying and updating Action Bar messages in Minecraft.
<br/><br/>
</div>

## Features

- Send dynamic information to the player's Action Bar.
- Automatically updates at set intervals (e.g., every second).
- Supports dynamic calculations for each Action Bar entry.

## Usage

```java
import fr.yanissou.actionbarapi.ActionBarAPI;
import fr.yanissou.actionbarapi.model.ActionBarEntry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        ActionBarAPI.get().addEntry(event.getPlayer().getUniqueId(), new ActionBarEntry("welcome", () -> "§aS§ba§cl§du§t §r!"));
    }
}
```

## Customization

An interface that defines how to retrieve the dynamic value for each entry (e.g., a score or remaining time).

```java
ActionBarAPI.get().addEntry(event.getPlayer().getUniqueId(), new ActionBarEntry("level", () -> String.valueOf(player.getLevel())));
```

## Contributing

Feel free to fork the repository, make improvements, or submit pull requests.
