package me.escapeNT.pail.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;

import me.escapeNT.pail.Pail;

/**
 * Class intended to handle the vanilla server configuration.
 * 
 * @author escapeNT
 */
public class ServerConfigHandler {

    public static final File file = new File("server.properties");

    /**
     * Saves the given configuration to file.
     * 
     * @param properties
     *            The server properties.
     */
    public static void save(HashMap<String, String> properties) {
        file.delete();
        try {
            FileWriter fw = new FileWriter(file);
            PrintWriter writer = new PrintWriter(fw);

            writer.println("#Minecraft server properties");
            writer.println("#" + new Date(System.currentTimeMillis()).toString());

            for (String p : properties.keySet()) {
                writer.println(p + "=" + properties.get(p));
            }

            writer.close();
        } catch (IOException ex) {
            Pail.getLogger().error(ex.toString());
        }
    }
}
