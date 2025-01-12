package fr.yanissou.actionbarapi.utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBarUtils {

    /**
     * Send an action bar message to a player
     * @param player the player to send the action bar message to
     * @param message the message to send
     */
    public static void sendActionBar(Player player, String message) {
        String jsonMessage = "{\"text\":\"" + message + "\"}";
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a(jsonMessage), (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}
