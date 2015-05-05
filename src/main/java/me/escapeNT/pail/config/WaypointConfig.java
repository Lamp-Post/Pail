package me.escapeNT.pail.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import me.escapeNT.pail.Util.Util;
import me.escapeNT.pail.Util.Waypoint;

/**
 * Class for handling saved waypoints.
 *
 * @author escapeNT
 */
public class WaypointConfig {
    private static List<Waypoint> waypoints = new ArrayList<Waypoint>();

    public static final File file = new File("waypoints.conf");
    public static final ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder()
            .setFile(file).build();
    public static ConfigurationNode rootNode;

    public static void save() {
        rootNode = loader.createEmptyNode(ConfigurationOptions.defaults());
        rootNode.setValue(waypoints);
    }

    public static void load() {
        if (!file.exists()) {
            save();
        }
        try {
            loader.save(rootNode);
        } catch (IOException ex) {
            Util.log(Level.SEVERE, ex.toString());
        }
        waypoints = (List<Waypoint>) rootNode.getValue();
    }

    /**
     * @return the waypoints
     */
    public static List<Waypoint> getWaypoints() {
        return waypoints;
    }
}
