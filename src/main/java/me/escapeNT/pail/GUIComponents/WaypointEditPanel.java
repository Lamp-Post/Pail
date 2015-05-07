package me.escapeNT.pail.GUIComponents;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle;

import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import me.escapeNT.pail.Pail;
import me.escapeNT.pail.Util.Localizable;
import me.escapeNT.pail.Util.Util;
import me.escapeNT.pail.config.WaypointConfig;
import me.escapeNT.pail.Util.Waypoint;

/**
 * Panel for editing teleport waypoints.
 *
 * @author escapeNT
 */
public class WaypointEditPanel extends javax.swing.JPanel implements Localizable {
    private static final long serialVersionUID = -2172996348655100460L;

    /**
     * Creates new form WaypointEditPanel
     */
    public WaypointEditPanel() {//TODO
        initComponents();
        WaypointConfig.load();

        for (Player p : Pail.getServer().getOnlinePlayers()) {
            players.addItem(p.getName());
        }

        for (World w : Pail.getServer().getWorlds()) {
            // TODO: Update this list...
            worlds.addItem(w.getName());
        }

        ((DefaultListModel) waypoints.getModel()).clear();
        
        for (Waypoint wp : WaypointConfig.getWaypoints()) {
            ((DefaultListModel) waypoints.getModel()).addElement(wp);
        }
        waypoints.setSelectedIndex(0);

        updateFields();

        translateComponent();
    }

    private void updateFields() {

        if (waypoints.getSelectedValue() == null) {
            x.setEnabled(false);
            y.setEnabled(false);
            z.setEnabled(false);
            name.setEnabled(false);
            worlds.setEnabled(false);
            save.setEnabled(false);
            players.setEnabled(false);
            playerSubmit.setEnabled(false);
        } else {
            x.setEnabled(true);
            y.setEnabled(true);
            z.setEnabled(true);
            name.setEnabled(true);
            worlds.setEnabled(true);
            save.setEnabled(true);
            players.setEnabled(true);
            playerSubmit.setEnabled(true);

            Waypoint p = (Waypoint) waypoints.getSelectedValue();
            Location l = p.getLocation();

            x.setValue(l.getBlockX());
            y.setValue(l.getBlockY());
            z.setValue(l.getBlockZ());
            name.setText(p.getName());
            worlds.setSelectedItem(p.getWorld());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        waypoints = new javax.swing.JList();
        addWaypoint = new javax.swing.JButton();
        removeWaypoint = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        z = new javax.swing.JSpinner();
        y = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        x = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        worlds = new javax.swing.JComboBox();
        save = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        players = new javax.swing.JComboBox();
        playerSubmit = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Waypoints"));

        waypoints.setModel(new DefaultListModel());
        waypoints.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                waypointsValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(waypoints);

        addWaypoint.setText("+");
        addWaypoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addWaypointActionPerformed(evt);
            }
        });

        removeWaypoint.setText("-");
        removeWaypoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeWaypointActionPerformed(evt);
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout
                        .createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout
                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout
                                        .createSequentialGroup()
                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 360,
                                                Short.MAX_VALUE).addGap(3, 3, 3))
                                .addGroup(jPanel1Layout
                                        .createSequentialGroup()
                                        .addGap(257, 257, 257)
                                        .addComponent(removeWaypoint, GroupLayout.PREFERRED_SIZE, 50,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(addWaypoint, GroupLayout.PREFERRED_SIZE, 50,
                                                GroupLayout.PREFERRED_SIZE))).addGap(29, 29, 29)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                jPanel1Layout
                        .createSequentialGroup()
                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout
                                .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(addWaypoint, GroupLayout.PREFERRED_SIZE, 40,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(removeWaypoint, GroupLayout.PREFERRED_SIZE, 40,
                                        GroupLayout.PREFERRED_SIZE)).addGap(2, 2, 2)));

        jLabel1.setText("Z");

        z.setModel(new javax.swing.SpinnerNumberModel());

        y.setModel(new javax.swing.SpinnerNumberModel());

        jLabel2.setText("Y");

        x.setModel(new javax.swing.SpinnerNumberModel());

        jLabel3.setText("X");

        jLabel4.setText("World");

        save.setText("Save Waypoint");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        jLabel5.setText("Name");

        jLabel6.setText("Use player location:");

        playerSubmit.setText("Go");
        playerSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerSubmitActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout
                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout
                                        .createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 60,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(name, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE))
                                .addGroup(layout
                                        .createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 30,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(x, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 20,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(y, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 20,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(z, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 60,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup().addGap(54, 54, 54)
                                        .addComponent(worlds, 0, 360, Short.MAX_VALUE))
                                .addGroup(layout
                                        .createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 380,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout
                                        .createSequentialGroup()
                                        .addGap(284, 284, 284)
                                        .addComponent(save, GroupLayout.PREFERRED_SIZE, 130,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout
                                        .createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addGroup(layout
                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 300,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addComponent(players, 0, 310, Short.MAX_VALUE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(playerSubmit, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(7, 7, 7))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup()
                        .addGroup(layout
                                .createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(GroupLayout.Alignment.LEADING,
                                        layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 410,
                                                        Short.MAX_VALUE))
                                .addGroup(GroupLayout.Alignment.LEADING,
                                        layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addGroup(layout
                                                        .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(name, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup().addGap(6, 6, 6).addComponent(jLabel5)))
                                                .addGap(32, 32, 32)
                                                .addGroup(layout
                                                        .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE,
                                                                30, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(x, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE,
                                                                30, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(y, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE,
                                                                30, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(z, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.PREFERRED_SIZE))
                                                .addGap(30, 30, 30)
                                                .addGroup(layout
                                                        .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE,
                                                                20, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(worlds, GroupLayout.PREFERRED_SIZE,
                                                                20, GroupLayout.PREFERRED_SIZE))
                                                .addGap(40, 40, 40)
                                                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(28, 28, 28)
                                                .addComponent(jLabel6)
                                                .addGap(4, 4, 4)
                                                .addGroup(layout
                                                        .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(players, GroupLayout.PREFERRED_SIZE,
                                                                18, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(playerSubmit)).addGap(92, 92, 92).addComponent(save)))
                        .addContainerGap()));
    }// </editor-fold>//GEN-END:initComponents

    private void addWaypointActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addWaypointActionPerformed
        Waypoint point = new Waypoint("waypoint", new Location(Pail.getServer().getWorld(Pail.getServer().getAllWorldProperties().iterator().next().getWorldName()).get(), 0, 0, 0), Pail.getServer()
                .getWorld(Pail.getServer().getAllWorldProperties().iterator().next().getWorldName()).get()); // TODO
                                                                                                             // verify
        WaypointConfig.getWaypoints().add(point);
        ((DefaultListModel) waypoints.getModel()).addElement(point);
        waypoints.setSelectedValue(point, true);
        updateFields();
    }// GEN-LAST:event_addWaypointActionPerformed

    private void removeWaypointActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_removeWaypointActionPerformed
        if (waypoints.getSelectedValue() != null) {
            Waypoint point = (Waypoint) waypoints.getSelectedValue();
            ((DefaultListModel) waypoints.getModel()).removeElement(point);
            WaypointConfig.getWaypoints().remove(point);
            WaypointConfig.save();
            updateFields();
        }
    }// GEN-LAST:event_removeWaypointActionPerformed

    private void playerSubmitActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_playerSubmitActionPerformed
        if (getPlayers().getSelectedItem() == null) {
            return;
        }
        Player p = Pail.getServer().getPlayer(getPlayers().getSelectedItem().toString()).get();
        if (p == null) {
            getPlayers().removeItem(getPlayers().getSelectedItem());
            return;
        }
        Location l = p.getLocation();
        name.setText(p.getName() + "'s " + Util.translate("location"));
        x.setValue(l.getBlockX());
        y.setValue(l.getBlockY());
        z.setValue(l.getBlockZ());
        worlds.setSelectedItem(p.getWorld().getName());
    }// GEN-LAST:event_playerSubmitActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_saveActionPerformed
        Waypoint p = (Waypoint) waypoints.getSelectedValue();
        p.setName(name.getText());
        p.setWorld(worlds.getSelectedItem().toString());
        p.setX((Integer) x.getValue());
        p.setY((Integer) y.getValue());
        p.setZ((Integer) z.getValue());

        WaypointConfig.save();
        updateFields();

        JOptionPane.showMessageDialog(this, Util.translate("Waypoint saved."), Util.translate("Success"),
                JOptionPane.INFORMATION_MESSAGE);
    }// GEN-LAST:event_saveActionPerformed

    private void waypointsValueChanged(javax.swing.event.ListSelectionEvent evt) {// GEN-FIRST:event_waypointsValueChanged
        updateFields();
    }// GEN-LAST:event_waypointsValueChanged
     // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JButton addWaypoint;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField name;
    private javax.swing.JButton playerSubmit;
    private javax.swing.JComboBox players;
    private javax.swing.JButton removeWaypoint;
    private javax.swing.JButton save;
    private javax.swing.JList waypoints;
    private javax.swing.JComboBox worlds;
    private javax.swing.JSpinner x;
    private javax.swing.JSpinner y;
    private javax.swing.JSpinner z;

    // End of variables declaration//GEN-END:variables

    /**
     * @return the players
     */
    public javax.swing.JComboBox getPlayers() {
        return players;
    }

    public final void translateComponent() {
        Util.translateTextComponent(jLabel1);
        Util.translateTextComponent(jLabel2);
        Util.translateTextComponent(jLabel3);
        Util.translateTextComponent(jLabel4);
        Util.translateTextComponent(jLabel5);
        Util.translateTextComponent(jLabel6);
        Util.translateTextComponent(playerSubmit);
        Util.translateTextComponent(removeWaypoint);
        Util.translateTextComponent(save);
    }
}
