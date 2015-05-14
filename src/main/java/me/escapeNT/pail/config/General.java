package me.escapeNT.pail.config;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import com.google.api.translate.Language;

import javax.swing.UIManager;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import me.escapeNT.pail.Util.Util;

/**
 * Class for handling general configuration.
 * 
 * @author escapeNT
 */
public class General {

    private static final File defaultConfig= new File(Util.getDataFolder(), "pail.conf");;

    private static final ConfigurationLoader<CommentedConfigurationNode> configManager = HoconConfigurationLoader.builder()
            .setFile(defaultConfig).build();
    public static ConfigurationNode rootNode;

    /**
     * Loads the configuration.
     */
    public static void load() {
        if (!defaultConfig.exists()) {
            rootNode = configManager.createEmptyNode(ConfigurationOptions.defaults());
        } else {
            try {
                rootNode = configManager.load();
            } catch (IOException e) {
                rootNode = null;
            }
        }
        defaults();
        setAutoUpdate(isAutoUpdate());
        setLookAndFeel(getLookAndFeel());
        loadConfigLang();
        save();
    }

    private static void defaults() {
        rootNode.getNode("Autoupdate").setValue(true);
        rootNode.getNode("Skin").setValue(UIManager.getSystemLookAndFeelClassName());
        rootNode.getNode("Language").setValue(Language.ENGLISH.toString());
    }

    /**
     * Saves the configuration.
     */
    public static void save() {
        try {
            configManager.save(rootNode);
        } catch (IOException ex) {
            Util.log(Level.SEVERE, ex.toString());
        }
    }

    /**
     * @return the autoUpdate
     */
    public static boolean isAutoUpdate() {
        return rootNode.getNode("Autoupdate").getBoolean(true);
    }

    /**
     * @param aAutoUpdate
     *            the autoUpdate to set
     */
    public static void setAutoUpdate(boolean autoUpdate) {
        rootNode.getNode("Autoupdate").setValue(autoUpdate);
    }

    /**
     * @return the lookAndFeel
     */
    public static String getLookAndFeel() {
        return rootNode.getNode("Skin").getString();
    }

    /**
     * @param aLookAndFeel
     *            the lookAndFeel to set
     */
    public static void setLookAndFeel(String lookAndFeel) {
        rootNode.getNode("Skin").setValue(lookAndFeel);
    }

    /**
     * @return the language
     */
    private static Language lang;

    public static Language getLang() {
        return lang;
    }

    private static void loadConfigLang() {
        String value = rootNode.getNode("Language").getString();
        Language lang = Language.fromString(value);
        if (lang == null) {
            lang = Language.ENGLISH;
        }
        setLang(lang);
    }

    /**
     * @param aLang
     *            the lang to set
     */
    public static void setLang(Language aLang) {
        if (aLang == null)
            return;
        rootNode.getNode("Language").setValue(aLang.toString());
        lang = aLang;
    }
}
