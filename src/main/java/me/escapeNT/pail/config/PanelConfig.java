package me.escapeNT.pail.config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import me.escapeNT.pail.Util.Util;

/**
 * Class to store the activation status of third party panels.
 * 
 * @author escapeNT
 */
public class PanelConfig {

    private static Map<String, Boolean> panelsActivated = new HashMap<String, Boolean>();

    public static final File file = new File("panels.conf");
    public static final ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder()
            .setFile(file).build();
    public static ConfigurationNode rootNode;

    public static void save() {
        rootNode = loader.createEmptyNode(ConfigurationOptions.defaults());
        rootNode.setValue(panelsActivated);
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
        panelsActivated = (Map<String, Boolean>) rootNode.getValue();
    }

    /**
     * @return the panels
     */
    public static Map<String, Boolean> getPanelsActivated() {
        return panelsActivated;
    }
}
