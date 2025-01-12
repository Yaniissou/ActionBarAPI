package fr.yanissou.actionbarapi.utils;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ActionBarUtils {

    /**
     * Send an action bar message to a player
     * @param player the player to send the action bar message to
     * @param message the message to send
     */
    public static void sendActionBar(Player player, String message) {
        try {
            // Obtenir CraftPlayer et son handle
            final Object craftPlayer = player.getClass().getMethod("getHandle").invoke(player);
            final Object playerConnection = craftPlayer.getClass().getField("playerConnection").get(craftPlayer);

            // Obtenir IChatBaseComponent
            final Class<?> chatBaseComponentClass = getNMSClass("IChatBaseComponent");
            final Class<?> chatSerializerClass = chatBaseComponentClass.getDeclaredClasses()[0]; // Classe interne ChatSerializer
            final Method chatSerializerMethod = chatSerializerClass.getMethod("a", String.class);
            final Object chatBaseComponent = chatSerializerMethod.invoke(null, "{\"text\":\"" + message + "\"}");

            // Construire le packet PacketPlayOutChat
            final Class<?> packetPlayOutChatClass = getNMSClass("PacketPlayOutChat");
            final Constructor<?> packetConstructor = packetPlayOutChatClass.getConstructor(chatBaseComponentClass, byte.class);
            final Object packet = packetConstructor.newInstance(chatBaseComponent, (byte) 2);

            // Envoyer le packet
            final Method sendPacketMethod = playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet"));
            sendPacketMethod.invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Class<?> getNMSClass(String className) throws ClassNotFoundException {
        final String version = org.bukkit.Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        return Class.forName("net.minecraft.server." + version + "." + className);
    }
}
