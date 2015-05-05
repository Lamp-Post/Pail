package me.escapeNT.pail.scheduler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.spongepowered.api.world.World;

import me.escapeNT.pail.Pail;
import me.escapeNT.pail.Util.Util;

/**
 * Class representing a scheduled world backup task.
 *
 * @author escapeNT
 */
public class BackupTask implements ScheduledTask {
    private final String world;
    private boolean repeating;
    private boolean broadcast;
    private boolean enabled = true;
    private String name;
    private long interval;

    /**
     * Constructs a new scheduled world backup.
     *
     * @param world The world to back up.
     * @param broadcastMessage True if a message will be broadcast in the world
     * during the backup.
     * @param repeating True if this task will repeat.
     * @param interval The interval between executions (or delay for a
     * non-repeating task).
     */
    public BackupTask(World world, boolean broadcastMessage, boolean repeating, long interval, String name) {
        this.world = world.getName();
        this.repeating = repeating;
        this.interval = interval;
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
    public void execute() {
        final File worldFolder = new File(world);
        final File backupFolder = new File(Util.getPlugin().getDataFolder(), "backups");
        final File backup = new File(backupFolder, world
                + new SimpleDateFormat("'@'MM-dd-yy_hh.mm.ss").format(new Date(System.currentTimeMillis())) + ".zip");
        if (!backupFolder.exists()) {
            backupFolder.mkdir();
        }
        Pail.getServer().getScheduler().runTaskAsynchronously(Util.getPlugin(), new Runnable() {
            public void run() {
                Util.log("Starting scheduled backup for " + world);
                if (broadcast) {
                    Pail.getServer().broadcastMessage(ChatColor.GRAY + "Scheduled backup is starting for " + world);
                }
                long start = System.currentTimeMillis();
                Util.zipDir(worldFolder, backup);
                int seconds = (int) (System.currentTimeMillis() - start) / 1000;
                if (broadcast) {
                    Pail.getServer().broadcastMessage(ChatColor.GRAY + "Scheduled backup completed for " + world);
                }
                Util.log("Backup completed in " + seconds + (seconds == 1 ? " second" : " seconds") + " for " + world);
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
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}