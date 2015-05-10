package me.escapeNT.pail.scheduler;

import java.io.Serializable;

/**
 * Interface containing the basic methods of a schedulable task.
 *
 * @author escapeNT
 */
public interface ScheduledTask extends Serializable {
    /**
     * Gets the time interval between executions of this task.
     *
     * @return The interval in milliseconds between task executions.
     */
    public long getInterval();
    
    /**
     * Sets the time interval between executions of this task.
     *
     */
    public void setInterval(long interval);
    
    /**
     * Gets the interval type of this task.
     *
     * @return The interval type.
     */
    public String getIntervalType();
    
    /**
     * Sets the interval type of this task.
     *
     */
    public void setIntervalType(String intervalType);

    /**
     * Returns true if the task should be repeated indefinitely.
     *
     * @return True if the task is repeating, false if not.
     */
    public boolean isRepeating();
    
    /**
     * Returns true if the task should be repeated indefinitely.
     *
     */
    public void setRepeating(boolean repeating);

    /**
     * Gets whether the task is enabled and running.
     *
     * @return True if the task is enabled.
     */
    public boolean isEnabled();

    /**
     * Sets whether the task is enabled and running.
     *
     * @param enabled
     *            True if the task will be enabled, false if disabled.
     */
    public void setEnabled(boolean enabled);

    /**
     * Gets the name of the specific task.
     *
     * @return The task name.
     */
    public String getName();

    /**
     * Sets the task name.
     *
     * @param name
     *            The name of the task.
     */
    public void setName(String name);
    
    
    /**
     * Gets the task type.
     *
     * @param type
     *            The type of the task.
     */
    public String getTastType();

    /**
     * Executes this task.
     */
    public void execute();
}
