package me.escapeNT.pail;

import javax.swing.SwingUtilities;
import me.escapeNT.pail.Util.ServerReadyListener;
import me.escapeNT.pail.Util.Util;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Listener for player login and quit events to update the player list.
 *
 * @author escapeNT
 */
public class PailPlayerListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
        final String player = event.getPlayer().getName();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Util.getServerControls().addPlayer(player);
                if (ServerReadyListener.settings != null) {
                    ServerReadyListener.settings.getWaypointEditor().getPlayers().addItem(player);
                }
            }
        });
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit(final PlayerQuitEvent event) {
        final String player = event.getPlayer().getName();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Util.getServerControls().getListModel().removeElement(player);
                if (ServerReadyListener.settings != null) {
                    ServerReadyListener.settings.getWaypointEditor().getPlayers().removeItem(player);
                }
            }
        });
    }
}