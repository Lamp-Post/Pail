package com.feildmaster.bukkit.api;

import org.json.JSONObject;

/**
 * @author feildmaster
 */
// TODO: Interface
public final class BukkitArtifact {
    private final String version;
    private final int build;
    private final int downloads;
    private final boolean broken;
    private final String breason;

    private BukkitArtifact(String version, int build, int downloads, boolean broken, String breason) {
        this.version = version;
        this.build = build;
        this.downloads = downloads;
        this.broken = broken;
        this.breason = breason;
    }

    public boolean getIsBroken() {
        return broken;
    }

    public String getBrokenReason() {
        return breason;
    }

    public int getDownloadCount() {
        return downloads;
    }

    public int getBuildNumber() {
        return build;
    }

    public String getVersion() {
        return version;
    }

    static BukkitArtifact getArtifact(final JSONObject json) throws Exception {
        String version = json.getString("version");
        int build = json.getInt("build_number");
        int downloads = json.getInt("download_count");
        boolean broken = json.getBoolean("is_broken");
        String breason = json.getString("broken_reason");
        //TODO: file.size, file.url, file.checksum_md5, commit, created, html_url

        return new BukkitArtifact(version, build, downloads, broken, breason);
    }
}
