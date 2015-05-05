package me.escapeNT.pail.Util;

import java.io.Serializable;

import me.escapeNT.pail.Pail;

import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

/**
 * Class for storing location data about a waypoint.
 * 
 * @author escapeNT
 */
public class Waypoint implements Serializable {

    private int x;
    private int y;
    private int z;
    private String world;
    private String name;

    /**
     * Constructs a new Waypoint from the specified Location,
     * 
     * @param loc
     *            The Location of the waypoint.
     */
    public Waypoint(String name, Location loc, World world) {
        this.x = loc.getBlockX();
        this.y = loc.getBlockY();
        this.z = loc.getBlockZ();
        this.world = world.getName();
        this.name = name;
    }

    /**
     * Gets this Waypoint's location.
     * 
     * @return The Location of the waypoint.
     */
    public Location getLocation() {
        return new Location(Pail.getServer().getWorld(world).get(), x, y, z);
    }

    public String getWorld() {
        return world;
    }

    /**
     * Returns the name of this waypoint.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Waypoint)) {
            return false;
        }
        return this.hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.x;
        hash = 67 * hash + this.y;
        hash = 67 * hash + this.z;
        hash = 67 * hash + (this.world != null ? this.world.hashCode() : 0);
        hash = 67 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    /**
     * @param x
     *            the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @param y
     *            the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @param z
     *            the z to set
     */
    public void setZ(int z) {
        this.z = z;
    }

    /**
     * @param world
     *            the world to set
     */
    public void setWorld(String world) {
        this.world = world;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
