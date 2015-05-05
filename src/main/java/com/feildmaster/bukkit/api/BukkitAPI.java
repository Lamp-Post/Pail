package com.feildmaster.bukkit.api;

import com.google.api.GoogleAPI;
import java.net.URL;
import org.json.JSONObject;

/**
 * @author feildmaster
 */
public final class BukkitAPI extends GoogleAPI {
    private static final String URI = "http://dl.bukkit.org/api/1.0/downloads";

    private BukkitAPI() {
    }

    public static BukkitArtifact getLatest(Project project, Channel channel) throws Exception {
        return getArtifact(project, "latest-" + channel);
    }

    public static BukkitArtifact getBuild(Project project, int build) throws Exception {
        return getArtifact(project, "build-" + build);
    }

    private static BukkitArtifact getArtifact(Project project, String slug) throws Exception {
        validateReferrer();

        final URL uri = new URL(String.format("%s/projects/%s/view/%s/", URI, project, slug)
                + "?_accept=application%2Fjson");
        final JSONObject json = retrieveJSON(uri);

        return BukkitArtifact.getArtifact(json);
    }

    public enum Project {
        craftbukkit, bukkit;
    }

    public enum Channel {
        rb, beta;
    }
}
