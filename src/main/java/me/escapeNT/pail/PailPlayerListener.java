package me.escapeNT.pail;

import javax.swing.SwingUtilities;

import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.entity.player.PlayerJoinEvent;
import org.spongepowered.api.event.entity.player.PlayerQuitEvent;

import me.escapeNT.pail.Util.ServerReadyListener;
import me.escapeNT.pail.Util.Util;

/**
 * Listener for player login and quit events to update the player list.
 *
 * @author escapeNT
 */
public class PailPlayerListener { //implements Listener {
	
	@Subscribe
    //@EventHandler(priority = EventPriority.MONITOR)TODO
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

	@Subscribe
    //@EventHandler(priority = EventPriority.MONITOR)TODO
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