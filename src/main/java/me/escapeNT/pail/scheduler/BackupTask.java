package me.escapeNT.pail.scheduler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.World;

import me.escapeNT.pail.Pail;
import me.escapeNT.pail.Util.Util;

/**
 * Class representing a scheduled world backup task.
 *
 * @author escapeNT
 */
public class BackupTask implements ScheduledTask {
    private static final long serialVersionUID = 2195093109413428297L;
    private final String world;
    private boolean repeating;
    private boolean broadcast;
    private boolean enabled = true;
    private String name;
    private long interval;
    private String intervalType;

    /**
     * Constructs a new scheduled world backup.
     *
     * @param world
     *            The world to back up.
     * @param broadcastMessage
     *            True if a message will be broadcast in the world during the backup.
     * @param repeating
     *            True if this task will repeat.
     * @param interval
     *            The interval between executions (or delay for a non-repeating task).
     */
    public BackupTask(World world, boolean broadcastMessage, boolean repeating, long interval, String intervalType, String name) {
        this.world = world.getName();
        this.repeating = repeating;
        this.interval = interval;
        this.intervalType = intervalType;
        this.broadcast = broadcastMessage;
        this.name = name;
    }

    public long getInterval() {
        return interval;
    }

    public boolean isRepeating() {
        return repeating;
    }

    /**
     * Executes this backup task.
     */
    public void execute() { //TODO world.getName() is not safe to be used as a filename
        final File worldFolder = new File(world);
        final File backupFolder = new File(Util.getDataFolder(), "backups");
        final File backup = new File(backupFolder, world
                + new SimpleDateFormat("'@'MM-dd-yy_hh.mm.ss").format(new Date(System.currentTimeMillis())) + ".zip");
        if (!backupFolder.exists()) {
            backupFolder.mkdir();
        }
        Pail.getGame().getAsyncScheduler().runTask(Util.getPlugin(), new Runnable() {
            @SuppressWarnings("deprecation")
            public void run() {
                Pail.getLogger().info("Starting scheduled backup for " + world);
                if (broadcast) {
                    Pail.getServer().broadcastMessage(
                            Texts.fromLegacy(TextColors.GRAY + "Scheduled backup is starting for " + world));
                }
                long start = System.currentTimeMillis();
                Util.zipDir(worldFolder, backup);
                int seconds = (int) (System.currentTimeMillis() - start) / 1000;
                if (broadcast) {
                    Pail.getServer().broadcastMessage(
                            Texts.fromLegacy(TextColors.GRAY + "Scheduled backup completed for " + world));
                }
                Pail.getLogger().info("Backup completed in " + seconds + (seconds == 1 ? " second" : " seconds") + " for " + world);
            }
        });
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getIntervalType() {
        return intervalType;
    }

    @Override
    public void setInterval(long interval) {
        this.interval = interval;
    }

    @Override
    public void setIntervalType(String intervalType) {
        this.intervalType = intervalType;
    }

    @Override
    public void setRepeating(boolean repeating) {
        this.repeating = repeating;
    }

    @Override
    public String getTastType() {
        return "World backup";
    }
}
