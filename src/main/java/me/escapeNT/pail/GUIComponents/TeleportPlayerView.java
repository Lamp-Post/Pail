package me.escapeNT.pail.GUIComponents;

import org.spongepowered.api.data.manipulators.entities.RespawnLocationData;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.world.Location;

import me.escapeNT.pail.Pail;
import me.escapeNT.pail.Util.Localizable;
import me.escapeNT.pail.Util.Util;
import me.escapeNT.pail.Util.Waypoint;
import me.escapeNT.pail.config.WaypointConfig;

/**
 * Player teleport interface.
 *
 * @author escapeNT
 */
public final class TeleportPlayerView extends javax.swing.JDialog implements Localizable {
    private final String player;

    /**
     * Creates new form TeleportPlayerView
     */
    public TeleportPlayerView(String player) {
        super(Util.getPlugin().getMainWindow());
        this.player = player;

        pack();
        setLocationRelativeTo(Util.getPlugin().getMainWindow());
        setModal(true);
        initComponents();

        for (Player p : Pail.getServer().getOnlinePlayers()) {
            if (p != null && !p.getName().equals(player)) {
                locations.addItem(p.getName());
            }
        }

        teleLabel.setText(Util.translate("Teleport " + player + " to:"));
        getRootPane().setDefaultButton(teleport);
        setResizable(false);
        setSize(400, 260);

        for (Waypoint p : WaypointConfig.getWaypoints()) {
            if (p != null) {
                waypoints.addItem(p);
            }
        }

        waypoints.setEnabled(false);

        translateComponent();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        teleport = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        teleLabel = new javax.swing.JLabel();
        locations = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        waypoints = new javax.swing.JComboBox();
        toWaypoint = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        teleport.setText("Teleport");
        teleport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teleportActionPerformed(evt);
            }
        });
        getContentPane().add(teleport);
        teleport.setBounds(290, 190, 96, 29);

        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        getContentPane().add(cancel);
        cancel.setBounds(200, 190, 86, 29);

        teleLabel.setText("Teleport *** to");
        getContentPane().add(teleLabel);
        teleLabel.setBounds(20, 20, 280, 16);

        locations.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Spawn", "Bed" }));
        getContentPane().add(locations);
        locations.setBounds(90, 50, 200, 27);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(40, 90, 300, 10);

        getContentPane().add(waypoints);
        waypoints.setBounds(90, 140, 200, 27);

        toWaypoint.setText("Waypoint");
        toWaypoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toWaypointActionPerformed(evt);
            }
        });
        getContentPane().add(toWaypoint);
        toWaypoint.setBounds(140, 110, 106, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelActionPerformed
        dispose();
    }// GEN-LAST:event_cancelActionPerformed

    private void teleportActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_teleportActionPerformed
        final Object _loc;

        if (toWaypoint.isSelected()) {
            Waypoint point = (Waypoint) waypoints.getSelectedItem();
            if (point == null) {
                return;
            }
            _loc = point.getLocation();
            if (point.getWorld() == null) {
                return;
            }
        } else {
            _loc = locations.getSelectedItem().toString();
        }

        Util.dispatch(new Runnable() {
            public void run() {
                Location l;
                Player _player = Pail.getServer().getPlayer(player).get();
                if (_loc instanceof Location) {
                    l = (Location) _loc;
                    _player.setLocation((Location) _loc);
                } else if (_loc.equals("Spawn")) {
                    l = _player.getWorld().getSpawnLocation();
                } else if (_loc.equals("Bed")) {
                    l = _player.getData(RespawnLocationData.class).get().getRespawnLocation();
                } else {
                    Player p = Pail.getServer().getPlayer(_loc.toString()).get();
                    if (p != null) {
                        l = p.getLocation();
                    } else {
                        l = null;
                    }
                }

                if (l != null) {
                    _player.setLocation(l);
                }
            }
        });
        dispose();
    }// GEN-LAST:event_teleportActionPerformed

    private void toWaypointActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_toWaypointActionPerformed
        if (toWaypoint.isSelected()) {
            locations.setEnabled(false);
            waypoints.setEnabled(true);
        } else {
            locations.setEnabled(true);
            waypoints.setEnabled(false);
        }
    }// GEN-LAST:event_toWaypointActionPerformed
     // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JButton cancel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox locations;
    private javax.swing.JLabel teleLabel;
    private javax.swing.JButton teleport;
    private javax.swing.JCheckBox toWaypoint;
    private javax.swing.JComboBox waypoints;

    // End of variables declaration//GEN-END:variables

    public final void translateComponent() {
        Util.translateTextComponent(cancel);
        Util.translateTextComponent(teleLabel);
        Util.translateTextComponent(teleport);
        Util.translateTextComponent(toWaypoint);
    }
}
