package me.escapeNT.pail.GUIComponents;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import me.escapeNT.pail.Pail;
import me.escapeNT.pail.Util.Localizable;
import me.escapeNT.pail.Util.Util;

import org.spongepowered.api.world.World;

/**
 * Panel containing options for a Server Task.
 * 
 * @author escapeNT
 */
public class BackupTaskOptions extends javax.swing.JPanel implements Localizable {

    private static final long serialVersionUID = 8377251626017201670L;

    /** Creates new form ServerTaskOptions */
    public BackupTaskOptions() {
        initComponents();
        translateComponent();
        for (World world : Pail.getServer().getWorlds()) {
            ((DefaultComboBoxModel) worlds.getModel()).addElement(world);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new JLabel();
        worlds = new JComboBox();
        broadcast = new JCheckBox("Broadcast");

        jLabel1.setText("World:");

        worlds.setModel(new DefaultComboBoxModel());

        GroupLayout layout = new GroupLayout(this);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(broadcast)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(worlds, 0, 388, Short.MAX_VALUE)))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(worlds, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addComponent(broadcast)
                    .addContainerGap(228, Short.MAX_VALUE))
        );
        this.setLayout(layout);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JComboBox worlds;
    private JLabel jLabel1;
    private JCheckBox broadcast;

    // End of variables declaration//GEN-END:variables

    public final void translateComponent() {
        Util.translateTextComponent(jLabel1);
    }

    /**
     * World selector.
     * 
     * @return the world
     */
    public JComboBox getWorld() {
        return worlds;
    }
    public JCheckBox getBroadcast() {
        return broadcast;
    }

    public void setDisabled() {
        worlds.setEnabled(false);
        jLabel1.setEnabled(false);
    }
    
    public void setEnabled() {
        worlds.setEnabled(true);
        jLabel1.setEnabled(true);
    }
}
