package me.escapeNT.pail.GUIComponents;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import org.spongepowered.api.world.World;

import me.escapeNT.pail.Pail;
import me.escapeNT.pail.Util.Localizable;
import me.escapeNT.pail.scheduler.ServerTask.Type;

/**
 * Panel containing options for a Task.
 * 
 * @author escapeNT
 */
public class OptionsTaskPanel extends javax.swing.JPanel implements Localizable {

    private static final long serialVersionUID = 8377251626017201670L;
    private String taskType = "Server action";

    /** Creates new form ServerTaskOptions */
    public OptionsTaskPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        if (taskType == "Server action") {
        stOptions = new ServerTaskOptions();
        setBorder(BorderFactory.createTitledBorder("Task Options"));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addComponent(stOptions)));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(stOptions)).addContainerGap(212, Short.MAX_VALUE)));
        } else if (taskType == "World backup") {  
            btOptions = new BackupTaskOptions();
            
            setBorder(BorderFactory.createTitledBorder("Task Options"));

            GroupLayout layout = new GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(
                    layout.createSequentialGroup().addContainerGap().addComponent(btOptions)));
            layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(
                    layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(
                                    layout.createParallelGroup(Alignment.BASELINE)
                                            .addComponent(btOptions)).addContainerGap(212, Short.MAX_VALUE)));
                        
        } else if (taskType == "Custom command") { 
            cctOptions = new ConsoleCommandTaskOptions();
            
            setBorder(BorderFactory.createTitledBorder("Task Options"));

            GroupLayout layout = new GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(
                    layout.createSequentialGroup().addContainerGap().addComponent(cctOptions)));
            layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(
                    layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(
                                    layout.createParallelGroup(Alignment.BASELINE)
                                            .addComponent(cctOptions)).addContainerGap(212, Short.MAX_VALUE)));
            } else {
                Pail.getLogger().info("kaskdjgaioj");
            }
        }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ServerTaskOptions stOptions;
    private BackupTaskOptions btOptions;
    private ConsoleCommandTaskOptions cctOptions;

    // End of variables declaration//GEN-END:variables
    
    public void setTaskType(String taskType) {
        this.taskType = taskType;
        removeAll();
        initComponents();
        revalidate();
        repaint();
    }
    
    public void setDisabled() {
        if(taskType == "Server action") {
            stOptions.setDisabled();
        } else if(taskType == "World backup") {
            btOptions.setDisabled();
        } else if(taskType == "Custom command") {
            cctOptions.setDisabled();
        }
    }
    
    public void setEnabled() {
        if(taskType == "Server action") {
            stOptions.setEnabled();
        } else if(taskType == "World backup") {
            btOptions.setEnabled();
        } else if(taskType == "Custom command") {
            cctOptions.setEnabled();
        }
    }

    @Override
    public void translateComponent() {
    }
    
    public String getCommand() {
        return cctOptions.getCommand().getText();
    }
    public boolean getBroadcast() {
        return btOptions.getBroadcast().isSelected();
    }
    public World getWorld() {
        return (World) btOptions.getWorld().getSelectedItem();
    }
    public Type getTask() {
        return (Type) stOptions.getAction().getSelectedItem();
    }
}
